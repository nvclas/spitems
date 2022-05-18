package de.theniclas.items.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.theniclas.items.commands.CMDspitems;
import de.theniclas.items.items.GoldenHead;
import de.theniclas.items.items.OitcBow;

public class Main extends JavaPlugin {

	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		Bukkit.getPluginManager().registerEvents(new GoldenHead(), this);
		Bukkit.getPluginManager().registerEvents(new OitcBow(), this);
		
		getCommand("spitems").setExecutor(new CMDspitems());
		
		Bukkit.getConsoleSender().sendMessage("§a" + getName() + " loaded");
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
}