package com.kk.cart.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName PropertieService
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/4 16:46
 */
@Service
public class PropertieService {

    @Value("${KK_SSO_URL}")
    public String KK_SSO_URL;

}
