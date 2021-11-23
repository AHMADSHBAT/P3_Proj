import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile
{
    String path;
    BufferedWriter file;
    OutputFile(String p) {
        if(p == null)
        {
            System.out.println("path not found.");
            return;
        }
        this.path = p;
        try {
            file = new BufferedWriter(new FileWriter(path));
        }catch (IOException ignored)
        {
            System.out.println("there are errors with writing to output file: " + ignored);
        }

    }
    public void writeLine(String s)
    {
        if(file == null)
        {
            System.out.println("the file you want to write in is null.");
            return;
        }
        try {
            file.write(s + "\n");
        } catch (IOException e) {
            System.out.println("there is an error: " + e);
        }

    }


    public String getPath() {
        return path;
    }


    public BufferedWriter getFileBuffer() {
        return file;
    }
}
