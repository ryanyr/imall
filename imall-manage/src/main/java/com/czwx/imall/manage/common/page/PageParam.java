package com.czwx.imall.manage.common.page;

public class PageParam {

    private int current; // 当前页

    private int pageSize; // 页面大小

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
