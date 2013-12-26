package se.aidium.mock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import se.aidium.mock.time.TimeProvider;

@Aspect
public class SystemTimeMockAspect {

	@Around("call(public long java.lang.System.currentTimeMillis())")
	// @Around("execution(* java.lang.System.currentTimeMillis())")
	public Object aroundSystemcurrentTimeMillis(ProceedingJoinPoint pjp) throws Throwable {
		try {
			System.out.println("Calling currentTimeMillis");
			new Exception().printStackTrace();
			if (TimeProvider.instance().isTimedMocked()) {
				return TimeProvider.instance().currentTimeMillis();
			}
			return pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
}
