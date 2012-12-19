package com.demo.repository;

import com.demo.domain.House;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:25
 * To change this template use File | Settings | File Templates.
 */
public interface HouseRepository extends PagingAndSortingRepository<House, Integer>,
        JpaSpecificationExecutor<House> {
}
