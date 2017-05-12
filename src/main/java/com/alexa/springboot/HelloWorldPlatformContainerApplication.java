package com.alexa.springboot;

import java.util.Arrays;
import java.util.Properties;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class HelloWorldPlatformContainerApplication extends SpringBootServletInitializer {

	private static BOTLogger LOG = BOTLogger.getLogger(HelloWorldPlatformContainerApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloWorldPlatformContainerApplication.class);
	}

	public static void main(String[] args) {
		try {
			MDC.put("txn-id", "CONTAINER");
			System.out.println("Boot main class [" + HelloWorldPlatformContainerApplication.class.getName() + "]");
			Properties properties = System.getProperties();
			properties.list(System.out);

			ConfigurableApplicationContext context = SpringApplication.run(HelloWorldPlatformContainerApplication.class,
					args);
			
			String[] beanNames = context.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			LOG.debug("Total number bean definitions [{}]", context.getBeanDefinitionCount());
			LOG.debug("Printing spring initalized bean name start..");
			for (String beanName : beanNames) {
				LOG.debug(beanName);
			}
			LOG.debug("Printing spring initalized bean name ends..");
		} finally {
			MDC.remove("txn-id");
		}
	}
	
	@Bean
	 public ServletRegistrationBean servletRegistration () {
		  LOG.debug("ServletRegistrationBean..");
	      ServletRegistrationBean srb = new ServletRegistrationBean();
	      srb.setServlet(new AlexaBootServlet());
	      srb.setUrlMappings(Arrays.asList("/alexabootservlet"));
	      return srb;
	  }
}
