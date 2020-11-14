package com.hollyday.timer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerInventory {
    public final static ItemStack m5Timer;
    public final static ItemStack m10Timer;
    public final static ItemStack stopAndStartTimer;
    public final static ItemStack resetTimer;

    static {
        m5Timer = new ItemStack(Material.INK_SACK);
        m5Timer.setDurability((short) 1);
        m10Timer = new ItemStack(Material.INK_SACK);
        m10Timer.setDurability((short) 2);
        stopAndStartTimer = new ItemStack(Material.INK_SACK);
        stopAndStartTimer.setDurability((short) 3);
        resetTimer = new ItemStack(Material.INK_SACK);
        resetTimer.setDurability((short) 4);
    }

    public void openInventory(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, "§6타이머");

        inv.setItem(1, TimerInventory.m5Timer);
        inv.setItem(3, TimerInventory.m10Timer);
        inv.setItem(5, TimerInventory.stopAndStartTimer);
        inv.setItem(7, TimerInventory.resetTimer);

        player.openInventory(inv);
    }
}