/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
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

public class Command_slap
{
    public boolean run(final CommandSender sender, Player player, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FUtil.bcastMsg(ChatColor.BLUE + sender.getName() + " gave " + player.getName() + " a hard slap in the face!");
        return true;
    }
}