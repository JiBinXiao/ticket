package pri.xjb.ticket.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
	@Bean(name = "ds1")
    @ConfigurationProperties(prefix = "base.datasource") // application-db.properteis中对应属性的前缀
	@Primary
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }


}
