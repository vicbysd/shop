package org.shop.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Test;
import com.google.common.collect.Lists;

/**
 * 对象工具类测试
 * @author VIC
 *
 */
public class ObjectUtilTest {
	
	/**
	 * 测试集合深复制
	 * @throws Exception
	 */
	@Test
	public void testCopyCollections() throws Exception {
		
		List<AdministratorTmp> administratorTmps = Lists.newArrayList();
		List<AdministratorDtoTmp> administratorDtoTmps = Lists.newArrayList();
		
		AdministratorTmp administrator = new AdministratorTmp();
		administrator.setAccount("manager");
		administratorTmps.add(administrator);
		
		AdministratorTmp administrator2 = new AdministratorTmp();
		administrator2.setAccount("manager2");
		administratorTmps.add(administrator2);
		
		
		ObjectUtil.copyCollection(administratorTmps, administratorDtoTmps, AdministratorDtoTmp.class);
		System.out.println(administratorDtoTmps);
	}
	
	/**
	 * 测试对象属性复制
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testCopyProperty() throws IllegalAccessException, InvocationTargetException{
		AdministratorTmp administrator = new AdministratorTmp();
		administrator.setAccount("manager");
		AdministratorDtoTmp administratorRequest = new AdministratorDtoTmp();
		administratorRequest.setAccount("admin");
		administratorRequest.setPassword("admin123");
		administratorRequest.setVerifyCode("adminCode");
		ObjectUtil.copyProperty(administratorRequest, administrator);
		System.out.println(administratorRequest);
		System.out.println(administrator);
	}

}
