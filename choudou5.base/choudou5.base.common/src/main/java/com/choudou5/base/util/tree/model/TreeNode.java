package com.choudou5.base.util.tree.model;

import java.io.Serializable;

/**
 * @Name：树节点
 * @Author：xuhaowen
 * @Date：2018-01-21
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public interface TreeNode extends Serializable{

    String getId();

    String getName();

    String getParentId();
}
