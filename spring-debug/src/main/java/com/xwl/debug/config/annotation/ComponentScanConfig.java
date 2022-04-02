package com.xwl.debug.config.annotation;

import com.xwl.debug.filter.MyTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

/**
 * @author xwl
 * @createdTime 2021/12/29 16:06
 * @description @ComponentScan注解使用
 * @ComponentScan(basePackages = "com.xwl.debug")
 * basePackages/value：指定要扫描的包，该包下只要是标注了@Controller、@Service、@Repository、@Component这四个注解中任意一个的类，都会被扫描并加入到IOC容器中
 * excludeFilters：指定扫描时的排除规则（需要排除哪些组件），是一个Filter[]
 * includeFilters：指定扫描时的包含规则（只需要扫描哪些组件），是一个Filter[]，需要设置useDefaultFilters = false，禁用默认过滤规则，默认是扫描所有
 * <p>
 * FilterType.ANNOTATION：按照注解；
 * FilterType.ASSIGNABLE_TYPE：按照给定的类型；
 * FilterType.ASPECTJ：使用ASPECTJ表达式；
 * FilterType.REGEX：使用正则指定；
 * FilterType.CUSTOM：使用自定义规则。
 */
/*@ComponentScan(basePackages = "com.xwl.debug")*/
/*@ComponentScan(basePackages = "com.xwl.debug", excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
})*/
/*@ComponentScan(basePackages = "com.xwl.debug", includeFilters = {
		@Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {PersonService.class})
}, useDefaultFilters = false)*/
@ComponentScan(basePackages = "com.xwl.debug", includeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class}) // com.xwl.debug包下的所有类都会进入MyTypeFilter进行匹配
}, useDefaultFilters = false)
public class ComponentScanConfig {
}
