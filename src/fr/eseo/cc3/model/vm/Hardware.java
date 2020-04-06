
package fr.eseo.cc3.model.vm;

/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe Hardware est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 *
 *
 *
 */

public class Hardware {

    private String upgradePolicy;
    private String upgradeStatus;
    private String version;
    
    
    /**
	 * Constructeur de la classe Hardware.
	 * Le constructeur ne requiert aucun parametre
	 * 
	 * */
    public Hardware() {
    	
    }
    
    
    //Accesseurs
    /**
	 * Accesseur getUpgradePolicy() recupere le upgradePolicy du Hardware.
	 * 
	 * @return [type: String] upgradePolicy du Hardware.
	 * */
    public String getUpgradePolicy() {
        return this.upgradePolicy;
    }
    /**
	 * Accesseur setUpgradePolicy() definis le upgradePolicy du Hardware.
	 * 
	 * @param [type: String] upgradePolicy du Hardware.
	 * */
    public void setUpgradePolicy(String upgradePolicy) {
        this.upgradePolicy = upgradePolicy;
    }

    
    
    /**
	 * Accesseur getUpgradeStatus() recupere le upgradeStatus du Hardware.
	 * 
	 * @return [type: String] upgradeStatus du Hardware.
	 * */
    public String getUpgradeStatus() {
        return this.upgradeStatus;
    }
    /**
	 * Accesseur setUpgradeStatus() definis le upgradeStatus du Hardware.
	 * 
	 * @param [type: String] upgradeStatus du Hardware.
	 * */
    public void setUpgradeStatus(String upgradeStatus) {
        this.upgradeStatus = upgradeStatus;
    }

    
    
    /**
	 * Accesseur getVersion() recupere la version du Hardware.
	 * 
	 * @return [type: String] version du Hardware.
	 * */
    public String getVersion() {
        return this.version;
    }
    /**
	 * Accesseur setVersion() definis la version du Hardware.
	 * 
	 * @param [type: String] version du Hardware.
	 * */
    public void setVersion(String version) {
        this.version = version;
    }

}
