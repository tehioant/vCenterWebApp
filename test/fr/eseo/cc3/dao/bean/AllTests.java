package fr.eseo.cc3.dao.bean;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestVirtualMachine.class, TestCour.class, TestUtilisateur.class, TestServeur.class })
public class AllTests {

}
