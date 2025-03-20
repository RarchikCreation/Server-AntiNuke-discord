package cogs.listeners;

import cogs.Cog;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.channel.update.GenericChannelUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.audit.ActionType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static other.utils.color.ColorUtil.protectColor;

public class ChannelChangesListener extends ListenerAdapter implements Cog {

    private static final long LOG_CHANNEL_ID = 1312804207186804810L;
    private final Map<Long, Long> lastAuditEntries = new HashMap<>();

    @Override
    public void register(JDA jda) {
        jda.addEventListener(this);
    }

    @Override
    public void onGenericChannelUpdate(GenericChannelUpdateEvent<?> event) {
        TextChannel logChannel = event.getJDA().getTextChannelById(LOG_CHANNEL_ID);
        if (logChannel == null || !(event.getChannel() instanceof GuildChannel guildChannel)) return;

        guildChannel.getGuild().retrieveAuditLogs().type(ActionType.CHANNEL_UPDATE).queue(entries -> {
            AuditLogEntry entry = entries.stream()
                    .filter(e -> !lastAuditEntries.containsKey(guildChannel.getIdLong()) || lastAuditEntries.get(guildChannel.getIdLong()) != e.getIdLong())
                    .findFirst()
                    .orElse(null);

            if (entry == null) return;

            lastAuditEntries.put(guildChannel.getIdLong(), entry.getIdLong());

            User user = entry.getUser();
            if (user == null) return;

            guildChannel.getGuild().retrieveMember(user).queue(
                    member -> processMember(member, event, logChannel, guildChannel, entry),
                    failure -> processMember(null, event, logChannel, guildChannel, entry)
            );
        }, failure -> {});
    }

    private void processMember(Member member, GenericChannelUpdateEvent<?> event, TextChannel logChannel, GuildChannel guildChannel, AuditLogEntry entry) {
        String roles = (member != null && !member.getRoles().isEmpty()) ? member.getRoles().stream()
                .map(role -> "<@&" + role.getId() + ">")
                .collect(Collectors.joining(", ")) : "Нет ролей";

        User user = (member != null) ? member.getUser() : entry.getUser();
        Guild guild = event.getGuild();

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Изменение канала")
                .setColor(protectColor)
                .addField("Пользователь", user != null ? user.getAsMention() + "\n" + user.getName() : "Неизвестно", true)
                .addField("ID пользователя", user != null ? user.getId() : "-", true)
                .addField("Роли пользователя", roles, true)
                .addField("Канал", guildChannel.getJumpUrl(), true)
                .addField("ID канала", guildChannel.getId(), true)
                .addField("Изменения", getChangeDescription(event.getPropertyIdentifier()), false)
                .setFooter("ID изменения: " + entry.getIdLong())
                .setThumbnail(user != null ? user.getAvatarUrl() : guild.getIconUrl());

        logChannel.sendMessageEmbeds(embed.build()).queueAfter(500, TimeUnit.MILLISECONDS);
    }

    private String getChangeDescription(String property) {
        return switch (property) {
            case "name" -> "Название канала";
            case "topic" -> "Описание канала";
            case "nsfw" -> "NSFW-режим";
            case "position" -> "Позиция канала";
            case "slowmode" -> "Медленный режим";
            case "bitrate" -> "Битрейт";
            case "userLimit" -> "Лимит пользователей";
            case "parent" -> "Категория";
            case "permissions" -> "Изменение прав доступа";
            default -> "Другие изменения";
        };
    }
}
