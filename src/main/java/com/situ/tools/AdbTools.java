/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.situ.tools;

import com.situ.entity.Location;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author 司徒彬
 * @date 2023/3/27 14:18
 */
@Slf4j
public class AdbTools {
    private static Runtime runtime;

    static {
        runtime = Runtime.getRuntime();
    }


    private static String ENCODING = "UTF-8";

    @Test
    public void test() {
        long l = System.currentTimeMillis();
        try {
            screenshot("192.168.31.202", 5037, "/mnt/data/temp/screen" + l + ".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Screenshot string.
     * <p>
     * 截图并取回图片
     *
     * @param ip       the ip
     * @param port     the port
     * @param savePath the save path
     * @return the string
     * @throws IOException the io exception
     * @author ErebusST
     * @since 2023 -03-27 15:43:19
     */
    public static String screenshot(String ip, Integer port, String savePath) throws IOException {
        FileUtils.createDirectory(savePath);
        FileUtils.deleteFile(savePath);
        String temp = "/sdcard/" + System.currentTimeMillis() + ".png";
        execute(ip, port, "adb shell screencap -p ".concat(temp),
                "adb pull ".concat(temp).concat(" ") + savePath,
                "adb shell rm ".concat(temp));
        return savePath;
    }

    /**
     * Click .
     * 点击某处
     *
     * @param ip       the ip
     * @param port     the port
     * @param location the location
     * @author ErebusST
     * @since 2023 -03-27 15:43:04
     */
    public static void click(String ip, Integer port, Location location) {
        execute(ip, port, "adb shell input tap ".concat(location.get()));
    }

    public static void longClick(String ip, Integer port, Location location) {
        swipe(ip, port, location, location);
    }

    /**
     * Swipe .
     * 拖动屏幕
     *
     * @param ip   the ip
     * @param port the port
     * @param from the from
     * @param to   the to
     * @author ErebusST
     * @since 2023 -03-27 15:54:45
     */
    public static void swipe(String ip, Integer port, Location from, Location to) {
        execute(ip, port, "adb shell input swipe ".concat(from.get()).concat(" ").concat(to.get()));
    }


    public static void execute(String ip, Integer port, String... commands) {
        port = ObjectUtils.isNull(port) ? 5037 : port;
        execute("adb connect ".concat(ip).concat(":").concat(String.valueOf(port)));
        for (String command : commands) {
            if (StringUtils.isEmpty(command)) {
                continue;
            }
            execute(command);
        }
        execute("adb disconnect");
    }

    private static void execute(String command) {
        Process process = null;
        try {
            //log.info("execute:{}", command);
            process = runtime.exec(command);
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(reader);
            String line;
            while ((line = input.readLine()) != null) {
                //log.info(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ObjectUtils.isNotNull(process)) {
                process.destroy();
            }
        }
    }
}
