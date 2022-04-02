package com.xwl.debug.service;

import com.xwl.debug.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xwl
 * @createdTime 2021/12/29 11:03
 * @description
 * 1、@Autowired：自动注入：
 * 	1）、默认优先按照类型去容器中找对应的组件:ioc.getBean(BookDao.class);找到就赋值
 * 	2）、如果找到多个相同类型的组件，再将属性的名称（bookDao/bookDao2）作为组件的id去容器中查找：ioc.getBean("bookDao")
 * 		BookService {
 *   		@Autowired
 *   		BookDao bookDao;
 *   		//BookDao bookDao2;
 *   	}
 * 	3）、@Qualifier("bookDao2")：使用@Qualifier("personDao2")指定需要装配的组件的id，而不是使用属性名
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
 */
@Service
public class PersonService {

//	@Resource(name = "personDao2")
//	@Qualifier("personDao2")
	@Autowired
	private PersonDao personDao;
//	private PersonDao personDao2;

	public void print() {
		System.out.println(personDao);
//		System.out.println(personDao2);
	}
}
