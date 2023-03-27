/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.entity;

import com.situ.tools.DataSwitch;
import com.situ.tools.NumberUtils;
import com.situ.tools.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author 司徒彬
 * @date 2023/3/27 15:39
 */
@Getter
@Setter
public class Location {

    public static Location get(Number x, Number y) {
        return new Location(x, y);
    }

    private BigDecimal x;
    private BigDecimal y;

    public Location(Number x, Number y) {
        this.x = DataSwitch.convertObjectToBigDecimal(x);
        this.y = DataSwitch.convertObjectToBigDecimal(y);
    }

    /**
     * Left location.
     *
     * @param value the value
     * @return the location
     * @author ErebusST
     * @since 2023 -03-27 16:02:31
     */
    public Location left(Number value) {
        this.x = NumberUtils.subtract(this.x, value);
        return this;
    }

    /**
     * Right location.
     *
     * @param value the value
     * @return the location
     * @author ErebusST
     * @since 2023 -03-27 16:02:29
     */
    public Location right(Number value) {
        this.x = NumberUtils.add(this.x, value);
        return this;
    }

    /**
     * Up location.
     *
     * @param value the value
     * @return the location
     * @author ErebusST
     * @since 2023 -03-27 16:02:26
     */
    public Location up(Number value) {
        this.y = NumberUtils.add(this.y, value);
        return this;
    }

    /**
     * Down location.
     *
     * @param value the value
     * @return the location
     * @author ErebusST
     * @since 2023 -03-27 16:02:23
     */
    public Location down(Number value) {
        this.y = NumberUtils.subtract(this.y, value);
        return this;
    }

    public String get() {
        return this.x + " " + this.y;
    }
}
