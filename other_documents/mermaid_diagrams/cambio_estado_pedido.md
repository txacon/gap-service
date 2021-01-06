```mermaid
sequenceDiagram
    Business->>+Front: changeStatusOrder(Status)
    Front->>+Backend: updateOrder
    Backend->>+Notification: notifyNewOrderStatus
    Backend-->>-Front: orderUpdated
    Front-->>-Business: changeStatusResponse
    Notification-->>Client: sendNewOrderNotifyClient
    Notification-->>-Business: sendNewOrderNotifyBusiness
```