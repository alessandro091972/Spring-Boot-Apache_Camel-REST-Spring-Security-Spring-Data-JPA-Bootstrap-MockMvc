package com.company.auth.model;

import lombok.Data;

@Data
public class Address {
	String street;
	String housenumber;
	String postalcode;
	String city;
	GeoLocation geoLocation;
}
