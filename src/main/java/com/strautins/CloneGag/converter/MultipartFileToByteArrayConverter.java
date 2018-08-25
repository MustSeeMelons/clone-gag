package com.strautins.CloneGag.converter;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class MultipartFileToByteArrayConverter implements Converter<CommonsMultipartFile, Byte[]> {

    @Override
    public Byte[] convert(CommonsMultipartFile commonsMultipartFile) {
        return ArrayUtils.toObject(commonsMultipartFile.getBytes());
    }
}
