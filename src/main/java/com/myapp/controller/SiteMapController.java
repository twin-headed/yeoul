package com.myapp.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class SiteMapController {

    private final ResourceLoader resourceLoader;

    public SiteMapController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<byte[]> serveSiteMapFile(HttpServletResponse response) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/sitemap.xml");
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] sitemapBytes = FileCopyUtils.copyToByteArray(inputStream);
            return ResponseEntity.ok().body(sitemapBytes);
        } catch (IOException e) {
            // 파일을 읽을 수 없는 경우 처리
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<byte[]> serveRobotsFile(HttpServletResponse response) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/robots.txt");
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] sitemapBytes = FileCopyUtils.copyToByteArray(inputStream);
            return ResponseEntity.ok().body(sitemapBytes);
        } catch (IOException e) {
            // 파일을 읽을 수 없는 경우 처리
            return ResponseEntity.notFound().build();
        }
    }

}
