public class ThreadsHandler extends Thread{
    FPS_Player fps_player;
    Game game;
    public ThreadsHandler()
    {
        this.fps_player = new FPS_Player("hello there", 100, 0);
        
    }

}
