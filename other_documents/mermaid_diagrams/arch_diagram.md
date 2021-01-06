graph TD;
    A[Front]-->|HTTP Port: 8081| B[NGINX - Gateway];
    B-->|Volume mount| C[UI - Static Directory];
    B-->|Http proxy Port: 8000| D[backend];
    B-->|Http proxy Port: 8080| E[gap-service];
    D-->|TCP Port: 5432| F[Postgres - DB];
    E-->|TCP Port: 5432| F[Postgres - DB];