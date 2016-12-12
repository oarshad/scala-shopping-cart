package com.arshado.service

import com.arshado.domain.ProductItem
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
    "APPLE" -> ProductItem("Apple", 60),
    "ORANGE" -> ProductItem("Orange", 25)
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