package liangzi.dying;

import org.bukkit.entity.Player;

/**
 * @Description
 * @Author liangzi
 * @Date 2023/4/20 20:18
 * @Version 1.0
 */

public class Fallen {
    Fallen(int chance,boolean fallen){
        this.fallen = fallen;
        this.chance = chance;
        this.taskid = 0;
    }

/*    Fallen(Player player) {
        this.player = player;
        this.fallen = false;
        this.chance = 1;
    }*/
    private int chance;
    private boolean fallen;
    private Player player;
    private int taskid;

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

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
