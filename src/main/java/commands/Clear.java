package main.java.commands;


import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.java.commandmanager.ServerCommand;
import main.java.util.SECRETS;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Clear implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
			String[] args = message.getContentDisplay().split(" ");
			
			if(e.getMember().getRoles().toString().contains(SECRETS.GL) || e.getMember().getRoles().toString().contains(SECRETS.Chefentwickler)){
				message.delete().queue();
				if(args.length == 2) {
				try {
					int amount = Integer.parseInt(args[1]);
					channel.purgeMessages(get(channel, amount));
					channel.sendMessage("`" + amount + "` messages deleted!").complete().delete().queueAfter(3, TimeUnit.SECONDS);
					return;
				} catch (NumberFormatException e2) {
					e2.printStackTrace();
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
