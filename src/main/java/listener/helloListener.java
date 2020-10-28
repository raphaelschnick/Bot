package main.java.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class helloListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived (MessageReceivedEvent e) {
		if(e.getMember().getUser().isBot()) {
			
		} else {
		if(e.getMessage().getContentDisplay().toLowerCase().startsWith("hallo")) {
			e.getTextChannel().sendMessage("Hey").queue();
		}
		if(e.getMessage().getContentDisplay().toLowerCase().startsWith("hi")) {
			e.getTextChannel().sendMessage("Hallo").queue();
		}
		if(e.getMessage().getContentDisplay().toLowerCase().startsWith("hey")) {
			e.getTextChannel().sendMessage("Moin").queue();
		}
		if(e.getMessage().getContentDisplay().toLowerCase().startsWith("hello")) {
			e.getTextChannel().sendMessage("Hi").queue();
		}
		}
	}

}
