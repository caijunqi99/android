package com.example.green.local_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.yiyatech.utils.BitmapUtil;
import com.yiyatech.utils.ext.ToastUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SaveImageUtils {

    // 下载图片
    public static void savePicture(final Bitmap pBitmap, final Context pContext) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> emitter) throws Exception {
                String fileParentPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/XZK";
                File appDir = new File(fileParentPath);
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }
                //保存的文件名
                String fileName = "ptg" + System.currentTimeMillis() + ".jpg";
                //目标文件
                File targetFile = new File(appDir, fileName);

                boolean b1 = BitmapUtil.saveBitmap(pBitmap, targetFile);
                if (b1) {
                    String extension = MimeTypeMap.getFileExtensionFromUrl(targetFile.getAbsolutePath());
                    String mimeTypes = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                    MediaScannerConnection.scanFile(pContext, new String[]{targetFile.getAbsolutePath()},
                            new String[]{mimeTypes}, null);
                    emitter.onNext(targetFile);
                }

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                               @Override
                               public void accept(File file) throws Exception {
                                   ToastUtils.show(pContext, "保存图片成功");
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                ToastUtils.show(pContext, "保存图片失败");
                            }
                        });
    }
}
