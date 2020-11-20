package com.hollyday.timer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timer extends JavaPlugin {
    private final TimerManager timerManager;

    public Timer() {
        this.timerManager = new TimerManager(this);
    }

    @Override
    public void onEnable() {
        this.timerManager.load();
        for(Player player : getServer().getOnlinePlayers()) {
            this.timerManager.addPlayer(player);
        }
        getServer().getPluginManager().registerEvents(new TimerListener(this.timerManager), this);
        getCommand("timer").setExecutor(new TimerCommand(timerManager));
    }

    @Override
    public void onDisable() {
        timerManager.save();
    }
}
