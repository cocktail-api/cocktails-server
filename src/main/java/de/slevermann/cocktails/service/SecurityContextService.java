package de.slevermann.cocktails.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }
}
