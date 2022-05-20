package products;

public class Product {
    private String name;
    private double rate;
    private double price;

    public static class Builder {
        private String name;
        private double rate;
        private double price;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder rate(double rate) {
            this.rate = rate;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.name = name;
            product.rate = rate;
            product.price = price;
            return product;
        }
    }

    private Product() {
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return ("Name: " + name + ", Rate: " + rate + ", Price: " + price + "€");
    }
}
