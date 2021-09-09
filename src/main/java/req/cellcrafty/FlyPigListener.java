package req.cellcrafty;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.HashSet;
import java.util.Set;

public class FlyPigListener implements Listener {
    Set<LivingEntity> flyingpigs= new HashSet<LivingEntity>();

    @EventHandler
    public void onPigRidden(EntityMountEvent e){
        var ridden = (LivingEntity)e.getMount();
        var riddentype = ridden.getType();
        if(riddentype== EntityType.PIG){
            flyingpigs.add(ridden);
            ridden.setGravity(false);
        }
    }

    @EventHandler
    public void onPigRiddenTick(ServerTickStartEvent e){
        for(LivingEntity pig : flyingpigs){
            var loc = pig.getLocation().getDirection();
            pig.setVelocity(loc.multiply(0.4));
        }
    }
    @EventHandler
    public void onRiddenPigDamaged(EntityDamageEvent e){
        if(flyingpigs.contains(e.getEntity())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPigLeft(EntityDismountEvent e){
        var pig = e.getDismounted();
        if(flyingpigs.contains(pig)){
            flyingpigs.remove(pig);
        }
        pig.setGravity(true);
    }

}
