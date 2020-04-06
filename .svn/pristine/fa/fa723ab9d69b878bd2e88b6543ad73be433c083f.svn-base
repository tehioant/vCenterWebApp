package fr.eseo.cc3.hash;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

public class TestHashage {

	@Test
	public void testHashageValidee() {
		String hash = "";
		String passWord = "motDePasse";
		Boolean validation = false;
		try {
			hash = Hashage.generatePasswordHash(passWord);
			validation = Hashage.validatePassword(passWord, hash);
		}
		catch(NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		catch( InvalidKeySpecException f) {
			System.out.println(f);
		}
		assertEquals(validation, true);
	}
	
	
	@Test
	public void testHashageNonValidee() {
		String hash = "";
		String passWord = "motDePasse";
		Boolean validation = true;
		try {
			hash = Hashage.generatePasswordHash(passWord);
			validation = Hashage.validatePassword("autreMotDePasse", hash);
		}
		catch(NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		catch( InvalidKeySpecException f) {
			System.out.println(f);
		}
		assertEquals(validation, false);
	}

}
