package com.choudou5.base.bean;

import java.io.Serializable;

/**
 * @Name：下拉框 bean
 * @Author：xuhaowende@sina.cn
 * @Date：2018-01-14
 * @Site：http://solrhome.com
 * @License：MIT
 * @Copyright：xuhaowende@sina.cn (@Copyright 2018-2020)
 */
public class SelectBean implements Serializable {

    private String text;
    private String value;
    private boolean selected;

    public SelectBean() {
    }

    public SelectBean(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public SelectBean(String text, String value, boolean selected) {
        this.text = text;
        this.value = value;
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
