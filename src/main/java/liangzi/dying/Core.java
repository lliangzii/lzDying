package liangzi.dying;

import liangzi.dying.commands.CommandHandler;
import liangzi.dying.listeners.Listener;
import liangzi.dying.objects.Fallen;

import liangzi.dying.objects.Countdown;
import liangzi.dying.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Core extends JavaPlugin {
    private static final String DEFAULT_COMMAND = "lzd";
    private CommandHandler handler;
    private static Map<Player, Fallen> FallenMap;
    private static Map<Player, Countdown> CountMap;
    private static ArrayList<String> DeathNote;
    public static boolean Debug;
    private static Core instance;
    public static Core getInstance(){
        return instance;
    }
    public static Map<Player, Fallen> getFallenMap(){
        return FallenMap;
    }
    public static Map<Player, Countdown> getCountMap(){
        return CountMap;
    }
    public static ArrayList<String> getDeathNote() {
        return DeathNote;
    }
    @Override
    public void onEnable() {
        instance = this;
        FallenMap = new HashMap<>();
        CountMap = new HashMap<>();
        DeathNote = new ArrayList<>();
        Debug = false;

        //注册命令处理器
        handler = new CommandHandler(this);
        Objects.requireNonNull(Bukkit.getPluginCommand(DEFAULT_COMMAND)).setExecutor(handler);

        //注册事件监听器
        getServer().getPluginManager().registerEvents(new Listener(), this);

        MessageUtil.sendMsg("&a 插件启动");
    }

    @Override
    public void onDisable() {
        MessageUtil.sendMsg("&c 插件卸载");
    }
}
