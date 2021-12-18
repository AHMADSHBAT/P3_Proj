import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Game
{
    FPS_Player userPlayer;
    int gameLevel;
    OutputFile outputFile;
    Map<Integer, EnemyPlayer> enemyPlayers = new HashMap<Integer, EnemyPlayer>(5);
    Integer lastEnemyIndex;
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
        this.outputFile.writeLine("[GAME] the Game has been initialized.");
    }

    /************************************************/


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
            player = new FPS_Player("AHMAD", 1000, 0);
        }
        this.outputFile.writeLine("[GAME] FPS player " + player.getName() + " initialized.");
        this.userPlayer = (FPS_Player) player;
        return Statments.SUCCESS;
    }


    /************************************************/


    public Statments enemyPlayerShoots(int index, int hp)
    {
        if(index >= 4)
        {
            this.outputFile.writeLine("[ERROR] error with enemy player shooting.");
            return Statments.UNEXPECTED_ERROR;
        }
        this.userPlayer.gotDamage(hp);
        if(this.userPlayer.getHP() <= 0)
        {
            this.playerDefeated();
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
        int i = hp / 2;
        this.FPS_playerGotPoints(i);
        this.playerGotDamaged(enemyPlayerIndex, hp);
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
        if(this.enemyPlayers.get(i) == null)
        {
            this.outputFile.writeLine("[error] there are no enemy player with this index.");
            return  Statments.INDEXING_ERROR;
        }
        this.enemyPlayers.remove(i);
        this.outputFile.writeLine("[GAME] the enemy index: " + i + "was killed.");
        return  Statments.SUCCESS;
    }

    /************************************************/
        public void playerGotDamaged(int index, int hp)
        {
            try{
                this.enemyPlayers.get(index).gotDamage(hp);
                if(enemyPlayers.get(index).HP <= 0)
                {
                    enemyKilled(index);
                }
                this.outputFile.writeLine("[Game] Player index " + index + " has got damaged " + hp + " hp.");
            } catch (Exception e)
            {
                this.outputFile.writeLine("[ERROR] " + e);
            }
        }
    /************************************************/

    public Statments playerDefeated()
    {
        this.userPlayer.defeated();
        this.outputFile.writeLine("[GAME] you have been defeated XD.");
        return Statments.SUCCESS;
    }

    /************************************************/
    public boolean isAlive()
    {
        return this.userPlayer.getHP() > 0;
    }

    /************************************************/


}
