package io.nasko222.name.randomcreeper;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		new startCommand(this);
	}
}
