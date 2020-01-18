package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.User;

import java.util.Map;

public class JsonUtil {
    private boolean success = true;// 是否成功
    private String msg = "操作成功";// 提示信息
    private Object obj = null;// 其他信息
    private Map<Integer, User> attributes;// 其他参数

    public Map<Integer, User> getAttributes() {
        return attributes;
    }


    public void setAttributes(Map<Integer, User> attributes) {
        this.attributes = attributes;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getObj() {
        return obj;
    }


    public void setObj(Object obj) {
        this.obj = obj;
    }


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getJsonStr() {
        JSONObject obj = new JSONObject();
        obj.put("success", this.isSuccess());
        obj.put("msg", this.getMsg());
        obj.put("obj", this.obj);
        obj.put("attributes", this.attributes);
        return obj.toJSONString();
    }
}
