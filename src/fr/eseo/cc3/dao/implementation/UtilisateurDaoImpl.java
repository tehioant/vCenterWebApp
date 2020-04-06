package fr.eseo.cc3.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;
import static fr.eseo.cc3.dao.DAOUtilitaire.*;



public class UtilisateurDaoImpl implements UtilisateurDao {
	private DAOFactory daoFactory;
	
    public UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    private static final String SQL_SELECT_PAR_NOMUTILISATEUR = "SELECT numUtilisateur, nomUtilisateur, hash, nom, prenom, email, role, annee, derniereConnexion FROM Utilisateur WHERE nomUtilisateur = ?";
    
    @Override
    public Utilisateur trouver( String nomUtilisateur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NOMUTILISATEUR, false, nomUtilisateur );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                utilisateur = map( resultSet );
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return utilisateur;
    }

    
    private static final String SQL_SELECT_ALL = "SELECT numUtilisateur, nomUtilisateur, hash, nom, prenom, email, role, annee, derniereConnexion FROM Utilisateur";
    
    @Override
    public ArrayList<Utilisateur> lister() throws SQLException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                utilisateurs.add(map( resultSet ));
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return utilisateurs;
    }
    
    
    private static final String SQL_INSERT = "INSERT INTO Utilisateur (nomUtilisateur, hash, nom, prenom, email, annee, role, derniereConnexion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void creer( Utilisateur utilisateur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getNomUtilisateur(), utilisateur.getHash(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAnnee(), utilisateur.getRole(), utilisateur.getDerniereConnexion() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                utilisateur.setNumUtilisateur( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new SQLException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    
    private static final String SQL_DELETE = "DELETE FROM Utilisateur WHERE numUtilisateur = ?";

    @Override
    public void supprimer( Utilisateur utilisateur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, utilisateur.getNumUtilisateur() );
            resultSet = preparedStatement.executeQuery();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    
    private static final String SQL_UPDATE = "UPDATE Utilisateur SET numUtilisateur = ?, nomUtilisateur = ?, hash = ?, nom = ?, prenom = ?, email = ?, annee = ?, role = ?, derniereConnexion = ? WHERE numUtilisateur = ?";

    @Override
    public void modifier( Utilisateur utilisateur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, utilisateur.getNumUtilisateur(), utilisateur.getNomUtilisateur(), utilisateur.getHash(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAnnee(), utilisateur.getRole(), utilisateur.getDerniereConnexion(), utilisateur.getNumUtilisateur());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la mise a jour de l'utilisateur, aucune ligne mise a jour dans la table." );
            }
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    

    private static final String SQL_RECHERCHE_UTILISATEUR_PAR_COUR = "SELECT numUtilisateur, nomUtilisateur, hash, nom, prenom, email, role, annee, derniereConnexion FROM Utilisateur INNER JOIN Assiste on Assiste.user = Utilisateur.numUtilisateur INNER JOIN Cours on Cours.numCours = Assiste.cours WHERE Cours.numCours = ? AND Utilisateur.role = \"Etudiant\"";
    
    @Override
    public ArrayList<Utilisateur> listerUtilisateurCours(Cour cours) throws SQLException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	ArrayList<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
    	
    	try {
    		connexion = daoFactory.getConnection();
    		preparedStatement = initialisationRequetePreparee( connexion, SQL_RECHERCHE_UTILISATEUR_PAR_COUR, false, cours.getNumCour());
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next() ) {
    			listeUtilisateur.add(map( resultSet ));
    		}
    	} finally {
    		fermeturesSilencieuses( resultSet, preparedStatement, connexion );
    	}
    	return listeUtilisateur;
    }
    
    
    private static final String SQL_RECHERCHE_REFERENT_COUR= "SELECT numUtilisateur, nomUtilisateur, hash, nom, prenom, email, role, annee, derniereConnexion FROM Utilisateur INNER JOIN Assiste WHERE Assiste.user = Utilisateur.numUtilisateur AND Utilisateur.role = \"Referent\" AND Assiste.cours = ?";
    
    @Override
    public ArrayList<Utilisateur> getReferent(Cour cours) throws SQLException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	ArrayList<Utilisateur> listeReferent = new ArrayList<Utilisateur>();
    	
    	try {
    		connexion = daoFactory.getConnection();
    		preparedStatement = initialisationRequetePreparee( connexion,  SQL_RECHERCHE_REFERENT_COUR, false, cours.getNumCour());
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next() ) {
    			listeReferent.add( map( resultSet ));
    		}
    	} finally {
    		fermeturesSilencieuses( resultSet, preparedStatement, connexion );
    	}
    	return listeReferent;
    }
    
    
    private static final String SQL_SELECT_REFERENT = "SELECT numUtilisateur, nomUtilisateur, hash, nom, prenom, email, role, annee, derniereConnexion FROM Utilisateur WHERE role = \"Referent\"";
    
    @Override
    public ArrayList<Utilisateur> listerReferent() throws SQLException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_REFERENT, false);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                utilisateurs.add(map( resultSet ));
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return utilisateurs;
    }
    
    private static final String SQL_SELECT_UTILISATEUR_VM = "SELECT numUtilisateur, nomUtilisateur, hash, nom, prenom, email, role, annee, Utilisateur.derniereConnexion FROM Utilisateur INNER JOIN Appartient on Utilisateur.numUtilisateur = Appartient.user INNER JOIN MachineVirtuel vm on vm.numVM = Appartient.machineVirtuel WHERE vm.numVM = ?";
    
    @Override
    public Utilisateur getUserVm(VirtualMachine vm) throws SQLException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_UTILISATEUR_VM, false, vm.getNumVm());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                utilisateur = map(resultSet);
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return utilisateur;
    }
    
    
    private static final String SQL_RECHERCHE_REFERENT_ETUDIANT= "SELECT * FROM Utilisateur WHERE role = \"Referent\" AND numUtilisateur IN (SELECT user FROM Assiste WHERE cours IN (SELECT cours FROM Assiste WHERE user = ?))";
    
    @Override
    public ArrayList<Utilisateur> getReferentUser(Utilisateur utilisateur) throws SQLException {
    	Connection connexion = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	ArrayList<Utilisateur> listeReferent = new ArrayList<Utilisateur>();
    	
    	try {
    		connexion = daoFactory.getConnection();
    		preparedStatement = initialisationRequetePreparee( connexion,  SQL_RECHERCHE_REFERENT_ETUDIANT, false, utilisateur.getNumUtilisateur());
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next() ) {
    			listeReferent.add( map( resultSet ));
    		}
    	} finally {
    		fermeturesSilencieuses( resultSet, preparedStatement, connexion );
    	}
    	return listeReferent;
    }
    
    private static Utilisateur map( ResultSet resultSet ) throws SQLException {
        Utilisateur utilisateur = new Utilisateur(resultSet.getLong("numUtilisateur"), resultSet.getString("nomUtilisateur"), resultSet.getString("hash"), resultSet.getString("nom"),
        		resultSet.getString("prenom"), resultSet.getString("email"), resultSet.getString("role"), resultSet.getString("annee"), resultSet.getString("derniereConnexion"));
        return utilisateur;
    }
}