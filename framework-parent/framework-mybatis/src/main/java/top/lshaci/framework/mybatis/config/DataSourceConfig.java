package top.lshaci.framework.mybatis.config;

import static top.lshaci.framework.mybatis.datasource.DynamicDataSourceType.FIRST;
import static top.lshaci.framework.mybatis.datasource.DynamicDataSourceType.SECOND;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;
import top.lshaci.framework.mybatis.datasource.DynamicDataSource;

/**
 * Config datasource with druid
 * 
 * @author lshaci
 * @since 0.0.1
 */
@Configuration
// Load datasource config
@PropertySources({
	@PropertySource("classpath:datasource_single.properties"),
	@PropertySource("classpath:datasource_first.properties"),
	@PropertySource("classpath:datasource_second.properties")
})
@Slf4j
public class DataSourceConfig {
	


	/**
	 * Define single data source
	 * 
	 * @return single data source
	 */
	@ConditionalOnProperty(value = "datasource.single", matchIfMissing = false)
	@Bean(name = "dataSource")
	@ConfigurationProperties("datasource")
	public DataSource singleDataSource() {
		log.info("Init Single Druid DataSource...");

		return new DruidDataSource();
	}
	
	/**
	 * Define dynamic data source(first)
	 * 
	 * @return dynamic data source first
	 */
	@ConditionalOnProperty(value = {"datasource.dynamic", "datasource.first.url"}, matchIfMissing = false)
	@Bean(name = "firstDataSource")
	@ConfigurationProperties("datasource.first")
	public DataSource firstDataSource() {
		log.info("Init First Druid DataSource...");
		
		return new DruidDataSource();
	}
	
	/**
	 * Define dynamic data source(second)
	 * 
	 * @return dynamic data source second
	 */
	@ConditionalOnProperty(value = {"datasource.dynamic", "datasource.second.url"}, matchIfMissing = false)
	@Bean(name = "secondDataSource")
	@ConfigurationProperties("datasource.second")
	public DataSource secondDataSource() {
		log.info("Init Second Druid DataSource...");
		
		return new DruidDataSource();
	}
	
	/**
	 * Define dynamic primary data source
	 * 
	 * @return dynamic primary data source
	 */
	@ConditionalOnProperty(value = "datasource.dynamic", matchIfMissing = false)
	@Bean(name = "dataSource")
	@Primary
    public DynamicDataSource dynamicDataSource() {
		log.info("Init Dynamic Druid DataSource...");
		
		DynamicDataSource dataSource = new DynamicDataSource();
		
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(FIRST, firstDataSource());
        targetDataSources.put(SECOND, secondDataSource());
        
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(firstDataSource());
        
        return dataSource;
    }
	
	/**
	 * Define transaction manager
	 * 
	 * @return transaction manager
	 */
	@ConditionalOnProperty(value = "datasource.dynamic", matchIfMissing = false)
    @Bean
    public DataSourceTransactionManager transactionManager() {
		log.info("Init Data Source Transaction Manager...");
		
        return new DataSourceTransactionManager(dynamicDataSource());
    }
	
}
