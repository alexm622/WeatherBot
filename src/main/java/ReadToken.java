import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadToken {
    public static String ReadToken(){
        String tokenPath = "/weatherbot/token.txt";
        String token;
        try{
            BufferedReader br = new BufferedReader(new FileReader(tokenPath));
            token = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
