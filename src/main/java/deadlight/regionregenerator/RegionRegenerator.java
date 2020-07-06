package deadlight.regionregenerator;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import deadlight.regionregenerator.commands.rrcommand;
import deadlight.regionregenerator.events.breakev;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class RegionRegenerator extends JavaPlugin {

    public static RegionRegenerator instance;
    public static RegionRegenerator getInstance() {

        return instance;
    }

    public WorldGuardPlugin wg;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        wg = getWorldGuard();
        getServer().getPluginCommand("rr").setExecutor(new rrcommand());
        getServer().getPluginManager().registerEvents(new breakev(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('t', "&7[&eRegion&bRegenerator&7] &aPlugin has been enabled."));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('t', "&7[&eRegion&bRegenerator&7] &cPlugin has been disabled."));
    }



    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {

            return null;
        }

        return (WorldGuardPlugin) plugin;
    }


}
