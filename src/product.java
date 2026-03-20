//--------------------------------PRODUCT CLASS--------------------------------------------------
class product {
    String productName;
    int productPrice, productQuantity, productQuantityMax, expireDate;

    public product(String name, int price, int quantity, int max, int expire) {
        productName = name;
        productPrice = price;
        productQuantity = quantity;
        productQuantityMax = max;
        expireDate = expire;
    }

    public String printInfo() {
        return ("<html>" + productName + "<br><br>Price: " + getProductRefillPrice() + "<br><br>Quantity: "
                + productQuantity + "/" + productQuantityMax + " <br><br>Expires in "+ expireDate +" week/s</html>");
    }

    public int getPrice() {
        return productPrice;
    }

    public String toString() {
        return productName + " | Price: " + productPrice + " | Expires in " + expireDate + " week/s | Stock: " + productQuantityMax;
    }

    public int getProductRefillPrice() {
        return (productQuantityMax - productQuantity) * (productPrice - 5);
    }
}
