import java.io.FileWriter;
import java.io.PrintWriter;

public interface Locations {

    void createFile(FileWriter fw);
    void writeFile(PrintWriter pw);
}
