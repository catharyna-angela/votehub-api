package com.octal.votehub.api.v1.config;

import com.octal.votehub.api.v1.entity.Client;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
