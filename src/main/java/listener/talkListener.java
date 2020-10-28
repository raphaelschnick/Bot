package main.java.listener;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class talkListener extends ListenerAdapter {

	private final String tempSuffix = "Talk [Join]";
	private final String acSuffix = "[Talk]";
	
	public void createAutochannel(Member member, VoiceChannel joinedChannel) {
		
		Category category = joinedChannel.getParent();
		
		int postion = joinedChannel.getPosition() + 1;
		
		String name = member.getUser().getName() + " " + acSuffix;
		
		VoiceChannel channel = joinedChannel.getGuild().createVoiceChannel(name)
				.setBitrate(joinedChannel.getBitrate())
				.setUserlimit(joinedChannel.getUserLimit())
				/*
				.addMemberPermissionOverride(member.getIdLong(), EnumSet.of(Permission.MANAGE_PERMISSIONS, Permission.VOICE_CONNECT, Permission.VOICE_MUTE_OTHERS, Permission.VOICE_SPEAK, Permission.VOICE_STREAM, Permission.VIEW_CHANNEL), null)
				.addPermissionOverride(member.getGuild().getRoleById(SECRETS.user), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.VOICE_CONNECT))
				.addPermissionOverride(member.getGuild().getRoleById(SECRETS.nitro_booster), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.VOICE_CONNECT))
				.addPermissionOverride(member.getGuild().getRoleById(SECRETS.friend), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.VOICE_CONNECT))
				.addPermissionOverride(member.getGuild().getRoleById(SECRETS.designer), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.VOICE_CONNECT))
				.addPermissionOverride(member.getGuild().getRoleById(SECRETS.game_tester), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.VOICE_CONNECT))
				*/
				.setParent(category)
				.complete();
		joinedChannel.getManager().sync(channel).queue();
		joinedChannel.getGuild().modifyVoiceChannelPositions().selectPosition(channel).moveTo(postion).queue();
		joinedChannel.getGuild().moveVoiceMember(member, channel).queue();

		
		
	}
	@Override
	public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent e) {
		if(e.getChannelJoined().getName().endsWith(tempSuffix)) {
			createAutochannel(e.getMember(), e.getChannelJoined());
		}
	}
	@Override
	public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent e) {
		if(e.getChannelJoined().getName().endsWith(tempSuffix)) {
			createAutochannel(e.getMember(), e.getChannelJoined());
			
		}
		if(e.getChannelLeft().getName().endsWith(acSuffix) && e.getChannelLeft().getMembers().isEmpty()) {
			e.getChannelLeft().delete().queue();
		}
		
	}
	@Override
	public void onGuildVoiceLeave (@Nonnull GuildVoiceLeaveEvent e) {
		if(e.getChannelLeft().getName().endsWith(acSuffix) && e.getChannelLeft().getMembers().isEmpty()) {
			e.getChannelLeft().delete().queue();
		}	
	}
	
}
