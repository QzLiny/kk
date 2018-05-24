package com.kk.store.order.mapper;

import java.util.Date;
import org.apache.ibatis.annotations.Param;
import com.kk.store.order.pojo.Order;

public interface OrderMapper extends IMapper<Order>{
	
	public void paymentOrderScan(@Param("date") Date date);

}
