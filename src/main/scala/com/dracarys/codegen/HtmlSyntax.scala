package com.dracarys.codegen

import scala.xml._

object HtmlSyntax {
  implicit class HtmlOps(node: Node) {
    def addAttribute(name: String, value: String): Node = {
      node.asInstanceOf[Elem] % Attribute(name, Text(value), Null)
    }
  }
}
