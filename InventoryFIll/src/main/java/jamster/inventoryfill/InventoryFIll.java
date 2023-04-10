package jamster.inventoryfill;



import jamster.inventoryfill.Commands.Pot;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryFIll extends JavaPlugin {
    private static Economy econ = null;

    @Override
    public void onEnable() {
        getCommand("pot").setExecutor(new Pot());
        getCommand("pots").setExecutor(new Pot());
        getCommand("p").setExecutor(new Pot());
        getCommand("fill").setExecutor(new Pot());
        if (!setupEconomy() ) {
            System.out.println("No eco detected");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
    private boolean setupEconomy() {
        if(getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

        if(rsp == null) {
            return false;
        }

        econ = rsp.getProvider();

        return econ != null;
    }
    public static Economy getEcon() {
        return econ;
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
