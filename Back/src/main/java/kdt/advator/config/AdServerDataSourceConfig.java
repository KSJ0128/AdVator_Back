//package kdt.advator.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.*;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class AdServerDataSourceConfig {
//
//    @Bean(name = "adServerDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.ad-server")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "adServerEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("adServerDataSource") DataSource secondaryDataSource) {
//        return builder
//                .dataSource(secondaryDataSource)
//                .packages("kdt.advator.ad_server.ad_company.domain", "kdt.advator.common.domain") // Secondary DB의 Entity 패키지
//                .persistenceUnit("secondary")
//                .build();
//    }
//    @Bean(name = "adServerTransactionManager")
//    public PlatformTransactionManager secondaryTransactionManager(
//            @Qualifier("adServerEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
//        return new JpaTransactionManager(secondaryEntityManagerFactory);
//    }
//}