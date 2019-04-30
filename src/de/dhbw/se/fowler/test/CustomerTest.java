package de.dhbw.se.fowler.test;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import de.dhbw.se.fowler.main.Customer;
import de.dhbw.se.fowler.main.Movie;
import de.dhbw.se.fowler.main.Rental;

class CustomerTest {

	@Test
	void testStatement() {
		String result = "Rental Record for joe\n" + //
				"\tTitle\t\tDays\tAmount\n" + //
				"\tmovie1\t\t10\t30.0\n" + //
				"\tmovie2\t\t5\t4.5\n" + //
				"Amount owed is 34.5\n" + //
				"You earned 3 frequent renter points";

		Customer target = new Customer("joe");
		Movie m1 = new Movie("movie1", 1);
		Movie m2 = new Movie("movie2", 2);
		Rental r1 = new Rental(m1, 10);
		Rental r2 = new Rental(m2, 5);
		target.addRental(r1);
		target.addRental(r2);
		assertThat(target.statement(), Is.is(result));
	}

}
