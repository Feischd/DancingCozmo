import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestOpen {

    public static void main(String[] args){
        try {
            Desktop.getDesktop().open(new File("cozmoDance.py"));
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

}
