package com.trainlocator.model;

import java.util.List;

public class Trains {

	List<TrainDetails> trains;

	public Trains(List<TrainDetails> trains) {
		super();
		this.trains = trains;
	}

	public List<TrainDetails> getTrains() {
		return trains;
	}

	public void setTrains(List<TrainDetails> trains) {
		this.trains = trains;
	}

}
