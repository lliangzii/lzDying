package liangzi.dying;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.SUICIDE;
import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.VOID;

/**
 * @Description
 * @Author liangzi
 * @Date 2023/4/20 18:54
 * @Version 1.0
 */

public class EventHandler implements Listener {
    Core core = Core.getInstance();

    @org.bukkit.event.EventHandler
    public void onPlayerFallDown(EntityDamageEvent e){

        if (e.getEntity() instanceof Player && e.getCause() != VOID && e.getCause() != SUICIDE) {
            core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 玩家被伤害");
            Player player = (Player) e.getEntity();
            Fallen f = Core.getFallenMap().get(player);
            if (player.getHealth() < e.getFinalDamage()) {
                if (!f.isFallen() && f.getChance() >= 1) {
                    e.setCancelled(true);

                    f.setFallen(true);
                    core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 倒地状态：是");

                    f.reduceChance();
                    core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 剩余倒地次数：" + f.getChance());


                    PotionEffect dark = new PotionEffect(PotionEffectType.DARKNESS,4800,1);
                    PotionEffect slow = new PotionEffect(PotionEffectType.SLOW,4800,5);
                    PotionEffect weakness = new PotionEffect(PotionEffectType.WEAKNESS,4800,10);
                    PotionEffect glowing = new PotionEffect(PotionEffectType.GLOWING,4800,1);
                    PotionEffect res = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4800,1);
                    PotionEffect fat = new PotionEffect(PotionEffectType.SLOW_DIGGING,4800,4);


                    player.setHealth(20);
                    player.addPotionEffect(dark);
                    player.addPotionEffect(slow);
                    player.addPotionEffect(weakness);
                    player.addPotionEffect(glowing);
                    player.addPotionEffect(res);
                    player.addPotionEffect(fat);


                    player.sendTitle("你重伤倒地...","倒地原因:" + e.getCause(),20,80,20);


                    int id = Bukkit.getScheduler().runTaskTimer(core, () -> {
                        if (f.isFallen()) {
                            player.damage(4);
                        }
                    }, 400L, 400L).getTaskId();
                    f.setTaskid(id);
                }
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 玩家加入游戏");
        Player player = event.getPlayer();
        Fallen fallen = new Fallen(1, false);
        Core.getFallenMap().put(player, fallen);
    }

    @org.bukkit.event.EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 玩家退出游戏");
        Player player = event.getPlayer();
        Core.getFallenMap().remove(player);
    }

    @org.bukkit.event.EventHandler
    public void onPlayerDead(PlayerDeathEvent e) {
        core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 玩家死亡");
        Player player = e.getEntity();
        Fallen f = Core.getFallenMap().get(player);
        f.setFallen(false);
        core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 倒地状态：否");
        f.setChance(1);
        core.getServer().getConsoleSender().sendMessage("[PlayerPlus] 剩余倒地次数：1");

        if (f.getTaskid() != 0) {
            Bukkit.getScheduler().cancelTask(f.getTaskid());
            f.setTaskid(0);
        }


    }
}
