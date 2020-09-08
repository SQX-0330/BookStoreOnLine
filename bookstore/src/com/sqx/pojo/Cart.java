package com.sqx.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SQX
 * @date 2020/9/6 - 9:50
 */
public class Cart {
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加商品，如果已添加，则数量增加，总金额更新，如果没有添加过，直接放集合中去
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(), cartItem);
        }else{
            item.setCount(item.getCount() + 1); //数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));  //更新总金额
        }
    }

    /**
     * 删除商品项
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }
    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     */
    public void updateCount(Integer id, Integer count){
        //先查看购物车中是否已经添加商品，如果已添加，修改商品数量。更新总金额
        CartItem item = items.get(id);
        if(item != null){
            item.setCount(count); //数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));  //更新总金额
        }
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer, CartItem> entry: items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer, CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
