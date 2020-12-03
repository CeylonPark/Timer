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

        inv.setItem(0, this.getSettingItemStack());
        inv.setItem(2, TimerInventory.resetTimer);
        inv.setItem(4, TimerInventory.startTimer);
        inv.setItem(6, TimerInventory.pauseTimer);
        inv.setItem(8, TimerInventory.stopTimer);

        player.openInventory(inv);
    }

    public void updateTimerInventory(Inventory inventory) {
        inventory.setItem(0, this.getSettingItemStack());
    }

    private ItemStack getSettingItemStack() {
        return new ItemBuilder(Material.INK_SACK).setDisplayName("§6타이머 셋팅").setDur((short) 1)
                .addLore("§f").addLore("§f좌클릭 §a+1분").addLore("§f쉬프트+좌클릭 §a+10분").addLore("§f")
                .addLore("§f우클릭 §c-1분").addLore("§f쉬프트+우클릭 §c-10분").addLore("§f").addLore("§f")
                .addLore("§f§l설정된 타이머 §6§l[ "+this.seconds/60+":"+this.seconds%60+" ]").build();
    }

    public void addSeconds(int seconds) {
        this.seconds += seconds;
    }
    public void removeSeconds(int seconds) {
        this.seconds = Math.max(this.seconds - seconds, 0);
    }
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public int getSeconds() {
        return this.seconds;
    }
}