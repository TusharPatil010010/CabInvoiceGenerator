package cabinvoicegenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InvoiceServiceTest {
	static InvoiceGenerator invoiceGenerator = null;
	static Ride[] rides = null;
	static RideRepository rideRepository = new RideRepository();
	static InvoiceSummary expectedInvoiceSummary = null;

	@BeforeAll
	public static void setUp() {
		invoiceGenerator = new InvoiceGenerator();
		rides = new Ride[] { new Ride(CabRide.NORMAL, 2.0, 5), new Ride(CabRide.PREMIUM, 0.1, 1) };
		expectedInvoiceSummary = new InvoiceSummary(2, 45);
	}

	@Test
	public void givenMultipleRides_shouldReturn_InvoiceSummary() {
		InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
		assertEquals(expectedInvoiceSummary, summary);
	}

	@Test
	public void givenUserIDAndRides_shouldReturn_InvoiceSummary() {
		String userId = "abc@xyz";
		rideRepository.addRides(userId, rides);
		invoiceGenerator.setRideRepository(rideRepository);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		assertEquals(expectedInvoiceSummary, summary);
	}
}
