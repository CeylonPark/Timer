package com.hollyday.timer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TimerListener implements Listener {
    private final Timer plugin;
    private final BossBarTimer barTimer;
    private final TeleportLocation tLocation;
    private final TimerInventory timerInventory;

    public TimerListener(Timer plugin, BossBarTimer barTimer, TeleportLocation tLocation) {
        this.plugin = plugin;
        this.barTimer = barTimer;
        this.tLocation = tLocation;
        this.timerInventory = new TimerInventory();
    }

    //해시계 클릭 이벤트
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        ItemStack item = inv.getItemInMainHand();

        if (event.getHand() == EquipmentSlot.HAND && event.getItem() != null && item.getType() == Material.WATCH) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                this.timerInventory.openInventory(player); //인벤토리 오픈
            } else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                this.tLocation.setLocation(player); //좌표 설정
            }
        }
    }

    //입장시 보스바 추가
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.barTimer.addPlayer(event.getPlayer());
    }

    //퇴장시 보스바 제거
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.barTimer.removePlayer(event.getPlayer());
    }

    //인벤토리 클릭
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equals("§6타이머")) {
            event.setCancelled(true);
            if (event.getRawSlot() == 1) {
                this.barTimer.setSecond(3); //5분 타이머
                this.barTimer.runTimer(plugin, 1);
                this.barTimer.setAfterRunnable(this.tLocation::teleportAll);
            } else if (event.getRawSlot() == 3) {
                this.barTimer.setSecond(6); //10분 타이머
                this.barTimer.runTimer(plugin, 1);
                this.barTimer.setAfterRunnable(this.tLocation::teleportAll);
            } else if (event.getRawSlot() == 5) {
                //스탑 and 스타트 기능
            } else if (event.getRawSlot() == 7) {
                this.barTimer.stop(); //타이머 리셋
            }
            player.closeInventory();
        }
    }
}
