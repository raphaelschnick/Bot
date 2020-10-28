package main.java.mute;

import java.awt.Color; 

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import main.java.util.SECRETS;

import java.util.concurrent.TimeUnit;


public class muteListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		String role = "717472141301383290";
		
		String message = e.getMessage().getContentDisplay().toLowerCase();
		
		if(e.isFromType(ChannelType.TEXT)) {
			if(e.getMember().getRoles().toString().contains(SECRETS.GL)){
					
			} else {
				if(message.contains("hure") || message.contains("hurensohn") || message.contains("wixxer") || message.contains("spast") || message.contains("schlampe")|| message.contains("bastard")) {
					//Add Role + delete message
					e.getMessage().delete().queue();
                    e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(role)).queue();
                    
                    //public
                    EmbedBuilder mute = new EmbedBuilder();
                    mute.setColor(Color.red);
                    mute.setDescription(e.getMember().getAsMention() + " is now muted for `7 Days`.");
                    e.getTextChannel().sendMessage(mute.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
                    
                    //#mute-log
                    EmbedBuilder mute2 = new EmbedBuilder();
                    mute2.setColor(Color.red);
                    mute2.setDescription(e.getMember().getUser().getAsTag() + " is now muted for `7 Days` from `System`.");
                    e.getGuild().getTextChannelsByName("mute-log", true).get(0).sendMessage(mute2.build()).queue();
                    
                    //Role remove after 7 Days
                    e.getGuild().removeRoleFromMember(e.getMember(), e.getGuild().getRoleById(role)).queueAfter(7, TimeUnit.DAYS);
				}
				
			}
		}
	}
}
		
		

