package me.totalfreedom.totalfreedommod;

import me.totalfreedom.totalfreedommod.banning.Ban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.util.Vector;
import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;

public class BanHammer extends FreedomService
{

    public BanHammer(TotalFreedomMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
    }

    @Override
    protected void onStop()
    {
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        FPlayer pl = plugin.pl.getPlayer(player);
        if (item == null)
        {
            return;
        }
        if (item.equals(FUtil.getBanHammer()) && pl.hasBanHammer())
        {
            final Entity e = pl.getTargetEntity(50);
        if (e instanceof Player)
        {
            Player eplayer = (Player) e;
            plugin.bm.addBan(Ban.forPlayerFuzzy(eplayer, player, null, "You were hit by " + player.getName() + "'s Ban Hammer."));
            player.kickPlayer(ChatColor.RED + "Hit by " + player.getName() + "'s Ban Hammer.");
        }
        else if (e instanceof LivingEntity)
        {
            final LivingEntity le = (LivingEntity) e;
            le.setVelocity(le.getVelocity().add(new Vector(0, 3, 0)));
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    le.getWorld().createExplosion(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 5f, false, false);
                    le.getWorld().strikeLightningEffect(e.getLocation());
                    le.setHealth(0d);
                }
            }.runTaskLater(TotalFreedomMod.plugin(), 20L * 2L);

        }
    }
}
}
