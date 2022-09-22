package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * 省
 */
public class Province extends DataSupport {
    private int id;
    private String provinceId;
    private String provinceName;

    public Province() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
