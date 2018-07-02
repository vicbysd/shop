package org.shop.backend.service.system;

import java.util.List;

import org.shop.domain.Role;

/**
 * 角色业务接口
 * @author VIC
 *
 */
public interface IRoleService {

	List<Role> selectRoleByAdministratorId(String id);

}
