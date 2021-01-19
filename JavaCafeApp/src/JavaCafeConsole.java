/**
 * A class to serve as the console-interface for the program
 * @author I Made Siva Aditya Surya
 * @version 1.4.2
 * @since 21 October 2020
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;

public class JavaCafeConsole {

    private static final Scanner sc = new Scanner(System.in);
    private static JavaCafe cafe;

    public static void main(String[] args) {
        cafe = new JavaCafe();

        String menu;
        do {
            // Display console menu
            System.out.println("+"+"-".repeat(50)+"+\n");
            System.out.println(" ".repeat(13)+"JAVA CAFE CONSOLE PROGRAM");
            System.out.println("\n+"+"-".repeat(50)+"+");
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Manage Account");
            System.out.println("4. Manage Item");
            System.out.println("5. View Receipt History");
            System.out.println("0. Exit Program\n");

            // Ask for input and validate the input
            List<Integer> menuList;
            menuList = Arrays.asList(1,2,3,4,5,0);

            do {
                System.out.print("Enter menu : ");
                menu = sc.nextLine();
            } while(!Validation.choice(menu, menuList));

            // Navigate to chosen menu
            System.out.println();
            switch(menu) {
                case "1": loginMenu(); break;
                case "2": registerCashier(); break;
                case "3": manageAccountMenu(); break;
                case "4": manageItemMenu(); break;
                case "5": receiptHistory(); break;
                case "0":
                    System.out.println("Exiting program...\n");
                    System.out.println("+"+"-".repeat(50)+"+");
                    break;
                default: break;
            }
            System.out.println();

            //---TESTING PURPOSE---//
            if (!cafe.findUser("user"))
                cafe.registUser("user","user","user");

            if (!cafe.findUser("haryinternship"))
                cafe.registUser("haryinternship","12341234","Hary");

        } while (!menu.equals("0"));

        System.out.println("Thank you " +
                "for using Java Cafe Console Program!\n"+
                "See you next time~\n");
        System.out.println("-".repeat(52));
        System.out.printf("%-19s%32s%n%n",
                "Author: Siva Aditya", "Oct 2020");


    }

    //--------------SUB MENU IN CONSOLE MENU--------------//
    private static void loginMenu() {
        System.out.println("\n"+" ".repeat(22)+"LOGIN");
        System.out.println("\n1. Login as Cashier");
        System.out.println("2. Login as Cashier HARY (tester)");
        System.out.println("0. Back to Console Menu\n");

        String choice;
        do {
            System.out.print("Enter choice : ");
            choice = sc.nextLine();
        } while(!Validation.choice(choice, Arrays.asList(1,2,0)));

        switch(choice) {
            case "1": loginCashier(); break;
            case "2":
                System.out.println("\nLogin successful!");
                next();
                cashierMenu(cafe.loginUser(
                        "haryinternship",
                        "12341234")
                );
                break;
            case "0":
                System.out.println("\nBack to console menu...");
                next();
                break;
            default: break;
        }
    }

    private static void manageAccountMenu() {
        if (cafe.getNoOfUser()<=0) {
            Validation.errMsg("No user has been added");
            next();
            return;
        }

        String menu;
        do {
            System.out.println("\n"+" ".repeat(18)+"MANAGE ACCOUNT");
            System.out.println("\n1. View All User Account");
            System.out.println("2. Delete Account");
            System.out.println("0. Back to Console Menu\n");


            do {
                System.out.print("Enter menu : ");
                menu = sc.nextLine();
            } while(!Validation.choice(menu, Arrays.asList(1,2,0)));

            switch (menu) {
                case "1":
                    System.out.println("\n"+cafe.allUser());
                    next();
                    break;
                case "2": deleteAccount(); break;
                case "0":
                    System.out.println("\nGoing back to console menu...\n");
                    break;
                default: break;
            }

            if (cafe.getNoOfUser()<=0) {
                System.out.println("\nGoing back to console menu...\n");
                next();
                return;
            }
        } while (!menu.equals("0"));
        next();
    }

    private static void manageItemMenu() {
        String menu;
        do {
            System.out.println("\n"+" ".repeat(20)+"MANAGE ITEM");
            System.out.println("\n1. Add New Item");
            System.out.println("2. Delete Item");
            System.out.println("3. View All Item");
            System.out.println("0. Back to Console Menu\n");

            do {
                System.out.print("Enter menu : ");
                menu = sc.nextLine();
            } while(!Validation.choice(menu, Arrays.asList(1,2,3,0)));

            switch (menu) {
                case "1": addNewItem(); break;
                case "2": deleteItem(); break;
                case "3":
                    if (cafe.getNoOfItem()>0)
                        System.out.println("\n"+cafe.allItem());
                    else
                        Validation.errMsg("No item has been added");
                    next();
                    break;
                case "0":
                    System.out.println("\nGoing back to console menu...\n");
                    break;
                default: break;
            }
        } while(!menu.equals("0"));
        next();
    }

    //----------------DISPLAY CASHIER MENU----------------//

    private static void cashierMenu(Cashier cashier) {
        String menu;
        do {
            System.out.println("\n"+"-".repeat(52));
            System.out.println(" ".repeat(20)+"CASHIER MENU");
            System.out.printf("%-17s%31s%n",
                    StringHelper.limit("Name: "+cashier.getFullName(),17),
                    LocalDate.now());
            System.out.println("-".repeat(52)+"\n");
            System.out.println("1. Create New Receipt");
            System.out.println("2. View All Receipt");
            System.out.println("3. Account Setting");
            System.out.println("0. Logout\n");

            List<Integer> menuList;
            menuList = Arrays.asList(1,2,3,0);

            do {
                System.out.print("Enter menu : ");
                menu = sc.nextLine();
            } while(!Validation.choice(menu,menuList));

            System.out.println();
            switch(menu) {
                case "1":
                    if (cafe.getNoOfItem()<=0){
                        Validation.errMsg("Item database is empty");
                        next();
                    }
                    else
                        receiptMenu(cashier);
                    break;
                case "2": viewCashierReceipt(cashier); break;
                case "3": accountSettingMenu(cashier); break;
                case "0":
                    System.out.println("\nLogging out...\n");
                    break;
                default: break;
            }
        } while(!menu.equals("0"));
        next();
    }
    //--------------SUB MENU IN CASHIER MENU--------------//

    private static void accountSettingMenu(Cashier cashier) {
        String menu;
        do {
            System.out.println("\n"+" ".repeat(19)+"Account Setting");
            System.out.println("\n1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Edit Full Name");
            System.out.println("0. Back to Cashier Menu\n");

            do {
                System.out.print("Enter menu : ");
                menu = sc.nextLine();
            } while(!Validation.choice(menu, Arrays.asList(1,2,3,0)));

            switch (menu) {
                case "1": changeUsername(cashier); break;
                case "2": changePassword(cashier); break;
                case "3": editCashierName(cashier); break;
                case "0":
                    System.out.println("\nGoing back " +
                            "to cashier menu...\n");
                    break;
                default: break;
            }

        } while(!menu.equals("0"));
        next();

    }

    //----------------DISPLAY RECEIPT MENU----------------//

    private static void receiptMenu(Cashier cashier) {
        Receipt newReceipt = new Receipt(cashier);
        inputItem(newReceipt);
        String menu;
        do {
            System.out.println("\n");
            System.out.println(" ".repeat(23)+"RECEIPT");
            System.out.printf("%-18s%31s%n",
                    StringHelper.limit(
                            "Cashier Name: "+cashier.getFullName().toUpperCase(),
                            18),
                    "Date: "+LocalDate.now());
            System.out.printf("%-20s%32s%n",
                    "Receipt ID: "+newReceipt.getReceiptID(),
                    newReceipt.getStatus());
            System.out.println("=".repeat(52)+"\n");
            System.out.println("1. Input Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View Checkout Item");
            System.out.println("4. Proceed with Payment");
            System.out.println("5. Print Receipt");
            System.out.println("6. Save Receipt to txt File");
            System.out.println("0. Back to Cashier Menu\n");

            List<Integer> menuList;
            menuList = Arrays.asList(1,2,3,4,5,6,0);

            do {
                System.out.print("Enter menu : ");
                menu = sc.nextLine();
            } while(!Validation.choice(menu,menuList));

            System.out.println();
            switch(menu) {
                case "1": inputItem(newReceipt); break;
                case "2": removeItem(newReceipt); break;
                case "3":
                    if (newReceipt.getNoOfItem()<=0)
                        Validation.errMsg("No item has been inputted");
                    else
                        System.out.println(newReceipt.checkoutItemList());
                    next();
                    break;
                case "4": proceedPayment(newReceipt); break;
                case "5": printReceipt(newReceipt); break;
                case "6": saveToFile(newReceipt); break;
                case "0":
                    menu = closeReceipt(newReceipt);
                    break;
                default: break;
            }

        } while(!menu.equals("0"));
        System.out.println("Going back to cashier menu...");
        next();
    }

    //------------Console Menu Related Methods------------//

    private static void registerCashier() {
        System.out.println("\n"+" ".repeat(16)+"CASHIER REGISTRATION");
        System.out.println("\nPlease fill out the data below!");
        System.out.println("-".repeat(52)+"\n");

        String username;
        boolean invalidUsername;
        do {
            do {
                System.out.print("Username  : ");
                username = sc.nextLine();
            } while(!Validation.username(username));

            invalidUsername = cafe.findUser(username);

            if (invalidUsername)
                Validation.errMsg("Username is already taken");

        } while(invalidUsername);

        String password;
        do {
            System.out.print("Password  : ");
            password = sc.nextLine();
        } while(!Validation.password(password));


        String fullName;
        do {
            System.out.print("Full Name : ");
            fullName = sc.nextLine();
        } while(!Validation.name(fullName));

        cafe.registUser(username,password,fullName);
        System.out.println("\nRegistration successful!");
        System.out.println("You can login using the credentials above\n"+
                "from the console menu to access the Cashier Menu.");
        next();

    }

    private static void loginCashier() {
        System.out.println("-".repeat(52)+"\n");
        System.out.print("Username : ");
        String username = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();
        System.out.println();
        if (cafe.loginUser(username,password)!=null){
            System.out.println("Login successful!");
            next();
            cashierMenu(cafe.loginUser(username,password));
        }
        else {
            Validation.errMsg("Username/password is incorrect");
            next();
        }

    }

    private static void deleteAccount() {
        System.out.println("\nDelete Account:\n");
        System.out.println(cafe.allUser()+"\n");

        String pos;
        do {
            System.out.print("Enter account number: ");
            pos = sc.nextLine();
        } while (!Validation.integer(pos));

        int index = Integer.parseInt(pos);
        Cashier user = cafe.deleteUser(index-1);

        if (user==null) {
            Validation.errMsg("Specified account not found");
        } else {
            System.out.println();
            System.out.println(user.getUsername()+" successfully " +
                    "deleted!");
        }
        next();
    }

    private static void addNewItem() {
        System.out.println("\nAdd new item");
        System.out.println("-".repeat(30)+"\n");

        String itemName;
        do {
            System.out.print("Item name  : ");
            itemName = sc.nextLine();
        } while(!Validation.string(itemName));

        if (cafe.findItem(itemName)) {
            System.out.println("\nWARNING: "+
                    itemName+" already exist in database");
            System.out.println(cafe.getItem(itemName).toString());

            System.out.println("\nPress <Enter> to continue or " +
                    "<C> to re-input item name");
            String choice = sc.nextLine().toUpperCase();

            if (choice.equals("C")) addNewItem();
        }

        String itemPrice;
        do {
            System.out.print("Item price : IDR ");
            itemPrice = sc.nextLine();
        } while(!Validation.decimal(itemPrice,-1));

        cafe.addItem(itemName,Double.parseDouble(itemPrice));
        System.out.println("\nItem successfully added!");
        System.out.print("Press <Enter> to view item list");
        sc.nextLine();
        System.out.println("\n"+cafe.allItem());

        System.out.println("\nNew Item:\n\n"+
                        cafe.getItem(itemName).toString());
        next();
    }

    private static void deleteItem() {
        if (cafe.getNoOfItem()<=0) {
            Validation.errMsg("No item has been added");
            next();
            return;
        }

        System.out.println("\n"+cafe.allItem());

        String pos;
        do {
            System.out.print("Enter item number: ");
            pos = sc.nextLine();
        } while (!Validation.integer(pos));

        int index = Integer.parseInt(pos);
        Item item = cafe.deleteItem(index-1);

        if (item==null) {
            Validation.errMsg("Specified item not found");
        } else {
            System.out.println();
            System.out.println(item.getName()+" successfully " +
                    "deleted!");
        }
        next();
    }

    private static void receiptHistory() {
        if (cafe.getNoOfReceipt()<=0) {
            Validation.errMsg("No receipt has been recorded");
            next();
            return;
        }

        System.out.println("\nReceipt History");
        System.out.println("-".repeat(20));
        System.out.println("\n"+cafe.viewReceipt()+"\n");
        next();
    }

    //------------Cashier Menu Related Methods------------//

    private static void viewCashierReceipt(Cashier cashier) {
        String receiptList = cafe.viewReceipt(cashier);
        if (receiptList.equals("")) {
            Validation.errMsg("No receipt has been recorded so far");
            next();
            return;
        }

        System.out.println("\nAll Receipt:");
        System.out.println("-".repeat(20));
        System.out.println(receiptList+"\n");
        next();
    }

    private static void changeUsername(Cashier cashier) {
        System.out.println("\nChange username");
        System.out.println("-".repeat(30)+"\n");

        String username;
        int invalidUsername;
        do {
            do {
                System.out.println("Old Username : "+cashier.getUsername());
                System.out.print("New Username : ");
                username = sc.nextLine();
            } while(!Validation.username(username));

            if (cashier.getUsername().equals(username))
                invalidUsername = -1;
            else {
                invalidUsername = cafe.findUser(username)? 1:0;

                if (invalidUsername==1)
                    Validation.errMsg("Username is already taken");
            }
        } while(invalidUsername!=0 && invalidUsername!=-1);

        switch (invalidUsername) {
            case 0:
                cashier.setUsername(username);
                System.out.println("\nUsername successfully changed!\n");
                break;
            case -1:
                System.out.println("\nNothing changed.\n");
                break;
            default: break;
        }
        next();
    }

    private static void changePassword(Cashier cashier) {
        System.out.println("\nChange password");
        System.out.println("-".repeat(30)+"\n");

        String password;
        int changePass;
        do {
            System.out.println("Old Password : "+cashier.getPassword());
            System.out.print("New Password : ");
            password = sc.nextLine();

            if (cashier.getPassword().equals(password))
                changePass = -1;
            else
                changePass = Validation.password(password)? 1:0;

        } while(changePass!=1 && changePass!=-1);

        switch (changePass) {
            case 1:
                cashier.setPassword(password);
                System.out.println("\nPassword successfully changed!\n");
                break;
            case -1:
                System.out.println("\nNothing changed.\n");
                break;
            default: break;
        }
        next();
    }

    private static void editCashierName(Cashier cashier) {
        System.out.println("\nEdit full name");
        System.out.println("-".repeat(30)+"\n");

        String fullName;
        int changeName;
        do {
            System.out.println("Old Full Name : "+cashier.getFullName());
            System.out.println("New Full Name : ");
            fullName = sc.nextLine();

            if (cashier.getFullName().equals(fullName))
                changeName = -1;
            else
                changeName = Validation.name(fullName)? 1:0;

        } while(changeName!=1 && changeName!=-1);

        switch (changeName) {
            case 1:
                cashier.setFullName(fullName);
                System.out.println("\nName successfully edited!\n");
                break;
            case -1:
                System.out.println("\nNothing changed.\n");
                break;
            default: break;
        }
        next();
    }

    //------------Receipt Menu Related Methods------------//

    private static void inputItem(Receipt receipt) {
        if (receipt.getStatus().equals("PAID")) {
            Validation.errMsg("Payment has already been done");
            next();
            return;
        }

        String input = "";
        do {
            System.out.println("\nInput Item:");
            System.out.println("----------------");
            System.out.println("\n"+cafe.allItem()+"\n");

            String id;
            do {
                System.out.print("Enter item ID  : ");
                id = sc.nextLine();
            } while (!Validation.integer(id));

            Item item = cafe.getItem(Integer.parseInt(id));

            if (item==null) {
                Validation.errMsg("Item ID not found");
            } else {
                String qty;
                do {
                    System.out.print("Enter item Qty : ");
                    qty = sc.nextLine();
                } while (!Validation.integer(qty,1));

                receipt.checkoutItem(item,Integer.parseInt(qty));
                System.out.println("\n"+receipt.inputItemList());
            }

            System.out.print("Press <Enter> to input another item or <F>" +
                    " to finish ");
            input = sc.nextLine().toUpperCase();

        } while (!input.equals("F"));
    }

    private static void removeItem(Receipt receipt) {
        if (receipt.getNoOfItem()<=0) {
            Validation.errMsg("No item has been inputted");
            next();
            return;
        }

        if (receipt.getStatus().equals("PAID")) {
            Validation.errMsg("Payment has already been done");
            next();
            return;
        }

        System.out.println("\nRemove Item:");
        System.out.println(receipt.inputItemList()+"\n");

        String pos;
        do {
            System.out.print("Enter item number: ");
            pos = sc.nextLine();
        } while (!Validation.integer(pos));

        int index = Integer.parseInt(pos);
        ItemCheckout item = receipt.removeItem(index-1);

        if (item==null) {
            Validation.errMsg("Specified item not found");
            next();
        } else {
            System.out.println();
            System.out.println(item.getItem().getName()+" successfully " +
                    "removed!");

            System.out.print("\nPress <Enter> to remove another item or " +
                    "<F> to finish ");
            String choice = sc.nextLine().toUpperCase();

            if (!choice.equals("F")){
                if (receipt.getNoOfItem()<=0) {
                    Validation.errMsg("Checkout item list is empty");
                    next();
                    return;
                }
                removeItem(receipt);
            }
        }
    }

    private static void proceedPayment(Receipt receipt) {
        if (receipt.getNoOfItem()<=0) {
            Validation.errMsg("No item has been inputted");
            next();
            return;
        }

        boolean paySuccess;

        do {
            System.out.println("\n"+receipt.checkoutItemList()+"\n");
            String pay;
            do {
                System.out.print("Enter cash: IDR ");
                pay = sc.nextLine();
            } while (!Validation.decimal(pay,-1));
            double cash = Double.parseDouble(pay);
            paySuccess = receipt.processPayment(cash);
            if (!paySuccess) {
                Validation.errMsg("Insufficient cash");
                System.out.print("Press <Enter> to retry payment or <C>" +
                        " to cancel payment ");
                String input = sc.nextLine().toUpperCase();

                if (input.equals("C")) return;
            }
        } while (!paySuccess);

        System.out.println("\nPayment successful!");
        System.out.println("Cash change: IDR "+(int)receipt.getCashChange());

        System.out.println("\nPrinting receipt...");
        System.out.print("Press <Enter> to show receipt");
        sc.nextLine();

        System.out.println("\n\n\n"+receipt.printReceipt()+"\n\n\n");
        next();
    }

    private static void printReceipt(Receipt receipt) {
        if (receipt.getStatus().equals("NOT PAID")) {
            Validation.errMsg("Payment has not been done");
            next();
            return;
        }

        System.out.println("\n\n\n"+receipt.printReceipt()+"\n\n\n");
        next();
    }

    private static void saveToFile(Receipt receipt) {
        if (receipt.getStatus().equals("NOT PAID")) {
            Validation.errMsg("Payment has not been done");
            next();
            return;
        }
        System.out.println("\nSave to txt file:");
        System.out.println("\n"+receipt.printReceipt()+"\n");
        String fileName;
        do {
            System.out.print("Enter txt file name : ");
            fileName = sc.nextLine();
        } while(!Validation.string(fileName));

        String result = receipt.saveToFile(fileName);

        if (!result.equals("")){
            Validation.errMsg(result);
            return;
        }

        System.out.println("\nReceipt successfully saved to "+fileName+
                ".txt!");
        System.out.println("File is saved in program directory folder.");
        next();
    }

    private static String closeReceipt(Receipt receipt) {
        if (receipt.getStatus().equals("PAID")) {
            cafe.recordReceipt(receipt);
            System.out.println("\n" +
                    "Receipt successfully recorded in database.");
            return "0";
        }

        System.out.println("\nWARNING: " +
                "Receipt has not been processed.");

        System.out.print("Are you sure you want to cancel this receipt " +
                "(Y/N)? ");
        char choice = sc.nextLine().toUpperCase().charAt(0);

        if (choice == 'Y') {
            cafe.resetReceiptID();
            System.out.println("Canceled...");
            return "0";
        } else
            return "";
    }

    /**
     * This method is used to prompt user to press Enter
     * before continuing to the next stage of the program
     */
    public static void next() {
        System.out.print("Press <Enter> to continue...");
        sc.nextLine(); System.out.println("\n");
    }

}
