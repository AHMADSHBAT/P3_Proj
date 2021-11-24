import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Game
{
    Player userPlayer;
    int gameLevel;
    OutputFile outputFile;
    Map<Integer, EnemyPlayer> enemyPlayers = new HashMap<Integer, EnemyPlayer>(5);
    Integer lastEnemyIndex;
    static int threadId = 0;
    Game GameInstance;
    /************************************************/

    Game(){}


    /************************************************/

    Game(int gameLevel, FPS_Player fps_player, OutputFile outputFile)
    {
        this.userPlayer = fps_player;
        this.gameLevel = gameLevel;
        this.outputFile = outputFile;
        this.lastEnemyIndex = -1;
        threadId++;
        this.outputFile.writeLine("[GAME  in " +  threadId +"] the Game has been initialized.");
    }

    /************************************************/


    public Statments enemySet(EnemyPlayer e)
    {
        if(this.lastEnemyIndex > 4)
            this.outputFile.writeLine("[error in " +  threadId + "] enemy players number reaches the max.");

        try {
            getEnemyPlayers().put(this.lastEnemyIndex + 1, e);
        } catch (UnsupportedOperationException ex)
        {
            this.outputFile.writeLine("[error  in " +  threadId +"] we can't add enemy player to the game." + ex);
            return Statments.EXCEPTION_ERROR;
        }
        this.lastEnemyIndex++;
        this.outputFile.writeLine("[GAME] new enemy " + this.lastEnemyIndex + " was set");
        System.out.println("we reach");
        return Statments.SUCCESS;

    }

    /************************************************/
    public Map<Integer, EnemyPlayer> getEnemyPlayers()
    {
        return this.enemyPlayers;
    }
    /************************************************/
    public Statments enemyKilled(Integer i)
    {
        if(this.enemyPlayers.get(i) == null)
        {
            this.outputFile.writeLine("[error in " +  threadId +"] there are no enemy player with this index.");
            return  Statments.INDEXING_ERROR;
        }
        this.enemyPlayers.remove(i);
        this.outputFile.writeLine("[GAME in " +  threadId +"] the enemy " + i + "was killed.");
        return  Statments.SUCCESS;
    }

    /************************************************/

    public Statments playerDefeated()
    {
        this.userPlayer.defeated();
        this.outputFile.writeLine("[GAME  in " +  threadId +"] you have been defeated XD.");
        return Statments.SUCCESS;
    }

    /************************************************/


}
