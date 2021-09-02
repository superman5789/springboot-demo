package com.example.springbootaop.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

	/**
	 * 方法正常执行完后执行
	 */
//	@AfterReturning(pointcut = "execution( * com.example.springbootaop.aop.*.*(..))", returning = "result")
//	public void afterHandle(JoinPoint joinPoint, Object result) throws Exception {
//		Object[] args = joinPoint.getArgs();
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		System.out.println("获取到的方法参数是：" + objectMapper.writeValueAsString(args));
//		System.out.println("获取到的className：" + joinPoint.getTarget().getClass().getName());
//		System.out.println("获取到的methodName：" + joinPoint.getSignature().getName());
//		System.out.println("获取到的返回值是：" + result);
//	}

	/**
	 * 环绕执行
	 */
	@Around("execution( * com.example.springbootaop.aop.*.*(..)) && @annotation(myAnnotation)")
	public Object aroundHandle(ProceedingJoinPoint joinPoint, MyAnnotation myAnnotation) {
		System.out.println("前置通知");
		Object proceed = null;
		try {
			System.out.println("########注解value：" + myAnnotation.value());
			System.out.println("########调用的类：" + joinPoint.getSignature().getDeclaringTypeName());
			System.out.println("########调用的方法：" + joinPoint.getSignature().getDeclaringType().getSimpleName());
			Object[] args = joinPoint.getArgs();
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println("########获取到的方法参数是：" + objectMapper.writeValueAsString(args));

			proceed = joinPoint.proceed();

			System.out.println("########返回值：" + proceed.toString());
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("异常通知");
		}
		System.out.println("后置通知");

		System.out.println("返回通知");
		return proceed;
	}


}
