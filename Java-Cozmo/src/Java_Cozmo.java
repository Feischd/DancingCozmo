import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;

public class Java_Cozmo {
    public static void main(String[] args) throws Exception {
        // creation of python file
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

        // run python program
        String program = "cozmoDance.py";
        Runtime.getRuntime().exec("cmd /c start " + program);

    }

    // Starting external programs from java
    // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_11_005.htm
}
