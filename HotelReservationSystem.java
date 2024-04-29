import java.util.*;

// Enum for room types
enum RoomType {
    STANDARD,
    DELUXE,
    SUITE
}

// Class to represent a Room
class Room {
    private int roomId;
    private RoomType type;
    private boolean isAvailable;

    public Room(int roomId, RoomType type) {
        this.roomId = roomId;
        this.type = type;
        this.isAvailable = true;
    }

    public int getRoomId() {
        return roomId;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Class to represent a Reservation
class Reservation {
    private int reservationId;
    private Room room;
    private String guestName;
    private String checkInDate;
    private String checkOutDate;
    private boolean isPaid;

    public Reservation(int reservationId, Room room, String guestName, String checkInDate, String checkOutDate) {
        this.reservationId = reservationId;
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isPaid = false;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}

// Class to represent a Hotel
class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationId = 1;
        initializeRooms(); // Initialize some sample rooms
    }

    private void initializeRooms() {
        rooms.add(new Room(101, RoomType.STANDARD));
        rooms.add(new Room(102, RoomType.DELUXE));
        rooms.add(new Room(103, RoomType.SUITE));
    }

    public List<Room> getAvailableRooms(RoomType type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType() == type && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, String guestName, String checkInDate, String checkOutDate) {
        Reservation reservation = new Reservation(nextReservationId++, room, guestName, checkInDate, checkOutDate);
        reservations.add(reservation);
        room.setAvailable(false);
        return reservation;
    }

    public void markReservationAsPaid(Reservation reservation) {
        reservation.setPaid(true);
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservation.getRoom().setAvailable(true);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        // Menu for user interaction
        while (true) {
            System.out.println("\n1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("\nRoom Types:");
                    System.out.println("1. Standard");
                    System.out.println("2. Deluxe");
                    System.out.println("3. Suite");
                    System.out.print("Select Room Type: ");
                    int roomTypeChoice = scanner.nextInt();
                    RoomType roomType;
                    switch (roomTypeChoice) {
                        case 1:
                            roomType = RoomType.STANDARD;
                            break;
                        case 2:
                            roomType = RoomType.DELUXE;
                            break;
                        case 3:
                            roomType = RoomType.SUITE;
                            break;
                        default:
                            System.out.println("Invalid choice. Returning to main menu.");
                            continue;
                    }
                    List<Room> availableRooms = hotel.getAvailableRooms(roomType);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms of selected type.");
                    } else {
                        System.out.println("\nAvailable Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println("Room ID: " + room.getRoomId() + ", Type: " + room.getType());
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nEnter Guest Name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
                    String checkInDate = scanner.nextLine();
                    System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
                    String checkOutDate = scanner.nextLine();

                    // Select available room
                    System.out.println("\nSelect Room ID for Reservation:");
                    availableRooms = hotel.getAvailableRooms(RoomType.STANDARD); // Assuming standard room by default
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms for reservation.");
                    } else {
                        System.out.println("Available Rooms:");
                        for (Room room : availableRooms) {
                            System.out.println("Room ID: " + room.getRoomId());
                        }
                        System.out.print("Enter Room ID: ");
                        int roomId = scanner.nextInt();
                        Room selectedRoom = null;
                        for (Room room : availableRooms) {
                            if (room.getRoomId() == roomId) {
                                selectedRoom = room;
                                break;
                            }
                        }
                        if (selectedRoom != null) {
                            Reservation reservation = hotel.makeReservation(selectedRoom, guestName, checkInDate, checkOutDate);
                            System.out.println("Reservation made with ID: " + reservation.getReservationId());
                        } else {
                            System.out.println("Invalid Room ID. Returning to main menu.");
                        }
                    }
                    break;

                case 3:
                    List<Reservation> allReservations = hotel.getAllReservations();
                    System.out.println("\nAll Reservations:");
                    for (Reservation reservation : allReservations) {
                        System.out.println("Reservation ID: " + reservation.getReservationId() +
                                ", Room ID: " + reservation.getRoom().getRoomId() +
                                ", Guest Name: " + reservation.getGuestName() +
                                ", Check-in Date: " + reservation.getCheckInDate() +
                                ", Check-out Date: " + reservation.getCheckOutDate());
                    }
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
