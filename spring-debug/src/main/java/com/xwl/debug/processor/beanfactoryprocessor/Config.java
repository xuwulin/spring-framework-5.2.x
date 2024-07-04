package com.xwl.debug.processor.beanfactoryprocessor;

import com.alibaba.druid.pool.DruidDataSource;
import com.xwl.debug.processor.beanprocessor.Bean1;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置类充当的角色就是工厂的角色，@Bean标注的方法就相当于时工厂方法
 *
 * @author xwl
 * @since 2022/4/9 19:54
 */
@Configuration
@ComponentScan("com.xwl.debug.processor.beanfactoryprocessor.component")
public class Config {
	@Bean
	public Bean1 bean1() {
		return new Bean1();
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) { // DataSource 是自动装配进来的
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean;
	}

	@Bean(initMethod = "init")
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	/**
	 * 使用@Bean的方式生成Mapper1对象
	 * 缺点：不能批量添加mapper对象
	 *
	 * @param sqlSessionFactory
	 * @return
	 */
	/*@Bean
	public MapperFactoryBean<Mapper1> mapper1(SqlSessionFactory sqlSessionFactory) {
		// 创建工厂对象，针对Mapper1接口，生产mapper1对象
		MapperFactoryBean<Mapper1> factory = new MapperFactoryBean<>(Mapper1.class);
		factory.setSqlSessionFactory(sqlSessionFactory);
		return factory;
	}*/

	/**
	 * 使用@Bean的方式生成Mapper2对象
	 * 缺点：不能批量添加mapper对象
	 *
	 * @param sqlSessionFactory
	 * @return
	 */
	/*@Bean
	public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<Mapper2> factory = new MapperFactoryBean<>(Mapper2.class);
		factory.setSqlSessionFactory(sqlSessionFactory);
		return factory;
	}*/
}
