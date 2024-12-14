package kdt.advator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "kdt.advator.ad_server",
        entityManagerFactoryRef = "adServerEntityManagerFactory",
        transactionManagerRef = "adServerTransactionManager"
)
class AdServerJpaConfig {
}