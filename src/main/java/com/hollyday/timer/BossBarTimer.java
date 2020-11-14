package com.hollyday.timer;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarTimer {
    private BossBar bossBar;
    private String title;
    private int second;
    private BukkitRunnable runnable;

    public BossBarTimer() {
        bossBar = Bukkit.createBossBar("<minute> Minute <second> Second", BarColor.PINK, BarStyle.SEGMENTED_10);
        bossBar.setVisible(false);
        second = 0;
    }
    public BossBarTimer setTitle(String title) {
        this.title = title;
        return this;
    }
    public BossBarTimer setSecond(int second) {
        this.second = second;
        return this;
    }
    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }
    public void removePlayer(Player player) {
        bossBar.removePlayer(player);
    }

    public void runTimer(Plugin plugin, int cool) {
        stop();
        bossBar.setVisible(true);
        setBossBar(second);
        runnable = new BukkitRunnable() {
            private int count = second;

            @Override
            public void run() {
                setBossBar(count = count - cool);
                if(count <= 0) stop();
            }
        };
        runnable.runTaskTimer(plugin, 20*cool, 20*cool);
    }
    public void stop() {
        if(runnable != null) {
            runnable.cancel();
            runnable = null;
            bossBar.setVisible(false);
        }
    }
    private void setBossBar(int count) {
        String title;
        if(this.title.contains("<minute>")) {
            title = this.title.replace("<second>", String.valueOf(count%60))
                    .replace("<minute>", String.valueOf(count/60));
        } else {
            title = this.title.replace("<second>", String.valueOf(count));
        }
        bossBar.setTitle(title);
        bossBar.setProgress((double) count/second);
    }
}