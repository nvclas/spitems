package de.theniclas.items.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OitcBow extends SpecialItem implements Listener {

	private static List<Entity> arrows = new ArrayList<>();

	public OitcBow() {
		super("§5OITC-Bow", Material.BOW);
	}

	@Override
	public ItemMeta getMeta() {
		ItemMeta im = getAsItemStack().getItemMeta();
		im.spigot().setUnbreakable(true);
		return im;
	}

	@EventHandler
	public void onUsage(EntityShootBowEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		if(!checkItem(event.getBow()))
			return;
		arrows.add(event.getProjectile());
	}

	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		if(!arrows.contains(event.getEntity()))
			return;
		event.getEntity().remove();
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void setEntityDamage(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			if(event.getDamage() < ((Damageable) event.getEntity()).getHealth())
				return;
			if(!p.getInventory().contains(getAsItemStack()))
				return;
			p.getInventory().addItem(new ItemStack(Material.ARROW));
			return;
		}
		if(!arrows.contains(event.getDamager()))
			return;
		event.setDamage(Integer.MAX_VALUE);
		if(!arrows.contains(event.getDamager()))
			return;
		Player p = (Player) ((Projectile) event.getDamager()).getShooter();
		p.getInventory().addItem(new ItemStack(Material.ARROW));
		p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 0.5f);
		arrows.remove(event.getDamager());
	}

}
