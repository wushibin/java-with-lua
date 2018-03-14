package lua.learn;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author wushibin on 2018/3/14.
 */
public class MethodLoggerAspect {
    public Object aroundLog(ProceedingJoinPoint joinpoint) {
        try{
            System.out.println("Method called");
            Object result= joinpoint.proceed();
            return result;
        }
        catch (Throwable t){
            return null;
        }
    }
}
