import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class AbilitiesSpec extends FlatSpec with ShouldMatchers {
  behavior of "abilities"

  def identity(value: Int): Int = value

  it should "have a default value of 10 for strength" in {
    val abilities = new Abilities()

    identity(abilities.strength) should be === 10
  }

  it should "have a default value of 10 for dexterity" in {
    val abilities = new Abilities()

    identity(abilities.dexterity) should be === 10
  }

  it should "have a default value of 10 for constitution" in {
    val abilities = new Abilities()

    identity(abilities.constitution) should be === 10
  }

  it should "have a default value of 10 for wisdom" in {
    val abilities = new Abilities()

    identity(abilities.wisdom) should be === 10
  }

  it should "have a default value of 10 for intelligence" in {
    val abilities = new Abilities()

    identity(abilities.intelligence) should be === 10
  }

  it should "have a default value of 10 for charisma" in {
    val abilities = new Abilities()

    identity(abilities.charisma) should be === 10
  }

  it should "be able to have a non-default value for strength" in {
    val abilities = new Abilities(strength = 7)

    identity(abilities.strength) should be === 7
  }

  it should "be able to have a non-default value for dexterity" in {
    val abilities = new Abilities(dexterity = 7)

    identity(abilities.dexterity) should be === 7
  }

  it should "be able to have a non-default value for constitution" in {
    val abilities = new Abilities(constitution = 7)

    identity(abilities.constitution) should be === 7
  }

  it should "be able to have a non-default value for intelligence" in {
    val abilities = new Abilities(intelligence = 7)

    identity(abilities.intelligence) should be === 7
  }

  it should "be able to have a non-default value for wisdom" in {
    val abilities = new Abilities(wisdom = 7)

    identity(abilities.wisdom) should be === 7
  }

  it should "be able to have a non-default value for charisma" in {
    val abilities = new Abilities(charisma = 7)

    identity(abilities.charisma) should be === 7
  }
}
