package com.rubber.oa.gateway.web.manager.model;

import com.alibaba.fastjson.JSONObject;
import com.rubber.base.components.util.result.RubberSession;
import com.rubber.base.components.util.result.RubberSystem;
import com.rubber.oa.geteway.api.query.dto.RubberGatewayConfigDto;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luffyu
 * Created on 2022/4/21
 */
@Data
public class RubberOaGatewayCtx {

    /**
     * 当前的请求url
     */
    private String url;

    /**
     * 请求类型 get，params
     */
    private String method;


    /**
     * 入参
     */
    private JSONObject params;


    /**
     * 当前的请求参数
     */
    private HttpServletRequest request;


    /**
     * 当前的servlet的请求
     */
    private HttpServletResponse response;


    private RubberGatewayConfigDto gatewayConfig;


    /**
     * 系统的参数
     */
    private RubberSystem system;

    /**
     * session的参数
     */
    private RubberSession session;


}
