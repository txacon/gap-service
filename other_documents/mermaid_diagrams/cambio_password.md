```mermaid
sequenceDiagram
    Client->>+Front: passwordRecovery
    Front->>+Backend: recoveryLaunch
    Backend-->>+Notification: notifyPasswordRecovery
    Backend-->>-Front: passwordRecoveryResponse
    Front-->>-Client: redirectToLong
    Notification-->>Client: sendPasswordRecoveryMessage
```