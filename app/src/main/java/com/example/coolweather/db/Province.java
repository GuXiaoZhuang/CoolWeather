package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

public class Province extends DataSupport {
    private String id;
    private String provinceEn;
    private String provinceZh;

    public Province() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }
}
