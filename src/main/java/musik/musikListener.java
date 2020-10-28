package main.java.musik;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import main.java.de.rapha.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class musikListener extends ListenerAdapter {
	
    @SuppressWarnings("unused")
	private static final int PLAYLIST_LIMIT = 1000;
    private static final AudioPlayerManager MANAGER = new DefaultAudioPlayerManager();



    public musikListener() {
        AudioSourceManagers.registerRemoteSources(MANAGER);
    }


    private String getTimestamp(long milis) {
        long seconds = milis / 1000;
        long hours = Math.floorDiv(seconds, 3600);
        seconds = seconds - (hours * 3600);
        long mins = Math.floorDiv(seconds, 60);
        seconds = seconds - (mins * 60);
        return (hours == 0 ? "" : hours + ":") + String.format("%02d", mins) + ":" + String.format("%02d", seconds);
    }

    @SuppressWarnings("unused")
	private String buildQueueMessage(AudioInfo info) {
        AudioTrackInfo trackInfo = info.getTrack().getInfo();
        String title = trackInfo.title;
        long length = trackInfo.length;
        return "`[ " + getTimestamp(length) + " ]` " + title + "\n";
    }
	
	@Override
	public void onMessageReactionAdd (MessageReactionAddEvent e) {
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("🔍")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
					e.getReaction().removeReaction(e.getMember().getUser()).queue();

				    MusicController controller = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	                AudioTrack track = controller.getPlayer().getPlayingTrack();
	                AudioTrackInfo info = track.getInfo();
	                e.getTextChannel().sendMessage(
	                        new EmbedBuilder()
	                                .setDescription("**CURRENT TRACK INFO:**")
	                                .addField("Title", "`" + info.title + "`", false)
	                                .addField("Duration", "`[ " + getTimestamp(track.getPosition()) + "/ " + getTimestamp(track.getDuration()) + " ]`", false)
	                                .addField("Author", "`" + info.author + "`", false)
	                                .build()
	               ).complete().delete().queueAfter(10, TimeUnit.SECONDS);
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("⏸️")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					e.getReaction().removeReaction(e.getMember().getUser()).queue();
					
					MusicController controller = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	AudioPlayer player = controller.getPlayer();
	            	
	            	player.setPaused(true);
	            	
	            	e.getTextChannel().sendMessage(
	                        new EmbedBuilder().setColor(Color.ORANGE).setDescription(":pause_button: Song paused").build()
	            	).complete().delete().queueAfter(5, TimeUnit.SECONDS);
	            	
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("▶️")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					e.getReaction().removeReaction(e.getMember().getUser()).queue();
					
					MusicController controller = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	AudioPlayer player = controller.getPlayer();
	            	
	            	player.setPaused(false);
	            	
	            	e.getTextChannel().sendMessage(
	                        new EmbedBuilder().setColor(Color.ORANGE).setDescription(":arrow_forward: Continue playing song").build()
	            	).complete().delete().queueAfter(5, TimeUnit.SECONDS);
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("🔀")) {
				
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					MusicController controller3 = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	controller3.getQueue().shuffle();
	            	
	            	e.getTextChannel().sendMessage(
	                        new EmbedBuilder().setColor(Color.GREEN).setDescription(":twisted_rightwards_arrows:  Shuffle modus activated").build()
	            	).complete().delete().queueAfter(5, TimeUnit.SECONDS);
					
				}
				
			} 
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("⏭️")) {
				
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					e.getReaction().removeReaction(e.getMember().getUser()).queue();
					MusicController controller3 = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	controller3.getQueue().next();
	            	e.getTextChannel().sendMessage(
	                        new EmbedBuilder().setColor(Color.ORANGE).setDescription(":track_next: Skipped").build()
	            	).complete().delete().queueAfter(5, TimeUnit.SECONDS);
	            	
					
				}
				
			}
				if(e.getReactionEmote().getEmoji().equalsIgnoreCase("🎚️")) {
				
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					e.getReaction().removeReaction(e.getMember().getUser()).queue();
					
					e.getTextChannel().sendMessage(
	                        new EmbedBuilder().setColor(Color.GREEN).setDescription(
	                        		"**Audio settings:** \n" + 
	                        		"\r\n" + 
	                        		":arrow_up: = Volume up \r\n" + 
	                        		":arrow_down:  = Volume down \r\n" + 
	                        		":white_check_mark: = Finish").build()
	                ).queue(msg ->{
	                	msg.addReaction("⬆️").queue();
	                	msg.addReaction("⬇️").queue();
	                	msg.addReaction("✅").queue();
	                });
	            	
					
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("⛔")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					MusicController controller = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	AudioManager manager = e.getGuild().getAudioManager();
	            	AudioPlayer player = controller.getPlayer();
	            	
	            	player.stopTrack();
	            	controller.getQueue().getQueuelist().clear();
	            	manager.closeAudioConnection();
	            	
	            	e.getGuild().getTextChannelsByName("musik", true).get(0).deleteMessageById(e.getMessageId()).queue();
	            	
					e.getChannel().purgeMessages(get(e.getChannel(), 1000));
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("⬆️")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					e.getReaction().removeReaction(e.getMember().getUser()).queue();
					
					MusicController controller = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	AudioPlayer player = controller.getPlayer();
	            	
	            	int i = player.getVolume();
	            	i++;
	            	i++;
	            	i++;
	            	i++;
	            	i++;
	            	player.setVolume(i);
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("⬇️")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
					
					e.getReaction().removeReaction(e.getMember().getUser()).queue();
					
					MusicController controller = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
	            	AudioPlayer player = controller.getPlayer();
	            	
	            	int i = player.getVolume();
	            	i--;
	            	i--;
	            	i--;
	            	i--;
	            	i--;
	            	player.setVolume(i);
				}
				
			}
			if(e.getReactionEmote().getEmoji().equalsIgnoreCase("✅")) {
				if(e.getMember().getUser().isBot()) {
					
				} else {
	            	
					e.getGuild().getTextChannelsByName("musik", true).get(0).deleteMessageById(e.getMessageId()).queue();
				}
				
			}
	}
	@Override
	public void onGuildMessageReactionRemove (GuildMessageReactionRemoveEvent e) {
		if(e.getReactionEmote().getEmoji().equalsIgnoreCase("🔀")) {
			
			if(e.getMember().getUser().isBot()) {
				
			} else {
				
				e.getReaction().removeReaction(e.getMember().getUser()).queue();
				
				//MusicController controller3 = Main.INSTANCE.playerManager.getController(e.getGuild().getIdLong());
            	//controller3.getQueue().shuffle();
            	
            	e.getChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.RED).setDescription(":twisted_rightwards_arrows:  Shuffle modus deactivated").build()
            	).complete().delete().queueAfter(5, TimeUnit.SECONDS);
				
			}
			
		} 
	}
	
public List<Message> get(MessageChannel channel, int amount){
		List<Message> messages = new ArrayList<Message>();
		int i = amount + 1;
		
		for(Message message : channel.getIterableHistory().cache(false)) {
			if(!message.isPinned()) {
				messages.add(message);
			}
			if(--i <= 0) break;
		}
		
		return messages;
		
	}
}
