case class Roll20(val roll: Int) {
  require(roll <= 20, "the roll cannot exceed 20.")
  require(roll > 0, "the roll cannot be less than 1")
}

object Roll20 {
  implicit def int2Roll20(roll: Int): Roll20 = Roll20(roll)
  implicit def roll202Int(roll: Roll20): Int = roll.roll
}
