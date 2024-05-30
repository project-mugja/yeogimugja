package com.mugja.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
public class MybatisConfig {
	@Configuration
	@MapperScan("com.mugja.*") // 매퍼 인터페이스 패키지 경로
	public class MyBatisConfig {

	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/mugja?serverTimezone=Asia/Seoule");
	        dataSource.setUsername("root");
	        dataSource.setPassword("1234");
	        return dataSource;
	    }

	    @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource);
	        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));
	        return sessionFactory.getObject();
	    }

	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
	}
}
