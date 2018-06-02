package fr.utarwyn.superjukebox.commands.music;

import fr.utarwyn.superjukebox.Config;
import fr.utarwyn.superjukebox.SuperJukebox;
import fr.utarwyn.superjukebox.commands.AbstractCommand;
import fr.utarwyn.superjukebox.commands.parameter.Parameter;
import fr.utarwyn.superjukebox.music.MusicManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MusicImportCommand extends AbstractCommand {

	private MusicManager manager;

	MusicImportCommand() {
		super("import", "i");

		this.manager = SuperJukebox.getInstance().getInstance(MusicManager.class);

		this.addParameter(Parameter.STRING);
	}

	@Override
	public void perform(CommandSender sender) {

	}

	@Override
	public void performPlayer(Player player) {
		String endpoint = this.readArg();

		Bukkit.getScheduler().runTaskAsynchronously(SuperJukebox.getInstance(), () -> {
            switch (this.manager.importMusic(endpoint)) {
                case GOOD:
                    player.sendMessage(Config.PREFIX + "§aMusic imported and ready to be played!");
                    break;
                case MALFORMATED_URL:
                    player.sendMessage(Config.PREFIX + "§cWe cannot import your music: check your url.");
                    break;
                case UNKNOWN_FILE:
                    player.sendMessage(Config.PREFIX + "§cWe cannot import your music: unknown file.");
                    break;
                case DECODING_ERROR:
                    player.sendMessage(Config.PREFIX + "§cWe cannot import your music: nbs decoding error.");
                    break;
                case ALREADY_IMPORTED:
                    player.sendMessage(Config.PREFIX + "§cWe cannot import your music: music already imported. Check your console to have more info.");
                    break;
            }
		});
	}

	@Override
	public void performConsole(CommandSender sender) {

	}

}
