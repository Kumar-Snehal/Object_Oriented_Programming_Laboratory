package Lab_4;

class User {

    private String userId, password, loginStatus;
    private dateformat registerDate;

    public Boolean verifyLogin() {
        Boolean ans = false;
        return ans;
    }
}

class Administrator extends User {

    private String adminName, email;

    public Boolean updateCatalog() {
        Boolean ans = false;
        return ans;
    }
}

class Customer extends User {

    private String customerName, address, email, creditCardInfo, shippingInfo;
    private float accountBalance;

    public void register() {

    }

    public void login() {

    }

    public void updateProfile() {

    }
}

class ShoppingCart{
    
}

public class Q6 {
    public static void main(String[] args) {
        
    }
}
