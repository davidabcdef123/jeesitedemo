package com.superman.common.supcan.treelist;

import com.google.common.collect.Lists;
import com.superman.common.supcan.annotation.common.fonts.SupFont;
import com.superman.common.supcan.annotation.treelist.SupTreeList;
import com.superman.common.supcan.common.Common;
import com.superman.common.supcan.common.fonts.Font;
import com.superman.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Define Super.Sun.
 * <p>Created with IntelliJ IDEA on 2016/6/13.</p>
 * 硕正TreeList
 * @author Super.Sun
 * @version 1.0
 */
@XStreamAlias("TreeList")
public class TreeList extends Common {

    /**
     * 列集合
     */
    @XStreamAlias("Cols")
    private List<Object> cols;

    public TreeList() {
        super();
    }

    public TreeList(Properties properties) {
        this();
        this.properties = properties;
    }

    public TreeList(SupTreeList supTreeList) {
        this();
        if (supTreeList != null){
            if (supTreeList.properties() != null){
                this.properties = new Properties(supTreeList.properties());
            }
            if (supTreeList.fonts() != null){
                for (SupFont supFont : supTreeList.fonts()){
                    if (this.fonts == null){
                        this.fonts = Lists.newArrayList();
                    }
                    this.fonts.add(new Font(supFont));
                }
            }
        }
    }

    public List<Object> getCols() {
        if (cols == null){
            cols = Lists.newArrayList();
        }
        return cols;
    }


    public void setCols(List<Object> cols) {
        this.cols = cols;
    }


}
