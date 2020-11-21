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
            /timer stop
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            switch (args[0]) {
                case "start":
                    if (args.length == 2) {
                        try {
                            int seconds = Integer.parseInt(args[1]);
                            if (seconds > 0) {
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
                    break;
                case "stop":
                    if(args.length == 1) {
                        this.timerManager.stop();
                    } else {
                        sender.sendMessage("Usage: /timer stop");
                    }
                    break;
                case "title":
                    if (args.length > 1) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i].replace('&', '§')).append(" ");
                        }
                        String title = builder.substring(0, builder.length() - 1);
                        System.out.println(title);
                        if (title.contains("<minute>") && title.contains("<second>")) {
                            this.timerManager.setTitle(title);
                            sender.sendMessage("타이틀이 " + title + " §f로 변경되었습니다.");
                        } else {
                            sender.sendMessage("Usage: /timer title <title> §6- <minute>, <second>이 포함되어야 합니다.");
                        }
                    } else {
                        sender.sendMessage("Usage: /timer title <title> §6- <minute>, <second>이 포함되어야 합니다.");
                    }
                    break;
                case "help":
                    String[] help = {
                            "§dUsage:",
                            "    /timer start <seconds>",
                            "    /timer title <title> §6- <minute>, <second>이 포함되어야 합니다.",
                            "    /timer stop"
                    };
                    sender.sendMessage(help);
                    break;
                default:
                    return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
