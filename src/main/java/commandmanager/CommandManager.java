package main.java.commandmanager;

import main.java.commands.*;
import main.java.musik.Musik;
import main.java.mute.Mute;
import main.java.mute.Unmute;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
	
	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager() {
		this.commands = new ConcurrentHashMap<>();
		
		this.commands.put("clear", new Clear());
		this.commands.put("info", new Info());
		this.commands.put("help", new Help());
		this.commands.put("server", new Server());
		this.commands.put("mute", new Mute());
		this.commands.put("unmute", new Unmute());
		this.commands.put("vote", new commands.Vote());
		this.commands.put("react", new React());
		this.commands.put("meme", new Meme());
		this.commands.put("oliver", new Meme());
		this.commands.put("color", new Colores());
		this.commands.put("test", new Test());
		this.commands.put("m", new Musik());
		this.commands.put("argh", new Argh());
	}
	

	public boolean perform(String command, Member m, TextChannel channel, Message message, MessageReceivedEvent e) {
		
		ServerCommand cmd;
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.perfomrCommand(m, channel, message, e);
			return true;
		}
		
		return false;
	}

}
