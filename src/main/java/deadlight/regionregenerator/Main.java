package deadlight.regionregenerator;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import deadlight.regionregenerator.commands.Commands;
import deadlight.regionregenerator.events.BlockBreakListener;
import deadlight.regionregenerator.util.Utils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {
    public WorldGuardPlugin wg;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new Commands(this);
        new BlockBreakListener(this);

        wg = getWorldGuard();
        getServer().getConsoleSender().sendMessage(Utils.chat("&7[&eRegion&bRegenerator&7] &aPlugin has been enabled."));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Utils.chat("&7[&eRegion&bRegenerator&7] &cPlugin has been disabled."));
    }

    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (!(plugin instanceof WorldGuardPlugin)) {

            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
}
