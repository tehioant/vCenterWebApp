package fr.eseo.cc3.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.eseo.cc3.dao.DAOFactory;

/**
 * Application Lifecycle Listener implementation class InitialisationDaoFactory
 *
 */
@WebListener
public class InitialisationDaoFactory implements ServletContextListener {
	
    private static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory daoFactory;

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        /* Récupération du ServletContext lors du chargement de l'application */
        ServletContext servletContext = event.getServletContext();
        /* Instanciation de notre DAOFactory */
        this.daoFactory = DAOFactory.getInstance(false);
        /* Enregistrement dans un attribut ayant pour portée toute l'application */
        servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
    	DAOFactory dao = ((DAOFactory) event.getServletContext().getAttribute( ATT_DAO_FACTORY ) );
    	dao.shutdownConnection();
    }
	
}
