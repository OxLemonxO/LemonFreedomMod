package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.banning.Ban;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Temporarily bans a player for one minute.", usage = "/<command> <player> [reason]", aliases = "cyn")
public class Command_canyounot extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            msg(FreedomCommand.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }

        String reason;
        if (args.length > 1)
        {
            reason = (ChatColor.RED + player.getName() + ", can you not?\n" + ChatColor.YELLOW + "You have been banned for one minute by " + sender.getName() + ".\n" + ChatColor.RED + "Reason provided: " + StringUtils.join(args, " ", 1, args.length));
        }
        else
        {
            reason = (ChatColor.RED + player.getName() + ", can you not?\n" + ChatColor.YELLOW + "You have been banned for one minute by " + sender.getName() + ".");
        }

        // strike with lightning effect:
        final Location targetPos = player.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        FUtil.adminAction(sender.getName(), "Tempbanning: " + player.getName() + " for 1 minute. Can they not?", true);
        plugin.bm.addBan(Ban.forPlayer(player, sender, FUtil.parseDateOffset("1m"), reason));

        player.sendMessage("Can you not?");
        
        player.kickPlayer(ChatColor.RED + player.getName() + ", can you not?");

        return true;
    }
}
