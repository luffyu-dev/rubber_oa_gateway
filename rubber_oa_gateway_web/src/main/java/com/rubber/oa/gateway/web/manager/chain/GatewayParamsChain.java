package com.rubber.oa.gateway.web.manager.chain;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.rubber.base.components.util.result.code.SysCode;
import com.rubber.base.components.util.result.exception.BaseResultRunTimeException;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author luffyu
 * Created on 2022/4/30
 */
@Service("gatewayParamsChain")
public class GatewayParamsChain extends AbstractRubberGatewayChain {

    private final static String GAT = "GET";


    @Resource(name = "gatewaySystemChain")
    private IRubberGatewayChain iRubberGatewayChain;

    /**
     * 下一个流程
     *
     * @return 返回下一个节点处理器
     */
    @Override
    public IRubberGatewayChain nextHandler() {
       return iRubberGatewayChain;
    }

    /**
     * 结果处理
     *
     * @param ctx 当前的ctx
     */
    @Override
    public void doHandler(RubberOaGatewayCtx ctx) {
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        JSONObject params ;
        if (GAT.equals(method)){
            params = resolveGatParams(request);
        }else {
            params = resolvePostParams(request);
        }
        ctx.setParams(params);
    }


    /**
     * 解析get的参数
     * @param request 当前的请求
     * @return 返回拓展参数
     */
    private JSONObject resolveGatParams(HttpServletRequest request){
        try {
            JSONObject params = new JSONObject();
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (MapUtil.isNotEmpty(parameterMap)){
                for (Map.Entry<String, String[]>  entry:parameterMap.entrySet()){
                    if (entry.getValue() == null || entry.getValue().length <= 0){
                        continue;
                    }
                    params.put(entry.getKey(),entry.getValue()[0]);
                }
            }
            return params;
        }catch (Exception e){
            throw new BaseResultRunTimeException(SysCode.PARAM_ERROR);
        }
    }


    /**
     * 解析post的参数
     * @param request 当前的请求
     * @return 返回拓展参数
     */
    private JSONObject resolvePostParams(HttpServletRequest request){
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            return JSONObject.parseObject(responseStrBuilder.toString());
        }catch (Exception e){
            throw new BaseResultRunTimeException(SysCode.PARAM_ERROR);
        }
    }

}
