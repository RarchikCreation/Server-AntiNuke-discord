package database.utils;

import database.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    public static void addUser(String discordId, String username) {
        String sql = "INSERT OR IGNORE INTO white_list (discord_id, username) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(Database.getUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, discordId);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean userExists(String discordId) {
        String sql = "SELECT COUNT(*) FROM white_list WHERE discord_id = ?";

        try (Connection conn = DriverManager.getConnection(Database.getUrl());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, discordId);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
