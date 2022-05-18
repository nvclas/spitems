package de.theniclas.items.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class SpecialItem {

	private String name;
	private Material material;
	private ItemStack itemStack;
	private static SpecialItem item;

	private static ArrayList<SpecialItem> itemList = new ArrayList<>();

	public SpecialItem(String name, Material material) {
		item = this;
		this.name = name;
		this.material = material;
		itemStack = new ItemStack(material);
		if(getMeta() != null)
			itemStack.setItemMeta(getMeta());
		itemList.add(this);
	}

	public ItemMeta getMeta() {
		return null;
	}

	public String getName() {
		return name;
	}

	public Material getMaterial() {
		return material;
	}

	public static ArrayList<SpecialItem> getItemList() {
		return itemList;
	}

	public static SpecialItem getItem() {
		return item;
	}

	public ItemStack getAsItemStack() {
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(name);
		itemStack.setItemMeta(im);
		return itemStack;
	}

	public boolean checkItem(ItemStack itemStack) {
		if(itemStack == null)
			return false;
		if(itemStack.getType() != getAsItemStack().getType())
			return false;
		if(itemStack.hasItemMeta() != getAsItemStack().hasItemMeta())
			return false;
		if(itemStack.hasItemMeta() && !itemStack.getItemMeta().equals(getAsItemStack().getItemMeta()))
			return false;
		return true;
	}

}
