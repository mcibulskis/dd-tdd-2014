class Character(val name: String = "Kristen", val alignment: Alignment = Good){
  val armorClass = 10
  val hitPoints = 5

  def canHit(target: Character, roll: Roll20): Boolean = {
    roll >= target.armorClass
  }
}
