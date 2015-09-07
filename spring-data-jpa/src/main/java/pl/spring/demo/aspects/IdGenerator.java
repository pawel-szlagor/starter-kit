package pl.spring.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.IdAware;

@Aspect
@Component
public class IdGenerator {

	@Before(" @annotation(pl.spring.demo.annotation.GenerateId) && args(element)")
	public void generateId(JoinPoint joinPoint, IdAware element) {
		System.out.println("udało się, generujemy Id-iki");
		// IdAware element = (IdAware) joinPoint.getArgs()[0];
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		Sequence sequence = (Sequence) context.getBean("sequence");
		BookServiceImpl service = (BookServiceImpl) joinPoint.getTarget();
		if (element.getId() == null) {
			element.setId(sequence.nextValue(service.findAllBooks()));
		}
		((AbstractApplicationContext) context).close();
	}
}
