package de.theniclas.items.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.theniclas.items.items.SpecialItem;

public class CMDspitems implements CommandExecutor {

	private static final String PREFIX = "§7[§5Items§7] §r";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!command.getName().equalsIgnoreCase("spitems"))
			return true;
		if(!(sender instanceof Player))
			return true;
		Player p = (Player) sender;
		if(!p.hasPermission("sp.open")) {
			p.sendMessage(PREFIX + "§cDafür hast du keine Rechte");
		}

		//Open inventory for SpecialItems
		Inventory inv = Bukkit.createInventory(null, 9 * 7, "§5Special Items");
		for(SpecialItem item : SpecialItem.getItemList())
			inv.addItem(item.getAsItemStack());
		p.openInventory(inv);
		return true;
	}

}
