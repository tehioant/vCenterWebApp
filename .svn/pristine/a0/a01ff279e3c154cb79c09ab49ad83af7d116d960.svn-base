package fr.eseo.cc3.dao.implementation;

import static fr.eseo.cc3.dao.DAOUtilitaire.fermeturesSilencieuses;
import static fr.eseo.cc3.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.ServeurDao;
import fr.eseo.cc3.dao.bean.Serveur;

/**
 * ServeurDaoImpl implémente les fonctions permettant de récupérer les informations liées à un ou plusieurs serveur.
 */
public class ServeurDaoImpl implements ServeurDao{
	
	private DAOFactory daoFactory;
	
	/**
	 * Constructeur permettant de récupérer le daoFactory.
	 * @param daoFactory 
	 */
    public ServeurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    /**
     * Récupère un serveur selon son nom.
     */
    private static final String SQL_SELECT_PAR_NOMSERVEUR = "SELECT numServeur, nomServeur, ipServeur, afficher FROM Serveur WHERE nomServeur = ?";
    
    @Override
    public Serveur trouverSemi(String nomServeur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Serveur serveur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NOMSERVEUR, false, nomServeur );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                serveur = mapSemi( resultSet );
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return serveur;
    }
    
    /**
     * Récupère un serveur selon son numéro dans la BDD.
     */
    private static final String SQL_SELECT_SEMI_PAR_NUMSERVEUR = "SELECT numServeur, nomServeur, ipServeur, afficher FROM Serveur WHERE numServeur = ?";

    @Override
    public Serveur trouverSemi(int numServeur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Serveur serveur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_SEMI_PAR_NUMSERVEUR, false, numServeur );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                serveur = mapSemi( resultSet );
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return serveur;
    }
    

    /**
     * Récupère un serveur selon son numéro dans la BDD.
     */
    private static final String SQL_SELECT_FULL_PAR_NUMSERVEUR = "SELECT numServeur, nomServeur, ipServeur, identifiant, motDePasse, afficher FROM Serveur WHERE numServeur = ?";

    @Override
    public Serveur trouverFull(int numServeur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Serveur serveur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_FULL_PAR_NUMSERVEUR, false, numServeur );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                serveur = mapFull( resultSet );
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return serveur;
    }
    
    
    /**
     * Récupère l'intégralité des serveurs.
     */
    private static final String SQL_SELECT_ALL = "SELECT numServeur, nomServeur, ipServeur, afficher FROM Serveur";

    @Override
    public ArrayList<Serveur> lister() throws SQLException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Serveur> serveurs = new ArrayList<Serveur>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                serveurs.add(mapSemi( resultSet ));
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return serveurs;
    }
    
    
    /**
     * Supprime un serveur selon son numéro.
     */
    private static final String SQL_DELETE = "DELETE FROM Serveur WHERE numServeur = ?";

    @Override
    public void supprimer( Serveur serveur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, serveur.getNumServeur() );
            resultSet = preparedStatement.executeQuery();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    
    /**
     * Modifie toutes les informations d'un serveur.
     */
    private static final String SQL_UPDATE_FULL = "UPDATE Serveur SET numServeur = ?, nomServeur = ?, ipServeur = ?, identifiant = ?, motDePasse = ?, afficher = ? WHERE numServeur = ?";

    @Override
    public void modifierFull( Serveur serveur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_FULL, true, serveur.getNumServeur(), serveur.getNomServeur(), serveur.getIpServeur(), serveur.getIdentifiant(), serveur.getPassDecrypte(), String.valueOf(serveur.isAfficher()), serveur.getNumServeur());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la mise a jour du serveur, aucune ligne mise a jour dans la table." );
            }
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    
    /**
     * Modifie les informations non sensible d'un serveur.
     */
    private static final String SQL_UPDATE_SEMI = "UPDATE Serveur SET numServeur = ?, nomServeur = ?, ipServeur = ?, afficher = ? WHERE numServeur = ?";

    @Override
    public void modifierSemi( Serveur serveur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_SEMI, true, serveur.getNumServeur(), serveur.getNomServeur(), serveur.getIpServeur(), String.valueOf(serveur.isAfficher()), serveur.getNumServeur());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la mise a jour du serveur, aucune ligne mise a jour dans la table." );
            }
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    
    /**
     * Créer un serveur.
     */
    private static final String SQL_INSERT = "INSERT INTO Serveur (nomServeur, ipServeur, identifiant, motDePasse, afficher) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void creer( Serveur serveur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, serveur.getNomServeur(), serveur.getIpServeur(), serveur.getIdentifiant(), serveur.getPassDecrypte(), String.valueOf(serveur.isAfficher()));
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                serveur.setNumServeur( valeursAutoGenerees.getInt( 1 ) );
            } else {
                throw new SQLException( "Échec de la création du serveur en base, aucun ID auto-généré retourné." );
            }
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
        
    /**
	 * Transforme le retour de la BDD en objet Serveur.
	 * @param resultSet Récupère la réponse de la base de données.
     * @return un objet Serveur remplie de toutes le informations.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
    private static Serveur mapFull( ResultSet resultSet ) throws SQLException {
    	return new Serveur(resultSet.getInt("numServeur")
    			, resultSet.getString("nomServeur")
    			, resultSet.getString("ipServeur")
    			, resultSet.getString("identifiant")
    			, resultSet.getString("motDePasse")
    			, Boolean.valueOf(resultSet.getString("afficher")
    					)
    			);

    }  

    /**
	 * Transforme le retour de la BDD en objet Serveur sans informations sensbile.
	 * @param resultSet Récupère la réponse de la base de données.
     * @return un objet Serveur remplie d'informations non sensible.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
    private static Serveur mapSemi( ResultSet resultSet ) throws SQLException {
    	return new Serveur(resultSet.getInt("numServeur")
    			, resultSet.getString("nomServeur")
    			, resultSet.getString("ipServeur")
    			, Boolean.valueOf(resultSet.getString("afficher")
    					)
    			);

    }  
}
