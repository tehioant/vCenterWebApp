package fr.eseo.cc3.json;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import fr.eseo.cc3.model.DataStore;
import fr.eseo.cc3.model.Host;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.vm.Boot;
import fr.eseo.cc3.model.vm.CDroms;
import fr.eseo.cc3.model.vm.Cpu;
import fr.eseo.cc3.model.vm.Disk;
import fr.eseo.cc3.model.vm.Floppies;
import fr.eseo.cc3.model.vm.Hardware;
import fr.eseo.cc3.model.vm.Memory;
import fr.eseo.cc3.model.vm.Nics;
import fr.eseo.cc3.model.vm.SataAdapters;
import fr.eseo.cc3.model.vm.ScsiAdapters;


public class JsonReader {


	int VALEUR_PAR_DEFAUT_INT = 0;
	long VALEUR_PAR_DEFAUT_LONG = 0;
	String VALEUR_PAR_DEFAUT_STRING = "none";
	JsonElement VALEUR_PAR_DEFAUT_JSONELEMENT = null;
	JSONArray VALEUR_PAR_DEFAUT_JSONARRAY = new JSONArray();
	JSONObject VALEUR_PAR_DEFAUT_OBJECT = new JSONObject();

	String CPU = "cpu";
	String NAME = "name";
	String SPEC = "spec";
	String HOST = "host";
	String OS = "guest_OS";
	String VALUE = "value";
	String LABEL = "label";
	String STATE = "state";
	String COUNT = "count";
	String FOLDER = "folder";
	String MEMORY = "memory";
	String BACKING = "backing";
	String CLUSTER = "cluster";
	String CAPACITY = "capacity";
	String SIZE_MIB = "size_MiB";
	String DATASTORE = "datastore";
	String PLACEMENT = "placement";
	String FREE_SPACE = "free_space";
	String RESOURCE = "resource_pool";
	String POWER_STATE = "power_state";
	String SATA_ADAPTERS = "sata_adapters";
	String START_CONNECTED = "start_connected";
	String HOT_ADD_ENABLED = "hot_add_enabled";
	String CORES_PER_SOCKET = "cores_per_socket";
	String HOT_REMOVE_ENABLED = "hot_remove_enabled";
	String ALLOW_GUEST_CONTROL = "allow_guest_control";

	public JsonReader() {


	}


	// Créer GSON Builder
	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.create();

	// créer JSONOBJECT
	JsonParser parser = new JsonParser();
	JsonElement jsonElement;



	/**
	 *
	 * Permet de récuperer la liste des datastores.
	 * 
	 * @param stream [type: String] contient les infos de la requete /rest/vcenter/datastore
	 * @return ArrayList de datastores {@link fr.eseo.cc3.model.DataStore}
	 *
	 * */

	// /rest/vcenter/datastore
	public ArrayList<DataStore> getListDataStore(String stream) {

		JSONArray array;
		String name;
		String datastore;
		String type;
		long free_space;
		long capacity;
		// Initialisation de la liste
		ArrayList<DataStore> list = new ArrayList<DataStore>(); // Liste des DataCenters

		// Transformation stream
		JSONObject object = new JSONObject(stream);
		try {
			array = object.getJSONArray(VALUE); }
		catch(final Exception e) {
			array = VALEUR_PAR_DEFAUT_JSONARRAY; }


		JSONObject jsonObj;
		// Traitement pour chaque DataStore
		for(int i = 0; i < array.length(); i++) {

			try {
				jsonObj = array.getJSONObject(i); }
			catch(final Exception e) {
				jsonObj = VALEUR_PAR_DEFAUT_OBJECT; }

			// Creation de l'objet DataStore
			try {
				name = jsonObj.getString("name"); }
			catch(final Exception e) {
				name = VALEUR_PAR_DEFAUT_STRING; }

			try {
				datastore = jsonObj.getString(DATASTORE); }
			catch(final Exception e) {
				datastore = VALEUR_PAR_DEFAUT_STRING; }

			try {
				type = jsonObj.getString("type"); }
			catch(final Exception e) {
				type = VALEUR_PAR_DEFAUT_STRING; }

			try {
				free_space = jsonObj.getLong(FREE_SPACE); }
			catch(final Exception e) {
				free_space = VALEUR_PAR_DEFAUT_LONG; }

			try {
				capacity = jsonObj.getLong(CAPACITY); }
			catch(final Exception e) {
				capacity = VALEUR_PAR_DEFAUT_LONG; }


			DataStore ds = new DataStore(datastore, name, type, free_space, capacity );
			list.add(ds);
		}

		return list;

	}




