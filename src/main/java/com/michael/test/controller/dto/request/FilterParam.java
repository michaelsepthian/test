package com.michael.test.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterParam {
    private String sort;
    private Integer page;
    private Integer size;
    private String sortBy;

    public String getSort() {
        return this.sort != null ? this.sort : "desc";
    }

    public int getPage() {
        return this.page != null ? this.page : 0;
    }

    public int getSize() {
        return this.size != null ? this.size : 10;
    }

    public String getSortBy() {
        return this.sortBy != null ? this.sortBy : "createdAt";
    }
}
