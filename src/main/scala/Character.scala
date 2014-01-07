import scala.math._

class Character(val name: String = "Kristen", val alignment: Alignment = Good, baseHitPoints: Int = 5, val abilities: Abilities = new Abilities(), damage: Int = 0) {
  val armorClass = 10

  def hitPoints(): Int = {
    maxHitPoints() - damage
  }

  def maxHitPoints(): Int = {
    max(baseHitPoints + asModifier(abilities.constitution), 1)
  }

  private def asModifier(ability: Ability): Modifier = ability

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

  private def incursDamage(newDamage: Int): Character = {
    new Character(name = name, alignment = alignment, baseHitPoints = baseHitPoints, damage = damage + newDamage, abilities = abilities)
  }
}
