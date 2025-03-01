import cogs.commands.PingCommand;
import database.Database;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.JDA;
import java.util.List;
import cogs.Cog;

public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");
        Database.init();

        JDA jda = JDABuilder.createDefault(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_EXPRESSIONS,
                        GatewayIntent.SCHEDULED_EVENTS
                )
                .build();
        loadCogs(jda);
    }

    private static void loadCogs(JDA jda) {
        List<Cog> cogs = List.of(
                new PingCommand()
        );

        cogs.forEach(cog -> cog.registerCommands(jda));
        cogs.forEach(jda::addEventListener);
        System.out.println(cogs);
    }
}
