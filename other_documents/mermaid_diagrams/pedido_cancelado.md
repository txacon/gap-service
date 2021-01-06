```mermaid
sequenceDiagram
    Client->>+Front: cancelOrder
    Front->>+Backend: cancelOrder
    Backend-->>+Notification: notifyCancelOrder
    Backend-->>-Front: orderCanceled
    Front-->>-Client: calcelOrderResponse
    Notification-->>Client: sendCanceledOrderNotify
    Notification-->>-Business: sendCanceledOrderNotify
```