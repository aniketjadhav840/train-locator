package com.trainlocator.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TrainLocationErrorHandler implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		
		Message message = exchange.getIn();
		message.setBody(exception.getMessage());
		message.setHeader(Exchange.CONTENT_TYPE, "text/plain");
		message.setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_INTERNAL_SERVER_ERROR);
		
	}

}
