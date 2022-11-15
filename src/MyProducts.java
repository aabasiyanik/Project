// importing all the necessary packages.
import java.text.*;
import java.util.*;

// Implementing Product.java class to have access to methods inside Product.java class.
public class MyProducts implements Product {
    // The 'date' and 'calendar' is for us to get the exact expiration date.
    DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
    Calendar calendar = Calendar.getInstance();
    static Scanner input = new Scanner(System.in);

    // The purpose of clearScreen() method is for user to have much cleaner experience
    // using the console. It creates 8 new lines everytime the method is being called.
    public static void clearScreen() {
        for (int i = 0; i < 8; i++) {
            System.out.println("\n");
        }
    }
    // This method is the most important method and this is because everything starts here.
    public void start() {
        //Asking user what they want to do.
        try {
            System.out.println("1. Add a New Product");
            System.out.println("2. Search Product by Name");
            System.out.println("3. List Products with Categories");
            System.out.println("4. Exit");
            int userInputForStart = input.nextInt();
            input.nextLine();
            // Making sure user input is valid for the program to work properly.
            while (userInputForStart > 4 || userInputForStart < 1) {
                // Using clearScreen() method so that user can see why the program didn't take them further.
                clearScreen();
                // Asking again until their response is between 1-4
                System.out.println("Your number has has to be between 1 and 4\n");
                System.out.println("\n1. Add a New Product");
                System.out.println("2. Search Product by Name");
                System.out.println("3. List Products With Categories");
                System.out.println("4. Exit");
                userInputForStart = input.nextInt();
                input.nextLine();
            }
            // Depending on the user input, the program will re-direct them to other methods.
            if (userInputForStart == 1) {
                this.addProducts();
            } else if (userInputForStart == 2) {
                clearScreen();
                this.searchProducts();
            } else if (userInputForStart == 3) {
                clearScreen();
                this.listProducts();
            } else if (userInputForStart == 4) {
                clearScreen();
                this.exit();
            }

            // In case if user enters something else other than integer.
        } catch (Exception exception) {
            input.next();
            clearScreen();
            System.out.println("Please enter an integer between 1 and 4");
            start();
        }
    }

// Here, I am creating an enum for Categories because the program is all about the Categories.
// These Categories are unique, that is why I had to create them using enum.
    public enum Categories {
        Fruits,
        SeaFood,
        FrozenFoods,
        BakedGoods;
    }
// For the status, the answer will be either "In_Store" or "Sold_Out".
    public enum Status {
        In_Store,
        Sold_Out;
    }
// The use of these variables is to print the result of the user's input at the end
    private String name;
    private String type;
    private double price;
    private int expirationDate;
    private Categories cate;
    private Status stat;

// This is the constructor of MyProducts class; the use is to create an Object of MyProducts class in Main class
    public MyProducts() {
    }
// Here, I am doing what is called, "constructor overloading". Here I am setting everything we need for the product that
    // user will create in order.(Category, name, type, price, expiration date, status of the product)
    public MyProducts(Categories cate, String name, String type, double price, int expirationDate, Status stat) {
        this.cate = cate;
        this.name = name;
        this.type = type;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stat = stat;
    }
// Here I created getters and setters also called encapsulation. Though, I did not use these methods on this project,
    // the project is open to future improvements.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

// From Product class, we are overriding all the methods that I created inside the Product class.
    @Override
    public void addProducts() {
        clearScreen();
        // I am creating the variables before starting the code and also initializing a value to them to prevent any error.
        int category = 0, expirationDate = 0, et = 0, whatNext = 0;
        String name, type;
        double price = 0;
        // Asking user what they want to create.
        System.out.println("Build your product\n");
        boolean isNot = true;
        // Using try-catch, inside do-while method to prevent user to enter unexpected inputs.
        do {
            try {
                // Asking user what type of category they would like to use for their product
                System.out.println("Choose the category:\n" +
                        "1. Fruits\n" +
                        "2. Sea Foods\n" +
                        "3. Baked Goods\n" +
                        "4. Frozen Foods");
                category = input.nextInt();
                input.nextLine();
                // If the user input is not between 1 or 4 ask again because we do not have any other options other than 1, 2, 3, and 4
                while (category < 1 || category > 4) {
                    clearScreen();
                    System.out.println("Your number has has to be between 1 and 4");
                    System.out.println("Choose the category:\n" +
                            "1. Fruits\n" +
                            "2. Sea Foods\n" +
                            "3. Baked Goods\n" +
                            "4. Frozen Foods");
                    category = input.nextInt();
                    input.nextLine();
                }
                isNot = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering an integer");
            }
        } while (isNot);
        // From the user's input, here I used ternary operations to set the category of their product,
        // If it is 1 it will be fruit, if it is 2 it will be seafood, if it is 3, it will be baked goods, else it will be frozenfood since that is the only option left.
        Categories cate = category == 1 ? Categories.Fruits :
                category == 2 ? Categories.SeaFood :
                        category == 3 ? Categories.BakedGoods :
                                category == 4 ? Categories.FrozenFoods :
                                        null;

// Asking user the name of their product,
        System.out.println("Enter the name");
        name = input.nextLine();
// For the result to look better I decided to make the name of the user's products' first letter Upper case and rest in lower case.
        name = name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();

// Asking user the type of their product, and again doing the same thing again for the result to look better.
        System.out.println("Enter the type");
        type = input.nextLine();
        type = type.substring(0, 1).toUpperCase() +
                type.substring(1).toLowerCase();
        boolean isNotEqual = true;
// Again using try-catch inside do-while to prevent user to enter something else other than number.
        do {
            try {
                System.out.println("Enter the price");
                System.out.print("$");
                price = input.nextDouble();
                input.nextLine();
                // Rounding user's answer to two decimal point.
                price = Math.round(price * 10.0) / 10.0;
                // If user's input is negative than ask again because it cannot be negative. It can be (0-free) but not negative
                while (price <= 0) {
                    clearScreen();
                    System.out.println("Price cannot be negative or 0");
                    System.out.println("Please try again");
                    System.out.print("$");
                    price = input.nextDouble();
                    input.nextLine();
                }
                isNotEqual = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering a floating number");
            }
        } while (isNotEqual);

        boolean isNotEqualToIntegerExpirationDate = true;
// Doing the same thing, to make sure user input is a number we are setting try-catch inside do-while
        do {
            try {
                System.out.println("Enter the expiration date");
                expirationDate = input.nextInt();
                input.nextLine();
                // If the expiration date is lower than 0 or bigger than 365, ask user to enter another expiration date.
                while (expirationDate < 0 || expirationDate > 365) {
                    clearScreen();
                    System.out.println("The expiration date must be less than or equal to 365");
                    System.out.println("Try entering the expiration date again");
                    expirationDate = input.nextInt();
                    input.nextLine();
                }
                isNotEqualToIntegerExpirationDate = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering an integer between 0 and 365");

            }
        } while (isNotEqualToIntegerExpirationDate);

        boolean isNotEqualToInStoreSoldOut = true;
// Again, here to prevent user input to be something else other than number, we are using try-catch inside do-while
        do {
            try {
                System.out.println("Choose the Status:\n" +
                        "1. In Store\n" +
                        "2. Sold Out");
                et = input.nextInt();
                input.nextLine();
                // If the input is not 1 or 2 ask again since those are the only option user have.
                while (et != 1 && et != 2) {
                    clearScreen();
                    System.out.println("Your number must be 1 or 2");
                    System.out.println("\nChoose the Status:\n" +
                            "1. In Store\n" +
                            "2. Sold Out");
                    et = input.nextInt();
                    input.nextLine();
                }
                isNotEqualToInStoreSoldOut = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering 1 or 2");
            }
        } while (isNotEqualToInStoreSoldOut);
// From the user's input, wer are setting the status of their product using the Status enum I created.
        Status stat = et == 1 ? Status.In_Store :
                et == 2 ? Status.Sold_Out :
                        null;
// Now we got everything from the user, it's time to create the product using the overloaded constructor.
        MyProducts product = new MyProducts(cate, name, type, price, expirationDate, stat);

// Adding the product into our database.
        productsList.add(product);
        clearScreen();

// Asking user what they want to do now since their product has been added.
        boolean WhatNext = true;
        do {
            // The user have the option to go main menu or to add another product.
            try {
                System.out.println("Your product has been added to the list! " +
                        "\n1. Add Another Product\n2. Return to the Main Menu");
                whatNext = input.nextInt();
                input.nextLine();
                // If user's input is not 1 or 2, ask again.
                while (whatNext != 1 && whatNext != 2) {
                    clearScreen();
                    System.out.println("Your number must be 1 or 2");
                    System.out.println("\n1. Add Another Product\n2. Return to the Main Menu");
                    whatNext = input.nextInt();
                    input.nextLine();
                }
                clearScreen();
                // If their answer is 1 execute addProducts() method again, if not then execute stat() method.
                if (whatNext == 1) {
                    this.addProducts();
                } else {
                    this.start();
                }
                WhatNext = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering 1 or 2");
            }
        } while (WhatNext);
    }

// Overriding the searchProducts() method from Product class
    @Override
    public void searchProducts() {
        // Asking user to enter the name of the product they want to search for
        System.out.println("Enter the name of the product you want to search from the list:");
        String name = input.nextLine();
        // Creating an ArrayList for searchProduct to access it later
        ArrayList<MyProducts> searchProduct = new ArrayList<>();
        // Simple for each loop, we are basically going over all the products within our database
        for (MyProducts num : productsList) {
            // If the input of the user is equals to any product within our database it will add that name to our searchProduct ArrayList.
            if (num.name.equalsIgnoreCase(name)) {
                searchProduct.add(num);
            }
        }
        // If the size of our searchProduct arrayList is equal to 0 that means no product is found.
        // If this is the case give user the option to try again or return to the main menu.
        if (searchProduct.size() == 0) {
            int whatNext;
            boolean WhatNext = true;
            do {
                try {
                    clearScreen();
                    System.out.println("No product is found!");
                    System.out.println();
                    System.out.println("1. Try Again\n2. Return to the Main Menu");
                    whatNext = input.nextInt();
                    input.nextLine();
                    clearScreen();

                    if (whatNext == 1) {
                        this.searchProducts();
                    } else {
                        this.start();
                    }
                    WhatNext = false;
                } catch (Exception exception) {
                    input.next();
                    clearScreen();
                    System.out.println("Please try entering 1 or 2");
                }
            } while (WhatNext);
        }
        clearScreen();

        // If the code come this far that means the product that the user search is within the searchProduct arrayList
        // Create for each loop to go print the name, category, type, etc. of the product that user searched for.
        // Reason we are doing for each is because there might be more than one product named apple with different price, type, etc.
        for (MyProducts num2 : searchProduct) {
            // Create Calendar that is already built in java to print the exact date of the expiration date
            calendar.add(Calendar.DATE, num2.expirationDate);
            // Let the user know the product has been found and print everything about that product
            System.out.println("Your product has been found!");
            System.out.println("\nName: " + num2.name +
                    "\nCategory: " + num2.cate +
                    "\nType: " + num2.type +
                    "\nPrice: $" + num2.price +
                    "\nExpiration Date: " + date.format(calendar.getTime()) +
                    "\nStatus: " + num2.stat + "\n");
            // Set the calendar to today's date again so that there is no confusion.
            // If we were not to write this line of code the expiration date for the second product would start from
            // today's date + the expiration date of the first product. To prevent it, simply bring the calendar back to today's date
            calendar = Calendar.getInstance();
        }
        // Ask user what they want to do next
        int whatNext;
        boolean WhatNext = true;
        do {
            try {
                System.out.println("1. Search Another Product\n2. Return to the Main Menu");
                whatNext = input.nextInt();
                input.nextLine();
                clearScreen();

                if (whatNext == 1) {
                    this.searchProducts();
                } else {
                    this.start();
                }
                WhatNext = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering 1 or 2");
            }
        } while (WhatNext);

//        try {
//            String name = input.nextLine();
//            MyProducts searchProduct = productsList.stream().filter(p -> p.name.equalsIgnoreCase(name)).findAny().get();
//            System.out.println("Your product has been found!");
//            System.out.println("\nName: " + searchProduct.name +
//                    "\nCategory: " + searchProduct.cate +
//                    "\nType: " + searchProduct.type +
//                    "\nPrice: " + searchProduct.price +
//                    "\nExpiration Date: " + searchProduct.expirationDate +
//                    "\nStatus: " + searchProduct.stat + "\n");
//            System.out.println();
//            System.out.println("1. Search Another Product\n2. Return to the Main Menu");
//            int whatNext = input.nextInt();
//            input.nextLine();
//            if (whatNext == 1) {
//                this.searchProducts();
//            } else {
//                this.start();
//            }
//        } catch (Exception exception) {
//            System.out.println("No product is found!");
//            System.out.println();
//            System.out.println("1. Try Again\n2. Return to the Main Menu");
//            int whatNext = input.nextInt();
//            input.nextLine();
//            if (whatNext == 1) {
//                this.searchProducts();
//            } else {
//                this.start();
//            }
//        }
    }

