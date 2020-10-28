package main.java.logs;

import java.awt.Color;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class joinListener extends ListenerAdapter {

	@Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        EmbedBuilder join = new EmbedBuilder();
        join.setColor(Color.cyan);
        join.setDescription("Welcome, " + event.getMember().getAsMention());

        event.getGuild().getTextChannelsByName("✍-allgemein", true).get(0).sendMessage(join.build()).queue();
    }
}
