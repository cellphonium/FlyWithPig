package req.cellcrafty;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyWithPig extends JavaPlugin {
    @Override
    public void onEnable(){
        var manager = this.getServer().getPluginManager();
        manager.registerEvents(new FlyPigListener(), this);
    }
    @Override
    public void onDisable(){
        HandlerList.unregisterAll();
    }
}
