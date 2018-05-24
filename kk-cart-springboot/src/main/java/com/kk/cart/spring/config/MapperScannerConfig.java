package com.kk.cart.spring.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MapperScannerConfig
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/17 22:29
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)//保证在MyBatisConfig实例化之后再实例化该类
public class MapperScannerConfig {

    /**
     * mapper接口的扫描器
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.kk.cart.mapper");
        return mapperScannerConfigurer;
    }
}
