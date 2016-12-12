package com.arshado.service

import com.arshado.domain.{Offer, ProductItem}
import com.arshado.service.MemoryProductService.products

/**
  * Product service Trait
  */
trait ProductService {

  def getProduct(id: String): Option[ProductItem] = {
    None
  }

}

/**
  * Memory Product Service companion object to provide in-memory static data
  */
object MemoryProductService {

  private val products = Map(
    "APPLE" -> ProductItem("Apple", 60, Some(Offer(2, 1))),
    "ORANGE" -> ProductItem("Orange", 25, Some(Offer(3, 2)))
  )

}

/**
  * Memory Product Service to provide products from in-memory
  */
trait MemoryProductService extends ProductService {

  override def getProduct(id: String): Option[ProductItem] = {
    products.get(id.toUpperCase)
  }

}