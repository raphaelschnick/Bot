package main.java.commandmanager;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ServerCommand {
	
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e);


}
