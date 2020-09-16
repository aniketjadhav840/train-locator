package com.trainlocator.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.trainlocator.camel.processor.GeoLocationProcessor;
import com.trainlocator.camel.processor.LocationProcessor;
import com.trainlocator.exception.TrainLocationErrorHandler;

@Component
public class TrainLocationRoute extends RouteBuilder {

	@Autowired
	LocationProcessor locationProcessor;

	@Autowired
	GeoLocationProcessor geoLocationProcessor;
	
	@Autowired
	TrainLocationErrorHandler trainLocationErrorHandler;
	
	@Value("${trainlocation.url}")
	String trainLocationUrl;

	@Override
	public void configure() throws Exception {

		from("direct:fetchTrainDetails")
		.doTry()
				.to(trainLocationUrl+"?bridgeEndpoint=true")
				.process(locationProcessor)
				.process(geoLocationProcessor)
		.doCatch(Exception.class)
				.process(trainLocationErrorHandler);

	}

}
