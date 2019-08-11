package pri.xjb.ticket.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"pri.xjb.ticket.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisDbAConfig {

    @Autowired
    @Qualifier("ds1")
    private DataSource ds1;

    @Value("${mybatis.mapper-locations}")
    private String MAPPER_LOCATION = "";


    @Bean
    @Qualifier("sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds1);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return factoryBean.getObject();

    }

    @Bean
    @Qualifier("sqlSessionFactory1")
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return  new SqlSessionTemplate(sqlSessionFactory1());
    }
    
    @Bean
    public PlatformTransactionManager firstDataSourceTransaction(@Qualifier("ds1")DataSource firstDataSource){
    	return new DataSourceTransactionManager(firstDataSource);
    }
}
