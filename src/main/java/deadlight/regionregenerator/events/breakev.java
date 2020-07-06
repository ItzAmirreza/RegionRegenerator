package deadlight.regionregenerator.events;

import com.sk89q.worldguard.protection.managers.RegionManager;
import deadlight.regionregenerator.RegionRegenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.List;

public class breakev implements Listener {


    @EventHandler
    public void onbreak(BlockBreakEvent e) {

        List<String> rblocks = RegionRegenerator.getInstance().getConfig().getStringList("RegenerativeBlocks");

        RegionManager rm = RegionRegenerator.getInstance().wg.getRegionManager(e.getBlock().getWorld());

        if (rblocks.contains(e.getBlock().getType().name())) {

            if (!e.getPlayer().hasPermission("rr.admin")) {


                List<String> listofregions = RegionRegenerator.getInstance().getConfig().getStringList("RegenerativeRegions");

                for (String i : listofregions) {

                    try {
                        if (rm.getRegion(i).contains(e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ())) {

                            startthework(e.getBlock().getType(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), e.getBlock().getWorld());

                        }
                    } catch (Exception ex) {

                        // do nothing
                    }



                }
            }


        }

    }

    public void startthework(Material material, int x, int y, int z, World world) {
        long time = RegionRegenerator.getInstance().getConfig().getInt("RegenerationAfter");
        RegionRegenerator.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(RegionRegenerator.getInstance(), new Runnable() {
            @Override
            public void run() {

                Location location = new Location(world, x, y, z);
                location.getBlock().setType(material);

            }
        }, time*20);


    }



}
