package com.ceylon.timer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TimerListener implements Listener {
    private final TimerManager timerManager;

    public TimerListener(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    //입장시 보스바 추가
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.timerManager.addPlayer(event.getPlayer());
    }
    //퇴장시 보스바 제거
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.timerManager.removePlayer(event.getPlayer());
    }
}
