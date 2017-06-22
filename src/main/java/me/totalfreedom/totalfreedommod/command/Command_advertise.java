package me.totalfreedom.totalfreedommod.command;

import java.util.ArrayList;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author mnpn0
 *
 * Thanks UYScutix and Jaguar for the idea. I've improved the code and it is in working state.
 *
 */
@CommandPermissions(level = Rank.OP, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "Advertise a message every 10 minutes.", usage = "/<command> <message>", aliases = "ad,advert")
public class Command_advertise extends FreedomCommand
{

    public static ArrayList<Player> cooldown = new ArrayList<Player>();

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "There is no need for any kind of Console to advertise.");
            return true;
        }
        
        final Player p = (Player) sender;

        if (cooldown.contains(p))
        {
            p.sendMessage(ChatColor.RED + "You must wait 10 minutes to make another advertisement.");
            return true;
        }

        if (args.length < 1)
        {
            return false;
        }

        String message = StringUtils.join(args, " ", 0, args.length);
        FUtil.bcastMsg(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "Advertisement" + ChatColor.DARK_GRAY + ":" + ChatColor.GOLD + sender.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_GREEN + message);

        cooldown.add(p);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                p.sendMessage(ChatColor.GRAY + "You may now make another advertisement.");
                cooldown.remove(p);
            }
        }, 600L * 20L);
        return true;
    }
}
