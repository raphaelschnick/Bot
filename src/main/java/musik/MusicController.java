package main.java.musik;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import main.java.de.rapha.main.Main;
import net.dv8tion.jda.api.entities.Guild;

public class MusicController {
	
	private Guild guild;
	private AudioPlayer player;
	private Queue queue;
	
	public MusicController(Guild guild) {
		this.guild = guild;
		this.player = Main.INSTANCE.audioPlayerManager.createPlayer();
		this.queue = new Queue(this);
		
		this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
		this.player.setVolume(10);
	}
	public Guild getGuild() {
		return guild;
	}
	public AudioPlayer getPlayer() {
		return player;
	}
	
	public Queue getQueue() {
		return queue;
	}

}
