import scala.reflect.runtime.universe._

sealed trait ContactInfo
case class PhoneNumbers(numbers: Seq[String]) extends ContactInfo
case class Email(email: String) extends ContactInfo
case class Person(id: String, firstName: String, lastName: String, phoneNumbers: PhoneNumbers, email: Email)

//def contactInfo[T: TypeTag] = typeOf[T].members.filter(!_.isMethod).map(_.typeSignature).collect {
//  case t if t <:< typeOf[ContactInfo] => t.typeSymbol.name.toString
//}

def describe[T: TypeTag] = typeOf[T].members.collect {
  case m: MethodSymbol if m.isCaseAccessor => m
}.toList.map(member => (member.name, member.typeSignature)).reverse

describe[PhoneNumbers]
describe[Email]
describe[Person]

def generateInputField(name: TermName, typeSignature: Type): String = {
  s"""<input type="${typeSignature.}"
      placeholder="${uncamelise(name.toString)}"
    />"""
}

def uncamelise(str: String): String = {
  val regex = "([a-z])([A-Z]+)"
  val replacement = "$1 $2"
  str.replaceAll(regex, replacement).capitalize
}
