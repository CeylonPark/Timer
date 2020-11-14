package com.hollyday.timer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timer extends JavaPlugin {
    private final BossBarTimer barTimer;
    private final TeleportLocation tLocation;

    public Timer() {
        this.barTimer = new BossBarTimer();
        this.tLocation = new TeleportLocation(this);
    }

    @Override
    public void onEnable() {
        tLocation.load();
        for(Player player : getServer().getOnlinePlayers()) {
            this.barTimer.addPlayer(player);
        }
        getServer().getPluginManager().registerEvents(new TimerListener(this, this.barTimer), this);
    }

    @Override
    public void onDisable() {
        tLocation.save();
    }
}
