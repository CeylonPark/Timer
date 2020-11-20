package com.hollyday.timer.module;

import com.hollyday.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class TeleportLocation {
    private final Plugin plugin;
    private final FileConfiguration config;
    private Location location;

    public TeleportLocation(Timer plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();

    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void teleportAll() {
        for(Player player : this.plugin.getServer().getOnlinePlayers()) {
            player.teleport(this.location);
        }
    }

    public void load() {
        if(this.config.isString("world") && this.config.isDouble("x") && this.config.isDouble("y")
                && this.config.isDouble("z") && this.config.isDouble("yaw") && this.config.isDouble("pitch")) {
            this.location = new Location(Bukkit.getServer().getWorld(this.config.getString("world")),
                    this.config.getDouble("x"),
                    this.config.getDouble("y"),
                    this.config.getDouble("z"),
                    (float) this.config.getDouble("yaw"),
                    (float) this.config.getDouble("pitch")
            );
        }
    }

    public void save() {
        if (this.location != null) {
            this.config.set("world", this.location.getWorld().getName());
            this.config.set("x", this.location.getX());
            this.config.set("y", this.location.getY());
            this.config.set("z", this.location.getZ());
            this.config.set("yaw", this.location.getYaw());
            this.config.set("pitch", this.location.getPitch());
            try {
                this.config.save(new File(this.plugin.getDataFolder(), "config.yml"));
            } catch (Exception ignore) {}
        }
    }
}
