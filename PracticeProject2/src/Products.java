
public class Products {

	private int productid;
	private String productname;
	private double productprice;
	
	public Products(){
		
	}
	
	
	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public double getProductprice() {
		return productprice;
	}


	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}


	@Override
	public String toString() {
		return "Products [productid=" + productid + ", productname=" + productname + ", productprice=" + productprice
				+ "]";
	}
	
}
