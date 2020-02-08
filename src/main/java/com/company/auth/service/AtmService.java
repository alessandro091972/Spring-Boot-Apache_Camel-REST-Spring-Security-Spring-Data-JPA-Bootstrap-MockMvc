/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.auth.service;

import com.company.auth.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/*
Camel through producerTemplate calls the external service to retrieve the data.
*/

@Slf4j
@Service
public class AtmService {

    /**
     * Inject Camel producer
     */
    @EndpointInject(uri = "https://www.dropbox.com/s/6fg0k2wxwrheyqk/ATMs?dl=1")
    private FluentProducerTemplate producer;

    public List<Location> findAtmsInCity(String city) {
        String json = producer.withHeader("Content-Type", MediaType.APPLICATION_JSON).request(String.class);
        List<Location> atmLocations = convert(json);
        List<Location> atmInCity
                = atmLocations.stream().filter(location -> location.getAddress().getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
        log.info("List atm in city: {}", atmInCity);
        return atmInCity;
    }

    public List<Location> convert(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Location> locationList = null;
        try {
            locationList = objectMapper.readValue(
                    jsonString,
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, Location.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    public List<Location> findAllAtms() {
        String json = producer.withHeader("Content-Type", MediaType.APPLICATION_JSON).request(String.class);
        List<Location> atmLocations = convert(json);
        return atmLocations;
    }

    public FluentProducerTemplate getProducer() {
        return producer;
    }

}
