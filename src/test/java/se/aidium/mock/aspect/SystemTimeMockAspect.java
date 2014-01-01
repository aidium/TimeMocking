package se.aidium.mock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import se.aidium.mock.time.TimeProvider;

@Aspect
public class SystemTimeMockAspect {

	@Pointcut("call(public long java.lang.System.currentTimeMillis())")
	void currentTimeMillis() {
	}

	@Around("currentTimeMillis()")
	public Object aroundSystemcurrentTimeMillis(ProceedingJoinPoint pjp) throws Throwable {
		if (TimeProvider.instance().isTimedMocked()) {
			return TimeProvider.instance().currentTimeMillis();
		}
		return pjp.proceed();
	}
}
