package in.niraj.student_mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class validationConfig {

	@Bean
	public ValidatingMongoEventListener validationMongoEventListner()
	{
		return new ValidatingMongoEventListener(validator());
	}
	
	@Bean
	public LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
