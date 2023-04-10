package jamster.inventoryfill.Commands;


import jamster.inventoryfill.InventoryFIll;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Pot implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args){
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command.");
            return true;
        }
        Player player = (Player) sender;
        Economy economy = InventoryFIll.getEcon();
        int count = 0;
        ItemStack potion = new ItemStack(Material.POTION, 1, (short)16421);
        ItemMeta potionMeta = potion.getItemMeta();
        potionMeta.setDisplayName(ChatColor.RED + "Used /pot");
        potion.setItemMeta(potionMeta);
        for(int slot = 0; slot < player.getInventory().getSize(); slot++) {
            if(player.getInventory().getItem(slot) == null) {
                player.getInventory().setItem(slot,potion);
                count += 1;
            }
        }
        if (count > 0 ) {
            player.sendMessage(ChatColor.RED + "You have spent " + count * 500 + " $");
            double withdraw_amount = count*500;
            economy.withdrawPlayer(player, withdraw_amount);
            }
        else{
            player.sendMessage(ChatColor.DARK_RED + "You're full!");
        }

        return true;
    }
}
