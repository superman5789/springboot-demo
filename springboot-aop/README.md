AOP:
1、定义一个切面类Aspect
即在声明的类中，增加@Component、@Aspect两个注解，springboot中要引入spring-boot-starter-aop依赖包
2、定义切点Pointcut
如@Pointcut(public * com.xx.xx.*.*(...))
规则：修饰符(public可以不写，但不能用*) + 返回类型 + 包名 + 方法 + 方法参数
3、定义Advice通知
@Before：目标方法调用之前，无论方法是否遇到异常都执行
@After：目标方法执行之后，无论方法是否遇到异常都执行
@AfterReturning：目标方法执行之后，有异常则不执行
@AfterThrowing：异常通知，在方法抛出异常时，可以获取异常信息
@Around：环绕通知，可以在执行全过程中执行