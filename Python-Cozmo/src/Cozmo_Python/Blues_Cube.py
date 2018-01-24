import time

import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps
from cozmo.objects import LightCube1Id, LightCube2Id, LightCube3Id

def cozmo_tanz(robot: cozmo.robot.Robot):
    #falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()

    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 90° nach rechts
    robot.turn_in_place(degrees(-90)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # faehrt 150 millimeters rueckwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(-150), speed_mmps(50)).wait_for_completed()

    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 90° nach rechts
    robot.turn_in_place(degrees(-90)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # dreht sich um 45° nach links
    robot.turn_in_place(degrees(45)).wait_for_completed()
    # Lift geht hoch und wieder runter (at 8 radians per second)
    robot.move_lift(8).wait_for_completed()
    robot.move_lift(-8).wait_for_completed()
    # faehrt 150 millimeters vorwaerts mit 50 millimeters-per-second.
    robot.drive_straight(distance_mm(150), speed_mmps(50)).wait_for_completed()


def cozmo_cube(robot: cozmo.robot.Robot):
    cube1 = robot.world.get_light_cube(LightCube1Id)  # looks like a paperclip
    cube2 = robot.world.get_light_cube(LightCube2Id)  # looks like a lamp / heart
    cube3 = robot.world.get_light_cube(LightCube3Id)  # looks like the letters 'ab' over 'T'

    if cube1 is not None:
        cube1.set_lights(cozmo.lights.red_light)
    else:
        cozmo.logger.warning("Cozmo is not connected to a LightCube1Id cube - check the battery.")

    if cube2 is not None:
        cube2.set_lights(cozmo.lights.green_light)
    else:
        cozmo.logger.warning("Cozmo is not connected to a LightCube2Id cube - check the battery.")

    if cube3 is not None:
        cube3.set_lights(cozmo.lights.blue_light)
    else:
        cozmo.logger.warning("Cozmo is not connected to a LightCube3Id cube - check the battery.")

    # Keep the lights on for 10 seconds until the program exits
    time.sleep(10)


def cozmo_program(robot: cozmo.robot.Robot):
    cozmo_tanz(robot)
    cozmo_cube(robot)

cozmo.run_program(cozmo_program)