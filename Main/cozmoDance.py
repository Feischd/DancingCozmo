import time
import cozmo
from cozmo.util import degrees, distance_mm, speed_mmps

def cozmo_program(robot: cozmo.robot.Robot):
   robot.set_lift_height(0.0).wait_for_completed()
   robot.move_lift(5)
   time.sleep(0.5)
   robot.move_lift(-5)
   time.sleep(0.5)
   robot.turn_in_place(degrees(90)).wait_for_completed()
   robot.drive_straight(distance_mm(-150), speed_mmps(150)).wait_for_completed()
   robot.turn_in_place(degrees(-180)).wait_for_completed()
   robot.drive_straight(distance_mm(-150), speed_mmps(150)).wait_for_completed()
   robot.turn_in_place(degrees(-360)).wait_for_completed()
   robot.drive_straight(distance_mm(150), speed_mmps(150)).wait_for_completed()

i = 0
while i < 15000.0:
   cozmo.run_program(cozmo_program)
   i = i + 1
