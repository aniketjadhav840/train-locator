package com.trainlocator.camel.processor;

import java.util.List;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainlocator.exception.TrainLocatorException;
import com.trainlocator.model.TrainLocation;
import com.trainlocator.model.Trains;

@Component
public class GeoLocationProcessor implements Processor {

	@Produce
	ProducerTemplate producerTemplate;
	
	@Value("${locationApi.url}")
	String locationApiUrl;

	@Value("${locationApi.key}")
	String apiKey;

	private ObjectMapper mapper = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(GeoLocationProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		try {

			Trains trains = exchange.getIn().getBody(Trains.class);

			trains.getTrains().parallelStream().forEach(traindetails -> {

				String ob = fetchTrainAddress(traindetails.getLocation().getCoordinates());

				traindetails.setTrainAddress(ob);

			});

		} catch (Exception e) {
			throw new TrainLocatorException(
					String.format("Exception occured while fetching train address : %s", e.getMessage()), e);
		}

	}

	private String fetchTrainAddress(List<Double> coordinates) {

		try {
			TrainLocation trainLocationDetails = mapper.readValue(
					producerTemplate.requestBody("https://api.opencagedata.com/geocode/v1/json?q=" + coordinates.get(0)
							+ "+" + coordinates.get(1) + "&key=" + apiKey, null, String.class),
					TrainLocation.class);

			if (trainLocationDetails != null && !CollectionUtils.isEmpty(trainLocationDetails.getResults())) {
				return trainLocationDetails.getResults().get(0).getFormatted();
			} else {
				return null;
			}

		} catch (CamelExecutionException | JsonProcessingException e) {
			LOGGER.error(String.format("Exception occured while fetching train address : %s", e.getMessage()), e);
		}

		return null;
	}

}
