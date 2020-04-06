package fr.eseo.cc3.model.exception;

/**
 * @author Alexis Lefort
 *
 */
public class ExceptionConnectionVSphere extends Exception {

	private final int codeErreur;
	private final String action;
	
	public ExceptionConnectionVSphere(String message,int codeErreur,String action) {
		super(message);
		this.codeErreur=codeErreur;
		this.action=action;
	}
	
	
	

	public ExceptionConnectionVSphere(String message) {
		super(message);
		this.codeErreur=1000;//code erreur par défaut
		this.action="erreur interne";
	}




	public int getCodeErreur() {
		return codeErreur;
	}




	public String getAction() {
		return action;
	}

	
}
