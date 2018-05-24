package com.kk.search.bean;

import java.util.List;

/**
 * @ClassName SearchResult
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/11 18:22
 */
public class SearchResult {

    private Long total;

    private List<?> data;

    public SearchResult() {

    }

    public SearchResult(Long total, List<?> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
