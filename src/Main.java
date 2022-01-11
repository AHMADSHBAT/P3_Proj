import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main extends Thread {
    public static void main(String[] args) throws IOException, InterruptedException {



//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EXAMPLE", "root", "aaaaaa");
//            Statement stmt = null;
//            String query = "SHOW TABLES";
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//
//            int columnCount = rs.getMetaData().getColumnCount();
//
//            while (rs.next()){
//                for (int i = 1; i < columnCount+1; i++){
//                    System.out.printf("Column %d: %s\n", i, rs.getString(i) );
//                }
//            }
//
//            rs.close();
//
//            String insert = "INSERT INTO Persons VALUES (?, ?, ?, ?, ?)";
//
//            PreparedStatement pstmt = conn.prepareStatement(insert);
//            Scanner in = new Scanner(System.in);
//            int id = Integer.parseInt(in.nextLine());
//            String last = in.nextLine();
//            String first = in.nextLine();
//            String add = in.nextLine();
//            String city = in.nextLine();
//
//            pstmt.setInt(1, id);
//            pstmt.setString(2, last);
//            pstmt.setString(3, first);
//            pstmt.setString(4, add);
//            pstmt.setString(5, city);
//
//
//
//
//            pstmt.execute();
//
//            rs = stmt.executeQuery("SELECT * FROM Persons");
//
//            columnCount = rs.getMetaData().getColumnCount();
//
//            for (int i = 1; i < columnCount+1; i++){
//                System.out.printf("%10s\t", rs.getMetaData().getColumnName(i));
//            }
//            System.out.println();
//
//            while (rs.next()){
//                for (int i = 1; i < columnCount+1; i++){
//                    System.out.printf("%10s\t", rs.getString(i) );
//
//                }
//                System.out.println();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        OutputFile out = new OutputFile();

        Game g1 = new Game(1, new FPS_Player("AHMAD", 100, 0), out);

        g1.set_FPS_player(new FPS_Player("Zee", 100, 0));
        g1.enemySet(new EnemyPlayer(100));
        g1.enemySet(new EnemyPlayer(100));
        g1.enemySet(new EnemyPlayer(100));
        g1.enemySet(new EnemyPlayer(100));
        g1.enemySet(new EnemyPlayer(100));
        while (g1.userPlayer.getHP() > 0) {
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 100));
            g1.enemyPlayerShoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 10));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 160));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.enemyPlayerShoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 10));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));

            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.enemyPlayerShoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 10));


        }

    }
}

