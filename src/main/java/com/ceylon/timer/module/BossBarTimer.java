package com.ceylon.timer.module;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarTimer {
    private final BossBar bossBar;
    private String title;
    private int second;
    private BarTimer runnable;
    private Runnable afterRunnable = null;

    public BossBarTimer() {
        this.title = "<minute> Minute <second> Second";
        this.second = 0;
        this.bossBar = Bukkit.createBossBar(this.title, BarColor.PINK, BarStyle.SEGMENTED_10);
        this.bossBar.setVisible(false);
    }

    public BossBarTimer(String title, int second) {
        this.title = title;
        this.second = second;
        this.bossBar = Bukkit.createBossBar(this.title, BarColor.PINK, BarStyle.SEGMENTED_10);
        this.bossBar.setVisible(false);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public void addPlayer(Player player) {
        this.bossBar.addPlayer(player);
    }
    public void removePlayer(Player player) {
        this.bossBar.removePlayer(player);
    }
    public void setAfterRunnable(Runnable afterRunnable) {
        this.afterRunnable = afterRunnable;
    }

    public void runTimer(Plugin plugin, int cool) {
        stop();
        this.bossBar.setVisible(true);
        setBossBar(second);
        this.runnable = new BarTimer(cool);
        this.runnable.runTaskTimer(plugin, 20*cool, 20*cool);
    }
    public void pause() {
        if(this.runnable != null) {
            this.runnable.pause();
        }
    }
    public void stop() {
        if(this.runnable != null) {
            this.runnable.cancel();
            this.runnable = null;
            this.bossBar.setVisible(false);
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
        this.bossBar.setTitle(title);
        if(second > 0) {
            this.bossBar.setProgress((double) count/second);
        }
    }

    private class BarTimer extends BukkitRunnable {
        private final int cool;
        private int count;
        private boolean pause;

        public BarTimer(int cool) {
            this.cool = cool;
            this.count = BossBarTimer.this.second;
        }

        @Override
        public void run() {
            if(!this.pause) {
                BossBarTimer.this.setBossBar(this.count = this.count - this.cool);
                if(this.count <= 0) {
                    BossBarTimer.this.stop();
                    if(BossBarTimer.this.afterRunnable != null) {
                        BossBarTimer.this.afterRunnable.run();
                    }
                }
            }
        }

        public void pause() {
            this.pause = !this.pause;
        }
    }
}