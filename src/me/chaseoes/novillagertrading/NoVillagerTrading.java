package me.chaseoes.novillagertrading;

import net.minecraft.server.v1_5_R3.MerchantRecipeList;
import net.minecraft.server.v1_5_R3.NBTTagCompound;

import org.bukkit.craftbukkit.v1_5_R3.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoVillagerTrading extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityEnteract(PlayerInteractEntityEvent event) {
        if (!event.getPlayer().hasPermission("novillagertrading.bypass")) {
            Entity e = event.getRightClicked();
            if (e instanceof Villager) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setCompound("Offers", new MerchantRecipeList().a());
                ((CraftVillager) (Villager) e).getHandle().a(nbt);
            }
        }
    }

}
