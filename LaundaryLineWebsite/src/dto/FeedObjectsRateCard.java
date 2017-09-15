package dto;

public class FeedObjectsRateCard {

	int id;
	String name;
	int price;
	int quantity;
	

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "FeedObjectsRateCard [id=" + id + ", name=" + name + ", price=" + price + ", quantity="+quantity+"]";
	}
	
}
