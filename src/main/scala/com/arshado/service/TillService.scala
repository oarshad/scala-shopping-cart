package com.arshado.service

import com.arshado.domain.ProductItem

/**
  * Till Service class parses list of strings to products and calculates total
  * @param input array of strings containing product ids
  */
class TillService(input: Array[String]) extends ProductService{

  /**
    * Parses array of product ids to list of products
    */
  private val basket = input.foldRight((List.empty[ProductItem], List.empty[String])) {
    case (productId, (products, invalids)) =>
      getProduct(productId).fold((products, productId :: invalids)) { product => (product :: products, invalids) }
  }

  /**
    * List of Products in Basket
    */
  val items = basket._1

  /**
    * List of Invalid Product Ids
    */
  val invalidCodes = basket._2

  /**
    * Calculates basket total
    */
  lazy val total = items.foldLeft(0){
      case (sum, product) => sum + product.price
  }

  /**
    * Displays items in basket and their total
    */
  def checkout() = println("[ %s ] => £ %.2f\nInvalid Codes: [ %s ]".format(items.mkString(", "), total / 100.00, invalidCodes.mkString(", ")))

}
