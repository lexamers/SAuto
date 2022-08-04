package Diploma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration

@PropertySource("classpath:database.properties")
@EnableTransactionManagement
        public class AppContext {

        @Autowired
        private Environment environment;

        @Bean
        public LocalSessionFactoryBean sessionFactory() {// локальный фабричный компонент
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());// источник данных
            sessionFactory.setPackagesToScan("diploma.persistence");// пакеты для скачивания
            sessionFactory.setHibernateProperties(hibernateProperties());// свойства режима гибернации
            return sessionFactory;
        }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();// источник данных драйвера
            dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));//имя класса двайвера
            dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
            dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));// имя пользователя
            dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));// пароль
            return dataSource;
        }

        private Properties hibernateProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
            properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
            properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
            properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
            return properties;
        }

        @Bean
        public HibernateTransactionManager getTransactionManager() {// менеджер транзакций
            HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(sessionFactory().getObject());
            return transactionManager;
        }
    }
}
