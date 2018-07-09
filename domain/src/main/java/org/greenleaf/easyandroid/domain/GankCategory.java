package org.greenleaf.easyandroid.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2018/7/4
 * time: 14:34
 */
@Entity
public class GankCategory {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    @NotNull
    private String name;

    @Generated(hash = 437991935)
    public GankCategory(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1282741113)
    public GankCategory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
