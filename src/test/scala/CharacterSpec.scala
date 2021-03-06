import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class CharacterSpec extends FlatSpec with ShouldMatchers {

  behavior of "basic character attributes"

  it should "have have a name" in {

    val character = new Character()

    character.name should be === "Kristen"
  }

  it should "be able to have a non-default name" in {
    val character = new Character("Joe")

    character.name should be === "Joe"
  }

  it should "have a default alignment" in {

    val character = new Character()

    character.alignment should be === Good
  }

  it should "be able to have an evil alignment" in {
    val character = new Character("Joe", Evil)

    character.alignment should be === Evil
  }

  it should "be able to have a neutral alignment" in {
    val character = new Character("Joe", Neutral)

    character.alignment should be === Neutral
  }

  it should "have a default armor class of 10" in {
    val character = new Character()

    character.armorClass should be === 10
  }

  it should "have default hit points of 5" in {
    val character = new Character()

    character.hitPoints should be === 5
  }

  it should "be able to have a non-default value for hit points" in {
    val character = new Character("Joe", Evil, 15)

    character.hitPoints should be === 15
  }

  it should "know it is alive if its hit points are greater than 0" in {
    val character = new Character("Joe", Evil, 19)

    character.isAlive should be === true
  }

  it should "know it is dead if its hit points are 0" in {
    val character = new Character("Joe", Evil, damage = 5)

    character.isAlive should be === false
  }

  it should "know it is dead if its hit points are less than 0" in {
    val character = new Character("Joe", Evil, damage = 6)

    character.isAlive should be === false
  }

  it should "have a default experience of 0" in {
    val character = new Character()

    character.experience should be === 0
  }

  it should "be able to have a non-default value for experience points" in {
    val character = new Character(experience = 15)

    character.experience should be === 15
  }

  it should "have a default level of 1" in {
    val character = new Character()

    character.level should be === 1
  }

  it should "have a level of 2 when it has 1000 experience points" in {
    val character = new Character(experience = 1000)

    character.level should be === 2
  }

  it should "have a level of 1 when it has an experience level less than 1000" in {
    val character = new Character(experience = 999)

    character.level should be === 1
  }

  it should "have a level of 2 when it experience points greater than 1000 and less than 2000" in {
    val character = new Character(experience = 1500)

    character.level should be === 2
  }

  it should "have a level of 3 when it experience points greater than 1000 and less than 2000" in {
    val character = new Character(experience = 2750)

    character.level should be === 3
  }

  behavior of "attacking a character"

  it should "allow a character to attack another character and hit when roll equal to armor class" in {
    val joe = new Character()
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 10)
    newSam.hitPoints should be === 4
  }

  it should "reduce target's hit point by 1 when attack is successful" in {
    val joe = new Character()
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 12)

    newSam.hitPoints should be === 4
  }

  it should "not reduce the target's hit points when the attack is unsuccessful" in {
    val joe = new Character()
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 9)

    newSam.hitPoints should be === 5
  }

  it should "reduce the target's hit points by 2 when the attack is a critical hit of exactly 20" in {
    val joe = new Character()
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 20)

    newSam.hitPoints should be === 3
  }

  it should "reduce target's hit points by 2 when the attack is successful and has a strength modifier bonus of +1" in {
    val joe = new Character(abilities = new Abilities(strength = 12))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 10)

    newSam.hitPoints should be === 3
  }

  it should "reduce target's hit points by 2 when the attack is successful only because of the strength bonus" in {
    val joe = new Character(abilities = new Abilities(strength = 12))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 9)

    newSam.hitPoints should be === 3
  }

  it should "reduce target's hit points by 4 when the attack is a critical hit and has a strength modifier bonus of +1" in {
    val joe = new Character(abilities = new Abilities(strength = 12))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 20)

    newSam.hitPoints should be === 1
  }

  it should "reduce hit points by 1 when attack is successful is strength modifier bonus is -5" in {
    val joe = new Character(abilities = new Abilities(strength = 1))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 15)

    newSam.hitPoints should be === 4
  }

  it should "reduce target's hit points by 1 when attack is a critical hit and the strength modifier bonus is -5" in {
    val joe = new Character(abilities = new Abilities(strength = 1))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 20)

    newSam.hitPoints should be === 4
  }

  it should "increase the attacker's experience by 10 when the attack is successful" in {
    val joe = new Character()
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 12)

    newJoe.experience should be === 10
  }

  it should "not increase the attacker's experience when the attack is unsuccessful" in {
    val joe = new Character()
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 5)

    newJoe.experience should be === 0
  }

  it should "increase the character's hit points when the character levels from attacking" in {
    val joe = new Character(experience = 990, abilities = new Abilities(constitution = 14))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 10)

    newJoe.maxHitPoints should be === 14
  }

  it should "increase the character's hit points when level 3" in {
    val joe = new Character(experience = 1990, baseHitPoints = 12, abilities = new Abilities(constitution = 14))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 10)

    newJoe.maxHitPoints should be === 21
  }

  it should "not increase the character's hit points when not gaining a level" in {
    val joe = new Character(experience = 1950, baseHitPoints = 12, abilities = new Abilities(constitution = 14))
    val sam = new Character()

    val (newJoe, newSam) = joe attacks(sam, 10)

    newJoe.maxHitPoints should be === 14
  }

  it should "increase the base attack roll on every even level gained" in {
    false should be === true
  }

  behavior of "modifier bonuses"

  it should "apply constitution modifier to hit points" in {
    val joe = new Character(abilities = new Abilities(constitution = 20))

    joe.hitPoints should be === 10
  }

  it should "always have a maximum hit points of at least 1" in {
    val joe = new Character(abilities = new Abilities(constitution = 1))

    joe.maxHitPoints should be === 1
  }

  it should "be able to get current armor class, including adjustment by dexterity modifier" in {
    val joe = new Character(abilities = new Abilities(dexterity = 1))

    joe.armorClass should be === 5
  }
}
