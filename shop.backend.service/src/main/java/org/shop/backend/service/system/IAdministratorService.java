package org.shop.backend.service.system;

import java.util.List;

import org.shop.backend.common.dto.AdministratorDto;
import org.shop.domain.Administrator;

/**
 * 管理员业务接口
 * @author VIC
 *
 */
public interface IAdministratorService {

	/**
	 * 添加管理员
	 * @param entity
	 * @return
	 */
	boolean add(AdministratorDto administratorDto);

	List<AdministratorDto> findByNames(AdministratorDto administratorDto);

	AdministratorDto findByName(AdministratorDto administratorDto);

}