	/**
	 *
	 * Permet de récuperer la liste des Hosts
	 * 
	 * @param stream [type: String] contient les infos de la requete /rest/vcenter/host
	 * @return ArrayList de hosts { @link fr.eseo.cc3.model.Host}
	 *
	 * */


	// /rest/vcenter/host
	public ArrayList<Host> getListHosts(String stream) {


		JSONArray array;
		String host;
		String connection_state;
		String name;
		String power_state;

		// Initialisation de la liste
		ArrayList<Host> list = new ArrayList<Host>(); // Liste des DataCenters

		// Transformation stream
		JSONObject object = new JSONObject(stream);
		try {
			array = object.getJSONArray(VALUE); }
		catch(final Exception e) {
			array = VALEUR_PAR_DEFAUT_JSONARRAY; }

		JSONObject jsonObj;
		// Traitement pour chaque host
		for(int i = 0; i < array.length(); i++) {

			try {
				jsonObj = array.getJSONObject(i); }
			catch(final Exception e) {
				jsonObj = VALEUR_PAR_DEFAUT_OBJECT; }

			// Creation de l'objet host
			try {
				name = jsonObj.getString("name"); }
			catch(final Exception e) {
				name = VALEUR_PAR_DEFAUT_STRING; }

			try {
				host = jsonObj.getString("host"); }
			catch(final Exception e) {
				host = VALEUR_PAR_DEFAUT_STRING; }

			try {
				connection_state = jsonObj.getString("connection_state"); }
			catch(final Exception e) {
				connection_state = VALEUR_PAR_DEFAUT_STRING; }

			try {
				power_state = jsonObj.getString(POWER_STATE); }
			catch(final Exception e) {
				power_state = VALEUR_PAR_DEFAUT_STRING; }


			Host hostMain = new Host(host, name, connection_state, power_state);
			list.add(hostMain);
		}

		return list;
	}






	/**
	 *
	 * Permet de récuperer la liste des Machines virtuelles
	 * 
	 * @param stream [type: String] contient les infos de la requete /rest/vcenter/vm
	 * @return ArrayList de Machines virtuelles { @link fr.eseo.cc3.model.VirtualMachine}
	 *
	 * */

