package com.github.daggerok.moviecatalogsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestResource {
  @GetMapping fun index() = mapOf("result" to "Hello, World")
}

@SpringBootApplication
class MovieCatalogApplication

fun main(args: Array<String>) {
  runApplication<MovieCatalogApplication>(*args)
}
