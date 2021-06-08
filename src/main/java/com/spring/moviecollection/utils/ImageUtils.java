package com.spring.moviecollection.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class ImageUtils {
    public static String getImgUtility(byte[] data) throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(data);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
    }
}
