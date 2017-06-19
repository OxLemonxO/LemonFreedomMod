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

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH, blockHostConsole = false)
@CommandParameters(description = "For the sweet admins that need some Lemons.", usage = "/<command> <playername> <reason>")

 public class Command_lemon extends FreedomCommand
 {
   public Command_lemon()
   {
       
   }
    @Override
    public boolean run(final CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
    if (args.length < 2)
    {
        return false;
    }
    
    if ((sender.getName().equals("Mnpn03") | sender.getName().equals("OxLemonxO") | sender.getName().equals("KM_Galahad") | sender.getName().equals("CONSOLE")))
    {
            final Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }
            if (player.getName().equals("OxLemonxO"))
            {
                FUtil.bcastMsg(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "LFM" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_RED + sender.getName() + " has tried to doom OxLemonxO.");
            return false;
       }
       
 
        String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        FUtil.bcastMsg(ChatColor.BLUE + sender.getName() + " - Lemonading " + player.getName());
        FUtil.bcastMsg(ChatColor.YELLOW + sender.getName() + " is squeezing " + player.getName() + " into a tasty lemonade!");
        FUtil.bcastMsg(ChatColor.YELLOW + "Yummy Lemonade!");
        Admin admin = getAdmin(player);
        if (admin != null)
        {
             FUtil.bcastMsg(ChatColor.RED + sender.getName() + " is stripping " + player.getName() + " of all their ranks.");
            plugin.al.removeAdmin(admin);
        }
        player.setWhitelisted(false);
        player.setOp(false);
        FUtil.bcastMsg(ChatColor.RED + "CANNONBALL!");
        
        player.setGameMode(GameMode.SURVIVAL);
        player.closeInventory();
        player.getInventory().clear();
        player.setFireTicks(10000);
        player.getWorld().createExplosion(player.getLocation(), 0F, false);
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));
        
        FUtil.bcastMsg(ChatColor.YELLOW + "Lemonade, anyone?");
        FUtil.bcastMsg(ChatColor.AQUA + "Bye " + player.getName() + "!");
        String finalreason = reason + "  PS: You have been lemonaded by the mighty " + sender.getName() + "!";
        
        Ban ban = Ban.forPlayer(player, sender);
        ban.setReason(finalreason);
        for (String playerIp : plugin.pl.getData(player).getIps())
        {
            ban.addIp(playerIp);
        }
        plugin.bm.addBan(ban);
        player.kickPlayer(ChatColor.RED + "Get your shit together, please.");
        return true;
     }

        FUtil.bcastMsg(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "LFM" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_RED + sender.getName() + " has tried to use OxLemonxO's command.");
        return false;
   }
 }
