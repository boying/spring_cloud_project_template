package com.jzw.user.domain;

import java.sql.Timestamp;

/**
 * Created by boying on 2017/7/5.
 */
/*
@Data
@ToString
@EqualsAndHashCode
*/
public class BaseModel {
    private long id;
    private int isDelete;
    private Timestamp updatedAt;
    private Timestamp createdAt;

    public boolean isDeleted(){
        return this.isDelete != 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
