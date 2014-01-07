class Character(val name: String = "Kristen", val alignment: Alignment = Good, val hitPoints: Int = 5){
  val armorClass = 10

  def canHit(target: Character, roll: Roll20): Boolean = {
    roll >= target.armorClass
  }
}
