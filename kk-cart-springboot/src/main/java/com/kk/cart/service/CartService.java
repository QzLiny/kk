package com.kk.cart.service;

import com.github.abel533.entity.Example;
import com.kk.cart.bean.Item;
import com.kk.cart.mapper.CartMapper;
import com.kk.cart.pojo.Cart;
import com.kk.cart.threadlocal.UserThreadLocal;
import com.kk.sso.query.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CartService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/12 22:50
 */
@Service
@Transactional
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemService itemService;

    public void addItemToCart(Long itemId) {
        //判断该商品是否存在购物车中
        User user = UserThreadLocal.get();

        Cart recond = new Cart();
        recond.setItemId(itemId);
        recond.setUserId(user.getId());
        Cart cart = this.cartMapper.selectOne(recond);

        if (null == cart){
            //购物车不存在该商品
            Item item = this.itemService.queryItemById(itemId);
            if (null == item){
                return;
            }
            cart = new Cart();
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cart.setItemId(itemId);
            cart.setItemImage(item.getImages()[0]);
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());
            cart.setNum(1);
            cart.setUserId(user.getId());
            this.cartMapper.insert(cart);
        }else {
            //该商品存在购物车中，数量相加
            cart.setNum(cart.getNum() + 1);
            cart.setUpdated(new Date());
            this.cartMapper.updateByPrimaryKeySelective(cart);
        }
    }

    public List<Cart> quertCartList() {
        User user = UserThreadLocal.get();

        return this.quertCartList(user.getId());
    }

    public List<Cart> quertCartList(Long userId) {
        User user = UserThreadLocal.get();
        Example example = new Example(Cart.class);
        example.createCriteria().andEqualTo("userId",userId);
        example.setOrderByClause("created DESC");
        return this.cartMapper.selectByExample(example);
    }

    public void updateNum(Long itemId, Integer num) {
        User user = UserThreadLocal.get();
        //更新的数据对象
        Cart record = new Cart();
        record.setNum(num);
        record.setUpdated(new Date());
        //更新的条件
        Example example = new Example(Cart.class);
        example.createCriteria().andEqualTo("itemId",itemId).
                andEqualTo("userId",user.getId());

        this.cartMapper.updateByExampleSelective(record,example);
    }

    public void deleteItem(Long itemId) {
        Cart record = new Cart();
        record.setUserId(UserThreadLocal.get().getId());
        record.setItemId(itemId);
        this.cartMapper.delete(record);
    }


}
