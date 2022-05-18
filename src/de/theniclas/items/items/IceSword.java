package de.theniclas.items.items;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import de.theniclas.items.main.Main;

public class IceSword extends SpecialItem implements Listener {

	private static HashMap<Player, Integer> frozen = new HashMap<>();
	private BukkitTask task;

	public IceSword() {
		super("§eEisschwert", Material.GOLD_SWORD);
	}

	@EventHandler
	public void onUsage(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		if(!(event.getDamager() instanceof Player))
			return;
		Player p = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		if(!checkItem(damager.getItemInHand()))
			return;
		event.setDamage(0);
		frozen.put(p, 5);
		p.sendMessage("§eDu wurdest für §c5 §eSekunden eingefroren");
		if(task != null)
			task.cancel();
		task = new BukkitRunnable() {

			@Override
			public void run() {
				if(frozen.get(p) == 0) {
					frozen.remove(p);
					p.sendMessage("§eDu wurdest aufgetaut");
					cancel();
				}
				if(frozen.get(p) != null)
					frozen.put(p, frozen.get(p) - 1);
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(), 0, 20);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(!frozen.containsKey(e.getPlayer()))
			return;
		if(e.getTo().getX() != e.getFrom().getX() || e.getTo().getZ() != e.getFrom().getZ()) {
			e.setTo(e.getFrom());
		}
	}

}
