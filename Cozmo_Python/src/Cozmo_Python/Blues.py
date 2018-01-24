import time

import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # H geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 90° nach rechts
    robot.turn_in_place(degrees(-90)).wait_for_completed()
    # H geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # H geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # faehrt 150 millimeters rueckwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(-150), speed_mmps(50)).wait_for_completed()

    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # H geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 90° nach rechts
    robot.turn_in_place(degrees(-90)).wait_for_completed()
    # H geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # H geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # faehrt 150 millimeters vorwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(150), speed_mmps(50)).wait_for_completed()


cozmo.run_program(cozmo_program)


