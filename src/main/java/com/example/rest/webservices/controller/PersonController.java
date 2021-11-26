package com.example.rest.webservices.controller;

import com.example.rest.webservices.bean.Name;
import com.example.rest.webservices.bean.PersonV1;
import com.example.rest.webservices.bean.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Ashish Yete");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Ashish","Yete"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 personParamV1(){
        return new PersonV1("Ashish Yete");
    }

    //commenting as Swagger was not working.. as request mapping is same.
  /*  @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 personParamV2(){
        return new PersonV2(new Name("Ashish","Yete"));
    }*/

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1(){
        return new PersonV1("Ashish Yete");
    }

    /*@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2(){
        return new PersonV2(new Name("Ashish","Yete"));
    }*/
}
