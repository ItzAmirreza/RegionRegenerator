package deadlight.regionregenerator.commands;

import deadlight.regionregenerator.RegionRegenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class rrcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("add")) {

                if (RegionRegenerator.getInstance().getConfig().getStringList("RegenerativeRegions").contains(args[1])) {

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &cThis item is already in the list"));
                } else {

                    List<String> list = RegionRegenerator.getInstance().getConfig().getStringList("RegenerativeRegions");
                    list.add(args[1]);
                    RegionRegenerator.getInstance().getConfig().set("RegenerativeRegions" , list);
                    RegionRegenerator.getInstance().saveConfig();
                    RegionRegenerator.getInstance().reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &a " + args[1] + " &dSuccessfully added to the regenerative regions."));

                }


            } else if (args[0].equalsIgnoreCase("remove")) {

                if (!RegionRegenerator.getInstance().getConfig().getStringList("RegenerativeRegions").contains(args[1])) {

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &cThis item is not in the list."));
                } else {

                    List<String> list = RegionRegenerator.getInstance().getConfig().getStringList("RegenerativeRegions");
                    list.remove(args[1]);
                    RegionRegenerator.getInstance().getConfig().set("RegenerativeRegions", list);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &a " + args[1] + " &dSuccessfully removed from the regenerative regions."));
                    RegionRegenerator.getInstance().saveConfig();
                    RegionRegenerator.getInstance().reloadConfig();
                }

            } else if (args[0].equalsIgnoreCase("reload")) {

                RegionRegenerator.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &aConfig File Reloaded."));

            } else {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &cCommand not found. Usage: \n &7/rr [add-remove] regionname \n &7/rr reload"));

            }


        } else {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eR&bR&8] &cCommand not found. Usage: \n &7/rr [add-remove] regionname \n &7/rr reload"));

        }


        return false;
    }
}
