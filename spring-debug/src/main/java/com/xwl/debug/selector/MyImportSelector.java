package com.xwl.debug.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xwl
 * @createdTime 2021/12/30 10:29
 * @description 自定义逻辑，返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector {
	/**
	 * 自定义逻辑，返回需要导入的组件
	 *
	 * @param importingClassMetadata 标注@Import注解的类的所有注解信息
	 * @return 返回值就是需要要导入的组件的全类名数组，注：一定要是全类名
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.xwl.debug.bean.Blue", "com.xwl.debug.bean.Yellow"};
	}
}
