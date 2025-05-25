package Service.Implementation;

import Exceptions.NoWorkerException;
import Exceptions.NotEnoughQuantity;
import Models.*;
import Service.Interface.ICashRegisterService;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CashRegisterService implements ICashRegisterService {
    @Override
    public CashRegister createCashRegister(int number) {
        CashRegister cashRegister = new CashRegister(number);
        return cashRegister;
    }

    @Override
    public ArrayList<Receipt> checkoutCustomers(LocalDateTime timeOfSale,
                                                Queue<Cart> customers, Store store, CashRegister cashRegister) throws NoWorkerException {
        int workerId = cashRegister.getWorkerId();
        if (workerId == 0) {
            throw new NoWorkerException(cashRegister.getNumber());
        }

        ArrayList<Receipt> receipts = new ArrayList<>();

        int receiptId = getReceiptId(store.getId());
        if (receiptId == 0) {
            receiptId = 1;
        }
        while (!customers.isEmpty()) {
            ArrayList<CartItem> items = customers.poll().getItems();
            ArrayList<SoldProduct> soldProducts = new ArrayList<>();
            double total = 0;
            for (int i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                total += item.getProductPrice() * item.getQuantity();
                Product product = getProduct(store, item.getProductId());

                if (product == null) {
                    System.out.println("Product " + item.getProductId() + " does not exist in store");
                    continue;
                }
                if (LocalDate.now().isAfter(product.getExpirationDate())) {
                    System.out.println("Product " + product.getProductName() + "(Id: " + product.getProductId() + ") is expired. Expiration date: " + product.getExpirationDate());
                } else {
                    product.setQuantity(product.getQuantity() - item.getQuantity());

                    Period period = Period.between(product.getExpirationDate(), LocalDate.now());
                    double price = product.getProductPrice();
                    if (period.getDays() <= store.getDaysTillExpiration()) {
                        price = price - price * store.getDiscount();
                    }

                    SoldProduct soldProduct = new SoldProduct(product.getProductName(), item.getProductId(),
                            price, item.getQuantity());
                    soldProducts.add(soldProduct);
                }
            }

            Receipt receipt = new Receipt(receiptId, workerId, timeOfSale, total, soldProducts, store.getId());
            receipts.add(receipt);
            receiptId++;
        }
        return receipts;
    }

    private int getReceiptId(int id) {
        String path = "./receipts/";

        Set<String> allReceipts = Stream.of(new File(path).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());

        return (int) allReceipts.stream().filter(r -> r.startsWith(id + "_")).count();
    }

    @Override
    public void addCustomersToQueue(Cart cart, CashRegister cashRegister) {
        if (cashRegister != null) {
            cashRegister.addCustomers(cart);
        }
    }

    @Override
    public void setWorkerToCashRegister(int workerId, int cashRegisterNumber, Store store) {
        CashRegister cashRegister = store.getCashRegisters().stream().filter(c -> c.getNumber() == cashRegisterNumber).findFirst().orElse(null);

        if (cashRegister != null) {
            if (cashRegister.getWorkerId() == 0) {
                Worker worker = store.getWorkers().stream().filter(w -> w.getWorkerId() == workerId).findFirst().orElse(null);
                if (worker != null) {
                    if (worker.getCashRegisterId() == 0) {
                        cashRegister.setWorkerId(workerId);
                        worker.setCashRegisterId(cashRegister.getNumber());
                    } else {
                        System.out.println("Worker already works at cash register " + worker.getCashRegisterId());
                    }
                } else {
                    System.out.println("Worker with Id: " + workerId + "does not exist!");
                }
            } else {
                System.out.println("There is already a worker at cash register " + cashRegisterNumber + "!");
            }
        } else {
            System.out.println("Cash register with number: " + cashRegisterNumber + " does not exist!");
        }
    }

    @Override
    public void removeWorkerFromCashRegister(int workerId, int cashRegisterNumber, Store store) {
        CashRegister cashRegister = store.getCashRegisters().stream().filter(c -> c.getNumber() == cashRegisterNumber).findFirst().orElse(null);

        if (cashRegister != null) {
            if (cashRegister.getWorkerId() != 0) {
                Worker worker = store.getWorkers().stream().filter(w -> w.getWorkerId() == workerId).findFirst().orElse(null);
                if (worker != null) {
                    if (worker.getCashRegisterId() == cashRegisterNumber) {
                        cashRegister.setWorkerId(0);
                        worker.setCashRegisterId(0);
                    } else {
                        System.out.println("Worker does not work at cash register " + cashRegisterNumber);
                    }
                } else {
                    System.out.println("Worker with Id: " + workerId + "does not exist!");
                }
            } else {
                System.out.println("There is no worker at cash register " + cashRegisterNumber + "!");
            }
        } else {
            System.out.println("Cash register with number: " + cashRegisterNumber + " does not exist!");
        }
    }

    private Product getProduct(Store store, int idToBeFound) {
        ArrayList<Product> products = store.getProducts();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductId() == idToBeFound) {
                return product;
            }
        }
        return null;
    }
}
