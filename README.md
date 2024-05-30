*** Scenario: Setting up custom filter and custom user details service

* Spring Boot Security workflow with a custom JWT token filter:

1. When a client makes a request to the application, the request first reaches the Spring Security filter chain.
2. The custom JWT token filter intercepts the request and checks for a JWT token in the request header.
3. If a valid JWT token is found, the filter extracts the username from the token
   and creates a UsernamePasswordAuthenticationToken object.
4. The authentication object is then set in the Spring Security context, marking the user as authenticated.
5. The filter then allows the request to continue through the filter chain.
6. Subsequent requests will be checked by the Spring Security filter chain,
   and if the user is already authenticated (as indicated by the presence of the authentication object in the context),
   the filter will allow the request to proceed without further authentication.
7. If a request is made to an endpoint that requires authentication and the user is not authenticated,
   the filter will respond with an HTTP 401 Unauthorized status.
8. If a request is made with an invalid or missing JWT token,
   the filter will also respond with an HTTP 401 Unauthorized status.


By using a custom JWT token filter, 
you can add a layer of security to your Spring Boot application by validating and authenticating incoming requests using JWT tokens. This allows you to control access to your application's endpoints based on the presence and validity of the JWT token.