package com.edu.ele.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

/*
 * @Aspect
 * 
 * @Component
 */
@Slf4j
public class DubboAspect {

  @Before("execution(* com.edu.api.dubbo.*.*(..))")
  public void BeforeInvoke(JoinPoint point) {

    // RpcContext.getContext().setAttachment("_user", "admin");
    String className = point.getTarget().getClass().getName();
    String name = point.getSignature().getName();
    log.info("className==>{}", className);
    log.info("methodName==>{}", name);
    log.info("===========================调用前==========================");
    System.out.println("hello world");
  }
}
