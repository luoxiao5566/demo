package com.thoughtworks.mall.demo.adapter.persistence.jpa.impl;

import com.thoughtworks.mall.demo.adapter.persistence.DefaultBaseRepository;
import com.thoughtworks.mall.demo.adapter.persistence.jpa.GoodsPo;
import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.repository.GoodsRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class GoodsRepositoryImpl extends DefaultBaseRepository<Goods, GoodsPo, Long> implements GoodsRepository {

    public GoodsRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
