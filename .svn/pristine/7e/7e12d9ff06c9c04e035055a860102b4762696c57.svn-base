package fr.eseo.cc3.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.BasicConfigurator;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


import fr.eseo.cc3.dao.exception.DAOConfigurationException;

import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao._interface.IsoDao;
import fr.eseo.cc3.dao.implementation.CourDaoImpl;
import fr.eseo.cc3.dao.implementation.IsoDaoImpl;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao.implementation.UtilisateurDaoImpl;

import fr.eseo.cc3.dao._interface.ServeurDao;
import fr.eseo.cc3.dao.implementation.ServeurDaoImpl;


import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.implementation.VirtualMachineDaoImpl;

/**
 * DAOFactory créer et gère le pool de connexion à la BDD
 */
public class DAOFactory {

	
    /**
     * Nom du fichier de configuration du DAO
     */
    private static final String FICHIER_PROPERTIES       = "dao";
    
    /**
     * Nom du paramêtre dans le fichier de config pour récupérer l'url
     */
    private static final String PROPERTY_URL             = "url";
    
    /**
     * Nom du paramêtre dans le fichier de config pour récupérer l'url de la bdd de test
     */
    private static final String PROPERTY_URL_TEST        = "urlTest";
    
    /**
     * Nom du paramêtre dans le fichier de config pour récupérer le nom du driver
     */
    private static final String PROPERTY_DRIVER          = "driver";
    
    /**
     * Nom du paramêtre dans le fichier de config pour récupérer le nom de l'utilisateur de BDD
     */
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    
    /**
     * Nom du paramêtre dans le fichier de config pour récupérer le mot de passe à la BDD
     */
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";
    
    /**
     * ResourceBundle contient le fichier de config du dao
     */
    private static ResourceBundle LOGIN_DAO;

    /* package */
    BoneCP connectionPool = null;

    /* package */
    DAOFactory( BoneCP connectionPool ) {
    	this.connectionPool = connectionPool;
    }


    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance(boolean test) throws DAOConfigurationException {
    	String url;
    	String driver;
    	String nomUtilisateur;
    	String motDePasse;
    	BoneCP connectionPool = null;
    	BasicConfigurator.configure();
    	try {
    		LOGIN_DAO = ResourceBundle.getBundle(FICHIER_PROPERTIES);
    		
    		if(test) {
    			url = LOGIN_DAO.getString(PROPERTY_URL_TEST).trim();
    			
    		}else {
    			url = LOGIN_DAO.getString(PROPERTY_URL).trim();
    		}
    		
    		driver = LOGIN_DAO.getString(PROPERTY_DRIVER).trim();
            nomUtilisateur = LOGIN_DAO.getString(PROPERTY_NOM_UTILISATEUR).trim();
            motDePasse = LOGIN_DAO.getString(PROPERTY_MOT_DE_PASSE).trim();
    	} catch ( MissingResourceException e ) {
    		throw new DAOConfigurationException( "Impossible de charger le fichier properties ", e );
    	}

    	try {
    		Class.forName( driver );
    	} catch ( ClassNotFoundException e ) {
    		throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
    	}
    	try {
    		/*
    		 * Cr�ation d'une configuration de pool de connexions via l'objet
    		 * BoneCPConfig et les diff�rents setters associ�s.
    		 */
    		BoneCPConfig config = new BoneCPConfig();
    		/* Mise en place de l'URL, du nom et du mot de passe */
    		config.setJdbcUrl( url );
    		config.setUsername( nomUtilisateur );
    		config.setPassword( motDePasse );
    		/* Param�trage de la taille du pool */
    		config.setMinConnectionsPerPartition( 5 );
    		config.setMaxConnectionsPerPartition( 10 );
    		config.setPartitionCount( 2 );
    		/* Cr�ation du pool � partir de la configuration, via l'objet BoneCP */
    		connectionPool = new BoneCP( config );
    	} catch ( SQLException e ) {
    		throw new DAOConfigurationException( "Erreur de configuration du pool de connexions.", e );
    	}
    	/*
    	 * Enregistrement du pool cr�� dans une variable d'instance via un appel
    	 * au constructeur de DAOFactory
    	 */
    	DAOFactory instance = new DAOFactory( connectionPool );
    	return instance;
    }

    
    /**
     * Méthode charger de fournir une connexion
     * @return une connexion du connexion pool
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
    	return connectionPool.getConnection();
    }
    
    /**
     * Methode qui ferme toutes les connexion de la BDD
     */
    public void shutdownConnection() {
    	connectionPool.shutdown();
    }

    /**
     * Fournit une instance de UtilisateurDaoImpl
     * @return une instance de UtilisateurDaoImpl
     */
    public UtilisateurDao getUtilisateurDao() {
    	return new UtilisateurDaoImpl( this );
    }

    /**
     * Fournit une instance de VirtualMachineDaoImpl
     * @return une instance de VirtualMachineDaoImpl
     */
    public VirtualMachineDao getVirtualMachineDao() {
    	return new VirtualMachineDaoImpl( this );
    }
    
    /**
     * Fournit une instance de CourDaoImpl
     * @return une instance de CourDaoImpl
     */
    public CourDao getCourDao() {
    	return new CourDaoImpl( this );
    }
    
    /**
     * Fournit une instance de ServeurDaoImpl
     * @return une instance de ServeurDaoImpl
     */
    public ServeurDao getServeurDao() {
    	return new ServeurDaoImpl( this );
    }
    
    /**
     * Fournit une instance de IsoDaoImpl
     * @return une instance de IsoDaoImpl
     */
    public IsoDao getIsoDao() {
    	return new IsoDaoImpl(this);
    }
}

