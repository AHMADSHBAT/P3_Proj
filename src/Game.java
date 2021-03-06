import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

public class Game
{
    FPS_Player userPlayer;
    int enemysCount;
    int gameLevel;
    OutputFile outputFile;
    Map<Integer, EnemyPlayer> enemyPlayers = new HashMap<Integer, EnemyPlayer>(5);
    Integer lastEnemyIndex;
    Player_thread_handler thread;
    Player_thread_handler1 thread1;

    Thread outsideThread;
    boolean win = false;
    boolean exit = false;
    static int id = 0;
    /************************************************/

    Game(){}


    /************************************************/

    Game(int gameLevel, FPS_Player FPS, OutputFile outputFile)
    {
        this.userPlayer = FPS;
        this.gameLevel = gameLevel;
        this.outputFile = outputFile;
        this.lastEnemyIndex = -1;
        this.outputFile.writeLine("[GAME] the Game has been initialized.");
        this.thread = new Player_thread_handler(this, this.userPlayer);
        this.thread1 = new Player_thread_handler1(this);

        this.thread.start();
        this.thread1.start();



    }

    public void setThread(Thread t)
    {
        this.outsideThread = t;
    }

    /************************************************/


    public void setEnemysCount(int i)
    {
        this.enemysCount = i;
    }


    public Statments enemySet(EnemyPlayer e)
    {
        if(this.lastEnemyIndex > 4)
            this.outputFile.writeLine("[error] enemy players number reaches the max.");

        try {
            getEnemyPlayers().put(this.lastEnemyIndex + 1, e);
        } catch (UnsupportedOperationException ex)
        {
            this.outputFile.writeLine("[error] we can't add enemy player to the game." + ex);
            return Statments.EXCEPTION_ERROR;
        }
        this.lastEnemyIndex++;
        this.outputFile.writeLine("[GAME] new enemy " + this.lastEnemyIndex + " was set");
        return Statments.SUCCESS;

    }

    /************************************************/

    public Statments set_FPS_player(FPS_Player player)
    {
        if(player == null)
        {
            this.outputFile.writeLine("[ERROR] you are trying to insert a null FPS player");
            return Statments.UNEXPECTED_ERROR;
        }
        this.userPlayer = player;
        return Statments.SUCCESS;
    }


    /************************************************/


