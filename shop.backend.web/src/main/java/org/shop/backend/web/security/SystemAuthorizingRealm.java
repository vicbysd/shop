package org.shop.backend.web.security;


import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.shop.backend.common.dto.AdministratorDto;
import org.shop.backend.common.dto.PermissionDto;
import org.shop.backend.common.dto.RoleDto;
import org.shop.backend.service.system.IAdministratorService;
import org.shop.domain.EntityState;
import org.shop.util.ObjectUtil;
import org.shop.util.VerifyCodeUtil;

/**
 * 认证及授权
 * @author VIC
 *
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {
	
	private IAdministratorService adminstratorService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		//获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		try{
			String account = (String)super.getAvailablePrincipal(principals);
			AdministratorDto administratorDto = ObjectUtil.getInstance(AdministratorDto.class);
			administratorDto.setAccount(account);
			
			AdministratorDto administrator = adminstratorService.findByName(administratorDto);
			if(administrator == null){
				throw new AuthenticationException("Account not exists.");
			}
			
			
			List<RoleDto> roleList = administrator.getRoles();
			List<PermissionDto> permList = administrator.getPermissions();
			
			if(roleList != null && roleList.size() > 0){
				for(RoleDto role : roleList){
					if(role.getCode() != null){
						simpleAuthorInfo.addRole(role.getCode());
					}
				}
			}
			
			if(permList != null && permList.size() > 0){
				for(PermissionDto perm : permList){
					if(perm.getCode() != null){
						simpleAuthorInfo.addStringPermission(perm.getCode());
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return simpleAuthorInfo;
		
	}

	
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		//获取基于用户名和密码的令牌
		//实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		
		StrengthenUsernamePasswordToken token = (StrengthenUsernamePasswordToken)authcToken;
		this.adminstratorService = token.administratorService;
		try{
			Session session = getSession();
			String code = (String)session.getAttribute(VerifyCodeUtil.VALIDATE_CODE);
			if (token.getVerifyCode() == null || !token.getVerifyCode().toUpperCase().equals(code)){
				throw new AuthenticationException("验证码错误, 请重试.");
			}
			
			AdministratorDto administratorDto = ObjectUtil.getInstance(AdministratorDto.class);
			administratorDto.setAccount(token.getUsername());
			AdministratorDto administrator = adminstratorService.findByName(administratorDto);
			if(administrator != null){
				if(administrator.getState().equals(EntityState.LOCKED) || administrator.getState().equals(EntityState.DISABLED)){
					throw new AuthenticationException("This is account already locked or close");
				}
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(administrator.getAccount(), administrator.getPassword(), this.getName());
				this.setSession("currentUser", administrator.getAccount());
				
				return authcInfo;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 保存登录名
	 */
	private void setSession(Object key, Object value){
		Session session = getSession();
		System.out.println("Session default timeout [" + session.getTimeout() + "] ms");
		if(null != session){
			session.setAttribute(key, value);
		}
	}
	
	private Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
}
