package fr.eseo.cc3.dao.implementation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.eseo.cc3.dao.bean.TestServeur;

@RunWith(Suite.class)
@SuiteClasses({ TestCourDaoImpl.class, TestUtilisateurDaoImpl.class, TestVirtualMachineDaoImpl.class, TestServeur.class })
public class AllTests {

}
