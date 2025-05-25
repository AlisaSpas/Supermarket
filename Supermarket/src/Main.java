import Exceptions.NoProductExeption;
import Exceptions.NoWorkerException;
import Exceptions.NotEnoughQuantity;
import Models.*;
import Service.Implementation.*;
import Service.Interface.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static ArrayList<Store> stores;
    static IStoreService storeService;
    static IWorkerService workerService;
    static IProductService productService;
    static ICashRegisterService cashRegisterService;
    static ICartService cartService;
    static ISerializationService serializationService;
    static IReceiptService receiptService;
    static ITxtService txtService;

    public static void menu() {
        System.out.println("1.Create a store: ");
        System.out.println("2.Create an employee: ");
        System.out.println("3.Create a product: ");
        System.out.println("4.Create cash register: ");
        System.out.println("5.Set worker to cash register: ");
        System.out.println("6.Remove worker from cash register: ");
        System.out.println("7.Shop at a store: ");
        System.out.println("8.Checkout clients: ");
        System.out.println("9.Store report: ");
        System.out.println("0.Exit: ");
    }

    public static void options(int choice) throws IOException, NoWorkerException {
        switch (choice) {
            case 1:
                createStore();
                break;
            case 2:
                createEmployee();
                break;
            case 3:
                createProduct();
                break;
            case 4:
                createCashRegister();
                break;
            case 5:
                setWorkerToCashRegister();
                break;
            case 6:
                removeWorkerFromCashRegister();
                break;
            case 7:
                shopAtStore();
                break;
            case 8:
                checkoutCustomers();
                break;
            case 9:
                storeReport();
            case 0:
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private static void storeReport() {
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        System.out.println(storeService.getReport(store));
    }

    private static void removeWorkerFromCashRegister() {
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        System.out.println("Get worker id: ");
        storeService.printWorkers(store);
        int workerId = Integer.parseInt(scanner.nextLine());

        System.out.println("Get cash register number: ");
        storeService.printCashRegisters(store);
        int cashRegisterNum = Integer.parseInt(scanner.nextLine());

        cashRegisterService.removeWorkerFromCashRegister(workerId, cashRegisterNum, store);
    }

    private static void setWorkerToCashRegister() {
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        System.out.println("Get worker id: ");
        storeService.printWorkers(store);
        int workerId = Integer.parseInt(scanner.nextLine());

        System.out.println("Get cash register number: ");
        storeService.printCashRegisters(store);
        int cashRegisterNum = Integer.parseInt(scanner.nextLine());

        cashRegisterService.setWorkerToCashRegister(workerId, cashRegisterNum, store);
    }

    private static void printStores() {
        System.out.println("Choose store by id: ");
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            System.out.println("id: " + store.getId());
        }
    }

    public static void checkoutCustomers() throws IOException {
        System.out.println("Checkout customers: ");
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);
        try {
            if (store != null) {
                System.out.println("Choose a cash register by number: ");
                storeService.printCashRegisters(store);
                int registerNum = Integer.parseInt(scanner.nextLine());
                CashRegister register = store.getCashRegisters().stream().
                        filter(r -> r.getNumber() == registerNum).findFirst().orElse(null);
                if (register != null) {
                    ArrayList<Receipt> receipts = cashRegisterService.checkoutCustomers(LocalDateTime.now(),
                            register.getCustomers(), store, register);
                    if (receipts != null) {
                        System.out.println("Receipts: ");
                        for (int i = 0; i < receipts.size(); i++) {
                            Receipt receipt = receipts.get(i);
                            serializationService.serialization(receipt);
                            txtService.writeTxtFile(receipt);
                            receiptService.printReceipt(serializationService.deserialization(receipt.getStoreId() + "_" + receipt.getReceiptId()));
                            System.out.println();
                        }
                    }

                }
            } else {
                System.out.println("Store not found with id: " + storeId);
            }
        } catch (NoWorkerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void shopAtStore() {
        System.out.println("Shop at store: ");
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        if (store != null) {
            System.out.println("Write cart id: ");
            int cartId = Integer.parseInt(scanner.nextLine());
            System.out.println("Write customer money: ");
            double customerMoney = Double.parseDouble(scanner.nextLine());
            Cart cart = new Cart(cartId, customerMoney);
            System.out.println("All products at store: ");
            storeService.printProducts(store);
            System.out.println("Choose product by id(0 to end): ");
            int productId = Integer.parseInt(scanner.nextLine());
            while (productId != 0) {
                System.out.println("Choose quantity: ");
                double quantity = Double.parseDouble(scanner.nextLine());
                try {
                    handleCartProduct(productId, store, quantity);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }
                CartItem item = cartService.createCartItem(store, productId, quantity);
                cart.addItems(item);
                System.out.println("Choose product by id(0 to end): ");
                productId = Integer.parseInt(scanner.nextLine());
            }
            System.out.println("Choose a cash register by number: ");
            storeService.printCashRegisters(store);
            int registerNum = Integer.parseInt(scanner.nextLine());
            cashRegisterService.addCustomersToQueue(cart,
                    store.getCashRegisters().stream().filter(r -> r.getNumber() == registerNum)
                            .findFirst().orElse(null));

        } else {
            System.out.println("Store not found with id: " + storeId);
        }
    }

    private static void handleCartProduct(int productId, Store store, double quantity) throws NoProductExeption, NotEnoughQuantity {
        Product product = store.getProducts().stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst().orElse(null);

        if (product == null) {
            throw new NoProductExeption(productId);
        } else if (product.getQuantity() < quantity) {
            throw new NotEnoughQuantity(productId, product.getProductName(), product.getQuantity());
        } else {
            product.setQuantity(product.getQuantity() - quantity);
        }
    }

    public static void createCashRegister() {
        System.out.println("Create cash register: ");
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        if (store != null) {
            System.out.println("Write register number: ");
            int registerNum = Integer.parseInt(scanner.nextLine());
            CashRegister cashRegister = cashRegisterService.createCashRegister(registerNum);
            storeService.addCashRegisters(store, cashRegister);
        } else {
            System.out.println("Store not found with id: " + storeId);
        }
    }

    public static void createProduct() {
        System.out.println("Create product: ");
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        if (store != null) {
            System.out.println("Write product id: ");
            int productId = Integer.parseInt(scanner.nextLine());
            System.out.println("Write product name: ");
            String productName = scanner.nextLine();
            System.out.println("Write product category(food/nonfood: ");
            String prodCategory = scanner.nextLine();
            ProductCategory productCategory;
            if (prodCategory.equalsIgnoreCase("food")) {
                productCategory = ProductCategory.FOOD;
            } else if (prodCategory.equalsIgnoreCase("nonfood")) {
                productCategory = ProductCategory.NONFOOD;
            } else {
                System.out.println("Invalid product category!");
                return;
            }
            System.out.println("Write single delivery fee: ");
            double singleDeliveryFee = Double.parseDouble(scanner.nextLine());
            System.out.println("Write months till expiry: ");
            int monthsTillExpire = Integer.parseInt(scanner.nextLine());
            System.out.println("Write product price: ");
            double productPrice = Double.parseDouble(scanner.nextLine());
            System.out.println("Write product quantity: ");
            double quantity = Double.parseDouble(scanner.nextLine());
            Product product = productService.createProduct(productId, productName, singleDeliveryFee,
                    productCategory, LocalDate.now().plusMonths(monthsTillExpire), productPrice, quantity);
            storeService.addProducts(store, product);
        } else {
            System.out.println("Store not found with id: " + storeId);
        }
    }

    public static void createEmployee() {
        System.out.println("Create employee:");
        printStores();
        int storeId = Integer.parseInt(scanner.nextLine());
        Store store = getStore(storeId);

        if (store != null) {
            System.out.println("Write worker id: ");
            int workerId = Integer.parseInt(scanner.nextLine());
            System.out.println("Write worker name: ");
            String workerName = scanner.nextLine();
            System.out.println("Write worker monthly salary: ");
            double workerSalary = Double.parseDouble(scanner.nextLine());
            Worker worker = workerService.createWorker(workerName, workerId, workerSalary);
            storeService.addWorkers(store, worker);
        } else {
            System.out.println("Store not found with id: " + storeId);
        }
    }

    public static void createStore() {
        System.out.println("Create a store: ");
        System.out.println("Store id: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (stores.stream().noneMatch(s -> s.getId() == id)) {
            System.out.println("Select a percent to overprice the food items: ");
            double overpriceFood = Double.parseDouble(scanner.nextLine());
            System.out.println("Select a percent to overprice the nonfood items: ");
            double overpriceNonFood = Double.parseDouble(scanner.nextLine());
            System.out.println("Select a number of days that an item is discounted before expiry: ");
            int daysForDiscountForSoonToExpire = Integer.parseInt(scanner.nextLine());
            System.out.println("Select the percent of the discount: ");
            double discount = Double.parseDouble(scanner.nextLine());
            Store newStore = storeService.createStore(id, overpriceFood, overpriceNonFood, daysForDiscountForSoonToExpire, discount);
            stores.add(newStore);
        } else {
            System.out.println("There is already store wtih the same Id: " + id);
        }
    }

    private static void initialize() {
        scanner = new Scanner(System.in);

        workerService = new WorkerService();
        workerService = new WorkerService();
        productService = new ProductService();
        cashRegisterService = new CashRegisterService();
        cartService = new CartService();
        serializationService = new SerializationService();
        receiptService = new ReceiptService();
        txtService = new TxtService();
        storeService = new StoreService(serializationService);

        ISeederService seederService = new SeederService();
        stores = seederService.seedStores();
    }

    private static Store getStore(int id) {
        return stores.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public static void main(String[] args) throws IOException, NoWorkerException {
        initialize();

        int choice = 99999;
        while (choice != 0) {
            try{

            menu();
            choice = Integer.parseInt(scanner.nextLine());
            options(choice);
            }catch (NumberFormatException ex){
                System.out.println("Invalid number. Please try again.");
            }
        }


//        IReceiptService receiptService = new ReceiptService();
//        SerializationService serializationService = new SerializationService();
//        ITxtService txtService = new TxtService();
//
//        ArrayList<Receipt> receipts = cashRegisterService.checkoutCustomers(LocalDateTime.now(),
//                register1.getCustomers(), store,register1);
//        for (int i = 0; i < receipts.size(); i++) {
//            serializationService.serialization(receipts.get(i));
//            receiptService.printReceipt(serializationService.deserialization(receipts.get(i).getReceiptId()));
//            txtService.writeTxtFile(receipts.get(i));
//        }
        //Receipt receipt1 = cashRegisterService.checkoutCustomers(1, LocalDateTime.now(),cart1,store,register1);
        //IReceiptService receiptService = new ReceiptService();
        //receiptService.printReceipt(receipt1);
//        SerializationService serializationService = new SerializationService();
//        serializationService.serialization(receipt1);
//        receiptService.printReceipt(serializationService.deserialization(receipt1.getReceiptId()));
//        ITxtService txtService = new TxtService();
//        txtService.writeTxtFile(receipt1);
    }
}