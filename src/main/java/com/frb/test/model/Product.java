package com.frb.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 53167480569830874L;

	private String id;
	private String name;
	private BigDecimal price;
	private Boolean availability;
}
