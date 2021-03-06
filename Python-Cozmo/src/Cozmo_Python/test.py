# falls der Lift oben ist wird er runter geholt
robot.set_lift_height(0.0).wait_for_completed()

# faehrt 150 millimeters vorwaerts mit 150 millimeters-per-second.
robot.drive_straight(distance_mm(150), speed_mmps(150)).wait_for_completed()

# faehrt 150 millimeters rueckwaerts mit 150 millimeters-per-second.
robot.drive_straight(distance_mm(-150), speed_mmps(150)).wait_for_completed()

# Lift geht hoch und wieder runter (at 5 radians per second)
robot.move_lift(5)
time.sleep(0.5)
robot.move_lift(-5)
time.sleep(0.5)

# dreht sich um 45° nach links
robot.turn_in_place(degrees(45)).wait_for_completed()
# dreht sich um 90° nach links
robot.turn_in_place(degrees(90)).wait_for_completed()
# dreht sich um 180° nach links
robot.turn_in_place(degrees(180)).wait_for_completed()
# dreht sich um 270° nach links
robot.turn_in_place(degrees(270)).wait_for_completed()
# dreht sich um 360° nach links
robot.turn_in_place(degrees(360)).wait_for_completed()

# dreht sich um 45° nach rechts
robot.turn_in_place(degrees(-45)).wait_for_completed()
# dreht sich um 90° nach rechts
robot.turn_in_place(degrees(-90)).wait_for_completed()
# dreht sich um 180° nach rechts
robot.turn_in_place(degrees(-180)).wait_for_completed()
# dreht sich um 270° nach rechts
robot.turn_in_place(degrees(-270)).wait_for_completed()
# dreht sich um 360° nach rechts
robot.turn_in_place(degrees(-360)).wait_for_completed()

# faehrt einen Kreis rechtsrum
robot.drive_wheels(200, 20)
time.sleep(3.6)
# faehrt einen Kreis linkssrum
robot.drive_wheels(20, 200)
time.sleep(3.6)
