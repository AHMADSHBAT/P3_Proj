import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException {
//        String userDirectory = System.getProperty("user.dir");
        OutputFile out = new OutputFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Codes\\Proj_java\\src\\output.txt"));
        Game g1 = new Game(1, new FPS_Player("AHMAD", 100, 0),out);
        Game g2 = new Game(1, new FPS_Player("Hello", 100, 0),out);
        ThreadsHandler t1 = new ThreadsHandler(g1);
        ThreadsHandler t2 = new ThreadsHandler(g2);



        bufferedWriter.write(out.getBufferString());
        bufferedWriter.close();


    }

}
