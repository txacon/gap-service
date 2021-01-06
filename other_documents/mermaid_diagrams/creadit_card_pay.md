```mermaid
sequenceDiagram
    Client->>+Front: payOrder
    Front->>+Backend: purchase
    Backend->>+PayPlatform: requestPaymentUriWithCallBack
    PayPlatform-->>-Backend: responseUri
    Backend-->>-Front: responseRedirectPurchaseUri
    Front-->>-Client: redirectToPayPlatform
    Client->>+PayPlatform: applyPay
    PayPlatform-->>-Backend: redirectToOkCallBack
    Backend->>Front: payOk
    Front-->>Client: redirectCorrectPay
```