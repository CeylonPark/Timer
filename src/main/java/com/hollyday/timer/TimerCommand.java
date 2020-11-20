package com.hollyday.timer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {
    private final TimerManager timerManager;

    public TimerCommand(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    /*
            /timer start <seconds>
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            if(args[0].equals("start")) {
                if(args.length == 2) {
                    try {
                        int seconds = Integer.parseInt(args[1]);
                        if(seconds > 0) {
                            timerManager.runBarTimer(seconds);
                        } else {
                            sender.sendMessage("잘못된 사용법 <seconds>는 자연수 형태만 가능");
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage("잘못된 사용법 <seconds>는 자연수 형태만 가능");
                    }
                } else {
                    sender.sendMessage("Usage: /timer start <seconds>");
                }
            }
        } else {
            sender.sendMessage("Usage: /timer start <seconds>");
        }
        return true;
    }
}
