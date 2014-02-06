/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.model.topic.query;

import java.util.List;

import com.github.obullxl.lang.Paginator;
import com.github.obullxl.lang.biz.BizPageList;
import com.github.obullxl.model.topic.TopicModel;

/**
 * 主题分页列表结果
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicPageList.java, V1.0.1 2013年12月6日 上午9:33:46 $
 */
public class TopicPageList extends BizPageList<TopicModel> {
    private static final long serialVersionUID = 299912871887174328L;

    /**
     * CTOR
     */
    public TopicPageList(Paginator pager, List<TopicModel> items) {
        super(pager, items);
    }

}
