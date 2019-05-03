package de.dhbw.se.fowler.main;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	};

	public String getName() {
		return name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + this.getName() + "\n";
		result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

		for (Rental rental : rentals) {
			double thisAmount = 0;
			// determine amounts for each line
			thisAmount = amountFor(rental);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (isTwoDayRelease(rental))
				frequentRenterPoints++;
			// show figures for this rental
			result += formatResult(thisAmount, rental);
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}

	private String formatResult(double thisAmount, Rental rental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + "\t" + rental.getDaysRented() + "\t"
				+ String.valueOf(thisAmount) + "\n";
	}

	private boolean isTwoDayRelease(Rental rental) {
		return (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1;
	}

	private double amountFor(Rental rental) {
		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			return rental.getDaysRented() > 2 ? (rental.getDaysRented() - 2) * 1.5 + 2 : 2;
		case Movie.NEW_RELEASE:
			return rental.getDaysRented() * 3;
		case Movie.CHILDRENS:
			return rental.getDaysRented() > 3 ? (rental.getDaysRented() - 3) * 1.5 + 1.5 : 1.5;
		default:
			return 0;
		}
	}

}
