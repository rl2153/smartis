package com.example;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import jakarta.enterprise.context.ApplicationScoped;

@GraphQLApi
@ApplicationScoped
public class HelloGraphQL {

    @Query
    public String hello() {
        return "Hello from Quarkus!";
    }
}
