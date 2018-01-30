import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ToCozmo {
    public static void main(String[] args) {
        PrintWriter pWriter = null;
        try {
            // creating file
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("cozmoDance.py")));

            // python code
            pWriter.println("import cozmo");


            pWriter.println("def cozmo_program(robot: cozmo.robot.Robot):");
            pWriter.println("   robot.say_text(\"Hello World\").wait_for_completed()");


            pWriter.println("cozmo.run_program(cozmo_program)");


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }
    }
} 