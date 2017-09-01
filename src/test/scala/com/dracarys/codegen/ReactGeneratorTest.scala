package com.dracarys.codegen

import org.scalatest.{FunSpec, Matchers}
import SampleData._

class ReactGeneratorTest extends FunSpec with Matchers {

  describe("generating a HTML input form") {
    it("should be possible from a given case class") {
      val resultForm = ReactGenerator.generateInputForm[Person]
      val expectedForm = <form className="commentForm">
        <input type="text" placeholder="First name"/>
        <input type="text" placeholder="Last name"/>
        <input type="tel" placeholder="Phone number"/>
        <input type="email" placeholder="Email"/>
        <input type="submit" value="Post"/>
      </form>

      resultForm shouldBe expectedForm
    }
  }

  describe("generating a HTML display") {
    it("should be possible from a given case class") {
      val resultDisplay = ReactGenerator.generateDisplay[Person]
      val expectedDisplay = <div>
        First name =
      </div>
    }
  }
}
