package com.jiebai.framework.common.util;

import com.google.common.base.Strings;

import java.io.File;
import java.util.Objects;

/**
 * DesUtil
 *
 * @author wyb
 * @version 1.0.0
 */
public class FileUtils {

    /**
     * 删除文件及文件加
     *
     * @param file-需要删除的文件文件
     * @return boolean
     */
    public static boolean deleteFile(File file) {

        if (Objects.isNull(file)) {
            return false;
        }
        // 如果dir对应的文件不存在
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (!deleteFile(files[i])) {
                return false;
            }
        }
        // 删除当前目录
        return file.delete();
    }

    public static boolean deleteFile(String path) {
        if (Strings.isNullOrEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return deleteFile(file);
    }
}
