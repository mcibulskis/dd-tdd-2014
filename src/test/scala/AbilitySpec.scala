import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class AbilitySpec extends FlatSpec with ShouldMatchers {
  behavior of "Abilities and their allowed values"


  it should "throw IllegalArgumentException if the ability value is greater than 20" in {
    intercept[IllegalArgumentException] {
      Ability(21)
    }
  }

  it should "throw IllegalArgumentException if the ability is less than 1" in {
    intercept[IllegalArgumentException] {
      Ability(0)
    }
  }

  it should "allow the roll when valid value is provided" in {
    val ability = Ability(15)

    ability.value should be === 15
  }

  it should "automatically transform a Ability to an Int" in {
    def identity(value: Int): Int = value

    val ability = Ability(10)

    identity(ability) should be === 10
  }

  it should "automatically transform an Int to a Ability" in {
    def identity(value: Ability): Ability = value

    identity(15) should be === Ability(15)
  }}
