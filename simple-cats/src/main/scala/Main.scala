import cats.Show
import cats.syntax.show._ // for show
import cats.implicits._
import java.util.Date
import cats.kernel.Semigroup
import cats.instances.int._
import cats.kernel.Monoid

@main def hello(): Unit =
  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]
  val intAsString: String = showInt.show(123)
  println(s"${showInt.show(123) == "123"}")
  println(s"${showString.show("123") == "123"}")
  println(123.show == "123")
  val onePlusTwo = Semigroup[Int].combine(1, 2)
  println(onePlusTwo)

  val multiplicationSemigroup = new Semigroup[Int] {
    override def combine(x: Int, y: Int): Int = x * y
  }
  val four = multiplicationSemigroup.combine(2, 3)
  println(four)

object CustomInstance extends App {
  implicit val customShow: Show[Date] =
    new Show[Date] {
      def show(date: Date): String =
        s"${date.getTime}ms since the epoch."
    }
  customDateExample
  def customDateExample =
    val actualDate: String = new Date().show
    val expectedDate: String = s"This year is: ${new Date().getYear}"
    println(s"actual date $actualDate")
    println(s"expected date $expectedDate")
    println(s"Custom Date ${actualDate == expectedDate}")
}

object CustomMonoid extends App {

  def combineAll[A](collection: Seq[A])(implicit ev: Monoid[A]): A = {
    val monoid = Monoid[A]
    collection.foldLeft(monoid.empty)(monoid.combine)
  }
  println(combineAll(Seq(1,2,3)))
}