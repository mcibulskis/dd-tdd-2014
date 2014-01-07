import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class CharacterSpec extends FlatSpec with ShouldMatchers {
  "Character" should "have have a name" in {

    val character = new Character()

    character.name should be === "Kristen"
  }

  "Character" should "be able to have a non-default name" in {
    val character = new Character("Joe")

    character.name should be === "Joe"
  }
}
