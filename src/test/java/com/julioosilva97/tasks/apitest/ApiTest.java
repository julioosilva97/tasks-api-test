package com.julioosilva97.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend/";
	}

	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.when()
			.get("todo")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given()
			.body("{\"task\":\"Teste API\", \"dueDate\":\"2021-12-25\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("todo")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void naoDeveAdicionarTarefaPassa() {
		RestAssured.given()
			.body("{\"task\":\"Teste API\", \"dueDate\":\"2011-12-25\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"));
	}
	
}
