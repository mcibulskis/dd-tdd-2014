import scala.math._

class Character(val name: String = "Kristen", val alignment: Alignment = Good, baseHitPoints: Int = 5, val abilities: Abilities = new Abilities(), damage: Int = 0, val experience: Int = 0) {
  private val baseArmorClass = 10
  private val baseLevel = 1

  def level(): Int = {
    calculateLevel(experience)
  }

  private def calculateLevel(forExperience: Int): Int = {
    (forExperience / 1000) + baseLevel
  }

  def armorClass(): Int = {
    baseArmorClass + asModifier(abilities.dexterity)
  }

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

  def attacks(target: Character, roll: Roll20): (Character, Character) = {
    def canHit(target: Character, roll: Roll20): Boolean = {
      (roll.roll + asModifier(abilities.strength)) >= target.armorClass
    }

    def isCriticalHit(roll: Int): Boolean = roll == 20

    def calculateDamage(roll: Int): Int = {
      if (isCriticalHit(roll)) max(2 + asModifier(abilities.strength)*2, 1)
      else max(1 + asModifier(abilities.strength), 1)
    }

    if (canHit(target, roll)) (gainExperience(10), target.incursDamage(calculateDamage(roll)))
    else (this, target)
  }

  private def gainExperience(addExperience: Int): Character = {
    new Character(name = name, alignment = alignment, baseHitPoints = calculateBaseHitPoints(addExperience), abilities = abilities, damage = damage, experience = experience + addExperience)
  }

  private def calculateBaseHitPoints(expGained: Int): Int = {
    if (isGainingLevel(expGained)) baseHitPoints + 5 + asModifier(abilities.constitution)
    else baseHitPoints
  }

  private def isGainingLevel(expGained: Int): Boolean = {
    level != calculateLevel(experience + expGained)
  }

  private def incursDamage(newDamage: Int): Character = {
    new Character(name = name, alignment = alignment, baseHitPoints = baseHitPoints, damage = damage + newDamage, abilities = abilities)
  }
}
