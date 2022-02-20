package com.thoughtworks.mall.demo.domain.service;

import com.thoughtworks.mall.demo.domain.exception.NotFoundException;
import com.thoughtworks.mall.demo.domain.model.Goods;
import com.thoughtworks.mall.demo.domain.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.thoughtworks.mall.demo.domain.exception.GoodsExceptionCode.GOODS_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsDomainService {

    private final GoodsRepository goodsRepository;

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    public List<Goods> findByIds(List<Long> ids) {
        return goodsRepository.findAllById(ids);
    }

    @Transactional
    public void deleteAll() {
        goodsRepository.deleteAll();
    }

    public Goods findById(Long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(GOODS_NOT_FOUND, "goods not found"));
    }
}
