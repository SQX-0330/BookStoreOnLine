package com.sqx.test;

import com.sqx.pojo.Cart;
import com.sqx.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/9/6 - 10:07
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1,new BigDecimal(1000),new BigDecimal(1000)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.deleteItem(1);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构", 1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.clear();
        cart.addItem(new CartItem(1, "java", 1,new BigDecimal(1000),new BigDecimal(1000)));

        cart.updateCount(1, 10);

        System.out.println(cart);
    }
}