package org.shop.backend.service.system;

import java.util.List;

import org.shop.domain.Role;
import org.springframework.stereotype.Service;

/**
 * 角色业务实现
 * @author VIC
 *
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Override
	public List<Role> selectRoleByAdministratorId(String id) {
		
		return null;
	}

}
