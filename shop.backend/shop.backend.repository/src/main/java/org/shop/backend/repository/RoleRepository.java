package org.shop.backend.repository;

import org.shop.domain.Role;
import org.shop.stroage.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色业务接口
 * @author VIC
 *
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, String> {

}
