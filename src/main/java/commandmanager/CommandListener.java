package main.java.commandmanager;


import java.awt.Color;

import main.java.de.rapha.main.Main;
import main.java.util.SECRETS;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		String message = e.getMessage().getContentDisplay();
		
		if(e.isFromType(ChannelType.TEXT)) {
			TextChannel channel = e.getTextChannel();
			
			if(message.startsWith(SECRETS.PREFIX)) {
				String[] args = message.substring(1).split(" ");
				
				/*if(!channel.getName().equalsIgnoreCase("bot")) {
					if(Permissions.check_team(e)) {
						channel.sendMessage(
								new EmbedBuilder().setColor(Color.RED).setDescription("Please us Bot commands only in #bot!").build()).complete();
					}
				}*/
				if(args.length > 0) {
						if(!Main.INSTANCE.getCmdMan().perform(args[0], e.getMember(), channel, e.getMessage(), e)) {
							channel.sendMessage(
							new EmbedBuilder().setColor(Color.RED).setDescription("Use `!help`").build()).complete();
						}
					}
				}
			}
			
		}
}
