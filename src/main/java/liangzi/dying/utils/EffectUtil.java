package liangzi.dying.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

/**
 * @Description
 * @Author liangzi
 * @Date 2023/4/23 23:31
 * @Version 1.0
 */

public class EffectUtil {

    public static void setEffect(Player player){
        PotionEffect dark = new PotionEffect(PotionEffectType.DARKNESS,4800,1);
        PotionEffect slow = new PotionEffect(PotionEffectType.SLOW,4800,5);
        PotionEffect weakness = new PotionEffect(PotionEffectType.WEAKNESS,4800,10);
        PotionEffect glowing = new PotionEffect(PotionEffectType.GLOWING,4800,1);
        PotionEffect res = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,4800,1);
        PotionEffect fat = new PotionEffect(PotionEffectType.SLOW_DIGGING,4800,4);

        player.setHealth(1);
        player.addPotionEffect(dark);
        player.addPotionEffect(slow);
        player.addPotionEffect(weakness);
        player.addPotionEffect(glowing);
        player.addPotionEffect(res);
        player.addPotionEffect(fat);
    }
    public static void clearEffect(Player player){

        // 获取玩家当前所有的药水效果
        Collection<PotionEffect> effects = player.getActivePotionEffects();

        // 遍历所有效果，移除添加的六种效果
        for (PotionEffect effect : effects) {
            PotionEffectType type = effect.getType();
            if (type == PotionEffectType.DARKNESS ||
                    type == PotionEffectType.SLOW ||
                    type == PotionEffectType.WEAKNESS ||
                    type == PotionEffectType.GLOWING ||
                    type == PotionEffectType.DAMAGE_RESISTANCE ||
                    type == PotionEffectType.SLOW_DIGGING) {
                player.removePotionEffect(type);
            }
        }
    }

}
