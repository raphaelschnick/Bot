package main.java.mute;

import java.awt.Color;

import main.java.commandmanager.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import main.java.util.SECRETS;

public class Unmute implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		String[] args = message.getContentDisplay().split(" ");
		String role = "Mute";

		if(e.getMember().getRoles().toString().contains(SECRETS.GL)){
		
		if(args.length > 2) {

				message.delete().queue();
				e.getGuild().removeRoleFromMember(e.getMessage().getMentionedMembers().get(0), e.getGuild().getRolesByName(role, true).get(0)).queue();
				
				//public
	            EmbedBuilder mute = new EmbedBuilder();
	            mute.setColor(Color.green);
	            mute.setDescription(e.getMessage().getMentionedMembers().get(0).getAsMention() + " is now unmuted");
	            e.getTextChannel().sendMessage(mute.build()).queue();
	            
	            //#mute-log
	            EmbedBuilder mute2 = new EmbedBuilder();
	            mute2.setColor(Color.green);
	            mute2.setDescription(e.getMessage().getMentionedMembers().get(0).getUser().getAsTag() + " is now unmuted from `" + e.getMember().getUser().getAsMention() + "`.");
	            e.getGuild().getTextChannelsByName("mute-log", true).get(0).sendMessage(mute2.build()).queue();

		} else {
			EmbedBuilder error = new EmbedBuilder();
            error.setColor(Color.red);
            error.setDescription("Use `-unmute <@member>`");
            e.getTextChannel().sendMessage(error.build()).queue();
		}
		
		} else {
			//Keine Permission
			EmbedBuilder perm = new EmbedBuilder();
			perm.setColor(Color.red);
			perm.setDescription(":warning: " + e.getMember().getAsMention() + ", you don't have the permissions to use this command!");
            e.getTextChannel().sendMessage(perm.build()).queue();
		}
	}

}
