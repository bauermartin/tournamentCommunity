package de.mb.tournamentCommunity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"de.mb.tournamentCommunity.controller"})
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.html").addResourceLocations("/");
		registry.addResourceHandler("/assets/templates/**").addResourceLocations("/assets/templates/");
		registry.addResourceHandler("/assets/testData/**").addResourceLocations("/assets/testData/");
		registry.addResourceHandler("/assets/img/**").addResourceLocations("/assets/img/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}

}
