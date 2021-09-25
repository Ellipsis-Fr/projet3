package fr.isika.projet3.config;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import fr.isika.projet3.filters.DashboardAssociationFilter;
import fr.isika.projet3.filters.DashboardPartnerFilter;

public class WebAppInitialize implements WebApplicationInitializer {
//	private String TMP_FOLDER = "/tmp"; 
//    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024; 

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
 		context.register(AppConfig.class);
 		context.setServletContext(servletContext);
 		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",	new DispatcherServlet(context));
 		dispatcher.setLoadOnStartup(1);
 		dispatcher.addMapping("/");
 		
 		//servletContext.setInitParameter("pathAssociations", "ServerContent/associations/");
 		
//		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(context);
//		
//		servletContext.addListener(contextLoaderListener);
//		
		// Filter.
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
		
		FilterRegistration.Dynamic dashboardAssociationFilter = servletContext.addFilter("dashboardAssociationFilter", DashboardAssociationFilter.class);
		dashboardAssociationFilter.addMappingForUrlPatterns(null, true, "/dashboardAsso/*");
		
		FilterRegistration.Dynamic dashboardPartnerFilter = servletContext.addFilter("dashboardPartnerFilter", DashboardPartnerFilter.class);
		dashboardPartnerFilter.addMappingForUrlPatterns(null, true, "/dashboardPartner/*");
		
//		// Gestion de fichiers
//		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);        
//		dispatcher.setMultipartConfig(multipartConfigElement);
}

}
