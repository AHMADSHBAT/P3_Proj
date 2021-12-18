import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main
{

    public static void main(String[] args) throws IOException, InterruptedException {
//        String userDirectory = System.getProperty("user.dir");
        OutputFile out = new OutputFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Codes\\Proj_java\\src\\output.txt"));
        Game g1 = new Game(1, new FPS_Player("AHMAD", 100, 0),out);
        Game g2 = new Game(1, new FPS_Player("Hello", 100, 0),out);
        ThreadsHandler t1 = new ThreadsHandler(g1, new FPS_Player("AHMAD", 100, 0));
        ThreadsHandler t2 = new ThreadsHandler(g2, new FPS_Player("AHMAD", 100, 0));

        t1.start();
        t2.start();
        t1.join();
        t2.join();



        bufferedWriter.write(out.getBufferString());
        bufferedWriter.close();


    }
    private static class ThreadsHandler extends Thread
    {
        Game game;
        FPS_Player p;
        ThreadsHandler(Game g, FPS_Player player)
        {
            this.game = g;
            this.p = player;
        }
        @Override
        public void run()
        {
            this.game.set_FPS_player(this.p);
            game.enemySet(new EnemyPlayer(234));
            game.enemySet(new EnemyPlayer(234));
            game.enemySet(new EnemyPlayer(234));
            game.enemySet(new EnemyPlayer(234));
            game.enemySet(new EnemyPlayer(234));
            game.FPS_player_shoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));
            game.enemyPlayerShoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));
            game.FPS_player_shoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));
            game.FPS_player_shoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));
            game.enemyPlayerShoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));
            game.FPS_player_shoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));
            game.enemyPlayerShoots((int)Math.floor(Math.random() * 5),(int)Math.floor(Math.random() * 100));

        }
    }

}
