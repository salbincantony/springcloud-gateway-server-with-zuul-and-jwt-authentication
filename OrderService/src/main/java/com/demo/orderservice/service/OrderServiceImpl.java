package com.demo.orderservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.demo.orderservice.CatalogException;
import com.demo.orderservice.model.CartList;
import com.demo.orderservice.model.Order;
import com.demo.orderservice.model.OrderDetail;
import com.demo.orderservice.model.OrderItem;
import com.demo.orderservice.model.OrderItemDetail;
import com.demo.orderservice.model.OrderItems;
import com.demo.orderservice.model.Product;
import com.demo.orderservice.repository.IOrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

	@Autowired
	IOrderRepository orderRepo;
	
	@Override
    public List<Order> getOrders() {
        return  (List<Order>) orderRepo.findAll();
    }
   
    @Override
    public Order getOrderById(int orderId) {
        return (orderRepo.findOrderByOrderId(orderId));
    }
   
	@Value("${ip.cartService}")
    private String ipcartService;
	
	@Value("${ip.productCatalog}")
    private String ipProductCatalog;
	
	@Autowired
	RestTemplate restTemplate;  
    
	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor=CatalogException.class)
	public void placeOrder(String userID) {
		CartList cartList;
		
		List<Product> prodsList = new ArrayList<Product>();
		/*cartList =  restTemplate.getForObject(ipcartService + userID, CartList.class);		
		for (Integer prodID : cartList.getProdID()) {
			Product product = restTemplate.getForObject(ipProductCatalog + prodID, Product.class);
			prodsList.add(product);
		}*/
		prodsList = testOrderMethod();
		Set<OrderItem> orderItemsList = new HashSet<OrderItem>();
		double totalprice =0.0;
		Order order=new Order();
		order.setCreatedBy(userID);
		order.setCreatedOn(new Date());
		try {
		for(Product product:prodsList) {
			OrderItem orderItemDetail = new OrderItem();
			totalprice = totalprice + product.getPrice();
			orderItemDetail.setOrder(order);
			orderItemDetail.setPrice(product.getPrice());
			orderItemDetail.setProductId(product.getId());
			orderItemDetail.setQuantity(1);
			orderItemDetail.setOrder(order);
			orderItemsList.add(orderItemDetail);
		
		}
		order.setOrderItem(orderItemsList);
		order.setTotalprice(totalprice);
		orderRepo.save(order);
		
		for (Product prodID : prodsList) {
			Product product = restTemplate.getForObject(ipProductCatalog + prodID.getId(), Product.class);
			if (product.getStockQuantity() != 0) {
				product.setStockQuantity(product.getStockQuantity() - 1);
			}
			restTemplate.postForEntity(ipProductCatalog, product, String.class);
		}
		}catch(RestClientException e) {
			throw new CatalogException(e.getMessage());
		}
	}
	private List<Product> testOrderMethod(){
		List<Product> prodList=new ArrayList<Product>();
		CartList cartList=new CartList();
		List<Integer> prodId = new ArrayList<Integer>(Arrays.asList(3,4));
		cartList.setProdID(prodId);
		for (Integer prodID : cartList.getProdID()) {
			Product product = restTemplate.getForObject(ipProductCatalog + prodID, Product.class);
			prodList.add(product);
		}
		return prodList;
		
	}
	
	public OrderItems getOrderList(List<Order> ordersList){
		OrderItems orderItems = new OrderItems();
		OrderDetail orderDetails = new OrderDetail();
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		 for (Order orders : ordersList) {
			 orderDetails = getOrder(orders);
			 orderDetailList.add(orderDetails);
		 }
		 orderItems.setOrderList(orderDetailList);
		return orderItems;		
	}
	
	public OrderDetail getOrder(Order orders) {
		OrderItemDetail orderItemDetail  = new OrderItemDetail();
        Set<OrderItemDetail> orderItemDet = new HashSet<OrderItemDetail>();
        OrderDetail orderDetails = new OrderDetail();
        orderDetails.setOrderId(orders.getOrderId());
        orderDetails.setCreatedOn(orders.getCreatedOn());
        orderDetails.setCreatedBy(orders.getCreatedBy());
        orderDetails.setTotalprice(orders.getTotalprice());
        Set<OrderItem> orderItemSet = orders.getOrderItem();
        
        for(OrderItem orderitem : orderItemSet) {
        	//product = restTemplate.getForObject(ipProductCatalog + orderitem.getProductId(), Product.class);
        	orderItemDetail.setOrderDetail(orderDetails);
			orderItemDetail.setPrice(orderitem.getPrice());
			orderItemDetail.setProductId(orderitem.getProductId());
			orderItemDetail.setQuantity(orderitem.getQuantity());
			orderItemDetail.setOrderItemId(orderitem.getOrderItemId());
        }
        //productList.add(product);
        orderItemDet.add(orderItemDetail);
        orderDetails.setOrderItemDetail(orderItemDet);
		return orderDetails;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public void cancelOrder(int orderID) {
		Order order=orderRepo.findOrderByOrderId(orderID);
		orderRepo.delete(order);
	}
	
	

    private Integer getOrderId() {
       Integer id=orderRepo.findOrderId();
        if (null!=id)
            return id;
        else
            return 0;
    }


}
