package main.java.commands;

import java.awt.Color;

import main.java.commandmanager.ServerCommand;
import main.java.util.SECRETS;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Colores implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		if(e.getMember().getRoles().toString().contains(SECRETS.GL)){
			
			EmbedBuilder red = new EmbedBuilder();
			red.setColor(Color.RED);
			red.setDescription("Red!");
	        e.getTextChannel().sendMessage(red.build()).queue();
	        
	        EmbedBuilder black = new EmbedBuilder();
	        black.setColor(Color.BLACK);
	        black.setDescription("Black!");
	        e.getTextChannel().sendMessage(black.build()).queue();
	        
	        EmbedBuilder blue = new EmbedBuilder();
	        blue.setColor(Color.BLUE);
	        blue.setDescription("Blue!!");
	        e.getTextChannel().sendMessage(blue.build()).queue();
	        
	        EmbedBuilder cyan = new EmbedBuilder();
	        cyan.setColor(Color.CYAN);
	        cyan.setDescription("Cyan!");
	        e.getTextChannel().sendMessage(cyan.build()).queue();
	        
	        EmbedBuilder drak_gray = new EmbedBuilder();
	        drak_gray.setColor(Color.DARK_GRAY);
	        drak_gray.setDescription("Dark_Gray!");
	        e.getTextChannel().sendMessage(drak_gray.build()).queue();
	        
	        EmbedBuilder gray = new EmbedBuilder();
	        gray.setColor(Color.GRAY);
	        gray.setDescription("Gray!!");
	        e.getTextChannel().sendMessage(gray.build()).queue();
	        
	        EmbedBuilder green = new EmbedBuilder();
	        green.setColor(Color.GREEN);
	        green.setDescription("Green!!");
	        e.getTextChannel().sendMessage(green.build()).queue();
	        
	        EmbedBuilder light_gray = new EmbedBuilder();
	        light_gray.setColor(Color.LIGHT_GRAY);
	        light_gray.setDescription("Light_Gray!");
	        e.getTextChannel().sendMessage(light_gray.build()).queue();
	        
	        EmbedBuilder magenta = new EmbedBuilder();
	        magenta.setColor(Color.MAGENTA);
	        magenta.setDescription("Magenta!");
	        e.getTextChannel().sendMessage(magenta.build()).queue();
	        
	        EmbedBuilder orange = new EmbedBuilder();
	        orange.setColor(Color.ORANGE);
	        orange.setDescription("Orange!");
	        e.getTextChannel().sendMessage(orange.build()).queue();
	        
	        EmbedBuilder pink = new EmbedBuilder();
	        pink.setColor(Color.PINK);
	        pink.setDescription("Pink!");
	        e.getTextChannel().sendMessage(pink.build()).queue();
	        
	        EmbedBuilder white = new EmbedBuilder();
	        white.setColor(Color.WHITE);
	        white.setDescription("White!");
	        e.getTextChannel().sendMessage(white.build()).queue();
	        
	        EmbedBuilder yellow = new EmbedBuilder();
	        yellow.setColor(Color.YELLOW);
	        yellow.setDescription("Yellow!");
	        e.getTextChannel().sendMessage(yellow.build()).queue();
	        
		}
		
	}

}
