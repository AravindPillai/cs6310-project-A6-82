package com.gatech.streamingwars.configurations;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class MainDataSourceConfigurations {

    @Bean("mainDSProperties")
    @Primary
    @ConfigurationProperties("spring.datasource.main")
    public DataSourceProperties mainDataSourceProperties()
    {
        return new DataSourceProperties();
    }

    @Bean("mainDS")
    @Primary
    @ConfigurationProperties("spring.datasource.main.configuration")
    public DataSource mainDataSource(@Qualifier("mainDSProperties") DataSourceProperties mainDSProperties) {
        return mainDSProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory( EntityManagerFactoryBuilder builder, @Qualifier("mainDS") DataSource mainDS) {
        return builder.dataSource(mainDS)
                .packages("com.gatech.streamingwars.configurations.model.main")
                .persistenceUnit("main")
                .build();
    }

    @Primary
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mainEntityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }
}
