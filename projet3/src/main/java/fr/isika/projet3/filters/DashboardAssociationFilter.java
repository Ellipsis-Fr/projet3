package fr.isika.projet3.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class DashboardAssociationFilter implements Filter {
	public static final String ACCESS_CONNEXION  = "/connexionAssociation";
    public static final String ATT_SESSION_ASSOCIATION = "sessionAssociation";

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		/* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        /* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring(request.getContextPath().length());
        if (chemin.startsWith("/resources") || chemin.startsWith("/ServerContent")) {
            chain.doFilter(request, response);
            return;
        }
        
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
//        System.out.println("je suis dans le filtre");
//        System.out.println(session.getAttribute(ATT_SESSION_ASSOCIATION));
        /**
         * Si l'objet association n'existe pas dans la session en cours, alors
         * l'association n'est pas connectée.
         */
        if (session.getAttribute(ATT_SESSION_ASSOCIATION) == null) {
            /* Redirection vers la page de connexion association */
//        	System.out.println("tu n'es pas connecté");
//        	request.getRequestDispatcher(ACCESS_CONNEXION).forward(request, response);
        	response.sendRedirect(request.getContextPath() + ACCESS_CONNEXION);
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
				
	}
}
