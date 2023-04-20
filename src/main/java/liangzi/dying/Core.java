package liangzi.dying;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Core extends JavaPlugin {
    private static Map<Player, Fallen> FallenMap;
    private static Core instance;
    public static Core getInstance(){
        return instance;
    }
    public static Map<Player, Fallen> getFallenMap(){
        return FallenMap;
    }
    @Override
    public void onEnable() {
        instance = this;
        FallenMap = new HashMap<>();
        getServer().getPluginManager().registerEvents(new EventHandler(), this);
        this.getServer().getConsoleSender().sendMessage("[PlayerPlus] Enabled");

    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("[PlayerPlus] Enabled");
    }
}
