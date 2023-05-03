package com.C21394933.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;


public class DownloadAIGeneratedImage {
    public static boolean downloadImage(String link) {
        try { 
            URL url = new URL(link);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf))){
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream("./java/data/images/ai-image-0.png");
            fos.write(response);
            fos.close();
            System.out.println("Download Complete");
        } catch(Exception e) {
            return false;
        } 

        return true;
    } // End void downloadImage
}
