package com.trainlocator.camel.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.maps.model.GeocodingResult;
import com.trainlocator.exception.TrainLocatorException;
import com.trainlocator.model.Trains;

@Component
public class GeoLocationProcessor implements Processor {

	@Produce
	ProducerTemplate producerTemplate;
	
	@Value("${geocoderApiKey}")
	String apiKey;

	@Override
	public void process(Exchange exchange) throws Exception {
		try {

			Trains trains = exchange.getIn().getBody(Trains.class);
			
			trains.getTrains().forEach(traindetails-> {
				
				GeocodingResult[] ob = fetchTrainAddress(traindetails.getLocation().getCoordinates());
	
				if (ob != null && ob.length > 0) {
					traindetails.setTrainAddress(ob[0].formattedAddress);
				}
			
			});

		} catch (Exception e) {
			throw new TrainLocatorException(
					String.format("Exception occured while fetching train address : %s", e.getMessage()), e);
		}

	}

	private GeocodingResult[] fetchTrainAddress(List<Double> coordinates) {
		GeocodingResult[] ob = producerTemplate.requestBody("geocoder:latlng:" + coordinates.get(0) + ","
				+ coordinates.get(1) + "?apiKey="+apiKey, null,
				GeocodingResult[].class);
		return ob;
	}

}
