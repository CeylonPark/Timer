package com.hollyday.timer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timer extends JavaPlugin {
    private final BossBarTimer barTimer = new BossBarTimer();

    @Override
    public void onEnable() {
        for(Player player : getServer().getOnlinePlayers()) {
            this.barTimer.addPlayer(player);
        }
        getServer().getPluginManager().registerEvents(new TimerListener(this, this.barTimer), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
