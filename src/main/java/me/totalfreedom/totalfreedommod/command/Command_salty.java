package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH, blockHostConsole = false)
@CommandParameters(description = "Be salty.", usage = "/<command> <playername>")

public class Command_salty extends FreedomCommand
{

    @Override
    public boolean run(final CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            FUtil.bcastMsg(ChatColor.RED + sender.getName() + ChatColor.YELLOW + " is utterly salty!");
            return true;
        }

        String theplayeritselfifnotactuallyfoundkek = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");

        final Player player = getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player \"" + theplayeritselfifnotactuallyfoundkek + "\" not found!");
            return true;
        }

        FUtil.bcastMsg(ChatColor.RED + player.getName() + ChatColor.YELLOW + " is salty!" + ChatColor.RED + " (states " + sender.getName() + ").");
        return true;
    }
}
