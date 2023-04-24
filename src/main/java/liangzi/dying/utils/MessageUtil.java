package liangzi.dying.utils;

import liangzi.dying.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Description 处理插件消息
 * @Author liangzi
 * @Date 2023/4/21 22:02
 * @Version 1.0
 */

public class MessageUtil {
    public static Core core = Core.getInstance();
    private static final String prefix = "[lzDying]";
    private static final String prefixDebug = "[lzDying DEBUG]";

    public static String getColorStr(String str){
        return ChatColor.translateAlternateColorCodes('&',str);
    }

    /**
     * 向玩家发送带有前缀的消息
     * @param player 玩家
     * @param msg 要发送的消息
     */
    public static void sendMsg(Player player, String msg){
        player.sendMessage(getColorStr("&a "+ prefix +"&b&l" + msg));
    }
    /**
     * 向控制台发送带有前缀的消息
     * @param msg 要发送的消息
     */
    public static void sendMsg(String msg){
        core.getServer().getConsoleSender().sendMessage(getColorStr("&a"+prefix+"&b&l"+msg));
        }
    /**
     * 向CommandSender发送带有前缀的消息
     * @param sender 命令发送者
     * @param msg 要发送的消息
     */
    public static void sendMsg(CommandSender sender,String msg){
        if (sender instanceof Player) {
            sendMsg((Player) sender,getColorStr("&a"+prefix+" &b&l"+msg));
        } else {
            sendMsg(getColorStr("&a"+prefix+": &b&l"+msg));
        }
    }
    /**
     * 向管理员玩家发送带有debug前缀的消息
     * @param player 管理员玩家
     * @param msg 要发送的消息
     */
    public static void debugMsg(Player player,String msg){
        if (Core.Debug) {
            if (player.isOp()){
                    player.sendMessage(getColorStr("&c"+prefixDebug+": &b&l"+msg));
                }
        }
    }
    /**
     * 向控制台发送带有debug前缀的消息
     * @param msg 要发送的消息
     */
    public static void debugMsg(String msg){
        if (Core.Debug) {
                core.getServer().getConsoleSender().sendMessage(getColorStr("&c"+prefixDebug+": &b&l"+msg));
            }
        }
    /**
     * 向CommandSender发送带有前缀的消息
     * @param sender 命令发送者
     * @param msg 要发送的消息
     */
    public static void debugMsg(CommandSender sender, String msg){
        if (sender instanceof Player) {
            debugMsg((Player) sender,getColorStr("&c"+prefixDebug+": &b&l"+msg));
        } else {
            debugMsg(getColorStr("&c"+prefixDebug+": &b&l"+msg));
        }
    }

    /**
     * 向玩家发送一个title
     * @param player 玩家
     * @param title 标题
     * @param sub 副标题
     */
    public static void titleMsg(Player player, String title,String sub){
        player.sendTitle(getColorStr(title),getColorStr(sub),20,80,20);
    }

    /**
     * 向玩家发送一个title
     * @param sender 命令发送者
     * @param title 标题
     * @param sub 副标题
     */
    public static void titleMsg(CommandSender sender, String title,String sub){
        if (sender instanceof Player) {
            titleMsg((Player) sender,title,sub);
        }
    }

}
