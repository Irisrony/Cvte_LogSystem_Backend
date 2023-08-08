package com.cvte.logsystem.response;

import java.util.HashMap;
import java.util.Map;

public abstract class BasicResponse {
    private final static Map<String,Boolean> status = new HashMap<>();

    public BasicResponse(){
        status.put("status",false);
    }

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
        if (data == null){
            Map<String,Boolean> mp = new HashMap<>();
            mp.put("status",false);
            res.setData(mp);
        }else{
            res.setData(data);
        }
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
        res.setData(status);
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
        res.setData(status);
        return res;
    }

    protected ResultVo responseFail(String code,String msg){
        ResultVo res = new ResultVo();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(status);
        return res;
    }
}
