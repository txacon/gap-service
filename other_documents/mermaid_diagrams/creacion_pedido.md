```mermaid
sequenceDiagram
    Client->>+Front: createNewOrder
    Front->>+Backend: saveNewOrder
    Backend-->>+Notification: notifyNewOrder
    Backend-->>-Front: orderSaved
    Front-->>-Client: savedResponse
    Notification-->>Client: sendNewOrderNotifyClient
    Notification-->>-Business: sendNewOrderNotifyBusiness
```