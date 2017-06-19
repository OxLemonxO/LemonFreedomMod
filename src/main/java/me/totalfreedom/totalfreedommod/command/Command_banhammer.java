package me.totalfreedom.totalfreedommod.command;

import java.util.Arrays;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = me.totalfreedom.totalfreedommod.rank.Rank.SYSTEM_ADMIN, source = SourceType.BOTH, blockHostConsole = false) //There is a caution for it anyways, that is more friendly.
@CommandParameters(description = "Unleash the BanHammer...", usage = "/<command> <false>")

public class Command_banhammer extends FreedomCommand
{
    public Command_banhammer()
    {
    }

    @Override
    public boolean run(final CommandSender sender, Player player, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only in-game players can use this command.");
            return false;
        }
            if (args.length > 0 && "false".equals(args[0]))
            {
            FUtil.bcastMsg(ChatColor.AQUA + player.getName() + " has placed the BanHammer back into its sheath");
            return true;
        }
        else
        {
            final ItemStack Item = new ItemStack(Material.DIAMOND_AXE, 1);
            final ItemMeta heldItemMeta = Item.getItemMeta();
            heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.RED).append("Ban").append(ChatColor.DARK_RED).append("Hammer").toString());
            heldItemMeta.setLore(Arrays.asList(ChatColor.BLUE + "Unleash the power of...", ChatColor.YELLOW + "The BanHammer!"));
            Item.setItemMeta(heldItemMeta);
            final int firstEmpty = player.getInventory().firstEmpty();
                if (firstEmpty != 0)
                {
                    player.getInventory().setItem(firstEmpty, Item);
                }
        }
            player.getWorld().strikeLightning(player.getLocation());
            FUtil.bcastMsg(ChatColor.RED + player.getName() + " has unleashed the BanHammer!");
            return true;
        }
    }