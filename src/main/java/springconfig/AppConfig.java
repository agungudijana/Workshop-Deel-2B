package springconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import dao.GenericDao;
import pojo.*;

@Configuration
@ComponentScan({"POJO", "Service","main" })
@EnableTransactionManagement
public class AppConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/workshopdeel2");
	    dataSource.setUsername("root");
	    dataSource.setPassword("mysql");
	 
	    return dataSource;
	}
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] { "POJO", "Dao", "Service" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
	  
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       
       return txManager;
    }
	
	private Properties hibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    return properties;
	}
	
	
	   @Bean
    public GenericDao<Klant, Long> klantDao() {
        return new GenericDao<Klant, Long>(Klant.class);
    }

	   @Bean
    public GenericDao<Account, Long> accountDao() {
        return new GenericDao<Account, Long>(Account.class);
    }

	   @Bean
    public GenericDao<Adres, Long> adresDao() {
        return new GenericDao<Adres, Long>(Adres.class);
    }
	
	   @Bean
    public GenericDao<Artikel, Long> artikelDao() {
        return new GenericDao<Artikel, Long>(Artikel.class);
    }
    
	   @Bean
    public GenericDao<BestelArtikel, Long> bestelArtikelDao() {
        return new GenericDao<BestelArtikel, Long>(BestelArtikel.class);
    }
	
	   @Bean
    public GenericDao<Bestelling, Long> bestellingDao() {
        return new GenericDao<Bestelling, Long>(Bestelling.class);
    }
	
	   @Bean
    public GenericDao<Betaling, Long> betalingDao() {
        return new GenericDao<Betaling, Long>(Betaling.class);
    }
	
	   @Bean
    public GenericDao<Factuur, Long> factuurDao() {
        return new GenericDao<Factuur, Long>(Factuur.class);
    }
	
	   @Bean
    public GenericDao<KlantAdres, Long> klantAdresDao() {
        return new GenericDao<KlantAdres, Long>(KlantAdres.class);
    }
	
	
	
}
