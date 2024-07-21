package com.rainier.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.security.Principal;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String SWAGGER_PATH = "swagger";
    private static final String LOGINAPI_PATH = "userService/login";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        if (path.startsWith(SWAGGER_PATH) || path.startsWith(LOGINAPI_PATH)) {
            return; // Skip JWT validation for Swagger and login endpoints
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.substring(BEARER_PREFIX.length()).trim();

            try {
                Claims claims = validateToken(token);
                requestContext.setSecurityContext(new JwtSecurityContext(claims));
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey("6bb69780ac1e1051e3143417daf4d5d30fcb1cc3a95846c366677cca94a47cdf") // Replace with your secret key
                .parseClaimsJws(token)
                .getBody();
    }

    private static class JwtSecurityContext implements SecurityContext {
        private final Claims claims;

        public JwtSecurityContext(Claims claims) {
            this.claims = claims;
        }

        @Override
        public Principal getUserPrincipal() {
            return new Principal() {
                @Override
                public String getName() {
                    return claims.getSubject();
                }
            };
        }

        @Override
        public boolean isUserInRole(String role) {
            // Implement role validation based on claims if needed
            return false;
        }

        @Override
        public boolean isSecure() {
            return false; // Adjust according to your application requirements
        }

        @Override
        public String getAuthenticationScheme() {
            return "Bearer";
        }
    }
}
