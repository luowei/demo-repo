package com.demo.repository;

import com.demo.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:24
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor<User> {
}
