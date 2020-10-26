package cabinvoicegenerator;

public class InvoiceGenerator {
	private static final int COST_PER_MIN = 1;
	private static final double COST_PER_KM = 10;
	private static final double MINIMUM_FARE = 5;
	private RideRepository rideRepository;

	public InvoiceGenerator() {
		rideRepository = new RideRepository();
	}

	public double calculateFare(double distance, int time) {
		double totalfare = distance * COST_PER_KM + time * COST_PER_MIN;
		return Math.max(totalfare, MINIMUM_FARE);
	}

	public InvoiceSummary calculateFare(Ride[] rides) {
		double totalFare = 0;
		for (Ride ride : rides) {
			totalFare += this.calculateFare(ride.distance, ride.time);
		}
		InvoiceSummary invoiceSummary = new InvoiceSummary(rides.length, totalFare);
		return invoiceSummary;
	}

	public void addRides(String userId, Ride[] rides) {
		rideRepository.addRides(userId, rides);
	}

	public InvoiceSummary getInvoiceSummary(String userId) {
		return this.calculateFare(rideRepository.getRides(userId));
	}
}
