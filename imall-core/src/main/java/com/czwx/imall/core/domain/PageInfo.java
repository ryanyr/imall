package com.czwx.imall.core.domain;

import java.io.Serializable;

public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int page;

    private int pageSize;

    private long total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
