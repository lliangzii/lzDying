package liangzi.dying.commands;

import liangzi.dying.Core;
import liangzi.dying.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @Description
 * @Author liangzi
 * @Date 2023/4/23 22:53
 * @Version 1.0
 */

public class CommandHandler implements CommandExecutor {
    public CommandHandler(Core core){
        this.core = core;
    }

    private Core core;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lzd") && args.length >= 1 && args[0].equalsIgnoreCase("debug")) {
            if (sender instanceof Player && !sender.isOp()) {
                // 如果发送者是玩家但不是操作员，则返回 false
                return false;
            }
            Core.Debug = !Core.Debug; // 切换 Debug 值

            // 发送消息通知发送者 Debug 值已被切换
            MessageUtil.debugMsg(sender,"Debug模式 " + (Core.Debug ? "&a启用" : "&c禁用"));
            return true;
        }
        return false;
    }
}
