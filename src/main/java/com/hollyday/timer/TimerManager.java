package com.hollyday.timer;

import com.hollyday.timer.module.BossBarTimer;
import com.hollyday.timer.module.TeleportLocation;
import org.bukkit.entity.Player;

public class TimerManager extends TeleportLocation {
    private final Timer plugin;
    private final BossBarTimer bossBarTimer;
    private boolean teleportUse = false;

    public TimerManager(Timer plugin) {
        super(plugin);
        this.plugin = plugin;
        this.bossBarTimer = new BossBarTimer();
    }

    public void setTitle(String title) {
        this.bossBarTimer.setTitle(title);
    }
    public void addPlayer(Player player) {
        this.bossBarTimer.addPlayer(player);
    }
    public void removePlayer(Player player) {
        this.bossBarTimer.removePlayer(player);
    }
    public void pause() {
        this.bossBarTimer.pause();
    }
    public void stop() {
        this.bossBarTimer.stop();
    }
    public void setTeleportUse(boolean teleportUse) {
        this.teleportUse = teleportUse;
    }
    public boolean getTeleportUse() {
        return this.teleportUse;
    }
    public void runBarTimer(int seconds) {
        this.bossBarTimer.setSecond(seconds);
        this.bossBarTimer.runTimer(this.plugin, 1);
        if(this.teleportUse) {
            this.bossBarTimer.setAfterRunnable(this::teleportAll);
        }
    }
}
