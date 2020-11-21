package com.hollyday.timer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class TimerCommand implements CommandExecutor {
    private final Plugin plugin;
    private final TimerManager timerManager;

    public TimerCommand(Plugin plugin, TimerManager timerManager) {
        this.plugin = plugin;
        this.timerManager = timerManager;
    }

    /*
            /timer start <seconds>
            /timer stop
            /timer title <title>
            /timer teleport <on/off>
            /timer help
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
                case "teleport":
                    if(args.length == 2) {
                        if(args[1].equals("on") || args[1].equals("off")) {
                            boolean use = args[1].equals("on");
                            String state = args[1].equals("on") ? "§a§lON" : "§c§lOFF";
                            if(this.timerManager.getTeleportUse() != use) {
                                this.timerManager.setTeleportUse(use);
                                sender.sendMessage("텔레포트 사용이 "+state+" §f되었습니다.");
                            } else {
                                sender.sendMessage("§c이미 텔레포트 사용이 "+state+" §c되어있습니다.");
                            }
                        } else {
                            sender.sendMessage("Usage: /timer teleport <on/off>");
                        }
                    } else {
                        sender.sendMessage("Usage: /timer teleport <on/off>");
                    }
                    break;
                case "help":
                    String[] help = {
                            "§cThis is Timer Plugin. Version: "+this.plugin.getDescription().getVersion(),
                            "§dUsage:",
                            "    /timer start <seconds>",
                            "    /timer stop",
                            "    /timer title <title> §6- <minute>, <second>이 포함되어야 합니다.",
                            "    /timer teleprot <on/off>",
                            "    /timer help"
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
