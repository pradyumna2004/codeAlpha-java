import java.util.*;

// Class to represent a Destination
class Destination {
    private String name;
    private String description;
    private double latitude;
    private double longitude;

    public Destination(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

// Class to represent a Travel Plan
class TravelPlan {
    private List<Destination> destinations;
    private String startDate,endDate;
    private double budget;

    public TravelPlan() {
        destinations = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getBudget() {
        return budget;
    }
}

// Class to represent a Travel Itinerary Planner
public class ItineraryPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelPlan travelPlan = new TravelPlan();

        // Input destinations
        System.out.print("Enter number of destinations: ");
        int numDestinations = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        for (int i = 0; i < numDestinations; i++) {
            System.out.println("\nDestination " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Latitude: ");
            double latitude = scanner.nextDouble();
            System.out.print("Longitude: ");
            double longitude = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            Destination destination = new Destination(name, description, latitude, longitude);
            travelPlan.addDestination(destination);
        }

        // Input travel dates
        System.out.print("\nEnter start date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();
        travelPlan.setStartDate(startDateStr);

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine();
        travelPlan.setEndDate(endDateStr);

        // Input budget
        System.out.print("\nEnter budget: ");
        double budget = scanner.nextDouble();
        travelPlan.setBudget(budget);

        // Display travel plan
        System.out.println("\nTravel Plan:");
        System.out.println(numDestinations+" Destinations:");
        List<Destination> destinations = travelPlan.getDestinations();
        for (Destination destination : destinations) 
            System.out.println("- " + destination.getName() + ": " + destination.getDescription());
        
        System.out.println("Start Date: " + startDateStr);
        System.out.println("End Date: " + endDateStr);
        System.out.println("Budget(IND): Rs " + budget);

        scanner.close();
    }
}
