package com.superman.common.supcan.common.properties;

import com.superman.common.supcan.annotation.common.properties.SupBackground;
import com.superman.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/13.</p>
 * 硕正TreeList Properties Background
 * @author Super.Sun
 * @version 1.0
 */
@XStreamAlias("Background")
public class Background {

    /**
     * 背景颜色
     */
    @XStreamAsAttribute
    private String bgColor = "#FDFDFD";

    public Background() {

    }

    public Background(SupBackground supBackground) {
        this();
        ObjectUtils.annotationToObject(supBackground, this);
    }

    public Background(String bgColor) {
        this();
        this.bgColor = bgColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
