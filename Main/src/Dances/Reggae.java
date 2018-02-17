package Dances;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Reggae extends Dance {

    public void createDance(double laenge){
        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("cozmoDance.py")));

            // python code
            pWriter.println("import time");
            pWriter.println("import cozmo");
            pWriter.println("from cozmo.util import degrees, distance_mm, speed_mmps");
            pWriter.println();

            pWriter.println("def cozmo_program(robot: cozmo.robot.Robot):");
            // Move lift down to the bottom
            pWriter.println("   robot.set_lift_height(0.0).wait_for_completed()");
            //The lift is raising (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            // Drive a pitch circle to the right side (left wheel: 200mmps; right wheel: 10mmps)
            pWriter.println("   robot.drive_wheels(200, 10)");
            pWriter.println("   time.sleep(0.5)");
            //The lift is lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(-5)");
            // Drive a pitch circle to the left side (left wheel: 10mmps; right wheel: 201mmps)
            pWriter.println("   robot.drive_wheels(10, 201)");
            pWriter.println("   time.sleep(2.3)");
            pWriter.println();

            pWriter.println("i = 0");
            pWriter.println("while i < " + laenge + ":");
            pWriter.println("   cozmo.run_program(cozmo_program)");
            pWriter.println("   i = i + 1");
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