	// /rest/vcenter/vm
	public ArrayList<VirtualMachine> getListVirtualMachine(String stream) {


		JSONArray array;
		int memory_size_MiB;
		String vm;
		String name;
		String power_state;
		int cpu_count;

		// Initialisation de la liste
		ArrayList<VirtualMachine> list = new ArrayList<VirtualMachine>(); // Liste des DataCenters

		// Transformation stream
		JSONObject object = new JSONObject(stream);
		try {
			array = object.getJSONArray(VALUE);  }
		catch(final Exception e) {
			array = VALEUR_PAR_DEFAUT_JSONARRAY; }


		JSONObject jsonObj;
		// Traitement pour chaque VM
		for(int i = 0; i < array.length(); i++) {

			try {
				jsonObj = array.getJSONObject(i); }
			catch(final Exception e) {
				jsonObj = VALEUR_PAR_DEFAUT_OBJECT; }

			// Creation de l'objet VM
			VirtualMachine virtualMachine = new VirtualMachine();
			try {
				name = jsonObj.getString("name"); }
			catch(final Exception e) {
				name = VALEUR_PAR_DEFAUT_STRING; }

			try {
				memory_size_MiB = jsonObj.getInt("memory_size_MiB"); }
			catch(final Exception e) {
				memory_size_MiB = VALEUR_PAR_DEFAUT_INT; }

			try {
				cpu_count = jsonObj.getInt("cpu_count"); }
			catch(final Exception e) {
				cpu_count = VALEUR_PAR_DEFAUT_INT; }

			try {
				power_state = jsonObj.getString(POWER_STATE); }
			catch(final Exception e) {
				power_state = VALEUR_PAR_DEFAUT_STRING; }

			try {
				vm = jsonObj.getString("vm"); }
			catch(final Exception e) {
				vm = VALEUR_PAR_DEFAUT_STRING; }


			virtualMachine.setMemorySizeMIB(new Memory(memory_size_MiB));
			virtualMachine.setVm(vm);
			virtualMachine.setName(name);
			virtualMachine.setPowerState(power_state);
			virtualMachine.setCpu(new Cpu(cpu_count));
			list.add(virtualMachine);
		}

		return list;
	}


	
	/**
	 *
	 * Permet de récuperer les details d'une machine virtuelle Machines virtuelles
	 * 
	 * @param stream [type: String] contient les infos de la requete "/rest/vcenter/vm"
	 * @return { @link fr.eseo.cc3.model.VirtualMachine}
	 *
	 * */


