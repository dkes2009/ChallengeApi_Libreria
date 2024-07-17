package com.ApiLibreria.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerPostgres", transactionManagerRef = "transactionManagerPostgres", basePackages = {"com.ApiLibreria.Repository"})
public class ConfigHibernatePostgreSQL {

	@Primary
	@Bean(name = {"dataSourcePostgres"})
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSourceOracle() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = {"entityManagerPostgres"})
	public LocalContainerEntityManagerFactoryBean entitityManagerOra(EntityManagerFactoryBuilder builder, @Qualifier("dataSourcePostgres") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.ApiLibreria.Entity").persistenceUnit("db1").build();
	}

	@Primary
	@Bean(name = {"transactionManagerPostgres"})
	public PlatformTransactionManager transaccionManager(@Qualifier("entityManagerPostgres") EntityManagerFactory manager) {
		return new JpaTransactionManager(manager);
	}
}