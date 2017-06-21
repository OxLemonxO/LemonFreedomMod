package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import me.totalfreedom.totalfreedommod.player.FPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SYSTEM_ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Unleash the power of the BanHammer..", usage = "/<command>")

public class Command_banhammer extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FPlayer pl = plugin.pl.getPlayer(playerSender);
        if(pl.hasBanHammer())
        {
            playerSender.getInventory().remove(FUtil.getBanHammer());
            FUtil.bcastMsg(ChatColor.AQUA + playerSender.getName() + " has placed the BanHammer back into its sheath");
            pl.setBanHammer(false);
            return true;
        }
        
        pl.setBanHammer(true); //Mmmm BanHammer boi!
         
        playerSender.getInventory().addItem(FUtil.getBanHammer());
        playerSender.getWorld().strikeLightning(playerSender.getLocation());
        FUtil.bcastMsg(ChatColor.RED + sender.getName() + " has unleashed the BanHammer!");
        return true;
    }
}
