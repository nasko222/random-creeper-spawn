package io.nasko222.name.randomcreeper;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class startCommand implements CommandExecutor {

	private Main plugin;
	public boolean isRunning = false;

	public startCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("creeper").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Console cannot use this command!");
			return false;
		}
		Player p = (Player) sender;
		if (!p.isOp()) {
			p.sendMessage("You need to be op to start my creeper plugin!");
			return false;
		}
		else if (!isRunning) {
			for (final Player p1 : Bukkit.getOnlinePlayers()){p1.sendMessage("Creeper machine is turned on, Beware every minute!");}	
			isRunning = true;
	        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable()
	        {
	        	Random random = new Random();
	            int time = random.nextInt(60) + 60;
				@Override
	            public void run()
	            {
	                if (this.time == 0)
	                {
	                	for (final Player p2 : Bukkit.getOnlinePlayers()){p2.sendMessage("aww man!");}
	                	Player[] targetPlayer = p.getServer().getOnlinePlayers();
	                	for (int i = 0; i < targetPlayer.length; i++)
	                	{
	                		if (!(targetPlayer[i].getGameMode() == GameMode.CREATIVE))
	                		{
			                	Location targetLocation = targetPlayer[i].getLocation();
			                	targetPlayer[i].getWorld().spawnEntity(targetLocation, EntityType.CREEPER);
	                		}
	                	}
	                	time = random.nextInt(60) + 60;
	                	return;
	                }
	                this.time--;
	            }
	        }, 0L, 20L);
	        return false;
		}
		else if (isRunning) {
			for (final Player p3 : Bukkit.getOnlinePlayers()){p3.sendMessage("Creeper machine is off, Have a nice day!");}
			Bukkit.getScheduler().cancelAllTasks();
			isRunning = false;
			return false;
		}
		return true;
	}
}