    // Override the listProducts() method from Product class
    @Override
    public void listProducts() {
        boolean isNot = true;
        int listScan =0;
        // Ask user which category they would like to see
        do {
            try {
                System.out.println("Choose from the categories:\n" +
                        "1. Fruits\n" +
                        "2. Sea Foods\n" +
                        "3. Baked Goods\n" +
                        "4. Frozen Foods");
                listScan = input.nextInt();
                while (listScan < 1 || listScan > 4) {
                    clearScreen();
                    System.out.println("Your number has has to be between 1 and 4");
                    System.out.println("Choose from the categories:\n" +
                            "1. Fruits\n" +
                            "2. Sea Foods\n" +
                            "3. Baked Goods\n" +
                            "4. Frozen Foods");
                    listScan = input.nextInt();
                    input.nextLine();
                }
                isNot = false;
            } catch (Exception exception) {
                input.next();
                clearScreen();
                System.out.println("Please try entering an integer");
            }
        } while (isNot);

        // Create an ArrayList as listShow to print the product
        ArrayList<MyProducts> listShow = new ArrayList<>();
        // Using switch statement to make the code faster.
        // If the answer of the user is 4 with switch statement the program would not need to go over all the cases.
        switch (listScan) {
            // If user input is 1 that means they want to see fruit category
            case 1:
                // With for each loop go over all the products within our product list
                for (MyProducts num : productsList) {
                    // If the category of the product is fruit than add that product to listShow ArrayList
                    if (num.cate == Categories.Fruits) {
                        // If there are 2 product with the same name within the same product, remove it from the listShow array to prevent the same thing to be written twice.
                        listShow.removeIf(num2 -> num2.name.equalsIgnoreCase(num.name));
                        listShow.add(num);
                    }
                }
                break;
            case 2:
                for (MyProducts num : productsList) {
                    if (num.cate == Categories.SeaFood) {
                        listShow.removeIf(num2 -> num2.name.equals(num.name));
                        listShow.add(num);
                    }
                }
                break;
            case 3:
                for (MyProducts num : productsList) {
                    if (num.cate == Categories.BakedGoods) {
                        listShow.removeIf(num2 -> num2.name.equals(num.name));
                        listShow.add(num);
                    }
                }
                break;
            case 4:
                for (MyProducts num : productsList) {
                    if (num.cate == Categories.FrozenFoods) {
                        listShow.removeIf(num2 -> num2.name.equals(num.name));
                        listShow.add(num);
                    }
                }
                break;
            default:
        }
        // If the size of the listShow is greater than 0 that means from the above for each loop code, the program found a product same as the category user asked for
        if (listShow.size() > 0) {
            boolean notInRange = true;
            int numb = 0;
            do {
                try {
                    clearScreen();
                    // Ask user which product they would like to learn more about.
                    System.out.println("Choose one of the product/s to learn more about it");
                    // Print all the products inside the listShow arrayList for user to see the options
                    for (int i = 0; i < listShow.size(); i++) {
                        System.out.println((i + 1) + ". " + listShow.get(i).name);
                    }
                    // Get the input from the user
                    numb = input.nextInt();
                    input.nextLine();
                    // If user input is less than 1 or greater than the size of the listShow ArrayList, ask again because the input of the user has to be in the range of the size of the ArrayList
                    while (listShow.size() < numb || 1 > numb){
                        clearScreen();
                        // Let the user know the problem with their input and ask again
                        System.out.println("Your number has has to be between 1 and " + listShow.size());
                        System.out.println("Choose one of the product/s to learn more about it");
                        for (int i = 0; i < listShow.size(); i++) {
                            System.out.println((i + 1) + ". " + listShow.get(i).name);
                        }
                        numb = input.nextInt();
                        input.nextLine();
                    }
                    notInRange = false;
                }catch (Exception exception){
                    input.next();
                    clearScreen();
                    System.out.println("Please try entering an integer");
                }
            }while(notInRange);

// If there are two or more product with the same name we also want to print those products, and that is why we will create another ArrayList named sameName
// numb is equal to the product that the user picked from the above code
// Reason we are subtracting 1 from numb is because index of array's always starts from 0.
            String name = listShow.get(numb - 1).name;
            ArrayList<MyProducts> sameName = new ArrayList<>();
            // for each loop to go over all the products from the productsList
            for (MyProducts product : productsList) {
                // if the String name of the product equals to another product within the productsList
                // add that to sameName ArrayList
                if (product.name.equalsIgnoreCase(name)) {
                    sameName.add(product);
                }
            }
            clearScreen();
            // for each loop to print the product with the same name as the product
            for (MyProducts sameN : sameName) {
                calendar.add(Calendar.DATE, sameN.expirationDate);
                System.out.println("Your product has been found!");
                System.out.println("\nName: " + sameN.name +
                        "\nCategory: " + sameN.cate +
                        "\nType: " + sameN.type +
                        "\nPrice: $" + sameN.price +
                        "\nExpiration Date: " + date.format(calendar.getTime()) +
                        "\nStatus: " + sameN.stat + "\n");
                calendar = Calendar.getInstance();
            }
            // Ask user what they want to do next, options are listing another object or returning to the main menu
            int whatNext;
            boolean WhatNext = true;
            do {
                try {
                    System.out.println("1. List Another Category\n2. Return to the Main Menu");
                    whatNext = input.nextInt();
                    input.nextLine();
                    clearScreen();

                    if (whatNext == 1) {
                        this.listProducts();
                    } else {
                        this.start();
                    }
                    WhatNext = false;
                } catch (Exception exception) {
                    input.next();
                    clearScreen();
                    System.out.println("Please try entering 1 or 2");
                }
            } while (WhatNext);

        // If the size of the listShow array is not greater than 0 that means there are no product within the
        // category that the user choose. If this is the case tell user there are no products to list for the category they choose.
        // Give the user the option to try again or return to the main menu
        } else {
            int whatNext;
            boolean WhatNext = true;
            do {
                try {
                    clearScreen();
                    System.out.println("Sorry, there are no products to list in this category.");
                    System.out.println();
                    System.out.println("1. Try Again\n2. Return to the Main Menu");
                    whatNext = input.nextInt();
                    input.nextLine();
                    clearScreen();
                    if (whatNext == 1) {
                        this.listProducts();
                    } else {
                        this.start();
                    }
                    WhatNext = false;
                } catch (Exception exception) {
                    input.next();
                    clearScreen();
                    System.out.println("Please try entering 1 or 2");
                }
            } while (WhatNext);
        }
    }

    // Simple exit() method, if user wants to exit from the program it wil simply call this method
    // to terminate the JVM.
    @Override
    public void exit() {
        System.out.println("\nExiting...");
        System.exit(0);
    }
}
