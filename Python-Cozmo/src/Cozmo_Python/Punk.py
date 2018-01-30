import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()
    # dreht sich um 180° nach rechts
    robot.turn_in_place(degrees(-180)).wait_for_completed()
    # faehrt 150 millimeters vorwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(150), speed_mmps(50)).wait_for_completed()
    # dreht sich um 180° nach links
    robot.turn_in_place(degrees(180)).wait_for_completed()
    # faehrt 150 millimeters vorwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(150), speed_mmps(50)).wait_for_completed()
    # dreht sich um 90° nach links
    robot.turn_in_place(degrees(90)).wait_for_completed()
    # H geht hoch und wieder runter + vor&zurück fahren
    robot.move_lift(5)
    robot.drive_wheels(50, 50)
    time.sleep(3)
    robot.move_lift(-5)
    robot.drive_wheels(-50, -50)
    time.sleep(3)
    # dreht sich um 180° nach rechts
    robot.turn_in_place(degrees(-180)).wait_for_completed()
    # H geht hoch und wieder runter + vor&zurück fahren
    robot.move_lift(5)
    robot.drive_wheels(50, 50)
    time.sleep(3)
    robot.move_lift(-5)
    robot.drive_wheels(-50, -50)
    time.sleep(3)
    # dreht sich um 90° nach links
    robot.turn_in_place(degrees(90)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(3)
    robot.move_lift(-5)
    time.sleep(3)



cozmo.run_program(cozmo_program)