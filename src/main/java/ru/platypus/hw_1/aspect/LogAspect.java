//package ru.platypus.hw_1.aspect;
//
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//
//@Aspect
//@Component
//public class LogAspect {
//
//    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
//
//    @Before("@annotation(ru.platypus.hw_1.annotation.LogBeforeMethod)")
//    public void logBefore(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        logger.info("Перед выполнением метода {} с аргументом: {}", methodName, Arrays.toString(args));
//    }
//
//    @Around("@annotation(ru.platypus.hw_1.annotation.LogExecutionTime)")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = joinPoint.proceed();
//        long endTime = System.currentTimeMillis();
//        long duration = endTime - startTime;
//        logger.info("Метод {} выполнен за {} миллисекунд", joinPoint.getSignature().getName(), duration);
//        return result;
//    }
//
//    @AfterThrowing(pointcut = "@annotation(ru.platypus.hw_1.annotation.LogException)", throwing = "ex")
//    public void logException(JoinPoint joinPoint, Throwable ex) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//
//        logger.error("исключение в методе {} с аргументами {}. Сообщение: {}", methodName, Arrays.toString(args), ex.getMessage());
//    }
//
//    @AfterReturning(pointcut = "@annotation(ru.platypus.hw_1.annotation.LogResult)", returning = "result")
//    public void logResult(JoinPoint joinPoint, Object result) {
//        String methodName = joinPoint.getSignature().getName();
//
//        logger.info("Метод {} завершён успешно. Результат: {}", methodName, result);
//    }
//
//}
