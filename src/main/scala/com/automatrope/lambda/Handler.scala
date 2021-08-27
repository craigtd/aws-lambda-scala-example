package com.automatrope.lambda

import com.amazonaws.services.lambda.runtime.Context
import org.slf4j.LoggerFactory

class Handler {

  import com.google.gson.{Gson, GsonBuilder}

  val gson: Gson = new GsonBuilder().setPrettyPrinting.create

  def handler(event: java.util.Map[String, String], context: Context) = {
    lazy val logger = LoggerFactory.getLogger(this.getClass)

    val response = "SUCCESS"
    logger.info("CONTEXT: " + gson.toJson(context))
    // process event
    logger.info("EVENT: " + gson.toJson(event))
    response
  }

}
