package com.pn.mapper;

import com.pn.entity.ProductType;
import com.pn.entity.Purchase;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseMapper {

    //添加采购单的方法
    public int insertPurchase(Purchase purchase);

    //查询采购单总行数的方法
    public int selectPurchaseCount(Purchase purchase);

    //分页查询采购单的方法
    public List<Purchase> selectPurchasePage(@Param("page") Page page, @Param("purchase") Purchase purchase);

    //根据id修改采购单的方法
    public int updatePurchaseById(Purchase purchase);

    //根据id删除采购单的方法
    public int deletePurchaseById(Integer buyId);

    //根据id将采购单状态改为已入库的方法
    public int updateIsInById(Integer buyId);
}
