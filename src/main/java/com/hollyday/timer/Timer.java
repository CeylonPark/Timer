package com.hollyday.timer;

import com.hollyday.timer.gui.TimerInventoryListener;
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
        getServer().getPluginManager().registerEvents(new TimerInventoryListener(this.timerManager), this);
        getCommand("timer").setExecutor(new TimerCommand(this, timerManager));
        this.getLogger().info("Enalble Timer Plugin ( Ver: "+this.getDescription().getVersion()+" )");
    }

    @Override
    public void onDisable() {
        timerManager.save();
    }
}
