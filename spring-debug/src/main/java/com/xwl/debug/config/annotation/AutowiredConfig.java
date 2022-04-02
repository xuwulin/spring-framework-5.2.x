package com.xwl.debug.config.annotation;

import com.xwl.debug.bean.Company;
import com.xwl.debug.bean.Department;
import com.xwl.debug.bean.Employee;
import com.xwl.debug.dao.PersonDao;
import org.springframework.context.annotation.*;

/**
 * @author xwl
 * @createdTime 2021/12/30 15:22
 * @description @Autowired注解使用
 * 自动装配：Spring利用依赖注入（DI），完成对IOC容器中中各个组件的依赖关系赋值；
 *
 * 1、@Autowired：自动注入：
 * 	1）、默认优先按照类型去容器中找对应的组件:ioc.getBean(BookDao.class);找到就赋值
 * 	2）、如果找到多个相同类型的组件，再将属性的名称（bookDao/bookDao2）作为组件的id去容器中查找：ioc.getBean("bookDao")
 * 		BookService {
 *   		@Autowired
 *   		BookDao bookDao;
 *   		//BookDao bookDao2;
 *   	}
 * 	3）、@Qualifier("bookDao2")：使用@Qualifier指定需要装配的组件的id，而不是使用属性名
 * 		BookService {
 * 			@Qualifier("personDao2")
 *     		@Autowired
 *   		BookDao bookDao;
 *    	}
 * 	4）、自动装配默认一定要将属性赋值好，没有就会报错；可以使用@Autowired(required=false);
 * 		BookService {
 *       	@Autowired(required = false)
 *     		BookDao bookDao;
 *      }
 * 	5）、@Primary：让Spring进行自动装配的时候，默认使用首选的bean；也可以继续使用@Qualifier指定需要装配的bean的名字
 * 		AutowiredConfig {
 *      	@Primary
 *    		@Bean("personDao2")
 *    		public PersonDao personDao() {
 * 				PersonDao personDao = new PersonDao();
 * 				personDao.setLabel("2");
 * 				return personDao;
 *    		}
 *      }
 *      BookService {
 *      	// 此时自动装配的就是personDao2
 *         	@Autowired
 *       	BookDao bookDao;
 *      }
 *
 * 2、Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
 * 	1）、@Resource:
 * 		可以和@Autowired一样实现自动装配功能；默认是按照组件名称进行装配的；
 * 		没有能支持@Primary功能没有支持@Autowired（reqiured=false）;
 * 	2）、@Inject:
 * 		需要导入javax.inject的包，和Autowired的功能一样。没有required=false的功能；
 * @Autowired、@Resource、@Inject的三者区别：
 *  @Autowired：Spring定义的；
 *  @Resource、@Inject都是java规范
 *
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能；
 *
 * 3、@Autowired可以标注的位置：构造器，参数，方法，属性；都是从容器中获取参数组件的值
 *  1）、【标注在方法位置】：@Bean+方法参数；参数从IOC容器中获取；默认不写@Autowired效果是一样的，都能自动装配
 *  2）、【标在构造器上】：如果组件只有一个有参构造器，参数从IOC容器中获取；这个有参构造器的@Autowired可以省略，
 *  3）、【放在参数位置】：参数位置的组件还是可以自动从IOC容器中获取
 *
 * 4、自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
 * 	自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
 * 	把Spring底层一些组件注入到自定义的Bean中；
 * 	xxxAware：功能使用xxxProcessor；
 * 		比如：ApplicationContextAware ==> ApplicationContextAwareProcessor；
 */
@ComponentScan(basePackages = {"com.xwl.debug.service", "com.xwl.debug.dao"})
@Import({Company.class, Employee.class})
@Configuration
public class AutowiredConfig {

	/**
	 * 使用@Bean注解 给容器众注册一个bean，默认是单实例的bean，类型为方法的返回值类型，beanName默认为方法名，也可以自定义beanName
	 * @return
	 */
	@Primary
	@Bean("personDao2")
	public PersonDao personDao() {
		PersonDao personDao = new PersonDao();
		personDao.setLabel("2");
		return personDao;
	}

	/**
	 * 【标注在方法位置】：@Bean+方法参数；参数从IOC容器中获取；默认不写@Autowired效果是一样的，都能自动装配
	 * @Bean 标注的方法，创建对象的时候，方法参数的值从IOC容器中获取
	 * @param employee
	 * @return
	 */
	@Bean
	public Department department(/*@Autowired*/ Employee employee) {
		Department department = new Department();
		department.setEmployee(employee);
		return department;
	}

}
