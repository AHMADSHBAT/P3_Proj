public class ThreadsHandler extends Thread{

    Game game;
    String bufferString;
    public ThreadsHandler(Game g)
        {
            this.game = g;
            this.game.
        }
        @Override
        public void run()
        {
            this.game.enemySet( new EnemyPlayer(50));
            this.game.enemySet( new EnemyPlayer(60));
            this.game.enemySet( new EnemyPlayer(70));
            this.game.enemySet( new EnemyPlayer(80));
            this.game.enemySet( new EnemyPlayer(90));
            this.game.enemySet( new EnemyPlayer(100));

        }



    }
