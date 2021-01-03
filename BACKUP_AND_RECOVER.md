# COPIA DE SEGURIDAD Y RESTAURACIÓN DE LA BASE DE DATOS

## INTRODUCCIÓN

Los datos contenidos en las bases de datos relacionales son de los más importante en una aplicación. Hacer copias de seguridad o volcados de forma regular es indispensable para poder restaurar los datos en caso de desastre en el servidor. 

## TIPOS DE BACKUP

En PostgreSQL existen diferentes tipos de Copias de Seguridad -Backups-: 

Completos y Parciales (Incrementales y diferenciales).

### BACKUP COMPLETO
  
Este tipo de copia de seguridad es un volcado completo de nuestra base de datos en un instante "t", y como es natural, contiene la información hasta el instante que comenzamos el proceso de backup.

Para realizar este backup utilizaremos el comando [pg_dump](https://www.postgresql.org/docs/12/app-pgdump.html) y, es posible realizar esta operación en caliente, es decir, podemos tener nuestra base de datos abierta.

Ventajas Backup Completo:

- Sencillo de realizar.
- Se puede realizar en caliente.
- Backup "consistente"
- En bases de datos pequeñas en muy rápido

Problemas Backup Completo:

- Se restaura "todo" o "nada"
- Consumo de I/O elevado
- En bases de datos grandes puede no ser efectivo

### BACKUP INCREMENTAL

una operación de respaldo incremental sólo copia los datos que han variado desde la última operación de backup de cualquier tipo. 

Se suele utilizar la hora y fecha de modificación estampada en los archivos, comparándola con la hora y fecha de la última copia de seguridad. Las aplicaciones de respaldo identifican y registran la fecha y hora de realización de las operaciones de respaldo para identificar los archivos modificados desde esas operaciones.

Como un backup incremental sólo copia los datos a partir del último respaldo de cualquier tipo, se puede ejecutar tantas veces como se desee, pues sólo guarda los cambios más recientes. La ventaja de un backup incremental es que copia una menor cantidad de datos que un respaldo completo. Por ello, esas operaciones se realizan más deprisa y exigen menos espacio para almacenar la copia de seguridad.

### BACKUP DIFERENCIAL

Una operación de backup diferencial es similar a un respaldo incremental la primera vez que se lleva a cabo, pues copiará todos los datos que hayan cambiado desde la copia de seguridad anterior. Sin embargo, cada vez que se vuelva a ejecutar, seguirá copiando todos los datos que hayan cambiado desde el respaldo completo anterior. Por lo tanto, en las operaciones subsiguientes almacenará más datos que un backup incremental, aunque normalmente muchos menos que un backup completo. Además, la ejecución de los respaldos diferenciales requiere más espacio y tiempo que la de los incrementales, pero menos que la de los completos.

### POSGRES Y LOS BACKUP PARCIALES.

El mejor sistema que nos plantea postgres para hacer un backup incremental es WAL (Write ahead log) en el directorio pg_wal.

El log registra cada cambio realizado en los archivos de la base de datos. Este registro existe principalmente para fines de seguridad contra choques: 

- Si el sistema falla, la base de datos se puede restaurar a la coherencia "reproduciendo" las entradas del registro realizadas desde el último punto de control.

**Sin embargo, la existencia del registro permite utilizar una tercera estrategia para realizar copias de seguridad de bases de datos: podemos combinar una copia de seguridad a nivel de sistema de archivos con una copia de seguridad de los archivos WAL**. Si es necesaria la recuperación, restauramos la copia de seguridad del sistema de archivos y luego reproducimos desde los archivos WAL respaldados para llevar el sistema a un estado actual. Este enfoque es más complejo de administrar que cualquiera de los enfoques anteriores, pero tiene algunos beneficios importantes:

**No necesitamos una copia de seguridad del sistema de archivos perfectamente coherente como punto de partida. Cualquier inconsistencia interna en la copia de seguridad se corregirá mediante la reproducción del registro (esto no es significativamente diferente de lo que sucede durante la recuperación de fallas). Por lo tanto, no necesitamos una capacidad de instantánea del sistema de archivos, solo tar o una herramienta de archivo similar.**

- Dado que podemos combinar una secuencia indefinidamente larga de archivos WAL para su reproducción, se puede lograr una copia de seguridad continua simplemente al continuar archivando los archivos WAL. Esto es particularmente valioso para bases de datos grandes, donde podría no ser conveniente realizar una copia de seguridad completa con frecuencia.

- No es necesario reproducir las entradas de WAL hasta el final. Podríamos detener la reproducción en cualquier momento y tener una instantánea consistente de la base de datos tal como estaba en ese momento. Por lo tanto, esta técnica admite la recuperación en un momento determinado: es posible restaurar la base de datos a su estado en cualquier momento desde que se realizó la copia de seguridad base.

- Si alimentamos continuamente la serie de archivos WAL a otra máquina que se ha cargado con el mismo archivo de copia de seguridad base, tenemos un sistema de espera activo: en cualquier momento podemos abrir la segunda máquina y tendrá una copia casi actual de la base de datos.


## ESTRATEGÍA DE BACKUP

Para implementar la estrategía de backup seleccionaremos los backups incrementales creados con los logs de WAL añadido a un backup completo de los ficheros de la db. El trabajo extra de configuración tienen como ventaja, la restauranción en cualquier punto además de facilitar una futura replicación de la db en un futuro si fuera necesarios, creando una arquitectura maestro-esclavo o fail-over.

### CONFIGURACIÓN DE LA DB

Configuración del sistema de backup de la db.

El fichero de configuración donde debemos establecer todas las configuraciones es $PGDATA\postgresql.conf

Generalmente en las distribuciones debian el directorio pgdata es /var/lib/postgresql/data

Antes de nada debemos proceder la configuración de postgres para que nos permita realizar backup desde wall.

Para ello seleccionaremos el nivel de WAL adecuado

```conf
# El nivel de WAL debe de ser archive o mayor.
wal_level = archive
```

Configuración de el tiempo de espera de archivo:

Si PostgreSQL tiene una tasa de transacción baja, puede llevar un tiempo completar un archivo WAL completo. 

Sin embargo, desde la perspectiva de las operaciones, generalmente es una buena idea tener al menos un archivo WAL respaldado cada X minutos (u horas o días, según su configuración).

```conf
# Aseguramos al menos 1 copia por hora
archive_timeout = 1h
```

Límite de los fichero WAL, para mayor rapidez de backup y carga. Los fijaremos en 1 GB y 10 segmentos.

```conf
# Es el limite de los fichero WAL
max_wal_size = 1GB

# Número de ficheros que guardaremos
wal_keep_segments = 10
```

Después de un tiempo podremos ver los ficheros de wal en la maquina (En nuestro caso no hay gran volumen de datos por lo que por ahora solo tenemos 1):

```bash
root@d2a3e3e83d71:/var/lib/postgresql/data/pg_wal# ls -l
total 16384
-rw------- 1 postgres postgres 16777216 Jan  3 09:28 000000010000000000000001
drwx------ 1 postgres postgres        0 Dec 28 22:25 archive_status
```

Nuestro backup completo pasara por ir guardando estos ficheros en el servidor de backup para realizar a posteri una restauración ante un desastre.

Para ello postgres también tiene un sistema de copia:

Lo podemos configurar de la siguiente manera:

```conf
archive_mode = on
archive_command = 'cp %p /backup_file_system/archive/%f'
archive_timeout = 60
```

### RECUPERACIÓN

En PostgreSQL, PITR es una forma de detener la reproducción de archivos WAL en un momento adecuado.

Puede haber muchos archivos WAL en el backup, pero es posible que no queramos reproducirlos todos. Reproducir todos los WAL dará como resultado el mismo estado en el que cometimos algún error. Hay dos requisitos previos importantes necesarios para que PITR funcione.

- Disponibilidad de una copia de seguridad base completa (generalmente se toma con pg_basebackup) Archivos WAL (archivo WAL)

Para lograr el PITR, el primer paso sería restaurar una copia de seguridad base tomada anteriormente y luego crear una configuración de recuperación. La instalación requiere configurar las opciones restore_command y recovery_target.

- restore_command: especifica desde dónde buscar los archivos WAL para reproducir en este servidor. Este comando acepta los mismos marcadores de posición que archive_command.
- recovery_target_time: Esta opción le dice al servidor cuándo detener el proceso de recuperación o reproducción. El proceso se detendrá tan pronto como se alcance la marca de tiempo indicada.

recovery_target_inclusive: Esta opción controla si detener la reproducción de los WAL justo después de que se alcance el recovery_target_time (si se establece en verdadero) o justo antes (si se establece en falso).

Ejemplo:

```bash
# Ejemplo
restore_command = ‘cp  /backup_file_system/%f %p’
recovery_target_time = ‘2021-01-03 18:01:18.157764+05’
recovery_target_inclusive = false
```

Los pasos completos serían:

```bash
# Parada del servicio
/pg_ctl -D $PGDATA stop

# Establecemos el recovery mode
touch $PGDATA/recovery.signal

# Editamos el fichero de configuración:
vim $PGDATA/postgresql.conf

# Establecemos los valores de recuperación en nuestro caso los backup se encuentran
# en backup_file_system
restore_command = ‘cp  /backup_file_system/%f %p’
recovery_target_time = ‘2021-01-03 18:01:18.157764+05’
recovery_target_inclusive = false

# Iniciamos el proceso de recuperación

./pg_ctl -D $BACKUP start

# Deberíamos ver el siguiente log.

2021-01-03 18:03:17.365 PKT [71219] LOG:  listening on IPv4 address "127.0.0.1", port 5432
2021-01-03 18:03:17.366 PKT [71219] LOG:  listening on Unix socket "/tmp/.s.PGSQL.5432"
2021-01-03 18:03:17.371 PKT [71220] LOG:  database system was interrupted; last known up at 2021-01-03 18:00:45 PKT
2021-01-03 18:03:17.413 PKT [71220] LOG:  starting point-in-time recovery to 2021-01-03 18:01:18.157764+05

2021-01-03 18:03:17.441 PKT [71220] LOG:  restored log file "000000010000000000000001" from archive

```

Una vez finalizado podemos conectarnos a la db con normalidad.