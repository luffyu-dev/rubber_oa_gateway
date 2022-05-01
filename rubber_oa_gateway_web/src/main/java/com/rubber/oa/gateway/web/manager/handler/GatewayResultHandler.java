package com.rubber.oa.gateway.web.manager.handler;

import com.alibaba.fastjson.JSON;
import com.rubber.base.components.util.result.IResultHandle;
import com.rubber.base.components.util.result.ResultMsg;
import com.rubber.base.components.util.result.RubberBaseResponse;
import com.rubber.base.components.util.result.code.ICodeHandle;
import com.rubber.base.components.util.result.code.SysCode;
import com.rubber.oa.gateway.web.manager.chain.GatewaySystemChain;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author luffyu
 * Created on 2022/4/30
 */
@Component
@Slf4j
public class GatewayResultHandler {

    @Resource
    private GatewaySystemChain gatewaySystemChain;


    /**
     * 成功的结果集合处理
     * @param ctx 当前的上下文
     * @param data 数据
     */
    public void handlerResultSuccess(RubberOaGatewayCtx ctx , Object data) {
        handler(ctx,ResultMsg.create(SysCode.SUCCESS,data));
    }

    /**
     * 失败的结果集合处理
     * @param ctx 当前的上下文
     */
    public void handlerResultError(RubberOaGatewayCtx ctx ,ICodeHandle codeHandle) {
        handler(ctx,ResultMsg.create(codeHandle));
    }


    /**
     * 失败的结果集合处理
     * @param ctx 当前的上下文
     */
    public void handlerResultError(RubberOaGatewayCtx ctx ,IResultHandle resultHandle) {
        handler(ctx,resultHandle);
    }

    /**
     * 返回结果集合
     */
    public void handler(RubberOaGatewayCtx ctx , IResultHandle resultHandle){
        try {
            RubberBaseResponse dataResponse = new RubberBaseResponse();
            if (ctx.getSystem() ==  null){
                doInitSystem(ctx);
            }
            ctx.getSystem().setResponseTime(System.currentTimeMillis());

            dataResponse.setSession(ctx.getSession());
            dataResponse.setSystem(ctx.getSystem());
            dataResponse.setCode(resultHandle.getCode());
            dataResponse.setMsg(resultHandle.getMsg());
            dataResponse.setData(resultHandle.getData());

            HttpServletResponse response = ctx.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(200);
            ServletOutputStream out = response.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            ow.write(JSON.toJSONString(dataResponse));
            ow.flush();
            ow.close();
        }catch (Exception e){
            log.info("处理结果集异常，msg={}",e.getMessage());
            HttpServletResponse response = ctx.getResponse();
            response.setStatus(500);
        }
    }


    private void doInitSystem(RubberOaGatewayCtx ctx){
        gatewaySystemChain.doHandler(ctx);
    }



}
