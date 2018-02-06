import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()
    # faehrt vorwaerts & H geht hoch
    robot.move_lift(10)
    robot.drive_wheels(150, 150)
    time.sleep(1)
    # faehrt rueckwaerts & H geht runter
    robot.move_lift(-10)
    robot.drive_wheels(-150, -150)
    time.sleep(1)
    # dreht sich um 360° nach rechts
    robot.turn_in_place(degrees(-360)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(0.5)
    robot.move_lift(-5)
    time.sleep(0.5)
    # dreht sich um 360° nach links
    robot.turn_in_place(degrees(360)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(0.5)
    robot.move_lift(-5)
    time.sleep(0.5)
    # faehrt rueckwaerts & H geht hoch
    robot.move_lift(10)
    robot.drive_wheels(-150, -150)
    time.sleep(1)
    # faehrt vorwaerts & H geht runter
    robot.move_lift(-10)
    robot.drive_wheels(150, 150)
    time.sleep(1)
    # dreht sich um 360° nach links
    robot.turn_in_place(degrees(360)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(0.5)
    robot.move_lift(-5)
    time.sleep(0.5)
    # dreht sich um 360° nach rechts
    robot.turn_in_place(degrees(-360)).wait_for_completed()


cozmo.run_program(cozmo_program)