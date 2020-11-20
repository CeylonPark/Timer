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
        m5Timer = new ItemBuilder(Material.INK_SACK).setDisplayName("§a5분 타이머").setDur((short) 1).build();
        m10Timer = new ItemBuilder(Material.INK_SACK).setDisplayName("§e10분 타이머").setDur((short) 2).build();
        stopAndStartTimer = new ItemBuilder(Material.INK_SACK).setDisplayName("§f타이머 일시정지").setDur((short) 3).build();
        resetTimer = new ItemBuilder(Material.INK_SACK).setDisplayName("§c타이머 리셋").setDur((short) 4).build();
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