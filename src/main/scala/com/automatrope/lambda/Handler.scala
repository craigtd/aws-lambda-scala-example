package com.automatrope.lambda

import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}

class Handler {

  import com.google.gson.Gson
  import com.google.gson.GsonBuilder

  val gson: Gson = new GsonBuilder().setPrettyPrinting.create

  def handler(event: java.util.Map[String, String], context: Context) = {
    val logger: LambdaLogger = context.getLogger
    val response = "SUCCESS"
    logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv))
    logger.log("CONTEXT: " + gson.toJson(context))
    // process event
    logger.log("EVENT: " + gson.toJson(event))
    response
  }

}
