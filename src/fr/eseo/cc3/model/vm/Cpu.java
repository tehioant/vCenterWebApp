package fr.eseo.cc3.model.vm;



/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe {@link fr.eseo.cc3.model.vm.Cpu.java} est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 */
public class Cpu {
	
	
	
	
	private boolean hot_remove_enabled;
	private boolean hot_add_enabled;
	private int count;
	private int cores_per_socket;
	
	
	/**
	 *
	 * Constructeur de la classe {@link fr.eseo.cc3.model.vm.Cpu.java}.
	 * 
	 * @param count [type: int]
	 *
	 * */
	public Cpu(int count) {
		this.count = count;
	}
	
	/**
	 *
	 * Constructeur par default de la classe {@link fr.eseo.cc3.model.vm.Cpu.java}.
	 *
	 * */
	public Cpu() {
		this(0);
	}
	
	
	/**
	 * Accesseur getHot_remove_enabled() recupere hot_remove_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: boolean] hot_remove_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java}
	 * */
	public boolean getHot_remove_enabled() {
		return this.hot_remove_enabled;
	}
	
	/**
	 * Accesseur setHot_remove_enabled() definis hot_remove_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: boolean] hot_remove_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java} à definir
	 * */
	public void setHot_remove_enabled(boolean hot_remove_enabled) {
		this.hot_remove_enabled = hot_remove_enabled;
	}
	
	/**
	 * Accesseur getHot_add_enabled() recupere hot_add_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: boolean] hot_add_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java}
	 * */
	public boolean getHot_add_enabled() {
		return this.hot_add_enabled;
	}
	
	/**
	 * Accesseur setHot_add_enabled() definis hot_add_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: String] hot_add_enabled de {@link fr.eseo.cc3.model.vm.CDroms.java} à definir
	 * */
	public void setHot_add_enabled(boolean b) {
		this.hot_add_enabled = b;
	}
	
	/**
	 * Accesseur getCount() recupere la count de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: int] count de {@link fr.eseo.cc3.model.vm.CDroms.java} à definir
	 * */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * Accesseur setCount() definis count de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: int] count de {@link fr.eseo.cc3.model.vm.CDroms.java}
	 * */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Accesseur getCores_per_socket() recupere cores_per_socket de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: int] cores_per_socket de {@link fr.eseo.cc3.model.vm.CDroms.java}
	 * */
	public int getCores_per_socket() {
		return this.cores_per_socket;
	}
	
	/**
	 * Accesseur setCores_per_socket() definis cores_per_socket de {@link fr.eseo.cc3.model.vm.CDroms.java}.
	 * 
	 * @param [type: int] cores_per_socket de {@link fr.eseo.cc3.model.vm.CDroms.java} à definir
	 * */
	public void setCores_per_socket(int cores_per_socket) {
		this.cores_per_socket = cores_per_socket;
	}
}
