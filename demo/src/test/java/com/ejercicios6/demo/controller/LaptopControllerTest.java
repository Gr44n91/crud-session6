package com.ejercicios6.demo.controller;

import com.ejercicios6.demo.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    //Creamos los objetos necesarios para probar servicios RESTs
    private TestRestTemplate testRestTemplate;

    //Creamos el puerto de prueba
    @LocalServerPort
    private int port;

    //Creamos el RestTemplateBuilder
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    //CREAMOS un metodo que va a lanzarse antes de todos los demas metodos de prueba
    //Este metodo creara el TestRestTemplate
    @BeforeEach
    void setUp(){
        //Creamos la URL desde donde se lanzaran las peticiones HTTP
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        //############################################
        HttpHeaders headers = new HttpHeaders();
        //Cuando queremos enviar JSON en JUnit debemos crear el objeto HttpHeaders
        //Especificamos el archivo que vamos aenviar por las headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "name": "Mac"
                }
                """;
        //Creamos una request o peticion de Json/String ala que pasamos por parametro el json y el headers
        //Es como usar postman pero con java directamente, debemos poner el json, los headers, el puerto etc
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        //Guardamos la response o respuesta con ResponseEntity del metodo exchange
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        //Guardamos el resultado
        Laptop laptop = response.getBody();

        //Realizamos las asersiones
        assertEquals(1L, laptop.getId());
        assertEquals("Mac", laptop.getName());

    }





    //@Test
    //void getLaptops() {
    //}

    //@Test
    //void findById() {
    //}

    @Test
    void save() {
    }

    //@Test
    //void uptdate() {
    //}

    //@Test
    //void delete() {
    //}

    //@Test
    //void testDelete() {
    //}
}