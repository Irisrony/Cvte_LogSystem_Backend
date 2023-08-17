package com.cvte.logsystem_sdk.response;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Classname BasicResponse
 * @Date 2023/8/2 4:58 PM
 * @Created by liushenghao
 */
public abstract class BasicResponse {
    private final Map<String,Boolean> fail = new HashMap<>();

    public BasicResponse(){
        fail.put("status",false);
    }

    /**
     * 默认成功返回
     *
     * @param data 返回信息
     * @return  信息统一返回封装
     */
    protected ResultVo responseSuccess(Object data) {
        ResultVo res = new ResultVo();
        res.setCode(ResultCode.SUCCESS.getCode());
        res.setMsg(ResultCode.SUCCESS.getMsg());
        if (data == null){
            Map<String,Boolean> mp = new HashMap<>();
            mp.put("status",true);
            res.setData(mp);
        }else{
            res.setData(data);
        }
        return res;
    }

    /**
     * 带失败代码的返回
     *
     * @param code 状态码
     * @return  信息统一返回封装
     */
    protected ResultVo responseFail(ResultCode code) {
        ResultVo res = new ResultVo();
        res.setCode(code.getCode());
        res.setMsg(code.getMsg());
        res.setData(fail);
        return res;
    }

    /**
     * 自定义消息的错误返回
     *
     * @param code 状态码
     * @param msg   自定义信息
     * @return  消息统一返回封装
     */
    protected ResultVo responseFail(ResultCode code, String msg) {
        ResultVo res = new ResultVo();
        res.setCode(code.getCode());
        res.setMsg(msg == null ? code.getMsg() : msg);
        res.setData(fail);
        return res;
    }

    protected ResultVo responseFail(String code,String msg){
        ResultVo res = new ResultVo();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(fail);
        return res;
    }
}
