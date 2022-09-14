package com.kh.spring.data.corona19;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Body {
	@JacksonXmlElementWrapper(useWrapping = true)
	@JacksonXmlProperty(localName = "items")
	List<Item> items;
	int numOfRows;
	int pageNo;
	int totalCount;
}
