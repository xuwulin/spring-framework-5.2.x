package com.xwl.debug.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xwl
 * @createdTime 2021/12/29 17:27
 * @description 判断是否是Windows系统
 */
public class WindowsCondition implements Condition {
	/**
	 * 判断是否是Windows系统
	 *
	 * @param context  判断条件能使用的上下文（环境）
	 * @param metadata 注释信息
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 获取当前环境信息
		Environment environment = context.getEnvironment();
		// 动态获取环境变量值：linux
		String property = environment.getProperty("os.name");
		if (property.contains("Windows")) {
			return true;
		}
		return false;
	}
}
