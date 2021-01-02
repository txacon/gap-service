graph TD;
    A[Front]-->B[NGINX - Gateway];
    B-->|Volume mount| C[UI - Static Directory];
    B-->|TCP Port: 8000| D[backend];
    B-->|TCP Port: 8080| E[gap-service];
    D-->|TCP Port: 5432| F[postgres];
    E-->|TCP Port: 5432| F[postgres]