package com.demo.productcatalogservice.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
	
		@Id	@GeneratedValue
		private int id;
		
		@NotNull
		private String name;
			
		@NotNull
		private double price;
		private int stockQuantity;
		private String image;
		
		private String category;
		

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
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
				
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}	
		
		
		

}
