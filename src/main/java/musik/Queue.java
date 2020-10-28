package main.java.musik;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queue {
	
	private List<AudioTrack> queuelist;
	protected MusicController controller;
	
	public Queue(MusicController controller) {
		this.setController(controller);
		this.setQueuelist(new ArrayList<AudioTrack>());
	}

	public boolean next() {
		
		if(this.queuelist.size() >= 1) {
			AudioTrack track = queuelist.remove(0);
			
			/*
			if(track != null) {
				this.controller.getPlayer().playTrack(track);
				return true;
			}
			*/
			this.controller.getPlayer().playTrack(track);
		}

		
		return false;
	}

	public void addTrackToQueue(AudioTrack track) {
		this.queuelist.add(track);
		
		if(controller.getPlayer().getPlayingTrack() == null) {
			next();
		}
	}
	
	public MusicController getController() {
		return controller;
	}

	public void setController(MusicController controller) {
		this.controller = controller;
	}

	public List<AudioTrack> getQueuelist() {
		return queuelist;
	}

	public void setQueuelist(List<AudioTrack> queuelist) {
		this.queuelist = queuelist;
	}

	public void shuffle() {
		Collections.shuffle(queuelist);
		
	}
}
