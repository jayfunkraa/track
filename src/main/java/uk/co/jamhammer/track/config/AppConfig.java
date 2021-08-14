package uk.co.jamhammer.track.config;

import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "uk.co.jamhammer.track.data")
@PropertySource("application.properties")
public class AppConfig {
  @Autowired
  private Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

    factory.setDataSource(dataSource());
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan(env.getProperty("jamhammer.entity.package"));
    factory.setJpaProperties(getHibernateProperties());

    return factory;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(env.getProperty("jamhammer.db.driver"));
    ds.setUrl(env.getProperty("jamhammer.db.url"));
    ds.setUsername(env.getProperty("jamhammer.db.username"));
    ds.setPassword(env.getProperty("jamhammer.db.password"));
    return ds;
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.implicit_naming_strategy", env.getProperty("hibernate.implicit_naming_strategy"));
    properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
    properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    return properties;
  }
}
