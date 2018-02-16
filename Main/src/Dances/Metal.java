package Dances;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Metal extends Dance {
    public Metal(){
        laengeTanz = 24;
    }

    public void createDance(double laenge){
        PrintWriter pWriter = null;
        try {
            // creating file
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("cozmoDance.py")));

            // python code
            pWriter.println("import time");
            pWriter.println("import cozmo");
            pWriter.println("from cozmo.util import degrees, distance_mm, speed_mmps");
            pWriter.println();

            pWriter.println("def cozmo_program(robot: cozmo.robot.Robot):");
            // Move lift down to the bottom
            pWriter.println("   robot.set_lift_height(0.0).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Turn 90 degrees to the left
            pWriter.println("   robot.turn_in_place(degrees(90)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Turn 180 degrees to the right
            pWriter.println("   robot.turn_in_place(degrees(-180)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Turn 90 degrees to the left
            pWriter.println("   robot.turn_in_place(degrees(90)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Drive backwards for 150 millimeters at 50 millimeters-per-second
            pWriter.println("   robot.drive_straight(distance_mm(-150), speed_mmps(150)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Turn 90 degrees to the left
            pWriter.println("   robot.turn_in_place(degrees(90)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Turn 180 degrees to the right
            pWriter.println("   robot.turn_in_place(degrees(-180)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Turn 90 degrees to the left
            pWriter.println("   robot.turn_in_place(degrees(90)).wait_for_completed()");
            // The lift and the head are first raising and then lowering (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   robot.move_head(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   robot.move_head(-5)");
            pWriter.println("   time.sleep(0.5)");
            // Drive forwards for 150 millimeters at 50 millimeters-per-second
            pWriter.println("   robot.drive_straight(distance_mm(150), speed_mmps(150)).wait_for_completed()");

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