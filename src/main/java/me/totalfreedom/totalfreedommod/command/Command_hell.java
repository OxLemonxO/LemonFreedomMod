package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.admin.Admin;
import me.totalfreedom.totalfreedommod.banning.Ban;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


// Good job Mnpn, you borked it. Attempting to run this only affects yourself. You can literally only ban yourself with this command. *facepalm*

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH, blockHostConsole = false) //Supers can *attempt* to run it, but to no avail.
@CommandParameters(description = "Send someone to hell.", usage = "/<command> <playername> <reason>")
 public class Command_hell extends FreedomCommand
 {
   public Command_hell()
   {
       
   }
    @Override
    public boolean run(final CommandSender sender, Player player, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        
        String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");

    if (args.length < 1)
    {
        return false;
    }
    
    if ((sender.getName().equals("Mnpn03") | sender.getName().equals("OxLemonxO") | sender.getName().equals("ArceCreeper") | sender.getName().equals("CONSOLE")))
    {

        if (player == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }
            if (player.getName().equals("ArceCreeper"))
            {
                FUtil.bcastMsg(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "LFM" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_RED + sender.getName() + " has tried to send ArceCreeper to hell.");
                player.getWorld().strikeLightning(player.getLocation());
                return true;
            }
        FUtil.adminAction(sender.getName(), "Sending " + player.getName() + " to the deepest pits of hell.", true);
        FUtil.bcastMsg(player.getName() + " is about to regret their decision!", ChatColor.RED);

        final String ip = player.getAddress().getAddress().getHostAddress().trim();

        Admin admin = getAdmin(player);
        if (admin != null)
        {
            FUtil.adminAction(sender.getName(), "Removing " + player.getName() + " from the admin list", true);
            plugin.al.removeAdmin(admin);
        }
        player.setWhitelisted(false);
        player.setOp(false);
        
        player.chat("It burns!");

        player.setGameMode(GameMode.SURVIVAL);

        player.closeInventory();
        player.getInventory().clear();

        player.setFireTicks(40000); // Set them on fire for 40000 ticks; 2000 seconds - 33 minutes.

        player.getWorld().createExplosion(player.getLocation(), 5F, false);
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 40, 0)));

        player.getWorld().strikeLightning(player.getLocation());
        Ban ban = Ban.forPlayer(player, sender);
        ban.setReason(reason);
        for (String playerIp : plugin.pl.getData(player).getIps())
        {
            ban.addIp(playerIp);
        }
        plugin.bm.addBan(ban);
        player.kickPlayer(ChatColor.RED + "Welcome to hell.");
        return true;
     }

        return true;
    }
}
