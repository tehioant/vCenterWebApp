package fr.eseo.cc3.model.vm;




/**
 * 
 * @author CC groupe 3
 *
 * La classe Memory est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 *
 */
public class Memory {
	int size_MiB;
	boolean hot_add_enabled;
	
	
	/**
	 * Constructeur de la classe Memory.
	 * 
	 * @param size [type: int]
	 * */
	public Memory(int size) {
		this.size_MiB = size;
	}
	
	
	/**
	 * Constructeur par defaut de la classe Memory.
	 * Le constructeur ne requiert aucun parametre
	 * 
	 * */
	public Memory() {
		this(0);
	}
	
	
	 /**
		 * Accesseur getSize_MiB() recupere le size_MiB du Memory.
		 * 
		 * @return [type: String] size_MiB du Memory.
		 * */
	public int getSize_MiB() {
		return this.size_MiB;
	}
	 /**
		 * Accesseur setSize_MiB() definis le size_MiB du Memory.
		 * 
		 * @param [type: String] size_MiB du Memory.
		 * */
	public void setSize_MiB(int size_MiB) {
		this.size_MiB = size_MiB;
	}
	
	
	 /**
		 * Accesseur getHot_add_enabled() recupere le hot_add_enabled du Memory.
		 * 
		 * @return [type: String] hot_add_enabled du Memory.
		 * */
	public boolean getHot_add_enabled() {
		return this.hot_add_enabled;
	}
	 /**
		 * Accesseur setHot_add_enabled() definis le hot_add_enabled du Memory.
		 * 
		 * @param [type: String] hot_add_enabled du Memory.
		 * */
	public void setHot_add_enabled(boolean b) {
		this.hot_add_enabled = b;
	}

}
