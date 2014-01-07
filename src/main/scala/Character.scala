class Character(val name: String = "Kristen", val alignment: Alignment = Good, val hitPoints: Int = 5){
  val armorClass = 10

  def canHit(target: Character, roll: Roll20): Boolean = {
    roll >= target.armorClass
  }

  def isAlive(): Boolean = {
    hitPoints > 0
  }

  def attacks(target: Character, roll: Roll20): Character = {
    def isCriticalHit(roll: Int): Boolean = roll == 20
    def calculateDamage(roll: Int): Int = {
      if (isCriticalHit(roll)) 2
      else 1
    }

    if (canHit(target, roll)) new Character(target.name, target.alignment, target.hitPoints - calculateDamage(roll))
    else target
  }
}
