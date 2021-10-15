package com.ceylon.timer.gui;

import com.ceylon.timer.TimerManager;
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
        if(event.getInventory().getName().equals("§6타이머")) {
            event.setCancelled(true);
            if (event.getRawSlot() == 0) {
                switch (event.getClick()) {
                    case LEFT: this.timerInventory.addSeconds(60); break;
                    case SHIFT_LEFT: this.timerInventory.addSeconds(600); break;
                    case RIGHT: this.timerInventory.removeSeconds(60); break;
                    case SHIFT_RIGHT: this.timerInventory.removeSeconds(600); break;
                    default: return;
                }
                this.timerInventory.updateTimerInventory(event.getInventory());
            } else if (event.getRawSlot() == 2) {
                this.timerInventory.setSeconds(0); //타이머 초기화
                this.timerInventory.updateTimerInventory(event.getInventory());
            } else if (event.getRawSlot() == 4) {
                this.timerManager.runBarTimer(this.timerInventory.getSeconds()); //타이머 시작
            } else if (event.getRawSlot() == 6) {
                this.timerManager.pause(); //타이머 일시정지
            } else if (event.getRawSlot() == 8) {
                this.timerManager.stop(); //타이머 정지
            }
        }
    }
}
