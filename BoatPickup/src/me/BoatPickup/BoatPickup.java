package me.BoatPickup;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BoatPickup extends JavaPlugin implements Listener {

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable()
    {}

    @EventHandler
    public void onExitVehicle(VehicleExitEvent event)
    {
        Vehicle vehicle = event.getVehicle();
        if(vehicle.getType() == EntityType.BOAT)
        {
            if(event.getExited().hasPermission("BoatPickup.boat.pickup"))
            {
                if(event.getExited() instanceof Player
                        && !vehicle.isDead()
                        && vehicle.getPassengers().size() == 1
                        && ((Player) event.getExited()).getInventory().firstEmpty() != -1) // Check if inventory is empty
                {
                    vehicle.remove();
                    Material mat;
                    switch(((Boat)vehicle).getWoodType()) {
                        default:
                            mat = Material.OAK_BOAT;
                            break;
                        case DARK_OAK:
                            mat = Material.DARK_OAK_BOAT;
                            break;
                        case ACACIA:
                            mat = Material.ACACIA_BOAT;
                            break;
                        case REDWOOD:
                            mat = Material.SPRUCE_BOAT;
                            break;
                        case JUNGLE:
                            mat = Material.JUNGLE_BOAT;
                            break;
                        case BIRCH:
                            mat = Material.BIRCH_BOAT;
                            break;
                    }
                    ((Player)event.getExited()).getInventory().addItem(new ItemStack(mat));
                }
            }
        }
    }
}
