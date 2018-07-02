package org.shop.backend.web.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shop.util.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码控制器
 * @author VIC
 *
 */
@Controller
@RequestMapping("/verify")
public class VerificationCodeController {
	
	/** 
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中) 
     */  
    @RequestMapping("/get")  
    public void generated(HttpServletRequest request, HttpServletResponse response) {  
        //设置页面不缓存  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);  
        //将验证码放到HttpSession里面  
        request.getSession().setAttribute(VerifyCodeUtil.VALIDATE_CODE, verifyCode);  
        System.out.println("本次生成的验证码为[" + verifyCode + "], 已存放到HttpSession中");  
        //设置输出的内容的类型为JPEG图像  
        response.setContentType("image/jpeg");  
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, null, null);
        try{
	        //写给浏览器  
	        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
        }catch (Exception e) {
			e.printStackTrace();
		}
    }  

}
