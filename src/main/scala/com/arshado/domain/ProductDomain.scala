package com.arshado.domain

/**
  * Product Item Domain Object
  * @param id Product Id
  * @param price Product Price in Pence
  */
case class ProductItem(id: String, price: Int) {
  override def toString: String = id
}
