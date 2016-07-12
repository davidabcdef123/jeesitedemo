package com.superman.common.utils.excel.filedtype;

import com.superman.common.utils.StringUtils;
import com.superman.modules.sys.entity.Office;
import com.superman.modules.sys.utils.UserUtils;

/**
 *  字段类型转换
 * Created by Super on 2016/7/12.
 */
public class OfficeType {

    /**
     * 获取对象值（导入）
     */
    public static Object getValue(String val) {
        for (Office e : UserUtils.getOfficeList()){
            if (StringUtils.trimToEmpty(val).equals(e.getName())){
                return e;
            }
        }
        return null;
    }

    /**
     * 设置对象值（导出）
     */
    public static String setValue(Object val) {
        if (val != null && ((Office)val).getName() != null){
            return ((Office)val).getName();
        }
        return "";
    }
}
