package ua.opinta.performanceMeasurement;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ClassesPerformance {

    final String thisPointcut = "execution(* ua.opinta.fileHandler.FileAnalytics.* (..))";

    //@Around("execution(* ua.opinta.fileHandler.FileAnalytics.* (..))")
    @Around(thisPointcut + " && @annotation(ua.opinta.annotation.ShowTime)")
    public Object watchTime(ProceedingJoinPoint joinPoint) {
        Object returnValue = null;

        long msDuration = System.currentTimeMillis();
        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        msDuration = System.currentTimeMillis() - msDuration;
        System.out.println("Duration of function " + joinPoint.getSignature().getName() + " is " + msDuration + "ms");

        return returnValue;
    }

    @AfterReturning(pointcut = thisPointcut + "&& @annotation(ua.opinta.annotation.ShowResult)", returning= "obj")
    public void print(JoinPoint joinPoint, Object obj) {
        System.out.println(obj);
    }

}
