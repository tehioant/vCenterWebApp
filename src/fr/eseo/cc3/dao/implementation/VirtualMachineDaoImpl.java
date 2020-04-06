package fr.eseo.cc3.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;
import static fr.eseo.cc3.dao.DAOUtilitaire.*;

public class VirtualMachineDaoImpl implements VirtualMachineDao{
private DAOFactory daoFactory;
	
    public VirtualMachineDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    private static final String SQL_SELECT_PAR_REFVM = "SELECT numVm, nomVm, refVm, serveur, dateCreation, dateRendu, derniereConnexion, template FROM MachineVirtuel WHERE refVm = ? AND serveur = ?";

    @Override
    public VirtualMachine trouver( String refVM, int serveur ) throws  SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        VirtualMachine vm = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_REFVM, false, refVM, serveur );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                vm = map( resultSet );
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return vm;
    }

    
    private static final String SQL_SELECT_ALL = "SELECT numVm, nomVm, refVm, serveur, dateCreation, dateRendu, derniereConnexion, template FROM MachineVirtuel WHERE serveur = ?";
    
    @Override
    public ArrayList<VirtualMachine> lister(int serveur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<VirtualMachine> vm = new ArrayList<VirtualMachine>();
        
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false, serveur);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                vm.add(map( resultSet ));
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return vm;
    }
    
    
    private static final String SQL_SELECT_TEMPLATE = "SELECT numVm, nomVm, refVm, serveur, dateCreation, dateRendu, derniereConnexion, template FROM MachineVirtuel WHERE serveur = ? AND template = 'true'";
    
    @Override
    public ArrayList<VirtualMachine> listeTemplate(int serveur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<VirtualMachine> vm = new ArrayList<VirtualMachine>();
        
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_TEMPLATE, false, serveur);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                vm.add(map( resultSet ));
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return vm;
    }
    
    
    private static final String SQL_INSERT_VM = "INSERT INTO MachineVirtuel (nomVm, refVm, serveur, dateCreation, dateRendu, derniereConnexion, template) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    @Override
    public void creer( VirtualMachine vm ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_VM, true, vm.getNomVm(), vm.getRefVm(), vm.getServeur(), vm.getDateCreation(), vm.getDateRendu(), vm.getDerniereConnexion(), vm.getTemplate());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la création de la vm, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                vm.setNumVm( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new SQLException( "Échec de la création de la vm en base, aucun ID auto-généré retourné." );
            }
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    
    private static final String SQL_DELETE_VM = "DELETE FROM MachineVirtuel WHERE refVm = ? AND serveur=?;";

    @Override
    public void supprimer( String refVm, int serveur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_VM, true, refVm,serveur);
            resultSet = preparedStatement.executeQuery();
       
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    
    private static final String SQL_UPDATE = "UPDATE MachineVirtuel SET numVm = ?, nomVm = ?, refVm = ?, serveur = ?, dateCreation = ?, dateRendu = ?, derniereConnexion = ?, template = ? WHERE numVm = ?";

    @Override
    public void modifier( VirtualMachine vm ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, vm.getNumVm(), vm.getNomVm(), vm.getRefVm(), vm.getServeur(), vm.getDateCreation(), vm.getDateRendu(), vm.getDerniereConnexion(), vm.getTemplate(), vm.getNumVm());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la mise a jour de la vm, aucune ligne mise a jour dans la table." );
            }

        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    
    private static final String SQL_RECHERCHE_VM = "SELECT numVm, nomVm, refVm, serveur, dateCreation, dateRendu, derniereConnexion, template FROM MachineVirtuel WHERE numVm IN (SELECT machineVirtuel FROM Appartient WHERE user = ?) AND serveur = ?";
    
    @Override
    public ArrayList<VirtualMachine> getUserVm(Utilisateur user, int serveur) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<VirtualMachine> vm = new ArrayList<VirtualMachine>();
        
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_RECHERCHE_VM, false, user.getNumUtilisateur(), serveur);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                vm.add(map( resultSet ));
            }

        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return vm;
    }
    
    
    private static final String SQL_RECHERCHE_VM_PAR_COUR = "SELECT numVm, nomVm, refVm, serveur, dateCreation, dateRendu, derniereConnexion, template FROM MachineVirtuel WHERE numVm IN (SELECT machineVirtuel FROM VmToCour WHERE cours = ?)";
    
    @Override
    public ArrayList<VirtualMachine> getVmCour(Cour cour) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<VirtualMachine> vm = new ArrayList<VirtualMachine>();
        
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_RECHERCHE_VM_PAR_COUR, false, cour.getNumCour());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next() ) {
                vm.add(map( resultSet ));
            }
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return vm;
    }
    
    
    private static final String SQL_INSERT_UTILISATEUR = "INSERT INTO Appartient (machineVirtuel, user) VALUES (?, ?)";
    
    @Override
    public void setUtilisateur( VirtualMachine vm, Utilisateur utilisateur ) throws SQLException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_UTILISATEUR, true, vm.getNumVm(), utilisateur.getNumUtilisateur());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new SQLException( "Échec de la créationd e l'appartenance, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
    
    
    private static final String SQL_DELETE_APPARTENANCE = "DELETE FROM Appartient WHERE machineVirtuel = ? and user = ?;";

    @Override
    public void deleteUtilisateur( VirtualMachine vm, Utilisateur referent ) throws SQLException  {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_APPARTENANCE, true, vm.getNumVm(), referent.getNumUtilisateur());
            resultSet = preparedStatement.executeQuery();
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    
    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static VirtualMachine map( ResultSet resultSet ) throws SQLException {
        VirtualMachine vm = new VirtualMachine(resultSet.getLong("numVM"), resultSet.getString("nomVm"), resultSet.getString("refVm"), resultSet.getInt("serveur"), resultSet.getString("dateCreation"), resultSet.getString("dateRendu"), resultSet.getString("derniereConnexion"),  resultSet.getString("template"));
        return vm;
    }
}
