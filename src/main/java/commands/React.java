package main.java.commands;

import java.awt.Color;
import java.util.List;

import main.java.commandmanager.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import main.java.util.SECRETS;

public class React implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		String[] args = message.getContentDisplay().split(" ");
		List<TextChannel> channels = message.getMentionedChannels();
		
		if(e.getMember().getRoles().toString().contains(SECRETS.GL)){
		if(!channels.isEmpty()) {
			TextChannel tc = message.getMentionedChannels().get(0);
			String messageIDString = args[2];
			
			try {
				long messageID = Long.parseLong(messageIDString);
				
				for(int i = 3; i < args.length; i++) {
					tc.addReactionById(messageID, args[i]).queue();
				}
			} catch (NumberFormatException e2) {
				
			}
		}
		
	} else {
		//No Permission
		EmbedBuilder perm = new EmbedBuilder();
		perm.setColor(Color.red);
		perm.setDescription(":warning: " + e.getMember().getAsMention() + ", you don't have the permissions to use this command!");
        e.getTextChannel().sendMessage(perm.build()).queue();
	}
	}

}
