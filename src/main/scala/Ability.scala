case class Ability(val value: Int) {
  require(value <= 20, "the ability value cannot exceed 20.")
  require(value > 0, "the ability value cannot be less than 1")
}

object Ability {
  implicit def int2Ability(value: Int): Ability = Ability(value)
  implicit def ability2Int(value: Ability): Int = value.value
}