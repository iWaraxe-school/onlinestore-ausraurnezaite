package products;

public class Product {
    private String name;
    private int rate;
    private double price;

    public Product(String name, int rate, int price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){
        return ("Name: " + name + ", Rate: " + rate + ", Price: " + price);
    }
}
