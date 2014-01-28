/**
 * obullxl@gmail.com
 */
package com.github.obullxl.lang.user;

import com.github.obullxl.lang.FlagValve;
import com.github.obullxl.lang.enums.ActiveEnum;
import com.github.obullxl.lang.enums.UserSexEnum;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * 用户模型开关值
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserValve.java, V1.0.1 2014年1月28日 上午9:29:53 $
 */
public class UserValve extends FlagValve {

    /** The object class. */
    private final UserDTO object;

    /**
     * CTOR
     */
    public UserValve(UserDTO object) {
        super(object.getFlag());
        this.object = object;
    }

    /**
     * Getter for object.
     */
    public UserDTO getObject() {
        return this.object;
    }

    // Custome codes.

    // 0-保留位

    /**
     * 1-管理员
     */
    public ValveBoolEnum gotAdmin() {
        return super.gotEnumBase(1, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public UserValve sotAdmin(ValveBoolEnum value) {
        super.sotValue(1, value);
        return this;
    }

    /**
     * 2-性别
     */
    public UserSexEnum gotSex() {
        return super.gotEnumBase(2, UserSexEnum.values(), ValveBoolEnum.findDefault());
    }

    public UserValve sotSex(UserSexEnum value) {
        super.sotValue(2, value);
        return this;
    }

    /**
     * 3-激活状态
     */
    public ActiveEnum gotActiveState() {
        return super.gotEnumBase(3, ActiveEnum.values(), ActiveEnum.findDefault());
    }

    public UserValve sotActiveState(ActiveEnum value) {
        super.sotValue(3, value);
        return this;
    }
    
    /**
     * 4-登录标志
     */
    public ValveBoolEnum gotLoginFlag() {
        return super.gotEnumBase(4, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public UserValve sotLoginFlag(ValveBoolEnum value) {
        super.sotValue(4, value);
        return this;
    }
    
    /**
     * 5-邮箱标志
     */
    public ValveBoolEnum gotEmailFlag() {
        return super.gotEnumBase(5, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public UserValve sotEmailFlag(ValveBoolEnum value) {
        super.sotValue(5, value);
        return this;
    }
    
    /**
     * 6-手机标志
     */
    public ValveBoolEnum gotMobileFlag() {
        return super.gotEnumBase(6, ValveBoolEnum.values(), ValveBoolEnum.findDefault());
    }

    public UserValve sotMobileFlag(ValveBoolEnum value) {
        super.sotValue(6, value);
        return this;
    }

}
