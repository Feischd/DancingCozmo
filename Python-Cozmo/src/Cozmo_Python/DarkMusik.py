import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()
    # dreht sich um 360° nach rechts
    robot.turn_in_place(degrees(-360)).wait_for_completed()
    # faehrt 150 millimeters vorwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(150), speed_mmps(150)).wait_for_completed()
    # faehrt 150 millimeters rueckwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(-300), speed_mmps(150)).wait_for_completed()
    # faehrt 150 millimeters vorwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(150), speed_mmps(150)).wait_for_completed()
    # dreht sich um 180° nach links
    robot.turn_in_place(degrees(180)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(0.5)
    robot.move_lift(-5)
    time.sleep(0.5)

cozmo.run_program(cozmo_program)