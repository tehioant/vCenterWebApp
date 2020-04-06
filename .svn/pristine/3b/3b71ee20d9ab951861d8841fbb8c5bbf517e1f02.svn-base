package fr.eseo.cc3.dao.implementation;

import static fr.eseo.cc3.dao.DAOUtilitaire.fermeturesSilencieuses;
import static fr.eseo.cc3.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.IsoDao;
import fr.eseo.cc3.dao.bean.Iso;

public class IsoDaoImpl implements IsoDao {
	private DAOFactory daoFactory;

	public IsoDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_SELECT_OS_PAR_SERVEUR = "SELECT DISTINCT os FROM `iso` WHERE numServeur= ? ";
	
	@Override
	public ArrayList<String> getListOsServeur(int numServeur) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> listeOs = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_OS_PAR_SERVEUR, false, numServeur);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				listeOs.add( resultSet.getString("os"));
			}
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return listeOs;
	}
	
	private static final String SQL_SELECT_ISO_PAR_OS = "SELECT DISTINCT nomIso FROM `iso` WHERE numServeur= ? AND os =? ";
	
	@Override
	public ArrayList<String> getListIsoOs(int numServeur, String os) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> listeIso = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ISO_PAR_OS, false, numServeur,os);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				listeIso.add( resultSet.getString("nomIso"));
			}
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return listeIso;
	}

	private static final String SQL_SELECT_ISO = "SELECT keyIso,nomIso,chemin,numServeur,os FROM `iso` WHERE numServeur= ? AND nomIso=? AND chemin like ? ";	
	
	@Override
	public Iso getIso(int numServeur, String nomIso, String nomDatastore) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Iso iso = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ISO, false, numServeur,nomIso,"%"+nomDatastore+"%");
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				iso=this.map(resultSet);
			}
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return iso;
	}
	private Iso map(ResultSet resultSet) throws SQLException {
		return new Iso(resultSet.getInt("keyIso")
				,resultSet.getString("nomIso")
				, resultSet.getString("chemin")
				, resultSet.getInt("numServeur")
				,resultSet.getString("os")
				);
	}




}
