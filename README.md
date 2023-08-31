## SSO单点登录框架



Based on the analysis of the repository, here's a breakdown of the architecture, its advantages, and a quick deployment strategy:

### Architecture Overview:

```
+------------------------+
|        Client          |
|                        |
| +-------------------+  |
| |   Chat Platform   |  |
| +-------------------+  |
|                        |
| +-------------------+  |
| | Unified Auth      |  |
| +-------------------+  |
+------------------------+
            |
            |
            v
+------------------------+
|        SSO Core        |
|                        |
| +-------------------+  |
| |   Login Helper    |  |
| +-------------------+  |
|                        |
| +-------------------+  |
| |   SSO Filter      |  |
| +-------------------+  |
|                        |
| +-------------------+  |
| | Session & Cookie  |  |
| |     Helper        |  |
| +-------------------+  |
+------------------------+
            |
            |
            v
+------------------------+
|        Database        |
|                        |
| +-------------------+  |
| |      Redis        |  |
| +-------------------+  |
+------------------------+
```



1. **chat-platform**:
   - **Purpose**: Appears to be a chat platform service.
   - **Main Components**:
     - `ChatPlatformApplication.java`: The main Spring Boot application.
     - `WebFilterConfig.java`: Configuration for web filters.
     - `TestController.java`: A controller for testing purposes.

2. **sso-core**:
   - **Purpose**: Core Single Sign-On (SSO) functionalities.
   - **Main Components**:
     - `Conf.java`: Configuration settings for SSO.
     - `SsoUser.java`: Entity representing an SSO user.
     - `SsoFilter.java`: Filter for SSO operations.
     - `CookieStoreBrowserHelper.java`, `SessionAndCookieHelper.java`, `SessionStoreRedisHelper.java`: Helpers for managing sessions and cookies.
     - `LoginHelper.java`: Assists with login operations.

3. **unified-authentication**:
   - **Purpose**: Unified authentication service.
   - **Main Components**:
     - `UnifiedAuthenticationApplication.java`: The main Spring Boot application.
     - `RedisConfig.java`: Configuration for Redis.
     - `LoginController.java`: Controller for login operations.
     - `Student.java`: Entity representing a student.

### Advantages:

1. **Modular Design**: The repository is divided into distinct modules, making it easier to manage, update, and scale.
2. **Single Sign-On (SSO)**: Provides a centralized session and user authentication service, allowing users to authenticate once and gain access to multiple applications.
3. **Redis Integration**: Utilizes Redis for session management, offering fast and scalable session storage.
4. **Spring Boot Framework**: Leveraging Spring Boot makes the application robust, scalable, and easier to deploy.

### Quick Deployment Strategy:

1. **Prerequisites**:
   - Ensure Java and Maven are installed.
   - Set up a Redis instance for session management.

2. **Steps**:
   - Clone the repository.
   - Navigate to each module (`chat-platform`, `sso-core`, `unified-authentication`) and run `mvn clean install` to build the projects.
   - Start the Redis instance.
   - Navigate to the main application classes (`ChatPlatformApplication.java`, `UnifiedAuthenticationApplication.java`) and run them to start the services.
   - Ensure all services are running and accessible.

### Areas for Improvement:

1. **Documentation**: The repository lacks comprehensive documentation. It would be beneficial to provide detailed setup, usage, and API documentation.
2. **Testing**: Consider adding unit and integration tests to ensure the reliability of the code.
3. **Security**: Ensure that the SSO implementation is secure. Consider adding features like 2FA (Two-Factor Authentication) and OAuth2 integration.

You can use this information to update the `README.md` of the repository. Would you like any additional details or assistance with anything else?

##### 





![image](https://user-images.githubusercontent.com/87351268/163117354-0410ddcd-0104-49b0-853b-9c443edf34c9.png)



