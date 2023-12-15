/** MybatisConfig.java */
/*
 * Programmed by 서보혁
 * 파일 업로드, 조회, 다운로드 등을 설정하는데 쓰이는 소스 코드이다.
 * Date : 2023.12.15.
 * Last Update : 2023.12.15.
 * Major update content : Source code 최초 작성 by 서보혁
 */
package com.example.board.config;

import jakarta.servlet.MultipartConfigElement;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages = "com.example.board.mapper")
public class MultipartConfig {
    @Value("100000000")
    private long maxUploadSize;

    @Value("100000000")
    private long maxInMemorySize;

    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.ofBytes(maxUploadSize));
        factory.setMaxFileSize(DataSize.ofBytes(maxInMemorySize));

        return factory.createMultipartConfig();
    }
}
