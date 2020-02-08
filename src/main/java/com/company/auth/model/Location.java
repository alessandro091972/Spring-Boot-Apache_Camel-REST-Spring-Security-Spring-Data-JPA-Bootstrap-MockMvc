package com.company.auth.model;

import lombok.Data;

@Data
public class Location {
	Address address;
	double distance;
	String type;
}
