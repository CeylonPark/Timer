package com.hollyday.timer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TimerListener implements Listener {
    private final Timer plugin;
    private final BossBarTimer barTimer;

    public TimerListener(Timer plugin, BossBarTimer barTimer) {
        this.plugin = plugin;
        this.barTimer = barTimer;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        ItemStack item = inv.getItemInMainHand();

        //돌 우클릭시 보스바 실행
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && event.getHand() == EquipmentSlot.HAND && event.getItem() != null
                && item.getType() == Material.WATCH) { //해시계로 변경
            this.barTimer.setSecond(15).runTimer(this.plugin, 1);
            //나중에 인벤토리 오픈
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.barTimer.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.barTimer.removePlayer(event.getPlayer());
    }
}
