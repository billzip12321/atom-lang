/**
 * obullxl@gmail.com
 */
package com.github.obullxl.lang.topic;

import com.github.obullxl.lang.FlagValve;
import com.github.obullxl.lang.enums.TopicMediaEnum;
import com.github.obullxl.lang.enums.TopicStateEnum;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * 主题开关值
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicValve.java, V1.0.1 2014年1月28日 下午3:38:03 $
 */
public class TopicValve extends FlagValve {

    /** The object class. */
    private final TopicDTO object;

    /**
     * CTOR
     */
    public TopicValve(TopicDTO object) {
        super(object.getFlag());
        this.object = object;
    }

    /**
     * Getter for object.
     */
    public TopicDTO getObject() {
        return this.object;
    }

    // Custome codes.
    
    // 0-保留位

    /**
     * 1-状态
     */
    public TopicStateEnum gotState() {
        return super.gotEnumBase(1, TopicStateEnum.values(), TopicStateEnum.findDefault());
    }

    public TopicValve sotState(TopicStateEnum value) {
        super.sotValue(1, value);
        return this;
    }

    /**
     * 2-全局置顶
     */
    public ValveBoolEnum gotTopGrobal() {
        return super.gotEnumBase(2, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public TopicValve sotTopGrobal(ValveBoolEnum value) {
        super.sotValue(2, value);
        return this;
    }
    
    /**
     * 3-分类置顶
     */
    public ValveBoolEnum gotTopCategory() {
        return super.gotEnumBase(3, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public TopicValve sotTopCategory(ValveBoolEnum value) {
        super.sotValue(3, value);
        return this;
    }
    
    /**
     * 4-加精标志
     */
    public ValveBoolEnum gotEliteFlag() {
        return super.gotEnumBase(4, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public TopicValve sotEliteFlag(ValveBoolEnum value) {
        super.sotValue(4, value);
        return this;
    }

    /**
     * 5-原创标志
     */
    public ValveBoolEnum gotLinkFlag() {
        return super.gotEnumBase(5, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public TopicValve sotLinkFlag(ValveBoolEnum value) {
        super.sotValue(5, value);
        return this;
    }

    /**
     * 6-多媒体标志
     */
    public TopicMediaEnum gotMediaFlag() {
        return super.gotEnumBase(6, TopicMediaEnum.values(), TopicMediaEnum.findDefault());
    }

    public TopicValve sotMediaFlag(TopicMediaEnum value) {
        super.sotValue(6, value);
        return this;
    }

    /**
     * 7-评论标志
     */
    public ValveBoolEnum gotReplyFlag() {
        return super.gotEnumBase(7, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public TopicValve sotReplyFlag(ValveBoolEnum value) {
        super.sotValue(7, value);
        return this;
    }

}
