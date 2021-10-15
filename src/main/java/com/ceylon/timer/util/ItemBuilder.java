package com.ceylon.timer.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder extends ItemStack {
    private ItemMeta itemMeta;
    private List<String> lore = new ArrayList<>();

    public ItemBuilder(Material material) {
        setType(material);
        setAmount(1);
        this.itemMeta = this.getItemMeta();
    }

    public ItemBuilder setDisplayName(String s) {
        this.itemMeta.setDisplayName(s);
        return this;
    }
    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
    public ItemBuilder addLore(String s) {
        this.lore.add(s);
        return this;
    }
    public ItemBuilder setDur(short durability) {
        super.setDurability(durability);
        return this;
    }

    public ItemStack build() {
        this.itemMeta.setLore(this.lore);
        setItemMeta(this.itemMeta);
        return this;
    }
}
