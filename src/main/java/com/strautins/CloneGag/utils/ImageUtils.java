package com.strautins.CloneGag.utils;

import org.apache.tika.Tika;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    private static Tika tika = new Tika();

    private static List<String> supportedMimes = new ArrayList<String>() {
        {
            add("image/jpg");
            add("image/png");
        }
    };

    public static String getSupportedMime(byte[] bytes) {
        InputStream is = new ByteArrayInputStream(bytes);

        String mime = tika.detect(bytes);
        return supportedMimes.contains(mime) ? mime : null;
    }
}
