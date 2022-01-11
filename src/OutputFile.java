import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile
{
    String BufferString;
    OutputFile() {
        this.BufferString = "";
    }

    public String getBufferString() {
        return BufferString;
    }

    public void writeLine(String s)
    {
        System.out.println(s);
        try {
            File file = new File("output.txt");

            // creates the file
            file.createNewFile();

            FileWriter out = new FileWriter("output.txt", true);
            out.write(s + "\n");
            out.close();

        } catch (Exception ignored)
        {
            System.out.println("uable to write to file \n");
        }

        this.BufferString += (s + "\n");
    }


}
