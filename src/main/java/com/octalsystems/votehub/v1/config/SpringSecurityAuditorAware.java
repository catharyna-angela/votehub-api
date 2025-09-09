package com.octalsystems.votehub.v1.config;

import com.octalsystems.votehub.v1.entity.Client;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
class SpringSecurityAuditorAware implements AuditorAware<Client> {

    @Override
    public Optional<Client> getCurrentAuditor() {

//        return Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(Client.class::cast);

        return null;
    }
}
