package cogs.commands;

import cogs.Cog;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;


public class PingCommand extends ListenerAdapter implements Cog {
    @Override
    public void registerCommands(JDA jda) {
        jda.upsertCommand("active", "Проверка бота на активность").queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("active")) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setAuthor("Отчет:")
                    .setDescription("Бот активен.");

            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
        }
    }
}
