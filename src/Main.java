import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        OutputFile out = new OutputFile();

        Game g1 = new Game(1, new FPS_Player("AHMAD", 100, 0), out);

        Thread_handler t1 = new Thread_handler(g1);
        g1.setThread(t1);
        t1.start();
    }

    private static class Thread_handler extends Thread {
        Game g1;
        Thread t;

        Thread_handler(Game g) {
            this.g1 = g;
            this.t = this.g1.thread;
        }

        @Override
        public void run() {
            g1.set_FPS_player(new FPS_Player("Zee", 100, 0));
            g1.enemySet(new EnemyPlayer(100));
            g1.enemySet(new EnemyPlayer(100));
            g1.enemySet(new EnemyPlayer(100));
            g1.enemySet(new EnemyPlayer(100));
            g1.enemySet(new EnemyPlayer(100));

            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 100));
            g1.enemyPlayerShoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 10));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 160));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.enemyPlayerShoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 10));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.FPS_player_shoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 200));
            g1.enemyPlayerShoots((int) Math.floor(Math.random() * 4), (int) Math.floor(Math.random() * 10));
        }


    }
}


