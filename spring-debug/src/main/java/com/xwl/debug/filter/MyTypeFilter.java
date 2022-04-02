package com.xwl.debug.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author xwl
 * @createdTime 2021/12/29 15:56
 * @description @ComponentScan -> @Filter -> TypeFilter：自定义TypeFilter实现
 */
public class MyTypeFilter implements TypeFilter {

	/**
	 * metadataReader：读取到的当前正在扫描的类的信息
	 * metadataReaderFactory:可以获取到其他任何类信息的
	 *
	 * @param metadataReader        the metadata reader for the target class
	 * @param metadataReaderFactory a factory for obtaining metadata readers
	 *                              for other classes (such as superclasses and interfaces)
	 * @return true：匹配成功；false：匹配失败
	 * @throws IOException
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		// 获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		// 获取当前正在扫描的类的类信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		// 获取当前类资源（类的路径）
		Resource resource = metadataReader.getResource();

		String className = classMetadata.getClassName();
		System.out.println("--->" + className);
		if (className.contains("er")) {
			return true;
		}
		return false;
	}
}
