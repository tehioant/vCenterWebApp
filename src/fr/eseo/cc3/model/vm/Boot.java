
package fr.eseo.cc3.model.vm;




/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe Boot est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 */
public class Boot {

	
	
	
    private Integer delay;
    private Integer retryDelay;
    private boolean enterSetupMode;
    private String type;
    private boolean retry;
    
    
    
    /**
	 * Accesseur getName() recupere le nom de Boot.
	 * 
	 * @return [type: String] name de Host
	 * */
    public Integer getDelay() {
        return delay;
    }

    
    /**
	 * Accesseur setDelay() recupere le delay de Boot.
	 * 
	 * @param [type: String] delay de Host
	 * */
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    /**
	 * Accesseur getName() recupere le nom de Boot.
	 * 
	 * @return [type: String] name de Host
	 * */
    public Integer getRetryDelay() {
        return this.retryDelay;
    }

    /**
	 * Accesseur setDelay() recupere le delay de Boot.
	 * 
	 * @param [type: String] delay de Host
	 * */
    public void setRetryDelay(Integer retryDelay) {
        this.retryDelay = retryDelay;
    }

    /**
	 * Accesseur getEnterSetupMode() recupere le setup mode de Host.
	 * 
	 * @return [type: String] enterSetupMode de Host
	 * */
    public boolean getEnterSetupMode() {
        return enterSetupMode;
    }

    /**
	 * Accesseur setEnterSetupMode() recupere le setup mode de Boot.
	 * 
	 * @param [type: Boolean] setup mode de Host
	 * */
    public void setEnterSetupMode(boolean b) {
        this.enterSetupMode = b;
    }

    /**
   	 * Accesseur getType() recupere le type de Boot.
   	 * 
   	 * @return [type: String] type de Host
   	 * */
    public String getType() {
        return type;
    }

    /**
	 * Accesseur setType() recupere le type de Boot.
	 * 
	 * @param [type: String] type de Host
	 * */
    public void setType(String type) {
        this.type = type;
    }

    /**
   	 * Accesseur getRetry() recupere le retry de Boot.
   	 * 
   	 * @return [type: String] retry de Host
   	 * */
    public boolean getRetry() {
        return retry;
    }

    /**
	 * Accesseur setRetry() recupere le retry de Boot.
	 * 
	 * @param [type: Boolean] retry de Host
	 * */
    public void setRetry(boolean b) {
        this.retry = b;
    }

}
