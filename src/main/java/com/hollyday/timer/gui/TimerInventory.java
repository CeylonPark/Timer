package com.hollyday.timer.gui;

import com.hollyday.timer.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimerInventory {
    public final static ItemStack resetTimer;
    public final static ItemStack startTimer;
    public final static ItemStack pauseTimer;
    public final static ItemStack stopTimer;
    private int seconds = 0;

    static {
        resetTimer = new ItemBuilder(Material.INK_SACK).setDisplayName("§6타이머 초기화").setDur((short) 2).build();
        startTimer = new ItemBuilder(Material.INK_SACK).setDisplayName("§a타이머 시작").setDur((short) 3).build();
        pauseTimer = new ItemBuilder(Material.INK_SACK).setDisplayName("§b타이머 일시정지").setDur((short) 4).build();
        stopTimer = new ItemBuilder(Material.INK_SACK).setDisplayName("§c타이머 정지").setDur((short) 5).build();
    }

    public void openInventory(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, "§6타이머");

        inv.setItem(0, this.getSettingItemStack(this.seconds));
        inv.setItem(2, TimerInventory.resetTimer);
        inv.setItem(4, TimerInventory.startTimer);
        inv.setItem(6, TimerInventory.pauseTimer);
        inv.setItem(8, TimerInventory.stopTimer);

        player.openInventory(inv);
    }

    public ItemStack getSettingItemStack(int seconds) {
        return new ItemBuilder(Material.INK_SACK).setDisplayName("§6타이머 셋팅").setDur((short) 1).
                addLore("초:"+seconds).build();
        //시간 추가
    }
    
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public int getSeconds() {
        return this.seconds;
    }
}