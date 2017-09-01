import scala.reflect.runtime.universe._
import com.dracarys.codegen.HtmlSyntax._
import scala.xml.Node

sealed trait ContactInfo
case class PhoneNumbers(numbers: Seq[String]) extends ContactInfo
case class Email(email: String) extends ContactInfo
case class Person(id: String, firstName: String, lastName: String, phoneNumbers: PhoneNumbers, email: Email)

def describe[T: TypeTag] = typeOf[T].members.collect {
  case m: MethodSymbol if m.isCaseAccessor => m
}.toList.map(member => (member.name, member.typeSignature.resultType)).reverse

describe[PhoneNumbers]
describe[Email]
describe[Person]

def generateInputField(name: TermName, typeSignature: Type): Node = {
  <input/>.addAttribute("type", getMatchingType(typeSignature)).addAttribute("placeholder", uncamelise(name.toString))
}

def getMatchingType(typeSignature: Type): String = {
  /*
    - Int, Double, Long => number
    - String => text
    - Boolean => <input type="checkbox" data-toggle="toggle">
    - Enum => radio
    - List[Enum] => checkboxes
    - Date => data
    - LocalDateTime => datetime-local
    - Time => time
    - Range => range
  */

  typeSignature match {
    case String => "text"
    case _ => "lost"
  }
}

def uncamelise(str: String): String = {
  val regex = "([a-z])([A-Z]+)"
  val replacement = "$1 $2"
  str.replaceAll(regex, replacement).capitalize
}