	// /rest/vcenter/vm
	public VirtualMachine getVirtualMachineDetails(String stream) {


		// Initialisation variables
		VirtualMachine vm = new VirtualMachine();

		JSONObject array;
		JSONObject cpus;

		CDroms cdrom = new CDroms();
		Memory ram = new Memory();
		Disk disk = new Disk();
		SataAdapters sata = new SataAdapters();
		Cpu cpu= new Cpu();
		ScsiAdapters scsi = new ScsiAdapters();
		Floppies floppies = new Floppies();
		Nics nics = new Nics();
		Boot boot = new Boot();
		Hardware hardware = new Hardware();

		// Transformation stream
		JSONObject obj2 = new JSONObject(stream);
		try {
			array = obj2.getJSONObject(VALUE); }
		catch(final Exception e) {
			array = VALEUR_PAR_DEFAUT_OBJECT; }


		// CDrom
		JSONArray cdrom_v;
		JSONObject value;
		JSONObject cdrom_obj;
		try {
			cdrom_v = array.getJSONArray("cdroms"); }
		catch(final Exception e) {
			cdrom_v = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			cdrom_obj = cdrom_v.getJSONObject(0); }
		catch(final Exception e) {
			cdrom_obj = VALEUR_PAR_DEFAUT_OBJECT; }

		try {
			value = cdrom_obj.getJSONObject(VALUE); }
		catch(final Exception e) {
			value = VALEUR_PAR_DEFAUT_OBJECT; }


		// Set values of CDrom
		try {
			cdrom.setBacking_device_access_type(value.getJSONObject(BACKING).getString("device_access_type")); }
		catch(final Exception e) {
			cdrom.setBacking_device_access_type(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setBacking_type(value.getJSONObject(BACKING).getString("type")); }
		catch(final Exception e) {
			cdrom.setBacking_type(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setLabel(value.getString(LABEL)); }
		catch(final Exception e) {
			cdrom.setLabel(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setState(value.getString(STATE));}
		catch(final Exception e) {
			cdrom.setLabel(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setType(value.getString("type")); }
		catch(final Exception e) {
			cdrom.setState(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setSata_bus(value.getString("sata_bus"));}
		catch(final Exception e) {
			cdrom.setSata_bus(VALEUR_PAR_DEFAUT_STRING);}

		try {
			cdrom.setSata_unit(value.getString("sata_unit"));}
		catch(final Exception e) {
			cdrom.setSata_unit(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setKey(cdrom_obj.getString("key")); }
		catch(final Exception e) {
			cdrom.setKey(VALEUR_PAR_DEFAUT_STRING); }

		try {
			cdrom.setStart_connected(value.getBoolean(START_CONNECTED));}
		catch(final Exception e) {
			cdrom.setStart_connected(false); }

		try {
			cdrom.setAllow_guest_control(value.getBoolean(ALLOW_GUEST_CONTROL));}
		catch(final Exception e) {
			cdrom.setAllow_guest_control(false); }


		vm.setCdroms(cdrom);

		// RAM
		JSONObject memory;
		try {
			memory = array.getJSONObject(MEMORY); }
		catch(final Exception e) {
			memory = VALEUR_PAR_DEFAUT_OBJECT; }


		// Set values of memory
		try {
			ram.setSize_MiB(memory.getInt(SIZE_MIB)); }
		catch(final Exception e) {
			ram.setSize_MiB(VALEUR_PAR_DEFAUT_INT); }

		ram.setHot_add_enabled(memory.getBoolean(HOT_ADD_ENABLED));

		vm.setMemorySizeMIB(ram);

		// Disk
		JSONArray disks;
		JSONObject disks_obj;
		try {
			disks = array.getJSONArray("disks"); }
		catch(final Exception e) {
			disks = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			disks_obj = disks.getJSONObject(0); }
		catch(final Exception e) {
			disks_obj = VALEUR_PAR_DEFAUT_OBJECT; }

		try {
			value = disks_obj.getJSONObject(VALUE); }
		catch(final Exception e) {
			value = VALEUR_PAR_DEFAUT_OBJECT; }


		// set values of disks
		try {
			disk.setBacking_type(value.getJSONObject(BACKING).getString("type")); }
		catch(final Exception e) {
			disk.setBacking_type(VALEUR_PAR_DEFAUT_STRING); }

		try {
			disk.setBacking_vmdk_file(value.getJSONObject(BACKING).getString("vmdk_file")); }
		catch(final Exception e) {
			disk.setBacking_vmdk_file(VALEUR_PAR_DEFAUT_STRING); }

		try {
			disk.setCapacity(value.getInt(CAPACITY)); }
		catch(final Exception e) {
			disk.setCapacity(VALEUR_PAR_DEFAUT_LONG); }

		try {
			disk.setKey(disks_obj.getInt("key")); }
		catch(final Exception e) {
			disk.setKey(VALEUR_PAR_DEFAUT_INT); }

		try {
			disk.setLabel(value.getString(LABEL)); }
		catch(final Exception e) {
			disk.setLabel(VALEUR_PAR_DEFAUT_STRING); }

		try {
			disk.setScsi_bus(value.getJSONObject("scsi").getInt("bus")); }
		catch(final Exception e) {
			disk.setScsi_bus(VALEUR_PAR_DEFAUT_INT); }

		try {
			disk.setScsi_unit(value.getJSONObject("scsi").getInt("unit")); }
		catch(final Exception e) {
			disk.setScsi_unit(VALEUR_PAR_DEFAUT_INT); }

		try {
			disk.setType(value.getString("type")); }
		catch(final Exception e) {
			disk.setType(VALEUR_PAR_DEFAUT_STRING); }


		vm.setDisk(disk);


		//parallel ports unknown //TODO parallel ports

		// Sata_Adapters
		JSONArray satas;
		JSONObject sata_obj;
		try {
			satas = array.getJSONArray(SATA_ADAPTERS); }
		catch(final Exception e) {
			satas = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			sata_obj = satas.getJSONObject(0); }
		catch(final Exception e) {
			sata_obj = VALEUR_PAR_DEFAUT_OBJECT; }

		try {
			value = sata_obj.getJSONObject(VALUE); }
		catch(final Exception e) {
			value = VALEUR_PAR_DEFAUT_OBJECT; }


		// set values of disks
		try {
			sata.setKey(sata_obj.getString("key")); }
		catch(final Exception e) {
			sata.setKey(VALEUR_PAR_DEFAUT_STRING); }

		try {
			sata.setLabel(value.getString(LABEL)); }
		catch(final Exception e) {
			sata.setLabel(VALEUR_PAR_DEFAUT_STRING); }

		try {
			sata.setBus(value.getInt("bus")); }
		catch(final Exception e) {
			sata.setBus(VALEUR_PAR_DEFAUT_INT); }

		try {
			sata.setType(value.getString("type")); }
		catch(final Exception e) {
			sata.setType(VALEUR_PAR_DEFAUT_STRING); }


		vm.setSata(sata);
		//CPU
		try {
			cpus = array.getJSONObject("cpu"); }
		catch(final Exception e) {
			cpus = VALEUR_PAR_DEFAUT_OBJECT; }


		//Set values of CPU
		try {
			cpu.setCores_per_socket(cpus.getInt(CORES_PER_SOCKET)); }
		catch(final Exception e) {
			cpu.setCores_per_socket(VALEUR_PAR_DEFAUT_INT); }

		try {
			cpu.setCount(cpus.getInt(COUNT)); }
		catch(final Exception e) {
			cpu.setCount(VALEUR_PAR_DEFAUT_INT); }

		try {
			cpu.setCount(cpus.getInt(COUNT)); }
		catch(final Exception e) {
			cpu.setCount(VALEUR_PAR_DEFAUT_INT); }

		try {
			cpu.setCount(cpus.getInt(COUNT)); }
		catch(final Exception e) {
			cpu.setCount(VALEUR_PAR_DEFAUT_INT);}


		vm.setCpu(cpu);



		// SCSI Adapters
		JSONArray scsia;
		JSONObject scsia_obj;
		try {
			scsia = array.getJSONArray(SATA_ADAPTERS); }
		catch(final Exception e) {
			scsia = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			scsia_obj = scsia.getJSONObject(0); }
		catch(final Exception e) {
			scsia_obj = VALEUR_PAR_DEFAUT_OBJECT; }

		try {
			value = VALEUR_PAR_DEFAUT_OBJECT; }
		catch(final Exception e) {
			value = sata_obj.getJSONObject(VALUE); }


		// set values of scsi
		try {
			scsi.setKey(scsia_obj.getString("key")); }
		catch(final Exception e) {
			scsi.setKey(VALEUR_PAR_DEFAUT_STRING); }

		try {
			scsi.setScsiBus(value.getJSONObject("scsi").getInt("bus")); }
		catch(final Exception e) {
			scsi.setScsiBus(VALEUR_PAR_DEFAUT_INT); }

		try {
			scsi.setScsiUnit(value.getJSONObject("scsi").getInt("unit")); }
		catch(final Exception e) {
			cpu.setCount(VALEUR_PAR_DEFAUT_INT); }

		try {
			scsi.setLabel(value.getString(LABEL)); }
		catch(final Exception e) {
			scsi.setScsiUnit(VALEUR_PAR_DEFAUT_INT); }

		try {
			scsi.setSharing(value.getString("sharing")); }
		catch(final Exception e) {
			scsi.setSharing(VALEUR_PAR_DEFAUT_STRING); }

		try {
			scsi.setType(value.getString("type")); }
		catch(final Exception e) {
			scsi.setType(VALEUR_PAR_DEFAUT_STRING); }



		vm.setScsi(scsi);


		// PowerState
		try {
			vm.setPowerState(array.getString(POWER_STATE)); }
		catch(final Exception e) {
			vm.setPowerState(VALEUR_PAR_DEFAUT_STRING); }



		// Floppies
		JSONArray floppie;
		JSONObject floppie_obj;
		try {
			floppie = array.getJSONArray("floppies"); }
		catch(final Exception e) {
			floppie = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			floppie_obj = floppie.getJSONObject(0); }
		catch(final Exception e) {
			floppie_obj = VALEUR_PAR_DEFAUT_OBJECT; }

		try {
			value = floppie_obj.getJSONObject(VALUE); }
		catch(final Exception e) {
			value = VALEUR_PAR_DEFAUT_OBJECT; }


		// Set values of floppies
		try {
			floppies.setAllow_guest_control(value.getBoolean(ALLOW_GUEST_CONTROL)); }
		catch(final Exception e) {
			floppies.setAllow_guest_control(false); }

		try {
			floppies.setAllow_guest_control(value.getBoolean(ALLOW_GUEST_CONTROL)); }
		catch(final Exception e) {
			floppies.setLabel(VALEUR_PAR_DEFAUT_STRING); }

		try {
			floppies.setStart_connected(value.getBoolean(START_CONNECTED)); }
		catch(final Exception e) {
			floppies.setStart_connected(false); }

		try {
			floppies.setState(value.getString(STATE)); }
		catch(final Exception e) {
			floppies.setState(VALEUR_PAR_DEFAUT_STRING); }

		try {
			floppies.setBackingType(value.getJSONObject(BACKING).getString("type")); }
		catch(final Exception e) {
			floppies.setBackingType(VALEUR_PAR_DEFAUT_STRING); }

		try {
			floppies.setKey(floppie_obj.getString("key"));}
		catch(final Exception e) {
			floppies.setKey(VALEUR_PAR_DEFAUT_STRING); }



		vm.setFloppies(floppies);


		// Name
		vm.setName(array.getString("name"));



		// Nics
		JSONArray nics_vm;
		JSONObject nics_obj;
		try {
			nics_vm = array.getJSONArray("nics"); }
		catch(final Exception e) {
			nics_vm = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			nics_obj = nics_vm.getJSONObject(0); }
		catch(final Exception e) {
			nics_obj = VALEUR_PAR_DEFAUT_OBJECT; }

		try {
			value = nics_obj.getJSONObject(VALUE); }
		catch(final Exception e) {
			value = VALEUR_PAR_DEFAUT_OBJECT;}


		// Set values of nics
		try {
			nics.setStart_connected(value.getBoolean(START_CONNECTED)); }
		catch(final Exception e) {
			nics.setStart_connected(false);}

		try {
			nics.setBacking_network(value.getJSONObject(BACKING).getString("network")); }
		catch(final Exception e) {
			nics.setBacking_network(VALEUR_PAR_DEFAUT_STRING); }

		try {
			nics.setBacking_network_name(value.getJSONObject(BACKING).getString("network_name")); }
		catch(final Exception e) {
			nics.setBacking_network_name(VALEUR_PAR_DEFAUT_STRING); }

		try {
			nics.setBacking_type(value.getJSONObject(BACKING).getString("type")); }
		catch(final Exception e) {
			nics.setBacking_type(VALEUR_PAR_DEFAUT_STRING); }

		try {
			nics.setMac_address(value.getString("mac_address")); }
		catch(final Exception e) {
			nics.setMac_address(VALEUR_PAR_DEFAUT_STRING); }

		try {
			nics.setMac_type(value.getString("mac_type")); }
		catch(final Exception e) {
			nics.setMac_type(VALEUR_PAR_DEFAUT_STRING); }

		try {
			nics.setAllow_guest_control(value.getBoolean(ALLOW_GUEST_CONTROL)); }
		catch(final Exception e) {
			nics.setAllow_guest_control(false); }

		try {
			nics.setWake_on_lan_enabled(value.getBoolean("wake_on_lan_enabled")); }
		catch(final Exception e) {
			nics.setWake_on_lan_enabled(false); }

		try {
			nics.setLabel(value.getString(LABEL));}
		catch(final Exception e) {
			nics.setLabel(VALEUR_PAR_DEFAUT_STRING); }

		try {
			nics.setState(VALEUR_PAR_DEFAUT_STRING); }
		catch(final Exception e) {
			nics.setState(value.getString(STATE)); }

		try {
			nics.setType(value.getString("type")); }
		catch(final Exception e) {
			nics.setType(VALEUR_PAR_DEFAUT_STRING); }


		vm.setNics(nics);

		// Boot
		JSONObject boot_vm;
		try {
			boot_vm = array.getJSONObject("boot"); }
		catch(final Exception e) {
			boot_vm = VALEUR_PAR_DEFAUT_OBJECT; }


		// Set values of boot
		try {
			boot.setDelay(boot_vm.getInt("delay")); }
		catch(final Exception e) {
			boot.setDelay(VALEUR_PAR_DEFAUT_INT); }

		try {
			boot.setEnterSetupMode(boot_vm.getBoolean("enter_setup_mode")); }
		catch(final Exception e) {
			boot.setEnterSetupMode(false); }

		try {
			boot.setRetry(boot_vm.getBoolean("retry")); }
		catch(final Exception e) {
			boot.setRetry(false); }

		try {
			boot.setRetryDelay(boot_vm.getInt("retry_delay")); }
		catch(final Exception e) {
			boot.setRetryDelay(VALEUR_PAR_DEFAUT_INT); }

		try {
			boot.setType(boot_vm.getString("type")); }
		catch(final Exception e) {
			boot.setType(VALEUR_PAR_DEFAUT_STRING); }


		vm.setBoot(boot);

		// serial_ports
		JSONArray serial_ports;
		try {
			serial_ports = array.getJSONArray(SATA_ADAPTERS); }
		catch(final Exception e) {
			serial_ports = VALEUR_PAR_DEFAUT_JSONARRAY; }

		try {
			vm.setSerial_ports(serial_ports.toString()); }
		catch(final Exception e) {
			vm.setSerial_ports(VALEUR_PAR_DEFAUT_STRING); }


		// guest_OS
		try {
			vm.setGuestOS(array.getString("guest_OS")); }
		catch(final Exception e) {
			vm.setGuestOS(VALEUR_PAR_DEFAUT_STRING); }



		// boot devices
		JSONArray boot_devices;
		try {
			boot_devices = VALEUR_PAR_DEFAUT_JSONARRAY; }
		catch(final Exception e) {
			boot_devices = array.getJSONArray("boot_devices"); }

		try {
			vm.setBoot_devices(VALEUR_PAR_DEFAUT_STRING); }
		catch(final Exception e) {
			vm.setBoot_devices(boot_devices.toString()); }


		// Hardware
		JSONObject hardware_vm;
		try {
			hardware_vm = array.getJSONObject("hardware"); }
		catch(final Exception e) {
			hardware_vm = VALEUR_PAR_DEFAUT_OBJECT; }


		// Set values of boot
		try {
			hardware.setUpgradePolicy(hardware_vm.getString("upgrade_policy")); }
		catch(final Exception e) {
			hardware.setUpgradePolicy(VALEUR_PAR_DEFAUT_STRING); }

		try {
			hardware.setUpgradeStatus(hardware_vm.getString("upgrade_status")); }
		catch(final Exception e) {
			hardware.setUpgradeStatus(VALEUR_PAR_DEFAUT_STRING); }

		try {
			hardware.setVersion(hardware_vm.getString("version")); }
		catch(final Exception e) {
			hardware.setVersion(VALEUR_PAR_DEFAUT_STRING); }


		vm.setHardware(hardware);

		return vm;

	}



	/**
   	Permet de créer une requête JSON afin de modifier le hardware d'une machine virtuelle.
    @param pName Le nom de la machine.
    @param pOs L'OS de la machine.
    @param pDatastore Le datastore dans lequel est placé la machine.
    @param pConfig La config choisie par l'utilisateur.
    @return La requête json transformée en String.
	 */
	public String createVMRequest(String pName, String pOs, String pDatastore, String pConfig) {

		JSONObject cpuChildren = new JSONObject();
		JSONObject memoryChildren = new JSONObject();

		switch(pConfig) {

		case "low" :
			cpuChildren.put(HOT_REMOVE_ENABLED, true);
			cpuChildren.put(HOT_ADD_ENABLED, true);
			cpuChildren.put(CORES_PER_SOCKET, 1);
			cpuChildren.put(COUNT, 1);

			memoryChildren.put(HOT_ADD_ENABLED, true);
			memoryChildren.put(SIZE_MIB, 1024);
			break;

		case "min" :
			cpuChildren.put(HOT_REMOVE_ENABLED, true);
			cpuChildren.put(HOT_ADD_ENABLED, true);
			cpuChildren.put(CORES_PER_SOCKET, 1);
			cpuChildren.put(COUNT, 1);

			memoryChildren.put(HOT_ADD_ENABLED, true);
			memoryChildren.put(SIZE_MIB, 4096);
			break;

		case "mid" :
			cpuChildren.put(HOT_REMOVE_ENABLED, true);
			cpuChildren.put(HOT_ADD_ENABLED, true);
			cpuChildren.put(CORES_PER_SOCKET, 1);
			cpuChildren.put(COUNT, 2);

			memoryChildren.put(HOT_ADD_ENABLED, true);
			memoryChildren.put(SIZE_MIB, 4096);
			break;

		case "high" :
			cpuChildren.put(HOT_REMOVE_ENABLED, true);
			cpuChildren.put(HOT_ADD_ENABLED, true);
			cpuChildren.put(CORES_PER_SOCKET, 1);
			cpuChildren.put(COUNT, 2);

			memoryChildren.put(HOT_ADD_ENABLED, true);
			memoryChildren.put(SIZE_MIB, 8192);
			break;

		case "ultra" :
			cpuChildren.put(HOT_REMOVE_ENABLED, true);
			cpuChildren.put(HOT_ADD_ENABLED, true);
			cpuChildren.put(CORES_PER_SOCKET, 1);
			cpuChildren.put(COUNT, 2);

			memoryChildren.put(HOT_ADD_ENABLED, true);
			memoryChildren.put(SIZE_MIB, 8192);
			break;

		default :
			cpuChildren.put(HOT_REMOVE_ENABLED, true);
			cpuChildren.put(HOT_ADD_ENABLED, true);
			cpuChildren.put(CORES_PER_SOCKET, 1);
			cpuChildren.put(COUNT, 1);

			memoryChildren.put(HOT_ADD_ENABLED, true);
			memoryChildren.put(SIZE_MIB, 4096);
			break;
		}


		JSONObject placementChildren = new JSONObject();
		placementChildren.put(DATASTORE, pDatastore);
		if(pDatastore.contains("32") || pDatastore.contains("38")) {
			placementChildren.put(CLUSTER, "domain-c26");
		} else if(pDatastore.contains("42")) {
			placementChildren.put(CLUSTER, "domain-c28");
		} else if(!pDatastore.contains("datastore")){
			placementChildren.put(CLUSTER, "clusterDefault");
		}
		placementChildren.put(FOLDER, "group-v22");


		JSONObject parent = new JSONObject();
		parent.put(CPU, cpuChildren);
		parent.put(MEMORY, memoryChildren);
		parent.put(PLACEMENT, placementChildren);
		parent.put(OS, pOs);
		parent.put(NAME, pName);

		JSONObject root = new JSONObject();
		root.put(SPEC, parent);
		
		return root.toString();
	}


	/**
   	Permet de créer une requête JSON afin de créer une machine virtuelle.
    @param pType cpu (cpu) ou mémoire (memory) en fonction de ce que l'on veut changer. 
    @param pParameter Si cpu alors le nombre de processeur souhaité, si memory la taille de la ram souhaitée.
    @return La requête json transformée en String.
	 */
	public String updateVMRequest(String pType, String pParameter) {

		JSONObject children = new JSONObject();
		JSONObject parent = new JSONObject();
		switch(pType) {

		case "cpu" :
			children.put(HOT_REMOVE_ENABLED, true);
			children.put(HOT_ADD_ENABLED, true);
			children.put(CORES_PER_SOCKET, 1);
			children.put(COUNT, pParameter);

			parent.put("spec", children);
			break;

		case "memory" :
			children.put(HOT_ADD_ENABLED, true);
			children.put(SIZE_MIB, pParameter);

			parent.put("spec", children);
			break;

		default :
			break;
		}

		System.out.println(parent.toString());
		return parent.toString();
	}

}
