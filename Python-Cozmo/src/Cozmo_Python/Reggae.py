import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
# falls der Lift oben ist wird er runter geholt
robot.set_lift_height(0.0).wait_for_completed()


-> faehrt Halbkreis rechtsherum + H geht hoch und wieder runter
-> faehrt Kreis linksherum + H geht hoch und wieder runter
-> faehrt Halbkreis rechtsherum + H geht hoch und wieder runter


# faehrt rueckwaerts & H geht hoch
robot.move_lift(5)
robot.drive_wheels(-50, -50)
time.sleep(3)
# faehrt vorwaerts & H geht runter
robot.move_lift(-5)
robot.drive_wheels(50, 50)
time.sleep(3)



cozmo.run_program(cozmo_program)