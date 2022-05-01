package com.rubber.oa.gateway.web.manager.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.rubber.base.components.util.result.IResultHandle;
import com.rubber.base.components.util.result.ResultMsg;
import com.rubber.base.components.util.result.code.SysCode;
import com.rubber.base.components.util.result.exception.IResultException;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.service.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 异常吹
 * @author luffyu
 * Created on 2022/4/21
 */
@Slf4j
@Component
public class GatewayExceptionHandler {


    @Autowired
    private GatewayResultHandler gatewayResultHandler;


    /**
     * 异常处理
     * @param ctx
     * @param e
     */
    public void handler(RubberOaGatewayCtx ctx ,Exception e){
        log.info("GatewayExceptionHandler handler {}",e.getMessage());
        if(e instanceof IResultException){
            IResultException re = (IResultException)e;
            IResultHandle resultHandle = re.getResult();
            gatewayResultHandler.handlerResultError(ctx,resultHandle);
        } else if (e instanceof BlockException){
            gatewayResultHandler.handlerResultError(ctx,SysCode.SYSTEM_BUS);
        }else if (e instanceof GenericException){
            GenericException ge = (GenericException)e;
            String exceptionMessage = ge.getExceptionMessage();
            ResultMsg resultMsg;
            if (StrUtil.isNotEmpty(exceptionMessage)){
                String[] emArray = exceptionMessage.split(":");
                String code = emArray[0];
                String msg = emArray.length > 1 ? emArray[1] : exceptionMessage;
                resultMsg = ResultMsg.create(code,msg,null);
            }else {
                resultMsg = ResultMsg.error();
            }
            gatewayResultHandler.handlerResultError(ctx,resultMsg);
        }else if (e instanceof com.alibaba.dubbo.rpc.service.GenericException ){
            com.alibaba.dubbo.rpc.service.GenericException ge = (com.alibaba.dubbo.rpc.service.GenericException )e;
            String exceptionMessage = ge.getExceptionMessage();
            ResultMsg resultMsg;
            if (StrUtil.isNotEmpty(exceptionMessage)){
                String[] emArray = exceptionMessage.split(":");
                String code = emArray[0];
                String msg = emArray.length > 1 ? emArray[1] : exceptionMessage;
                resultMsg = ResultMsg.create(code,msg,null);
            }else {
                resultMsg = ResultMsg.error();
            }
            gatewayResultHandler.handlerResultError(ctx,resultMsg);
        } else {
            gatewayResultHandler.handlerResultError(ctx,SysCode.SYSTEM_ERROR);
        }
    }


}
