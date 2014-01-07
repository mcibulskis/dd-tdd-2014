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

  behavior of "attacking a character"

  it should "allow a character to attack another character and hit when roll greater than armor class" in {
    val joe = new Character()
    val sam = new Character()

    joe canHit(sam, 20) should be === true
  }

  it should "allow a character to attack another character and not hit when roll less than armor class" in {
    val joe = new Character()
    val sam = new Character()

    joe canHit(sam, 5) should be === false
  }

  it should "allow a character to attack another character and hit when roll equal to armor class" in {
    val joe = new Character()
    val sam = new Character()

    joe canHit(sam, 10) should be === true
  }
}
