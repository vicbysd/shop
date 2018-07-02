package org.shop.backend.repository;

import org.shop.domain.Permission;
import org.shop.stroage.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 权限业务接口
 * @author VIC
 *
 */
@Repository
public interface PermissionRepository extends BaseRepository<Permission, String> {

}
