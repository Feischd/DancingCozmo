package Dances;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HipHop extends Dance {

    public void createDance(double laenge){
        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("temp\\cozmoDance.py")));

            // python code
            pWriter.println("import time");
            pWriter.println("import cozmo");
            pWriter.println("from cozmo.util import degrees, distance_mm, speed_mmps");
            pWriter.println();

            pWriter.println("def cozmo_program(robot: cozmo.robot.Robot):");
            // Move lift down to the bottom
            pWriter.println("   robot.set_lift_height(0.0).wait_for_completed()");
            // Turn 360 degrees to the right
            pWriter.println("   robot.turn_in_place(degrees(-360)).wait_for_completed()");
            // Turn 360 degrees to the left
            pWriter.println("   robot.turn_in_place(degrees(360)).wait_for_completed()");
            // Drive forwards for 100 millimeters at 50 millimeters-per-second
            pWriter.println("   robot.drive_straight(distance_mm(100), speed_mmps(150)).wait_for_completed()");
            // Drive backwards for 100 millimeters at 50 millimeters-per-second
            pWriter.println("   robot.drive_straight(distance_mm(-100), speed_mmps(150)).wait_for_completed()");
            pWriter.println();

            pWriter.println("i = 0");
            pWriter.println("while i < " + laenge + ":");
            pWriter.println("   cozmo.run_program(cozmo_program)");
            pWriter.println("   i = i + 1");
        } catch (IOException ioe) {
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }
    }
}