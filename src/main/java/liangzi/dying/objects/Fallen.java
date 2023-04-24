package liangzi.dying.objects;

import org.bukkit.entity.Player;

/**
 * @Description 表示一个“玩家被击倒的状态”的对象
 * @Author liangzi
 * @Date 2023/4/20 20:18
 * @Version 1.0
 */

public class Fallen {
    /**
     *
     * @param chance 剩余重生次数，为0时无法触发倒地
     * @param fallen 布尔值，表示玩家是否处于倒地状态
     * damage_taskid
     */
    public Fallen(int chance, boolean fallen){
        this.fallen = fallen;
        this.chance = chance;
    }

/*    Fallen(Player player) {
        this.player = player;
        this.fallen = false;
        this.chance = 1;
    }*/
    private int chance;
    private boolean fallen;
    private Player player;

    public void reduceChance(){
        this.chance--;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isFallen() {
        return fallen;
    }

    public void setFallen(boolean fallen) {
        this.fallen = fallen;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

}
