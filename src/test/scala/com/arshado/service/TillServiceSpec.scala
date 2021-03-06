package com.arshado.service

import com.arshado.service.TillServiceSpec._
import org.scalatest.{FlatSpec, Matchers}

/**
  * TillServiceSpec Companion Object containing Static Data
  */
object TillServiceSpec {
  private val apple = "apple"
  private val orange = "orange"
  private val invalid = "invalid"
}

class TillServiceSpec extends FlatSpec with TillServiceBehavior {

  "Input with zero products" should behave like addToBasketAndCheckout(TestData(input = Array()))

  "Input with two products" should behave like addToBasketAndCheckout(
    TestData(input = Array(apple, orange), total = 85, itemsCount = 2)
  )

  "Input with valid and invalid products" should behave like addToBasketAndCheckout(
    TestData(input = Array(apple, invalid, orange, invalid), total = 85, itemsCount = 2, invalidCount = 2)
  )

  "Input with invalid products" should behave like addToBasketAndCheckout(
    TestData(input = Array(invalid), invalidCount = 1)
  )

  "Multiple Products Qty which doesn't qualify for offers" should behave like addToBasketAndCheckout(
    TestData(input = Array(apple, orange, orange), total = 110, itemsCount = 3)
  )

  "Apple qty qualifying for offer" should behave like addToBasketAndCheckout(
    TestData(input = Array(apple, apple), total = 60, itemsCount = 2)
  )

  "Apple qty qualifying for offer and without qualifying for offer" should behave like addToBasketAndCheckout(
    TestData(input = Array(apple, apple, apple), total = 120, itemsCount = 3)
  )

  "Oranges qty qualifying for offer" should behave like addToBasketAndCheckout(
    TestData(input = Array(orange, orange, orange), total = 50, itemsCount = 3)
  )

  "Oranges qty qualifying for offer and withough qualifying for offer" should behave like addToBasketAndCheckout(
    TestData(input = Array(orange, orange, orange, orange), total = 75, itemsCount = 4)
  )

  "Mixed Products qty qualifying for offer" should behave like addToBasketAndCheckout(
    TestData(input = Array(apple, apple, orange, orange, orange), total = 110, itemsCount = 5)
  )

}

trait TillServiceBehavior extends Matchers {
  this: FlatSpec =>
    def addToBasketAndCheckout(testData: TestData): Unit = {

      val classUnderTest = new TillService(testData.input) with MemoryProductService

      it should "have correct number of products" in {
        classUnderTest.items.length should be(testData.itemsCount)
      }

      it should "have correct number of invalid Codes" in {
        classUnderTest.invalidCodes.length should be(testData.invalidCount)
      }

      it should "calculate correct basket total" in {
        classUnderTest.total should be(testData.total)
      }

    }
}

/**
  * Case Class Used for Tests containing input and outputs
  * @param input Array of Product Ids
  * @param total Expected Sum of Shopping Basket
  * @param itemsCount Expected Number of Items
  * @param invalidCount Expected Number of Invalid Item Codes
  */
case class TestData(input: Array[String], total: Int = 0, itemsCount: Int = 0, invalidCount: Int = 0)