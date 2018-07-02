package org.shop.backend.service.system;


import java.util.List;
import org.shop.backend.common.dto.AdministratorDto;
import org.shop.backend.repository.AdministratorRepository;
import org.shop.domain.Administrator;
import org.shop.util.MD5Util;
import org.shop.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

/**
 * 管理员业务类
 * @author VIC
 *
 */
@Service("administratorService")
public class AdministratorServiceImpl implements IAdministratorService {

	@Autowired
	AdministratorRepository administratorRepository;
	
	public boolean add(AdministratorDto administratorDto) {
		boolean state = false;
		try{
			// Dto对象转换实体对象
			Administrator entity = ObjectUtil.getInstanceAndCopyProperty(Administrator.class,administratorDto);
			
			// 1. 验证管理员是否存在(可选),如果存在则不添加
			if(administratorRepository.exists(entity.getId())){
				return state;
			}
			
			// 2. 设置相关管理员属性值
			entity.init();
			entity.setPassword(MD5Util.encryptMD5(entity.getPassword()));
			//entity.setCreator(creator);
			
			// 3. 执行添加
			administratorRepository.save(entity);
			state = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}

	public List<AdministratorDto> findByNames(AdministratorDto administratorDto) {
		List<AdministratorDto> administratorDtos = Lists.newArrayList();
		try{
			Administrator entity = ObjectUtil.getInstanceAndCopyProperty(Administrator.class,administratorDto);
			List<Administrator> administrators = administratorRepository.findByPropertys(entity);
			ObjectUtil.copyCollection(administrators, administratorDtos, AdministratorDto.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return administratorDtos;
	}

	public AdministratorDto findByName(AdministratorDto administratorDto) {
		AdministratorDto targetDto = null;
		try{
			targetDto = ObjectUtil.getInstance(AdministratorDto.class);
			Administrator entity = ObjectUtil.getInstanceAndCopyProperty(Administrator.class,administratorDto);
			entity = administratorRepository.findByProperty(entity);
			ObjectUtil.copyProperty(entity, targetDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return targetDto;
	}

}
