package de.theniclas.items.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenHead extends SpecialItem implements Listener {

	public GoldenHead() {
		super("§6Golden Head", Material.GOLDEN_APPLE);
	}
	
	@EventHandler
	public void onUsage(PlayerItemConsumeEvent event) {
		if(!event.getItem().equals(getAsItemStack()))
			return;
		Player p = event.getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 10, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 120, 0));
		p.getInventory().remove(getAsItemStack());
		return;
	}

	//tempworlds
	
}
