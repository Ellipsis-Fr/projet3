package fr.isika.projet3.config;

import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.isika.projet3.filters.DashboardAssociationFilter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:persistance-jdbc.properties")
@ComponentScan(basePackages = "fr.isika.projet3")
public class AppConfig implements WebMvcConfigurer {

	@Autowired
 	private Environment environment;
 	
 	@Override
 	public void configureViewResolvers(ViewResolverRegistry registry) {
 		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 		viewResolver.setViewClass(JstlView.class);
 		viewResolver.setSuffix(".jsp");
 		registry.viewResolver(viewResolver);
 	}

 	@Override
 	public void addResourceHandlers(ResourceHandlerRegistry registry) {
 		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
 		registry.addResourceHandler("/ServerContent/**").addResourceLocations("/ServerContent/");
 	}
 	
 	@Bean
  	public DataSource getDataSource() {
  		DriverManagerDataSource dataSource = new DriverManagerDataSource();
  		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
  		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
  		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
  		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
  		return dataSource;
 	}
 	
 	@Bean
 	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
  		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
  		em.setDataSource(getDataSource());
  		em.setPackagesToScan("fr.isika.projet3.entities");
  		
  		final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
  		em.setJpaVendorAdapter(vendorAdapter);
  		em.setJpaProperties(hibernateProperties());
  		
  		return em;
 	}
 	
 	@Bean
 	public PlatformTransactionManager transactionManager() {
  		final JpaTransactionManager transactionManager = new JpaTransactionManager();
  		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
  		return transactionManager;
 	}
 	
 	@Bean
 	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
  		return new PersistenceExceptionTranslationPostProcessor();
 	}
 	
 	private final Properties hibernateProperties() {
  		final Properties hibernateProperties = new Properties();
  		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
  		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
  		hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
  		return hibernateProperties;
 	}
 	
 	@Bean
 	public Filter DashboardAssociationFilter() {
 	    DashboardAssociationFilter compressFilter = new DashboardAssociationFilter();
 	    return compressFilter;
 	}
 	
 	@Bean(name = "multipartResolver")
 	public CommonsMultipartResolver multipartResolver() {
 	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
 	    multipartResolver.setMaxUploadSize(18000000); // 18 Mo
 	    return multipartResolver;
 	}
 	
// 	@Bean
// 	public StandardServletMultipartResolver multipartResolver() {
// 	    return new StandardServletMultipartResolver();
// 	}
	
}
