package me.pljr.chatcontroller.managers;

import me.pljr.chatcontroller.objects.ChatPlayer;
import me.pljr.pljrapispigot.database.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QueryManager {
    private final DataSource dataSource;

    public QueryManager(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ChatPlayer loadPlayer(UUID uuid){
        ChatPlayer chatPlayer = new ChatPlayer(uuid);
        try {
            Connection connection1 = dataSource.getConnection();
            PreparedStatement preparedStatement1 = connection1.prepareStatement(
                    "SELECT * FROM chatcontroller_players WHERE uuid=?"
            );
            preparedStatement1.setString(1, uuid.toString());
            ResultSet results1 = preparedStatement1.executeQuery();
            if (results1.next()){
                chatPlayer.setSpy(results1.getBoolean("isSpy"));
                chatPlayer.setIgnoring(results1.getBoolean("isIgnoring"));
            }
            dataSource.close(connection1, preparedStatement1, results1);

            Connection connection2 = dataSource.getConnection();
            PreparedStatement preparedStatement2 = connection2.prepareStatement(
                    "SELECT * FROM chatcontroller_ignores WHERE uuid=?"
            );
            preparedStatement2.setString(1, uuid.toString());
            ResultSet results2 = preparedStatement2.executeQuery();
            List<UUID> ignoreList = new ArrayList<>();
            while (results2.next()){
                ignoreList.add(UUID.fromString(results2.getString("blocked")));
            }
            chatPlayer.setIgnoreList(ignoreList);
            dataSource.close(connection2, preparedStatement2, results2);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return chatPlayer;
    }

    public void savePlayer(ChatPlayer chatPlayer){
        try {
            Connection connection1 = dataSource.getConnection();
            PreparedStatement preparedStatement1 = connection1.prepareStatement(
                    "REPLACE INTO chatcontroller_players VALUES (?,?,?)"
            );
            preparedStatement1.setString(1, chatPlayer.getUniqueId().toString());
            preparedStatement1.setBoolean(2, chatPlayer.isSpy());
            preparedStatement1.setBoolean(3, chatPlayer.isIgnoring());
            preparedStatement1.executeUpdate();
            dataSource.close(connection1, preparedStatement1, null);

            Connection connection2 = dataSource.getConnection();
            PreparedStatement preparedStatement2 = connection2.prepareStatement(
                    "DELETE FROM chatcontroller_ignores WHERE uuid=?"
            );
            preparedStatement2.setString(1, chatPlayer.getUniqueId().toString());
            preparedStatement2.executeUpdate();
            dataSource.close(connection2, preparedStatement2, null);

            for (UUID blocked : chatPlayer.getIgnoreList()){
                Connection connection3 = dataSource.getConnection();
                PreparedStatement preparedStatement3 = connection3.prepareStatement(
                        "REPLACE INTO chatcontroller_ignores VALUES (?,?)"
                );
                preparedStatement3.setString(1, chatPlayer.getUniqueId().toString());
                preparedStatement3.setString(2, blocked.toString());
                preparedStatement3.executeUpdate();
                dataSource.close(connection3, preparedStatement3, null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setupTables(){
        try {
            Connection connection1 = dataSource.getConnection();
            PreparedStatement preparedStatement1 = connection1.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS chatcontroller_players (" +
                            "uuid char(36) NOT NULL PRIMARY KEY," +
                            "isSpy tinyint(1) NOT NULL," +
                            "isIgnoring tinyint(1) NOT NULL);"
            );
            preparedStatement1.executeUpdate();
            dataSource.close(connection1, preparedStatement1, null);

            Connection connection2 = dataSource.getConnection();
            PreparedStatement preparedStatement2 = connection2.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS chatcontroller_ignores (" +
                            "uuid char(36) NOT NULL," +
                            "blocked char(36) NOT NULL);"
            );
            preparedStatement2.executeUpdate();
            dataSource.close(connection2, preparedStatement2, null);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
