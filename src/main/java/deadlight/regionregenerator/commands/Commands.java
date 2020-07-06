package deadlight.regionregenerator.commands;

import deadlight.regionregenerator.Main;
import deadlight.regionregenerator.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor {
    private Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginCommand("regenerativeRegions").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                if (plugin.getConfig().getStringList("RegenerativeRegions").contains(args[1])) {
                    p.sendMessage(Utils.chat("&8[&eR&bR&8] &cThis item is already in the list"));
                } else {
                    List<String> list = plugin.getConfig().getStringList("RegenerativeRegions");
                    list.add(args[1]);
                    plugin.getConfig().set("RegenerativeRegions", list);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    p.sendMessage(Utils.chat("&8[&eR&bR&8] &a " + args[1] + " &dSuccessfully added to the regenerative regions."));
                }

            } else if (args[0].equalsIgnoreCase("remove")) {

                if (!plugin.getConfig().getStringList("RegenerativeRegions").contains(args[1])) {

                    p.sendMessage(Utils.chat("&8[&eR&bR&8] &cThis item is not in the list."));
                } else {
                    List<String> list = plugin.getConfig().getStringList("RegenerativeRegions");
                    list.remove(args[1]);
                    plugin.getConfig().set("RegenerativeRegions", list);
                    p.sendMessage(Utils.chat("&8[&eR&bR&8] &a " + args[1] + " &dSuccessfully removed from the regenerative regions."));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                p.sendMessage(Utils.chat("&8[&eR&bR&8] &aConfig File Reloaded."));

            } else {
                p.sendMessage(Utils.chat("&8[&eR&bR&8] &cCommand not found. Usage: \n &7/rr [add-remove] regionname \n &7/rr reload"));
            }

        } else {
            p.sendMessage(Utils.chat("&8[&eR&bR&8] &cCommand not found. Usage: \n &7/rr [add-remove] regionname \n &7/rr reload"));
        }
        return false;
    }
}