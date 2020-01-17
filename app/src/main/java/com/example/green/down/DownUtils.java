package com.example.green.down;

import android.os.Environment;

import com.yiyatech.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class DownUtils {


    /**
     * 保存文件
     *
     * @param body
     * @return
     */
    public static boolean writeFileToDisk(ResponseBody body, String pathName) {
        try {
            File futureStudioIconFile = new File(pathName);

            LogUtil.e("数值", "开始写入: " + Environment.getExternalStorageDirectory().getAbsolutePath());

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;

                }

                outputStream.flush();
                LogUtil.e("数值", "写入完成: " + fileSizeDownloaded + " of " + fileSize);
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


}
