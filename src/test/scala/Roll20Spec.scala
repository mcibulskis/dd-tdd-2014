import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class Roll20Spec extends FlatSpec with ShouldMatchers {

  behavior of "rolling a die"

  it should "throw IllegalArgumentException if the roll is greater than 20" in {
    intercept[IllegalArgumentException] {
      Roll20(21)
    }
  }

  it should "throw IllegalArgumentException if the roll is less than 1" in {
    intercept[IllegalArgumentException] {
      Roll20(0)
    }
  }

  it should "allow the roll when valid value is provided" in {
    val roll = Roll20(15)

    roll.roll should be === 15
  }

  it should "automatically transform a Roll20 to an Int" in {
    def identity(value: Int): Int = value

    val roll = Roll20(10)

    identity(roll) should be === 10
  }

  it should "automatically transform an Int to a Roll20" in {
    def identity(value: Roll20): Roll20 = value

    identity(15) should be === Roll20(15)
  }
}
