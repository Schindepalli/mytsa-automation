package com.appium.serenity.MyTSA.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static net.serenitybdd.rest.RestRequests.given;

public class AirlinesApi {

    protected String uri = RestAssured.baseURI ="https://www.tsa.gov/api";

    @Test
    public void verifyAirportEndpoint() {
        Response response =
                given().pathParam("param", "airports")
                        .when().get("/{param}")
                        .then().statusCode(200).contentType(ContentType.JSON).extract().response();

        System.out.println(response.asString());

        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("airports");
        List<String> names = jsonPath.get("airport.Name");

        for (String name: names
             ) {
            System.out.println(name);
        }
    }

}
