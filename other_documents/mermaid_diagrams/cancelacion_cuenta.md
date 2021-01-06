```mermaid
sequenceDiagram
    Client->>+Front: deactivateAccount
    Front->>+Backend: updateCustomer
    Backend-->>+Notification: notifyDeactivateCustomer
    Backend-->>-Front: updateCustomerResponse
    Front-->>-Client: redirecLoginPage
    Notification-->>Client: sendDeactivateMessage
```