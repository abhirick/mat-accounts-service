package com.abhirick.matrimonial.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.owasp.encoder.Encode.forJava;

@Aspect
@Component
public class RequestLogAspect {

	private final Logger log = LoggerFactory.getLogger(RequestLogAspect.class);

	@Around("@annotation(com.abhirick.matrimonial.aspect.RequestLog) && execution(public * *(..))")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Object value;

		try {
			value = proceedingJoinPoint.proceed();
		} finally {
			log.info("Request Method :: {} || Request URI :: {} || Request Remote Addr :: {} ", forJava(request.getMethod()),
					forJava(request.getRequestURI()), forJava(request.getRemoteAddr()));
			log.info(
					"Logging Header Values :: x-JourneyID :: {} || x-MessageID :: {} || x-SourceSystemID :: {} || x-CreateDateTime :: {} || x-RequesterType :: {} || x-RequesterID :: {} || x-RequestURI :: {} || x-APIVersion :: {}",
					forJava(request.getHeader("x-JourneyID")), forJava(request.getHeader("x-MessageID")),
					forJava(request.getHeader("x-SourceSystemID")), forJava(request.getHeader("x-CreateDateTime")),
					forJava(request.getHeader("x-RequesterType")), forJava(request.getHeader("x-RequesterID")),
					forJava(request.getHeader("x-RequestURI")), forJava(request.getHeader("x-APIVersion")));
		}
		return value;
	}
}