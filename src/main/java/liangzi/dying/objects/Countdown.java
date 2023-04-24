package liangzi.dying.objects;

import liangzi.dying.Core;
import liangzi.dying.utils.MessageUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

/**
 * @Description 一个ActionBar倒计时
 * @Author liangzi
 * @Date 2023/4/23 22:00
 * @Version 1.0
 */

public class Countdown {
    private final Core core = Core.getInstance();
    private BukkitRunnable task;
    private int secondsLeft;
    private Map<Player, Countdown> countMap;
    private Player player;

    public Countdown(Player player, int seconds) {
        this.player = player;
        //添加Map ： player -> countdown
        MessageUtil.debugMsg("添加Map " + player.getDisplayName() +"->"+ this);
        countMap = Core.getCountMap();
        countMap.put(player,this);

        //倒计时
        this.secondsLeft = seconds;
        this.task = new BukkitRunnable() {
            public void run() {
                if (secondsLeft == 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "倒计时结束！"));
                    player.setHealth(0);
                    this.cancel();

                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "距离死亡时间: " + secondsLeft));
                    player.setHealth(1);
                    secondsLeft--;

                }
            }
        };
    }


    public void start() {
        this.task.runTaskTimer(core , 0L, 20L); // 每秒(20ticks)执行一次
    }

    public void stop() {
        this.task.cancel();
        //移除Map
        MessageUtil.debugMsg("移除Map" + player.getDisplayName());
        countMap.remove(player);
    }
}


