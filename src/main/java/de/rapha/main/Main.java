package main.java.de.rapha.main;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import main.java.commandmanager.CommandListener;
import main.java.commandmanager.CommandManager;
import main.java.listener.helloListener;
import main.java.listener.talkListener;
import main.java.logs.joinListener;
import main.java.musik.PlayerManager;
import main.java.musik.musikListener;
import main.java.mute.muteListener;
import main.java.util.SECRETS;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static ArrayList<Member> mute = new ArrayList<Member>();
	
	public static ArrayList<String> support_ticket = new ArrayList<String>();
	public static ArrayList<String> support_voice = new ArrayList<String>();
	
	public static Main INSTANCE;
	
	public static String[] status = new String[] {"-help" , "Argh Bot"};
	
	public ShardManager shardMan;
	private CommandManager cmdMan;
	
	private Thread loop;
	
	public AudioPlayerManager audioPlayerManager;
	public PlayerManager playerManager;

	public static void main(String[] args)  {
		
		try {
			new Main();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}
	
	public Main() throws LoginException, IllegalArgumentException{
		INSTANCE = this;
		
		@SuppressWarnings("deprecation")
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		
		builder.setToken(SECRETS.TOKEN);
		
		builder.setActivity(Activity.playing("!help"));
		builder.setStatus(OnlineStatus.ONLINE);
		
		builder.setAutoReconnect(true);
		
		this.audioPlayerManager = new DefaultAudioPlayerManager();
		this.playerManager = new PlayerManager();
		
		this.cmdMan = new CommandManager();
		
		// Listener
		builder.addEventListeners(new CommandListener());
		builder.addEventListeners(new joinListener());
		builder.addEventListeners(new muteListener());
		builder.addEventListeners(new talkListener());
		builder.addEventListeners(new musikListener());
		builder.addEventListeners(new helloListener());
		
		shardMan = builder.build();
		System.out.println("Bot fährt hoch!");
		
		AudioSourceManagers.registerRemoteSources(audioPlayerManager);
		audioPlayerManager.getConfiguration().setFilterHotSwapEnabled(true);
		
		
		shutdown();
		runLoop();
		
	}
	
	// Shutdown
	public void shutdown() {
		
		new Thread(() -> {
			
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while((line = reader.readLine()) != null) {
			
			if(line.equalsIgnoreCase("stop")) {
				if(shardMan != null) {
					shardMan.setStatus(OnlineStatus.OFFLINE);
					shardMan.shutdown();
					System.out.println("Bot fährt herunter!");
				}
				
				if(loop != null) {
					loop.interrupt();
				}
				
				reader.close();
			} else {
				System.out.println("Use 'stop' to shutdown the Bot.");
			}
			
		}
			} catch (IOException e) {
			}
			
		}).start();
	}
	public void runLoop() {
		this.loop = new Thread(() -> {
			
			long time = System.currentTimeMillis();
			
			while(true) {
				if(System.currentTimeMillis() >= time + 1000) {
					time = System.currentTimeMillis();
					onSecond();
				}
			}
			
		});
		this.loop.setName("Loop");
		this.loop.start();
	}
	
	int next = 0;
	
	public void onSecond() {
		
		if(next <= 0) {
			Random ran = new Random();
			int i = ran.nextInt(status.length);
			
			shardMan.getShards().forEach(jda ->{
				String text = status[i].replaceAll("%members", "" + jda.getUsers().size());
				
				jda.getPresence().setActivity(Activity.playing(text));
			});
			next = 15;
		} else {
			next--;
		}
		
	}
	
	
	public CommandManager getCmdMan() {
		return cmdMan;
	}

}
