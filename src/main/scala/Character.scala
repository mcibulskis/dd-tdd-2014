class Character(val name: String = "Kristen", val alignment: Alignment = Good, val hitPoints: Int = 5, val abilities: Abilities = new Abilities()) {
  val armorClass = 10

  def isAlive(): Boolean = {
    hitPoints > 0
  }

  def attacks(target: Character, roll: Roll20): Character = {
    def canHit(target: Character, roll: Roll20): Boolean = {
      roll >= target.armorClass
    }

    def isCriticalHit(roll: Int): Boolean = roll == 20

    def calculateDamage(roll: Int): Int = {
      if (isCriticalHit(roll)) 2
      else 1
    }

    if (canHit(target, roll)) target.incursDamage(calculateDamage(roll))
    else target
  }

  private def incursDamage(damage: Int): Character = {
    new Character(name, alignment, hitPoints - damage, abilities)
  }
}
