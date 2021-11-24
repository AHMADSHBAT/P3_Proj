import java.io.BufferedWriter;
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
        this.BufferString += (s + "\n");
    }


}
