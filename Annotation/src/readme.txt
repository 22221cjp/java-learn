把注解类看成特殊的类
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Description {
	public String value();
}
这个类特殊在哪里：
1. 声明用@interface
2. 类中的方法实际上是个字段（可以这么看），因为如此，这些方法都不能有参数
3. 这些字段的类型只能是“四类八种”+类+枚举+它们的数组
4. 可以给字段赋默认值，使用default关键字
5. 可以通过反射拿到类的注解和它们的值

