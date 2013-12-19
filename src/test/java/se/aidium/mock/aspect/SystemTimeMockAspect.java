package se.aidium.mock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SystemTimeMockAspect {

	@Around(value = "execute long System.currentTimeMillis()")
	public Object aroundSystemcurrentTimeMillis(ProceedingJoinPoint pjp)
			throws Throwable {
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
}
