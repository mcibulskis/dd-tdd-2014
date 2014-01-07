import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ModifierSpec extends FlatSpec with ShouldMatchers {
  behavior of "transformation of modifiers"

  it should "automatically transform an ability to a modifier" in {
    def identity(modifier: Modifier): Modifier = modifier

    identity(Ability(3)) should be === Modifier(-4)
  }

  it should "automatically transform a modifier to it's value" in {
    def identity(value: Int): Int = value

    identity(Modifier(3)) should be === 3
  }

  it should "generate the correct modifiers for ability values" in {
    val expected = List((1, -5), (2, -4), (3, -4), (4, -3), (5, -3), (6, -2), (7, -2), (8, -1), (9, -1), (10, 0),
                        (11, 0), (12, 1), (13, 1), (14, 2), (15, 2), (16, 3), (17, 3), (18, 4), (19, 4), (20, 5))

    expected.foreach {
      element =>
        val actual: Modifier = Ability(element._1)
        actual should be === Modifier(element._2)
    }
  }
}
