package com.longke.shot.entity;

import java.util.List;

/**
 * 作者：$ longke on 2018/3/15 10:33
 * 邮箱：373497847@qq.com
 */

public class Data {
    String studentCode;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public List<Info.DataBean.ShootDetailListBean> getList() {
        return list;
    }

    public void setList(List<Info.DataBean.ShootDetailListBean> list) {
        this.list = list;
    }

    List<Info.DataBean.ShootDetailListBean> list;
}
