/**
 * 'Atif Mustaffa
 * 1429619
 * 12 Dec 2016
 * WP_Project
 *
 */
package main;

public class Item {
	
	int product_id, stock;
	double price;
	String name = "", description = "", brand = "", imgurl = "";

	public Item() {
		
	}
	
	public Item(int product_id, int stock, double price, String name, String description, String brand, String imgurl) {
		this.product_id = product_id;
		this.stock = stock;
		this.price = price;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.imgurl = imgurl;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getStock() {
		return stock;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getBrand() {
		return brand;
	}

	public String getImgurl() {
		return imgurl;
	}

}