    public Statments enemyPlayerShoots(int index, int hp)  {
        if(this.enemyPlayers.get(index) == null)
            return Statments.SUCCESS;
        if(index >= 4)
        {
            this.outputFile.writeLine("[ERROR] error with enemy player shooting.");
            return Statments.UNEXPECTED_ERROR;
        }
        this.userPlayer.gotDamage(hp);
        if(this.userPlayer.getHP() <= 0)
        {
            this.outputFile.writeLine("[Game] Enemy player damaged FPS player with " + hp + " hp and " + this.userPlayer.getHP() + " hp remaining. ");
            this.exit = true;
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EXAMPLE", "root", "aaaaaa");
                Statement stmt = null;
                String query = "INSERT INTO p VALUES("+ id++ + ",'" + this.userPlayer.getName() + "'," + this.userPlayer.getPoints() + ")";
                stmt = conn.createStatement();
                stmt.execute(query);
                stmt.close();

            }catch (Exception e)
            {
                System.out.println(e);
            }

            System.exit(0);
        }
        this.outputFile.writeLine("[Game] Enemy player damaged FPS player with " + hp + " hp and " + this.userPlayer.getHP() + " hp remaining. ");
        return Statments.SUCCESS;

    }



    /************************************************/
    public Statments FPS_playerGotPoints(int points)
    {
        this.userPlayer.gotPoints(points);
        this.outputFile.writeLine("[GAME] FPS player got extra points +" + points + ". now he has " + this.userPlayer.getPoints() + " totally.");
        return Statments.SUCCESS;
    }



    /************************************************/

    public Statments FPS_player_shoots(int enemyPlayerIndex, int hp)
    {

        if( enemyPlayerIndex > 4)
        {
            this.outputFile.writeLine("[ERROR] there is no enemy player with that index.");
            return Statments.UNEXPECTED_ERROR;
        }
        if(this.enemyPlayers.get(enemyPlayerIndex).getHP() <= 0)
            return Statments.SUCCESS;
        int i = hp / 2;
        this.FPS_playerGotPoints(i);
        this.enemyPlayerGotDamaged(enemyPlayerIndex, hp);
        return Statments.SUCCESS;
    }



    /************************************************/

    public EnemyPlayer getEnemyPlayersIndex(int i)
    {
        return this.enemyPlayers.get(i);
    }
    /************************************************/
    public Map<Integer, EnemyPlayer> getEnemyPlayers()
    {
        return this.enemyPlayers;
    }

    /************************************************/
    public Statments enemyKilled(int i)
    {
        if(this.enemyPlayers.get(i).getHP() == 0)
        {
            this.outputFile.writeLine("[error] there are no enemy player with this index.");
            return  Statments.INDEXING_ERROR;
        }
        this.outputFile.writeLine("[GAME] the enemy index: " + i + "was killed.");
        return  Statments.SUCCESS;
    }

    /************************************************/
        public void enemyPlayerGotDamaged(int index, int hp)
        {
            if(this.enemyPlayers.get(index) != null)
                this.enemyPlayers.get(index).gotDamage(hp);
            else
                return;
            try{

                if(enemyPlayers.get(index).HP <= 0)
                {
                    enemyKilled(index);
                    return;
                }
                this.outputFile.writeLine("[Game] Player index " + index + " has got damaged " + hp + " hp, remaining "
                                            + this.enemyPlayers.get(index).HP);
            } catch (Exception e)
            {
                this.outputFile.writeLine("[ERROR] " + e);
            }
        }
    /************************************************/

    public Statments FPS_got_damaged(int i)
    {

//        this.userPlayer.defeated();
        this.userPlayer.gotDamage(i);
        this.outputFile.writeLine("[GAME] FPS player got damaged "+i+" and remaining " + this.userPlayer.getHP());
        this.outputFile.writeLine("[GAME] you have been defeated XD.");
        return Statments.SUCCESS;
    }

    /************************************************/
    public boolean isAlive()
    {
        return this.userPlayer.getHP() > 0;
    }

    /************************************************/

    public static class Player_thread_handler extends Thread {
        FPS_Player player;
        Game g;
        Player_thread_handler( Game g, FPS_Player i )
        {
            this.player = i;
            this.g = g;
        }

        @Override
        public void run()
        {
            int i = 0;
            while(true) {
                for (EnemyPlayer e : this.g.enemyPlayers.values()) {
//                    System.out.println("hello");
                    if (e.getHP() <= 0)
                        i++;
                }
                if(i > 2) {
                    this.g.YOUWIN();
                    System.out.println("you reached");
                    this.g.outsideThread.interrupt();

                    break;
                }
                i = 0;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    public static class Player_thread_handler1 extends Thread {
        Game g;
        Player_thread_handler1( Game g )
        {
            this.g = g;
        }

        @Override
        public void run()
        {
            while(!this.g.exit) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.g.YOULOSE();
            System.out.println("you reached");
            this.g.outsideThread.interrupt();
            }

        }



    private void YOUWIN(){
        this.outputFile.writeLine("[game] you won!");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EXAMPLE", "root", "aaaaaa");
            Statement stmt = null;
            String query = "INSERT INTO p (id, ename, points) VALUES("+ id++ + ",'" + this.userPlayer.getName() + "'," + this.userPlayer.getPoints() + ")";
            stmt = conn.createStatement();
            stmt.execute(query);
            this.win = true;
        }catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }

    }

    private void YOULOSE(){
        this.outputFile.writeLine("[game] YOU LOSE, GAME OVER!");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/EXAMPLE", "root", "aaaaaa");
            Statement stmt = null;
            String query = "INSERT INTO p (id, ename, points) VALUES("+ id++ + ",'" + this.userPlayer.getName() + "'," + this.userPlayer.getPoints() + ")";
            stmt = conn.createStatement();
            stmt.execute(query);
        }catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }

    }



}
