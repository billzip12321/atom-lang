/**
 * obullxl@gmail.com
 */
package com.github.obullxl.lang.catg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.obullxl.lang.biz.BaseDTO;

/**
 * 模块分类数据模型
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgDTO.java, V1.0.1 2014年1月26日 下午12:01:43 $
 */
public class CatgDTO extends BaseDTO implements Comparator<CatgDTO> {
    private static final long serialVersionUID = 590577067746906426L;

    /** parent */
    private CatgDTO           parent;

    /** children */
    private List<CatgDTO>     children         = new ArrayList<CatgDTO>();

    public CatgDTO getParent() {
        return parent;
    }

    public void setParent(CatgDTO parent) {
        this.parent = parent;
    }

    public List<CatgDTO> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<CatgDTO>();
        }

        return children;
    }

    public void setChildren(List<CatgDTO> children) {
        this.children = children;
    }

    /** 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(CatgDTO src, CatgDTO dst) {
        if (src == null || src.getSort() == null) {
            return -1;
        }

        if (dst == null || dst.getSort() == null) {
            return 1;
        }

        return src.getSort().compareTo(dst.getSort());
    }

    /** 上级分类 */
    private String catg;

    /** 分类代码 */
    private String code;

    /** 排序值 */
    private String sort;

    /** 分类说明 */
    private String title;

    /** 分类扩展属性 */
    private String extMap;

    /** 分类描述 */
    private String summary;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCatg() {
        return catg;
    }

    public void setCatg(String catg) {
        this.catg = catg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtMap() {
        return extMap;
    }

    public void setExtMap(String extMap) {
        this.extMap = extMap;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
