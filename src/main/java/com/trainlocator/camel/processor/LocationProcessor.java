package com.trainlocator.camel.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.trainlocator.exception.TrainLocatorException;
import com.trainlocator.model.TrainDetails;
import com.trainlocator.model.Trains;

@Component
public class LocationProcessor implements Processor {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void process(Exchange exchange) throws Exception {

		try {

			String jsonString = exchange.getIn().getBody(String.class);

			List<TrainDetails> trainDetails = mapper.readValue(jsonString,
					TypeFactory.defaultInstance().constructCollectionType(List.class, TrainDetails.class));

			exchange.getIn().setBody(new Trains(trainDetails), Trains.class);

		} catch (Exception e) {
			throw new TrainLocatorException(
					String.format("Exception occured while mapping train details : %s", e.getMessage()), e);
		}

	}

}
