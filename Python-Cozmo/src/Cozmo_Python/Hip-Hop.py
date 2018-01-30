import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()
    # dreht sich um 360° nach rechts
    robot.turn_in_place(degrees(-360)).wait_for_completed()
    # dreht sich um 360° nach links
    robot.turn_in_place(degrees(360)).wait_for_completed()



-> faehrt ein Kreis linksherum
-> faehrt ein Kreis rechtsherum



cozmo.run_program(cozmo_program)