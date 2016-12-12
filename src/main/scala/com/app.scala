package com

import com.arshado.service.{MemoryProductService, TillService}

/**
  * Main Application Entry
  */
object app {

  def main (args: Array[String]): Unit = {
    if (args.length == 0)
      println("Usage run <product> <product>")
    else {
      val tillService = new TillService(args) with MemoryProductService
      tillService.checkout()
    }

  }
}
