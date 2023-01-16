package com.Microservice.Galleryserver.Controller;

import java.util.List;

import com.Microservice.Galleryserver.Entity.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String test(){
        return "Hello";
    }

    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);

        // get list of available images
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory ();
        restTemplate = new RestTemplate(factory);
        List<Object> images = restTemplate.getForObject("http://localhost:8200/images", List.class);
        gallery.setImages(images);

        return gallery;
    }
}
