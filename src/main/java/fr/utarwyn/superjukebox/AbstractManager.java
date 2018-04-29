package fr.utarwyn.superjukebox;

import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abtsract base class for creating an EnderContainers manager
 * @since 1.0.0
 * @author Utarwyn
 */
public abstract class AbstractManager implements Listener {

	/**
	 * Stores the plugin main class
	 */
	protected SuperJukebox plugin;

	/**
	 * Constructs the manager
	 * @param plugin Main class of the plugin
	 * @param listeners List of listeners to automatically load during the initialization of the manager
	 */
	public AbstractManager(SuperJukebox plugin, Listener ... listeners) {
		this.plugin = plugin;

		// Now we register all listeners (and the manager too)
		List<Listener> listenerList = new ArrayList<>();

		Collections.addAll(listenerList, listeners);
		listenerList.add(this);

		for (Listener listener : listenerList)
			this.registerListener(listener);


		// We start the initialization of the manager
		this.initialize();

		// Register the manager for the plugin
		try {
			Managers.registerManager(this.getClass(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Register a specific listener to the server
	 * @param listener Listener to register
	 */
	protected void registerListener(Listener listener) {
		this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
	}

	/**
	 * Returns the main class of the plugin. Only a shortcut.
	 * @return Main class
	 */
	public SuperJukebox getPlugin() {
		return this.plugin;
	}

	/**
	 * Called when the manager is initializing.
	 * Called in the constructor so before the constructor of sub-managers.
	 */
	public abstract void initialize();

	/**
	 * Called when the manager is unloading.
	 */
	protected abstract void unload();

}
