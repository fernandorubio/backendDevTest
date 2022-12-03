package com.frb.testapp.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 53167480569830874L;

	private String id;
	private String name;
	private BigDecimal price;
	private Boolean availability;
}
