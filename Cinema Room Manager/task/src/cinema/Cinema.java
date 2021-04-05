package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        int seatCounter = 0;
        int totalIncome = 0;
        int rowNumber = 0;
        int seatNumber = 0;
        int price = 0;
        int currentIncomeTotal = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        char[][] cinema = new char[numberOfRows][numberOfSeats];
        int seatCount = numberOfSeats * numberOfRows;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                cinema[i][j] = 'S';
            }
        }


        for (; ; ) {
            int choices = getChoices(scanner);
            switch (choices) {
                case 1:
                    cinemaPlan(numberOfRows, numberOfSeats, cinema);
                    break;
                case 2:
                    boolean seatFree = false;
                    boolean rowOK = false;
                    boolean seatOK = false;
                    while (seatFree == false) {
                        while (rowOK == false || seatOK == false) {
                            System.out.println();
                            System.out.println("Enter a row number:");
                            rowNumber = scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            seatNumber = scanner.nextInt();
                            if (seatNumber <= numberOfSeats && rowNumber <= numberOfRows) {
                                seatOK = true;
                                rowOK = true;
                            } else {
                                System.out.println("Wrong Input!");
                            }
                        }
                        if (cinema[rowNumber - 1][seatNumber - 1] != 'B') {
                            seatFree = true;
                            System.out.println();
                            cinema[rowNumber - 1][seatNumber - 1] = 'B';
                            System.out.printf("Ticket price: $%d", calcTicketPrice(numberOfRows, rowNumber, numberOfSeats, price));
                            currentIncomeTotal = currentIncomeTotal + calcTicketPrice(numberOfRows, rowNumber, numberOfSeats, price);
                            System.out.println();
                            seatCounter++;
                            break;

                        } else {
                            System.out.println();
                            System.out.println("That ticket has already been purchased!");
                            rowOK = false;
                            seatOK = false;

                        }

                    }
                    break;
                case 3:
                    String percentageStringStringFormat = getStringPercentage(seatCounter, seatCount);

                    outputStatistics(numberOfRows, numberOfSeats, seatCounter, totalIncome, rowNumber, price, seatCount, percentageStringStringFormat, currentIncomeTotal);
                case 0:
                    break;
                default:
            }
            if (choices == 0) {
                break;
            }
        }

    }

    public static void cinemaPlan(int numberOfRows, int numberOfSeats, char[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" ");
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1 + " "); //Number of row
            for (int j = 0; j < numberOfSeats; j++) {

                System.out.print(cinema[i][j] + " ");
                System.out.print("");
            }
            System.out.println();
        }
    }

    public static void outputStatistics(int numberOfRows, int numberOfSeats, int seatCounter, int totalIncome, int rowNumber, int price, int seatCount, String percentageStringStringFormat, int currentIncomeTotal) {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d ", seatCounter);
        System.out.println();
        System.out.println(percentageStringStringFormat + "%");
        System.out.printf("Current income: $%d", currentIncomeTotal);
        System.out.println();
        System.out.printf("Total income: $%d", priceTotalIncome(numberOfRows, numberOfSeats, seatCount, totalIncome));
        System.out.println();
    }

    public static String getStringPercentage(int seatCounter, int seatCount) {
        float seatCounterDouble = seatCounter;

        float seatCountDouble = seatCount;

        float percentage = seatCounterDouble * 100 / seatCountDouble;
        String percentageString = String.format("Percentage: %.2f", percentage);
        String percentageStringStringFormat = percentageString.replace(",", ".");
        return percentageStringStringFormat;
    }

    public static int priceTotalIncome(int numberOfRows, int numberOfSeats, int seatCount, int totalIncome) {

        seatCount = numberOfRows * numberOfSeats;
        if (seatCount <= 60) {

            totalIncome = seatCount * 10;
        } else {
            int rowsCheap = (numberOfRows - (numberOfRows / 2))*numberOfSeats;
            int rowsExpensive = (numberOfRows / 2)*numberOfSeats;
            totalIncome = (rowsCheap * 8 + rowsExpensive * 10);
        }
        return totalIncome;
    }

    public static int getChoices(Scanner scanner) {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    public static int calcTicketPrice(int numberOfRows, int rowNumber, int numberOfSeats, int price) {
        int seatsCount = numberOfSeats * numberOfRows;

        if (seatsCount > 60 && rowNumber > numberOfRows / 2) {
            price = 8;

        } else if (rowNumber == 0) {
            price = 0;
        } else {
            price = 10;
        }
        return price;
    }

}

