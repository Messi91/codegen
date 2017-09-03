import shapeless._
import record._

case class Person(name: String, address: String, age: Int)

val joe = Person("Joe", "Brighton", 33)

val gen = LabelledGeneric[Person]

val joeRecord = gen.to(joe)

joeRecord.keys

joeRecord.values

joeRecord('name)

joeRecord('address)

joeRecord('age)
