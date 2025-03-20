package database;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static final String DB_FOLDER = "resources";
    private static final String DB_NAME = "database.db";
    private static String dbFilePath;
    private static String url;

    public static void init() {
        try {
            String jarPath = new File(Database.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            String dbPath = jarPath + File.separator + DB_FOLDER;
            dbFilePath = dbPath + File.separator + DB_NAME;
            url = "jdbc:sqlite:" + dbFilePath;

            if (!Files.exists(Paths.get(dbPath))) {
                Files.createDirectories(Paths.get(dbPath));
            }

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS white_list (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "discord_id TEXT UNIQUE NOT NULL, " +
                        "username TEXT NOT NULL" +
                        ");";
                stmt.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return url;
    }
}
