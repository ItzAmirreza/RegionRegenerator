package deadlight.regionregenerator.util;

import org.bukkit.ChatColor;

public class Utils {
    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    //Prefix used for chat / rr command etc..
    public static String chatprefix = "&8[&eR&bR&8]&r";
    //Prefix used for main messages (Mainly Console)
    public static String mainprefix = "&8[&eR&bR&8]&r";
    
}
