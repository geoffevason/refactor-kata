package movierental;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CustomerTest {

  @Test
  public void testCustomer() {
    Customer c = new CustomerBuilder().build();
    assertNotNull(c);
  }

  @Test
  public void testAddRental() {
    Customer customer2 = new CustomerBuilder().withName("Julia").build();
    Movie movie1 = new Movie("Gone with the Wind", Movie.REGULAR);
    Rental rental1 = new Rental(movie1, 3); // 3 day rental
    customer2.addRental(rental1);
  }

  @Test
  public void testGetName() {
    Customer c = new Customer("David");
    assertEquals("David", c.getName());
  }

  @Test
  public void statementForRegularMovie() {
    Movie movie1 = new Movie("Gone with the Wind", Movie.REGULAR);
    Rental rental1 = new Rental(movie1, 3); // 3 day rental
    Customer customer2 =
            new CustomerBuilder()
                    .withName("Sallie")
                    .withRentals(rental1)
                    .build();
    String expected = "Rental Record for Sallie\n" +
            "\tGone with the Wind\t3.5\n" +
            "Amount owed is 3.5\n" +
            "You earned 1 frequent renter points";
    String statement = customer2.textStatement();
    assertEquals(expected, statement);
  }

  @Test
  public void statementForNewReleaseMovie() {
    Movie movie1 = new Movie("Star Wars", Movie.NEW_RELEASE);
    Rental rental1 = new Rental(movie1, 3); // 3 day rental
    Customer customer2 =
            new CustomerBuilder()
                    .withName("Sallie")
                    .withRentals(rental1)
                    .build();
    String expected = "Rental Record for Sallie\n" +
            "\tStar Wars\t9.0\n" +
            "Amount owed is 9.0\n" +
            "You earned 2 frequent renter points";
    String statement = customer2.textStatement();
    assertEquals(expected, statement);
  }

  @Test
  public void statementForChildrensMovie() {
    Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
    Rental rental1 = new Rental(movie1, 3); // 3 day rental
    Customer customer2
            = new CustomerBuilder()
            .withName("Sallie")
            .withRentals(rental1)
            .build();
    String expected = "Rental Record for Sallie\n" +
            "\tMadagascar\t1.5\n" +
            "Amount owed is 1.5\n" +
            "You earned 1 frequent renter points";
    String statement = customer2.textStatement();
    assertEquals(expected, statement);
  }

  @Test
  public void statementForManyMovies() {
    Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
    Rental rental1 = new Rental(movie1, 6); // 6 day rental
    Movie movie2 = new Movie("Star Wars", Movie.NEW_RELEASE);
    Rental rental2 = new Rental(movie2, 2); // 2 day rental
    Movie movie3 = new Movie("Gone with the Wind", Movie.REGULAR);
    Rental rental3 = new Rental(movie3, 8); // 8 day rental
    Customer customer1
            = new CustomerBuilder()
            .withName("David")
            .withRentals(rental1, rental2, rental3)
            .build();
    String expected = "Rental Record for David\n" +
            "\tMadagascar\t6.0\n" +
            "\tStar Wars\t6.0\n" +
            "\tGone with the Wind\t11.0\n" +
            "Amount owed is 23.0\n" +
            "You earned 4 frequent renter points";
    String statement = customer1.textStatement();
    assertEquals(expected, statement);
  }

  // This test represents the new requirement. It will fail. Try to get it working.
  // Aim to refactor the code (rather than have lots of duplicate code)
//  @Test
//  public void htmlStatementForManyMovies() {
//    Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
//    Rental rental1 = new Rental(movie1, 6); // 6 day rental
//    Movie movie2 = new Movie("Star Wars", Movie.NEW_RELEASE);
//    Rental rental2 = new Rental(movie2, 2); // 2 day rental
//    Movie movie3 = new Movie("Gone with the Wind", Movie.REGULAR);
//    Rental rental3 = new Rental(movie3, 8); // 8 day rental
//    Customer customer1
//            = new CustomerBuilder()
//            .withName("David")
//            .withRentals(rental1, rental2, rental3)
//            .build();
//    String expected = "<h1>Rental Record for David</h1>" +
//            "<table>" +
//            "<tr><td>Madagascar</td><td>6.0</td></tr>" +
//            "<tr><td>Star Wars</td><td>6.0</td></tr>" +
//            "<tr><td>Gone with the Wind</td><td>11.0</td></tr>" +
//            "</table>" +
//            "<p>Amount owed is <em>23.0</em></p>" +
//            "<p>You earned <em>4</em> frequent renter points</p>";
//    String statement = customer1.textStatement();
//    assertEquals(expected, statement);
//  }

}
