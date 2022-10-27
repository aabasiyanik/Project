import java.text.*;
import java.util.*;

public class MyProducts implements Product {
    DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
    Calendar calendar = Calendar.getInstance();
    static Scanner input = new Scanner(System.in);

    public void start() {
        try {
            System.out.println("1. Add a New Product");
            System.out.println("2. Search Product by Name");
            System.out.println("3. List Products with Categories");
            System.out.println("4. Exit");
            int userInputForStart = input.nextInt();
            input.nextLine();
            while (userInputForStart > 4 || userInputForStart < 1) {
                clearScreen();
                System.out.println("Your number has has to be between 1 and 4\n");
                System.out.println("\n1. Add a New Product");
                System.out.println("2. Search Product by Name");
                System.out.println("3. List Products With Categories");
                System.out.println("4. Exit");
                userInputForStart = input.nextInt();
                input.nextLine();
            }

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

        } catch (Exception exception) {
            input.next();
            clearScreen();
            System.out.println("Please enter an integer between 1 and 4");
            start();
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 8; i++) {
            System.out.println("\n");
        }
    }

    public enum Categories {
        Fruits,
        SeaFood,
        FrozenFoods,
        BakedGoods;
    }

    public enum Status {
        In_Store,
        Sold_Out;
    }

    private String name;
    private String type;
    private double price;
    private int expirationDate;

    public MyProducts() {
    }

    private Categories cate;
    private Status stat;

    public MyProducts(Categories cate, String name, String type, double price, int expirationDate, Status stat) {
        this.cate = cate;
        this.name = name;
        this.type = type;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stat = stat;
    }

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

    @Override
    public void addProducts() {
        clearScreen();
        int category = 0, expirationDate = 0, et = 0, whatNext = 0;
        String name, type;
        double price = 0;
        System.out.println("Build your product\n");
        boolean isNot = true;
        do {
            try {
                System.out.println("Choose the category:\n" +
                        "1. Fruits\n" +
                        "2. Sea Foods\n" +
                        "3. Baked Goods\n" +
                        "4. Frozen Foods");
                category = input.nextInt();
                input.nextLine();
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
        Categories cate = category == 1 ? Categories.Fruits :
                category == 2 ? Categories.SeaFood :
                        category == 3 ? Categories.BakedGoods :
                                category == 4 ? Categories.FrozenFoods :
                                        null;


        System.out.println("Enter the name");
        name = input.nextLine();
        name = name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();

        System.out.println("Enter the type");
        type = input.nextLine();
        type = type.substring(0, 1).toUpperCase() +
                type.substring(1).toLowerCase();
        boolean isNotEqual = true;
        do {
            try {
                System.out.println("Enter the price");
                System.out.print("$");
                price = input.nextDouble();
                input.nextLine();
                price = Math.round(price * 10.0) / 10.0;
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
        do {
            try {
                System.out.println("Enter the expiration date");
                expirationDate = input.nextInt();
                input.nextLine();
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
        do {
            try {
                System.out.println("Choose the Status:\n" +
                        "1. In Store\n" +
                        "2. Sold Out");
                et = input.nextInt();
                input.nextLine();
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

        Status stat = et == 1 ? Status.In_Store :
                category == 2 ? Status.Sold_Out :
                        null;

        MyProducts product = new MyProducts(cate, name, type, price, expirationDate, stat);

        productsList.add(product);
        clearScreen();

        boolean WhatNext = true;
        do {
            try {
                System.out.println("Your product has been added to the list! " +
                        "\n1. Add Another Product\n2. Return to the Main Menu");
                whatNext = input.nextInt();
                input.nextLine();
                while (whatNext != 1 && whatNext != 2) {
                    clearScreen();
                    System.out.println("Your number must be 1 or 2");
                    System.out.println("\n1. Add Another Product\n2. Return to the Main Menu");
                    whatNext = input.nextInt();
                    input.nextLine();
                }
                clearScreen();
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

    @Override
    public void searchProducts() {
        System.out.println("Enter the name of the product you want to search from the list:");
        String name = input.nextLine();
        ArrayList<MyProducts> searchProduct = new ArrayList<>();
        for (MyProducts num : productsList) {
            if (num.name.equalsIgnoreCase(name)) {
                searchProduct.add(num);
            }
        }
        if (searchProduct.size() == 0) {
            clearScreen();
            System.out.println("No product is found!");
            System.out.println();
            System.out.println("1. Try Again\n2. Return to the Main Menu");
            int whatNext = input.nextInt();
            input.nextLine();
            clearScreen();
            if (whatNext == 1) {
                this.searchProducts();
            } else {
                this.start();
            }
        }
        for (MyProducts num2 : searchProduct) {
            clearScreen();
            calendar.add(Calendar.DATE, num2.expirationDate);
            System.out.println("Your product has been found!");
            System.out.println("\nName: " + num2.name +
                    "\nCategory: " + num2.cate +
                    "\nType: " + num2.type +
                    "\nPrice: $" + num2.price +
                    "\nExpiration Date: " + date.format(calendar.getTime()) +
                    "\nStatus: " + num2.stat + "\n");
        }
        System.out.println("1. Search Another Product\n2. Return to the Main Menu");
        int whatNext = input.nextInt();
        input.nextLine();
        clearScreen();
        if (whatNext == 1) {
            this.searchProducts();
        } else {
            this.start();
        }

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

    @Override
    public void listProducts() {
        System.out.println("Choose from the categories:\n" +
                "1. Fruits\n" +
                "2. Sea Foods\n" +
                "3. Baked Goods\n" +
                "4. Frozen Foods");
        int listScan = input.nextInt();
        input.nextLine();
        ArrayList<MyProducts> listShow = new ArrayList<>();
        switch (listScan) {
            case 1:
                for (MyProducts num : productsList) {
                    if (num.cate == Categories.Fruits) {
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
        if (listShow.size() > 0) {
            clearScreen();
            System.out.println("Choose one of the product/s to learn more about it");
            for (int i = 0; i < listShow.size(); i++) {
                System.out.println((i + 1) + ". " + listShow.get(i).name);
            }
            int numb = input.nextInt();
            input.nextLine();
            String name = listShow.get(numb - 1).name;
            ArrayList<MyProducts> sameName = new ArrayList<>();
            for (MyProducts product : productsList) {
                if (product.name.equalsIgnoreCase(name)) {
                    sameName.add(product);
                }
            }
            for (MyProducts sameN : sameName) {
                clearScreen();
                System.out.println("Your product has been found!");
                System.out.println("\nName: " + sameN.name +
                        "\nCategory: " + sameN.cate +
                        "\nType: " + sameN.type +
                        "\nPrice: $" + sameN.price +
                        "\nExpiration Date: " + date.format(calendar.getTime()) +
                        "\nStatus: " + sameN.stat + "\n");
            }
            System.out.println("1. List Another Category\n2. Return to the Main Menu");
            int whatNext = input.nextInt();
            input.nextLine();
            clearScreen();
            if (whatNext == 1) {
                this.listProducts();
            } else {
                this.start();
            }
        } else {
            clearScreen();
            System.out.println("Sorry, there are no products to list in this category.");
            System.out.println();
            System.out.println("1. Try Again\n2. Return to the Main Menu");
            int whatNext = input.nextInt();
            input.nextLine();
            clearScreen();
            if (whatNext == 1) {
                this.listProducts();
            } else {
                this.start();
            }
        }
    }

    @Override
    public void exit() {
        System.out.println("\nExiting...");
        System.exit(0);
    }
}
