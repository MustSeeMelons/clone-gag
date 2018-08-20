package com.strautins.CloneGag.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class MultipartFileToByteArrayConverter implements Converter<CommonsMultipartFile, byte[]> {

    @Override
    public byte[] convert(CommonsMultipartFile commonsMultipartFile) {
        return commonsMultipartFile.getBytes();
    }
}
