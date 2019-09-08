package com.javed.aws.applications.config;

import com.javed.aws.applications.controller.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class.getName());

    @Before("execution(* com.javed.aws.applications.controller.*.*())")
    public void beforePerfLogs(JoinPoint joinPoint) {
        logger.debug(" calling {} before method execution", joinPoint.getSignature().getName());
    }

    @After("execution(* com.javed.aws.applications.controller.*.*())")
    public void afterPerfLogs(JoinPoint joinPoint) {
        logger.debug(" after calling {} method execution", joinPoint.getSignature().getName());
    }
    /*@Before("getNamePointcut()")
    public void loggingAdvice(){
        System.out.println("Executing loggingAdvice on getName()");
    }

    @Before("getNamePointcut()")
    public void secondAdvice(){
        System.out.println("Executing secondAdvice on getName()");
    }

    @Pointcut("execution(public String getName())")
    public void getNamePointcut(){}

    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice(){
        System.out.println("Before executing service method");
    }

    //Pointcut to execute on all the methods of classes in a package
    @Pointcut("within(com.journaldev.spring.service.*)")
    public void allMethodsPointcut(){}*/

}
