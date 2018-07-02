package org.shop.backend.repository;

import org.shop.domain.Administrator;
import org.shop.stroage.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员业务接口
 * @author VIC
 *
 */
@Repository
public interface AdministratorRepository extends BaseRepository<Administrator, String> {

}
