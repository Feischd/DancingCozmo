package Dances;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Country extends Dance {
    public Country(){
        laengeTanz = 22;
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
            // falls der Lift oben ist wird er runter geholt
            pWriter.println("   robot.set_lift_height(0.0).wait_for_completed()");
            //dreht sich um 360° nach rechts
            pWriter.println("   robot.turn_in_place(degrees(-360)).wait_for_completed()");
            //Lift geht hoch und wieder runter (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   time.sleep(0.5)");
            //dreht sich um 360° nach links
            pWriter.println("   robot.turn_in_place(degrees(360)).wait_for_completed()");
            //Lift geht hoch und wieder runter (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   time.sleep(0.5)");
            //faehrt 150 millimeters rueckwaerts mit 50 millimeters-per-second
            pWriter.println("   robot.drive_straight(distance_mm(-150), speed_mmps(150)).wait_for_completed()");
            //dreht sich um 360° nach links
            pWriter.println("   robot.turn_in_place(degrees(360)).wait_for_completed()");
            //Lift geht hoch und wieder runter (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   time.sleep(0.5)");
            //faehrt 150 millimeters rueckwaerts mit 50 millimeters-per-second.
            pWriter.println("   robot.drive_straight(distance_mm(-150), speed_mmps(150)).wait_for_completed()");
            //dreht sich um 90° nach rechts
            pWriter.println("   robot.turn_in_place(degrees(-90)).wait_for_completed()");
            //Lift geht hoch und wieder runter (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   time.sleep(0.5)");
            //dreht sich um 270° nach links
            pWriter.println("   robot.turn_in_place(degrees(270)).wait_for_completed()");
            //Lift geht hoch und wieder runter (at 5 radians per second)
            pWriter.println("   robot.move_lift(5)");
            pWriter.println("   time.sleep(0.5)");
            pWriter.println("   robot.move_lift(-5)");
            pWriter.println("   time.sleep(0.5)");

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