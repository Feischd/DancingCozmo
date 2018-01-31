import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(3)
    robot.move_lift(-5)
    time.sleep(3)
    # dreht sich um 180° nach links
    robot.turn_in_place(degrees(180)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(3)
    robot.move_lift(-5)
    time.sleep(3)
    # dreht sich um 180° nach rechts
    robot.turn_in_place(degrees(-180)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(3)
    robot.move_lift(-5)
    time.sleep(3)
    # dreht sich um 90° nach rechts
    robot.turn_in_place(degrees(-90)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(3)
    robot.move_lift(-5)
    time.sleep(3)
    # dreht sich um 180° nach rechts
    robot.turn_in_place(degrees(-180)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 5 radians per second)
    robot.move_lift(5)
    time.sleep(3)
    robot.move_lift(-5)
    time.sleep(3)
    # dreht sich um 270° nach links
    robot.turn_in_place(degrees(270)).wait_for_completed()


cozmo.run_program(cozmo_program)
