package Lab_3;

abstract class Asset {
    String descriptor;
    dateformat date;
    double current_value;

    abstract void displayDetails();
}

class Stock extends Asset {
    int num_shares;
    double share_price;
    assets asset_type;

    Stock() {
        this.descriptor = "Company";
        date = new dateformat();
        current_value = 10;
        num_shares = 100;
        share_price = 6.7;
        asset_type = assets.stock;
    }

    @Override
    void displayDetails() {
        System.out.println();
        System.out.println("Descriptor: " + descriptor);
        System.out.println("Date: ");
        date.display();
        System.out.println("Current Value: " + current_value);
        System.out.println("Number of Shares: " + num_shares);
        System.out.println("Share Price: " + share_price);
        System.out.println("Asset Type: " + asset_type);
    }
}

class Bond extends Asset {
    double interest_rate;
    assets asset_type;

    Bond() {
        descriptor = "Government";
        date = new dateformat(9, 7, 1945);
        current_value = 3.14;
        interest_rate = 16.64;
        asset_type = assets.bond;
    }

    @Override
    void displayDetails() {
        System.out.println();
        System.out.println("Descriptor: " + descriptor);
        System.out.println("Date: ");
        date.display();
        System.out.println("Current Value: " + current_value);
        System.out.println("Interest Rate: " + interest_rate);
        System.out.println("Asset Type: " + asset_type);
    }
}

class Savings extends Asset {
    double interest_rate;
    assets asset_type;

    Savings() {
        descriptor = "Personal";
        date = new dateformat(1, 1, 2020);
        current_value = 5000.0;
        interest_rate = 5.5;
        asset_type = assets.saving;
    }

    @Override
    void displayDetails() {
        System.out.println();
        System.out.println("Descriptor: " + descriptor);
        System.out.println("Date: ");
        date.display();
        System.out.println("Current Value: " + current_value);
        System.out.println("Interest Rate: " + interest_rate);
        System.out.println("Asset Type: " + asset_type);

    }
}

public class Q2 {
    public static void main(String[] args) {
        Stock s = new Stock();
        Bond b = new Bond();
        Savings sv = new Savings();
        s.displayDetails();
        b.displayDetails();
        sv.displayDetails();
    }
}
