package deadlight.regionregenerator.events;

import com.sk89q.worldguard.protection.managers.RegionManager;
import deadlight.regionregenerator.Main;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.List;

public class BlockBreakListener implements Listener {
    private Main plugin;

    public BlockBreakListener(Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    List<String> rBlocks = plugin.getConfig().getStringList("RegenerativeBlocks");
    List<String> regions = plugin.getConfig().getStringList("RegenerativeRegions");

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        RegionManager rm = plugin.wg.getRegionManager(e.getBlock().getWorld());

        if (!rBlocks.contains(e.getBlock().getType().name())) return;
        if (e.getPlayer().hasPermission("rr.admin")) return;
        e.getPlayer().playSound(e.getBlock().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.3F, 1F);
        for (String s : regions) {
            if (rm.getRegion(s).contains(e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ())) {
                breakBlocks(e.getBlock().getType(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), e.getBlock().getWorld());
            }
        }
    }

    public void breakBlocks(Material material, int x, int y, int z, World world) {
        long time = plugin.getConfig().getInt("RegenerationAfter");
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            Location location = new Location(world, x, y, z);
            location.getBlock().setType(material);
        }, time * 20);
    }
}
