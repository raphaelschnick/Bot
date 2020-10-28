package main.java.commands;

import java.awt.Color;

import main.java.commandmanager.ServerCommand;
import main.java.util.SECRETS;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		channel.sendMessage(
		        new EmbedBuilder().setColor(Color.GREEN).setDescription(
		        		":pushpin: **PREFIX:**  -\r\n" + 
		        		"\r\n" + 
		        		":bulb: `-server` --> Get the latest server stats.\r\n" + 
		        		"\r\n" + 
		        		":no_mobile_phones: `-meme`  -->  Get a funny meme\r\n" + 
		        		"\r\n" + 
		        		":headphones: `-m help` --> Get help for all music commands").build()
		        ).complete();
		if(e.getMember().getRoles().toString().contains(SECRETS.GL) || e.getMember().getRoles().toString().contains(SECRETS.Chefentwickler)){
		channel.sendMessage(
		        new EmbedBuilder().setColor(Color.ORANGE).setDescription(
		        		":lock: **Team help** \r\n" + 
		        		"\r\n" + 
		        		":pencil2: `-clear <number>`  --> Clear messages\r\n" + 
		        		"\r\n" + 
		        		":gear: `-info <@member>` --> Get all information about a member\r\n" + 
		        		"\r\n" + 
		        		":no_mobile_phones: `-mute <@member>` --> Mute a Member\r\n" + 
		        		"\r\n" + 
		        		":speaking_head: `-unmute <@member>` --> Unmute a Member").build()
		        ).complete();
		} 
		if(e.getMember().getRoles().toString().contains(SECRETS.GL)){
		channel.sendMessage(
        new EmbedBuilder().setColor(Color.RED).setDescription(
        		":closed_lock_with_key: **GL help**  \r\n" + 
        		"\r\n" + 
        		":joy: `-react <#channel> <messageid> <reaction> <reaction> ...` --> React to a \r\n" + 
        		"message with the bot").build()
        ).complete();
		}
	}
	
	

}
