package com.chen.annotation;

import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) {
		Class<Csdn> clazz = Csdn.class;

		Method[] methods = clazz.getDeclaredMethods();
		if (clazz.isAnnotationPresent(Description.class)) {
			Description description = clazz.getAnnotation(Description.class);
			System.out.println(description.value());
		}
		
		if (clazz.isAnnotationPresent(Author.class)) {
			Author author=clazz.getAnnotation(Author.class);
			System.out.println(author.name());
		}

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAnnotationPresent(Name.class)) {
				Name name = methods[i].getAnnotation(Name.class);
				System.out.println(name.origin() + ":" + name.community());
			}
		}
	}
}
