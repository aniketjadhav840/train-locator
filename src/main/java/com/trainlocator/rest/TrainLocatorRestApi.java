package com.trainlocator.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class TrainLocatorRestApi extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
		
		rest().get("/train-locations").to("direct:fetchTrainDetails");
		
	}

}
