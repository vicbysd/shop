package org.shop.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 对象工具类
 * @author VIC
 *
 */
public class ObjectUtil {
	
    /**
    *
    * 获取默认实例
    * @param entityClass
    * @param <T>
    * @return
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
    */
   public static <T> T getInstance(Class<T> entityClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  {
       return (T)entityClass.getConstructor().newInstance();
   }
   
   /**
    * 复制对象属性
    * @param orig 源对象
    * @param dest 目标对象
    * @throws IllegalAccessException
    * @throws InvocationTargetException
    */
   public static void copyProperty(Object orig,Object dest) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(dest, orig);
   }
   
   /**
    * 创建实例对象并复制属性
    * @param entityClass
    * @param orig
    * @return
    * @throws InstantiationException
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    * @throws InvocationTargetException
    * @throws NoSuchMethodException
    * @throws SecurityException
    */
   public static <T> T getInstanceAndCopyProperty(Class<T> entityClass, Object orig) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
	   T dest = getInstance(entityClass);
	   copyProperty(orig, dest);
	   return dest;
   }

	public static <S,T> void copyCollection(List<S> ss,List<T> ts,Class<T> targetClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		for(S s : ss){
			T t = (T) getInstanceAndCopyProperty(targetClass, s);
			ts.add(t);  
		} 
		
	}

}
