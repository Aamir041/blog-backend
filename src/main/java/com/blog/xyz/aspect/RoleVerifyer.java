package com.blog.xyz.aspect;

import com.blog.xyz.annotation.RequiredRole;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Aspect
@Component
public class RoleVerifyer {

    @Before("@annotation(requiredRole)")
    public void checkRoles(JoinPoint joinPoint, RequiredRole requiredRole){
        String[] role = {requiredRole.value()};
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("User is not authenticated");
        }

        boolean hasRequiredRole = authentication.getAuthorities()
                .stream().anyMatch(
                        grantedAuthority -> List.of(role).contains(grantedAuthority.getAuthority())
                );

        if (!hasRequiredRole){
            throw new SecurityException("User does not have the required roles to access this resource");
        }
    }

}
