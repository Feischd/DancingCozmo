import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
    # falls der Lift oben ist wird er runter geholt
    robot.set_lift_height(0.0).wait_for_completed()
    # H geht hoch
    robot.move_lift(10)
    # faehrt einen Kreis rechtsrum
    robot.drive_wheels(200, 10)
    time.sleep(0.5)
    # H geht runter
    robot.move_lift(-10)
    # faehrt einen Kreis linkssrum
    robot.drive_wheels(10, 201)
    time.sleep(2.3)




cozmo.run_program(cozmo_program)