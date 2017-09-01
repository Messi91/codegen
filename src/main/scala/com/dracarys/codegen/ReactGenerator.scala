package com.dracarys.codegen

import scala.reflect.runtime.universe._
import com.dracarys.codegen.HtmlSyntax._
import scala.xml.Node

object ReactGenerator {

  def generateInputForm[T]: Node = {
    ""
  }

  def generateDisplay[T]: Node = {
    ""
  }

  def describe[T: TypeTag] = typeOf[T].members.collect {
    case m: MethodSymbol if m.isCaseAccessor => m
  }.toList.map(member => (member.name, member.typeSignature.resultType)).reverse

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
}
