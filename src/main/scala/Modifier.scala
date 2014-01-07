import scala.math._

case class Modifier(val value: Int) {

}

object Modifier {
  implicit def ability2Modifier(ability: Ability): Modifier = Modifier(floor(((ability.value - 10) / 2.0)).toInt)
  implicit def modifier2Int(modifier: Modifier): Int = modifier.value
}
