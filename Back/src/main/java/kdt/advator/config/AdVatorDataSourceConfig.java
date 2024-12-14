package kdt.advator.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AdVatorDataSourceConfig {

    @Primary
    @Bean(name = "advatorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.advator")
    public DataSource advatorDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "entityManagerFactory") // 이름을 그대로 유지
    public LocalContainerEntityManagerFactoryBean advatorEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("advatorDataSource") DataSource advatorDataSource) {
        return builder
                .dataSource(advatorDataSource)
                .packages("kdt.advator.advator.advertise.domain", "kdt.advator.advator.estimate.domain", "kdt.advator.common.domain") // Primary DB의 Entity 패키지
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean(name = "advatorTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory advatorEntityManagerFactory) {
        return new JpaTransactionManager(advatorEntityManagerFactory);
    }
}