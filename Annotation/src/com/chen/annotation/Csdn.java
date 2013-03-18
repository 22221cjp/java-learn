package com.chen.annotation;

@Description(value = "this is good community for programmer!")
@Author
public class Csdn {

	@Name(origin = "chen", community = "java")
	public String getName1() {
		return null;
	}

	@Name(origin = "jinpeng")
	public String getName2() {
		return null;
	}
}
