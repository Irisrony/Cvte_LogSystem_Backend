package com.cvte.cvte_logsystem_sdk_backend.response;

import java.util.HashMap;

/**
 * @Description TODO
 * @Classname BasicResponse
 * @Date 2023/8/2 4:58 PM
 * @Created by liushenghao
 */
public abstract class BasicResponse {
    /**
     * 默认成功返回
     *
     * @param data
     * @return
     */
    protected ResultVo responseSuccess(Object data) {
        ResultVo res = new ResultVo();
        res.setCode(ResultCode.SUCCESS.getCode());
        res.setMsg(ResultCode.SUCCESS.getMsg());
        res.setData(data == null ? new HashMap<>() : data);
        return res;
    }

    /**
     * 带失败代码的返回
     *
     * @param code
     * @return
     */
    protected ResultVo responseFail(ResultCode code) {
        ResultVo res = new ResultVo();
        res.setCode(code.getCode());
        res.setMsg(code.getMsg());
        res.setData(new HashMap<>());
        return res;
    }

    /**
     * 自定义消息的错误返回
     *
     * @param code
     * @param msg
     * @return
     */
    protected ResultVo responseFail(ResultCode code, String msg) {
        ResultVo res = new ResultVo();
        res.setCode(code.getCode());
        res.setMsg(msg == null ? code.getMsg() : msg);
        res.setData(new HashMap<>());
        return res;
    }

    protected ResultVo responseFail(String code,String msg){
        ResultVo res = new ResultVo();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(new HashMap<>());
        return res;
    }
}
