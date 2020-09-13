package secretary.common.aspect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Around("@annotation(secretary.common.annotation.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.debug("시작================>");
		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		log.debug("끝================>"+stopWatch.prettyPrint());
		System.out.println("annotation======>");
		
		return proceed;
	}
}
