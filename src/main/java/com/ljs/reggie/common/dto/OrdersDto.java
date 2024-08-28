package com.ljs.reggie.common.dto;


import com.ljs.reggie.entity.OrderDetail;
import com.ljs.reggie.entity.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
