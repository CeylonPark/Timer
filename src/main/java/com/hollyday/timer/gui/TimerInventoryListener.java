package com.hollyday.timer.gui;

import com.hollyday.timer.TimerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TimerInventoryListener implements Listener {
    private final TimerManager timerManager;
    private final TimerInventory timerInventory;

    public TimerInventoryListener(TimerManager timerManager) {
        this.timerManager = timerManager;
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
                this.timerManager.setLocation(player.getLocation()); //좌표 설정
                player.sendMessage("좌표가 설정되었습니다.");
            }
        }
    }

    //인벤토리 클릭
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equals("§6타이머")) {
            event.setCancelled(true);
            if (event.getRawSlot() == 1) {
                this.timerManager.runBarTimer(300); //5분 타이머
            } else if (event.getRawSlot() == 3) {
                this.timerManager.runBarTimer(600); //10분 타이머
            } else if (event.getRawSlot() == 5) {
                this.timerManager.pause(); //스탑 and 스타트 기능
            } else if (event.getRawSlot() == 7) {
                this.timerManager.stop(); //타이머 리셋
            }
            player.closeInventory();
        }
    }
}
