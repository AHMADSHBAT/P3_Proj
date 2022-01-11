import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        this.thread.start();


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
//            this.enemyPlayers.get(this.lastEnemyIndex).setIndex(this.lastEnemyIndex);
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
            this.outputFile.writeLine("[GAME] GAME OVER!");

            System.exit(0);
        }
        this.outputFile.writeLine("[Game] Enemy player damaged FPS player with " + hp + " hp and " + this.userPlayer.getHP() + " hp remaining. ");
        return Statments.SUCCESS;

    }


//    public void END() {
//        try {
//            OutputFile out = new OutputFile();
////        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Codes\\Proj_java\\src\\output.txt"));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Java\\P3_Proj\\src\\output.txt"));
//            bufferedWriter.write(this.outputFile.getBufferString());
//            System.exit(0);
//        } catch (Exception ignored)
//        {
//
//        }
//    }


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
            boolean bool = false;
            while(true) {
                for (EnemyPlayer e : this.g.enemyPlayers.values()) {
//                    System.out.println("hello");
                    if (e.getHP() <= 0)
                        i++;
                }
                if(i > 2)
                    break;
                i = 0;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.g.YOUWIN();
        }

    }

    private void YOUWIN(){
        this.outputFile.writeLine("[game] you won!");
        System.exit(0);
    }


}
