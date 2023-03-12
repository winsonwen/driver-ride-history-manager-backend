package driver.ride.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Component

public class HibernateConfig {

    @Resource
    private DataSource dataSource;


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("driver.ride.manager.domain.entity");
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        bean.setHibernateProperties(properties);
        return bean;
    }

}
