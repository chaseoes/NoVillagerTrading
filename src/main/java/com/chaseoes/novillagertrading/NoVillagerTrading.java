package com.chaseoes.novillagertrading;

import java.io.IOException;

import net.minecraft.server.v1_6_R2.MerchantRecipeList;
import net.minecraft.server.v1_6_R2.NBTTagCompound;

import org.bukkit.craftbukkit.v1_6_R2.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class NoVillagerTrading extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit Metrics.
        }
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
