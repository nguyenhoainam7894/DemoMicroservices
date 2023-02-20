package com.Microservice.Galleryserver.Entity;

import java.util.List;

import com.Microservice.Galleryserver.Response.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gallery {
    private Integer id;
    private List<Image> images;
}
