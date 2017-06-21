/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author mnpn0
 */
@CommandPermissions(level = Rank.OP, source = SourceType.BOTH, blockHostConsole = false)
@CommandParameters(description = "Slap someone.", usage = "/<command> <playername>")

public class Command_slap extends FreedomCommand
        
{
    
    public Command_slap()
    {
       
    }
       
    @Override
    public boolean run(final CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            return false; 
        }
        
        final Player player = getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player \"" + args + "\" not found!");
            return true;
        }
        
        
        FUtil.bcastMsg(ChatColor.RED + sender.getName() + ChatColor.YELLOW + " gave " + ChatColor.RED + player.getName() + ChatColor.YELLOW + " a nice slap in the face!");
        return true;
    }
}