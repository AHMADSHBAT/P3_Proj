public class Main extends Thread
{
    Game game;
    public Main(Game g)
    {
        this.game = g;
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



    public static void main(String[] args)
    {
        String userDirectory = System.getProperty("user.dir");
        OutputFile out = new OutputFile("D:\\Codes\\Proj_java\\src\\output.txt");
        Game g1 = new Game(1, new FPS_Player("AHMAD", 100, 0),out);
        Game g2 = new Game(1, new FPS_Player("Hello", 100, 0),out);
        new Main(g1).start();
        new Main(g2).start();

    }

}
