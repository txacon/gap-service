```mermaid
sequenceDiagram
    Client->>+Front: singUp
    Front->>+Backend: createCustomer
    Backend-->>+Notification: notifyNewCustomer
    Backend-->>-Front: createCustomerResponse
    Front-->>-Client: redirecLoginPage
    Notification-->>Client: sendWelcomeMessage
```