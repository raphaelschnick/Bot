package main.java.commands;

import main.java.commandmanager.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Argh implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		e.getTextChannel().sendMessage("Argh, Bebisch!").queue();
		
	}
	
	
	
	

}
