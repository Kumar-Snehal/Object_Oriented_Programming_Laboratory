package Lab_4;

import java.util.*;

abstract class User {

    private String userId;
    private String password;
    private String loginStatus;
    private java.util.Date registerDate;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.loginStatus = "Logged Out";
        this.registerDate = new java.util.Date();
    }

    public boolean verifyLogin(String userId, String password) {
        return this.userId.equals(userId) && this.password.equals(password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

class Customer extends User {

    private String customerName;
    private String address;
    private String email;
    private String creditCardInfo;
    private String shippingInfo;
    private float accountBalance;

    private ArrayList<ShoppingCart> shoppingCarts;
    private ArrayList<Order> orders;

    public Customer(String email, String password, float accountBalance) {
        super(email, password);
        this.accountBalance = accountBalance;
        this.shoppingCarts = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void register() {

    }

    public void login() {

    }

    public void updateProfile(String name, String address, String creditCardInfo, String shippingInfo) {
        this.customerName = name;
        this.address = address;
        this.creditCardInfo = creditCardInfo;
        this.shippingInfo = shippingInfo;
    }
}

class Administrator extends User {

    private String adminName;
    private String email;

    public Administrator(String adminName, String email, String userId, String password) {
        super(userId, password);
        this.adminName = adminName;
        this.email = email;
    }

    public boolean updateCatalog(int productId) {

        return true;
    }
}

class ShoppingCart {

    private int cardId;
    private int productId;
    private int quantity;
    private java.util.Date dateAdded;

    public ShoppingCart(int cardId) {
        this.cardId = cardId;
        this.dateAdded = new java.util.Date();
    }

    public void addCardItem(int productId, int quantity) {
    }

    public void updateQuantity(int quantity) {
    }

    public void viewCartDetails() {

    }

    public void checkOut() {

    }
}

class Order {

    private int orderId;
    private java.util.Date dateCreated;
    private java.util.Date dateShipped;
    private String customerName;
    private String customerId;
    private String status;
    private String shippingId;

    private static int nextOrderId = 1;

    private Customer customer;
    private OrderDetails orderDetails;
    private ShippingInfo shippingInfo;

    public Order(Customer customer) {
        this.customer = customer;
        this.orderId = nextOrderId++;
        this.dateCreated = new java.util.Date();
        this.orderDetails = new OrderDetails(orderId, this);
        this.shippingInfo = new ShippingInfo(this);
    }

    public void placeOrder() {

        this.status = "Placed";
    }
}

class OrderDetails {

    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private float unitCost;
    private float subTotal;

    private Order order;

    public OrderDetails(int orderId, Order order) {
        this.orderId = orderId;
        this.order = order;
    }

    public void calPrice() {
        subTotal = quantity * unitCost;
    }

    public void setDetails(int productId, String productName, int quantity, float unitCost) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }
}

class ShippingInfo {

    private int shippingId;
    private String shippingType;
    private int shippingCost;
    private int shippingRegionId;

    private static int nextShippingId = 1;

    private Order order;

    public ShippingInfo(Order order) {
        this.order = order;
        this.shippingId = nextShippingId++;
    }

    public void updateShippingInfo(String shippingType, int shippingCost, int shippingRegionId) {
        this.shippingType = shippingType;
        this.shippingCost = shippingCost;
        this.shippingRegionId = shippingRegionId;
    }
}

public class Q6 {

    public static void main(String[] args) {

    }
}
