package org.shop.backend.service.system;

import java.util.List;

import org.shop.domain.Permission;

/**
 * 权限业务接口
 * @author VIC
 *
 */
public interface IPermissionService {

	List<Permission> selectPermissionByAdministratorId(String id);

}
