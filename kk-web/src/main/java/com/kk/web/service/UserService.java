package com.kk.web.service;

import com.kk.sso.query.api.UserQueryService;
import com.kk.sso.query.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName UserService
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/10 22:54
 */
@Service
public class UserService {

   /* @Autowired
    private PropertieService propertieService;

    @Autowired
    private ApiService apiService;
*/
//    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private UserQueryService userQueryService;

    public User queryUserByToken(String token){
        return this.userQueryService.queryUserByToken(token);
    }


    /*public User queryUserByToken(String token){
        String url = propertieService.KK_SSO_URL + "/com.kk.cart.bean.service/user/" + token;
        try {
            String jsonData = this.apiService.doGet(url);

            if (StringUtils.isNotEmpty(jsonData)){
                return MAPPER.readValue(jsonData,User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/



}
