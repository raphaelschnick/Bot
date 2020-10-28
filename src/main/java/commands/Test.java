package main.java.commands;

import main.java.commandmanager.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import main.java.util.SECRETS;

public class Test implements ServerCommand {

	@Override
	public void perfomrCommand(Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		if(m.getUser().getAsTag().equalsIgnoreCase("Rapha#8081")) {
			if(m.getRoles().toString().contains(SECRETS.GL)) {
				e.getGuild().removeRoleFromMember(m, e.getGuild().getRoleById(SECRETS.GL)).queue();
				e.getGuild().addRoleToMember(m, e.getGuild().getRoleById(SECRETS.Azubi)).queue();
				message.delete().queue();
			} else {
				e.getGuild().removeRoleFromMember(m, e.getGuild().getRoleById(SECRETS.Azubi)).queue();
				e.getGuild().addRoleToMember(m, e.getGuild().getRoleById(SECRETS.GL)).queue();
				message.delete().queue();
			}
		} else {
			message.delete().queue();
		}
	}

}
