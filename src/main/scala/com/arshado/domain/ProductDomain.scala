package com.arshado.domain

/**
  * Product Item Domain Object
  * @param id Product Id
  * @param price Product Price in Pence
  */
case class ProductItem(id: String, price: Int, offer: Option[Offer]) {
  override def toString: String = id
}


/**
  * Product Offer Domain Object
  * @param getQty Get product item qty
  * @param forPriceOfQty For the price of qty
  */
case class Offer(getQty: Int, forPriceOfQty: Int)