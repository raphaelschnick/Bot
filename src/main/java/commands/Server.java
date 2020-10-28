package main.java.commands;

import java.awt.Color;

import main.java.commandmanager.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Server implements ServerCommand {

		
      
		

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		  channel.sendMessage(


			        new EmbedBuilder().setColor(Color.YELLOW).setDescription(
			                ":pushpin:  **Server Stats:**" +
			                "\n" +
			                "\n" +
			                ":bust_in_silhouette:  	**Member:** " + e.getGuild().getMemberCount() + "\n" +
			                "\n" +
			                ":earth_africa:  		**Region:** " + e.getGuild().getRegionRaw() + "\n" +
			                "\n" +
			                ":hammer:         		**Created:** " + e.getGuild().getTimeCreated().getDayOfMonth() + "." + e.getGuild().getTimeCreated().getMonth() + "." + e.getGuild().getTimeCreated().getYear() + "\n" +
			                "\n" +
			                ":diamonds:       		**Server Booster:** " + e.getGuild().getBoosters().size() + "\n" +
			                "\n" +
			                ":level_slider:   		**Server Booster Level:** " + e.getGuild().getBoostTier().getKey() + "\n" +
			                "\n" +
			                ":file_folder:    		**Categories:** " + e.getGuild().getCategories().size() + "\n" +
			                "\n" +
			                ":pencil:         		**Text-Channel:** " + e.getGuild().getChannels().size() + "\n" +
			                "\n" +
			                ":loud_sound:           **Voice-Channel:** " + e.getGuild().getVoiceChannels().size() + "\n" +
			                "\n" +
			                ":zany_face:            **Emotes:** " + e.getGuild().getEmotes().size() + "\n").build()
			        ).complete();

	}

}
