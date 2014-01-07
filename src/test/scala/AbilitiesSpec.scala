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
}
