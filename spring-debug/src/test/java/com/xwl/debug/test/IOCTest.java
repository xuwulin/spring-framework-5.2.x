package com.xwl.debug.test;

import com.xwl.debug.bean.*;
import com.xwl.debug.config.*;
import com.xwl.debug.config.annotation.*;
import com.xwl.debug.dao.PersonDao;
import com.xwl.debug.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author xwl
 * @createdTime 2021/12/29 11:06
 * @description
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:beans.xml")
@SpringJUnitConfig(locations = "classpath:beans.xml") // 复合注解，替换@ExtendWith和@ContextConfiguration
public class IOCTest {
	@Test
	public void testXml() {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) ioc.getBean("person");
		System.out.println(person);
		Teacher teacher = (Teacher) ioc.getBean("teacher");
		System.out.println(teacher);
	}

	@Test
	public void testBeanConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(BeanConfig.class);
		Person bean = ioc.getBean(Person.class);
		System.out.println(bean);
		System.out.println(bean.sayHello());
	}

	@Test
	public void testComponentScanConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
		printBeanDefinitionNames(ioc);
	}

	@Test
	public void testScopeConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(ScopeConfig.class);
		System.out.println("从IOC容器中获取Person");
		Object bean1 = ioc.getBean("person");
		Object bean2 = ioc.getBean("person");
		System.out.println(bean1 == bean2);
	}

	@Test
	public void testLazyConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(LazyConfig.class);
		System.out.println("从IOC容器中获取Person");
		Object bean1 = ioc.getBean("person");
		Object bean2 = ioc.getBean("person");
		System.out.println(bean1 == bean2);
	}

	@Test
	public void testConditionalConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(ConditionalConfig.class);
		// 动态获取环境变量值：Windows 10
		Environment environment = ioc.getEnvironment();
		String property = environment.getProperty("os.name");
		System.out.println(property);

		String[] namesForType = ioc.getBeanNamesForType(Person.class);
		for (String name : namesForType) {
			System.out.println(name);
		}

		Map<String, Person> personMap = ioc.getBeansOfType(Person.class);
		System.out.println(personMap);
	}

	@Test
	public void testImportConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(ImportConfig.class);
		printBeanDefinitionNames(ioc);
		System.out.println(ioc.getBean(Blue.class));
	}

	@Test
	public void testFactoryBeanConfig() {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
		printBeanDefinitionNames(ioc);
		// 工厂Bean获取的是调用getObject()方法创建的对象
		Object bean1 = ioc.getBean("myFactoryBean");
		Object bean2 = ioc.getBean("myFactoryBean");
		System.out.println("sout-->bean的类型：" + bean1.getClass());
		System.out.println(bean1 == bean2);
		// 要获取工厂Bean本身，我们需要给id前面加一个&，比如&myFactoryBean
		Object bean3 = ioc.getBean("&myFactoryBean");
		System.out.println(bean3);
	}

	@Test
	public void testBeanLifeCycleConfig() {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);
		System.out.println("sout-->容器创建完成...");
		ioc.close();
	}

	@Test
	public void testBeanPropertyValuesConfig() {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(BeanPropertyValuesConfig.class);
		printBeanDefinitionNames(ioc);
		Person person = (Person) ioc.getBean("person");
		System.out.println(person);
		ConfigurableEnvironment environment = ioc.getEnvironment();
		String nickname = environment.getProperty("nickname");
		System.out.println(nickname);
	}

	@Test
	public void testAutowiredConfig() {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AutowiredConfig.class);
		PersonService personService = ioc.getBean(PersonService.class);
		personService.print();
		PersonDao personDao = ioc.getBean(PersonDao.class);
		System.out.println(personDao);

		System.out.println("=================");
		Company company = ioc.getBean(Company.class);
		System.out.println(company);
		Employee employee = ioc.getBean(Employee.class);
		System.out.println(employee);
		Department department = ioc.getBean(Department.class);
		System.out.println(department);
	}

	@Test
	public void testAwareConfig() {
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AwareConfig.class);
	}

	/**
	 * 1、使用命令行动态参数的方式，在虚拟机参数位置：-Dspring.profiles.active=dev
	 * 2、使用代码的方式
	 */
	@Test
	public void testProfileConfig() {
		// 1、使用命令行动态参数的方式，在虚拟机参数位置：-Dspring.profiles.active=dev
//		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(ProfileConfig.class);
		// 2、使用代码的方式
		AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext();
		// 1)、设置需要激活的环境
		ioc.getEnvironment().setActiveProfiles("dev", "test");
		// 2)、注册主配置类
		ioc.register(ProfileConfig.class);
		// 3)、启动刷新
		ioc.refresh();

		String[] beanNamesForType = ioc.getBeanNamesForType(DataSource.class);
		for (String beanName : beanNamesForType) {
			System.out.println(beanName);
		}
	}

	private void printBeanDefinitionNames(ApplicationContext ioc) {
		String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}
}
