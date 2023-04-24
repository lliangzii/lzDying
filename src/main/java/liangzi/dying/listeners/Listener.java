package liangzi.dying.listeners;

import liangzi.dying.Core;
import liangzi.dying.objects.Fallen;
import liangzi.dying.objects.Countdown;
import liangzi.dying.utils.EffectUtil;
import liangzi.dying.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.SUICIDE;
import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.VOID;

/**
 * @Description
 * @Author liangzi
 * @Date 2023/4/20 18:54
 * @Version 1.0
 */

public class Listener implements org.bukkit.event.Listener {
    Core core = Core.getInstance();

    @org.bukkit.event.EventHandler
    public void onPlayerFallDown(EntityDamageEvent e){

        if (e.getEntity() instanceof Player && e.getCause() != VOID && e.getCause() != SUICIDE) {


            Player player = (Player) e.getEntity();
            Fallen f = Core.getFallenMap().get(player);

            if (player.getHealth() < e.getFinalDamage()) {
                MessageUtil.debugMsg(player.getDisplayName()+ " 受到致命伤害");

                if (!f.isFallen() && f.getChance() >= 1) {
                    MessageUtil.debugMsg(player.getDisplayName()+ " 倒地了");
                    e.setCancelled(true);
                    f.setFallen(true);
                    MessageUtil.debugMsg(player.getDisplayName() + "f.setFallen(true)");

                    //倒地机会减一
                    f.reduceChance();
                    MessageUtil.debugMsg(player.getDisplayName() + "f.reduceChance()" + " =" + f.getChance());
                    MessageUtil.titleMsg(player,"你重伤倒地","原因: " + e.getCause());

                    //设置倒地状态效果
                    EffectUtil.setEffect(player);
                    Countdown c = new Countdown(player,20);
                    c.start();

                }
                if (f.isFallen()) {
                    MessageUtil.debugMsg("取消了 "+player.getDisplayName()+" 在倒地状态下受到的伤害");
                    e.setCancelled(true);
                }
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Fallen f = Core.getFallenMap().get(player);

        if (f == null) {
            //为进入服务器的玩家添加一个 Player -> Fallen 映射
            Fallen fallen = new Fallen(1, false);
            Core.getFallenMap().put(player, fallen);
            MessageUtil.debugMsg("新建Map: "+player.getDisplayName()+" -> " +fallen);
        }
        if (Core.getDeathNote().contains(player.getDisplayName())){
            //清除死亡名单中的玩家
            MessageUtil.debugMsg("玩家 "+player.getDisplayName()+" 在死亡名单中，抹杀");
            player.setHealth(0);
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        MessageUtil.debugMsg("玩家 "+player.getDisplayName()+" 退出服务器");
        Fallen f = Core.getFallenMap().get(player);

        //在玩家退出时清除Map
        Core.getFallenMap().remove(player);
        MessageUtil.debugMsg("已清除 "+ player.getDisplayName() +" 的Map映射");

        if (f.isFallen()) {
            MessageUtil.debugMsg("因为玩家 "+player.getDisplayName()+" 在倒地状态退出，加到死亡名单");
            Core.getCountMap().get(player).stop();
            Core.getDeathNote().add(player.getDisplayName());
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerDead(PlayerDeathEvent e) {

        Player player = e.getEntity();
        Fallen f = Core.getFallenMap().get(player);
        MessageUtil.debugMsg(player.getDisplayName() + " 已死亡");


        f.setFallen(false);
        f.setChance(1);
        MessageUtil.debugMsg(player.getDisplayName() + " 已重置倒地状态、机会");

        if (Core.getDeathNote().contains(player.getDisplayName())) {
            MessageUtil.debugMsg(player.getDisplayName() +" 已移出死亡名单");
            Core.getDeathNote().remove(player.getDisplayName());
        }

    }
}
