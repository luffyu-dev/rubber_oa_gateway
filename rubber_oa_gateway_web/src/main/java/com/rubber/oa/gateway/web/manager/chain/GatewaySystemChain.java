package com.rubber.oa.gateway.web.manager.chain;

import com.rubber.base.components.util.result.RubberSystem;
import com.rubber.oa.gateway.web.manager.model.RubberOaGatewayCtx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 系统认证的handler
 * @author luffyu
 * Created on 2022/4/21
 */
@Slf4j
@Component("gatewaySystemChain")
public class GatewaySystemChain extends AbstractRubberGatewayChain {


    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP = "127.0.0.1";
    // 客户端与服务器同为一台机器，获取的 ip 有时候是 ipv6 格式
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    private static final String SEPARATOR = ",";




    @Resource(name = "gatewaySessionAuthChain")
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
        log.info("start gatewaySysParamHandler handler");
        //组装系统参数
        RubberSystem system = ctx.getSystem();
        if (system == null){
            system = initRubberSystem();
            system.setIp(getIpAddress(ctx.getRequest()));
        }
        ctx.setSystem(system);
        //组装调用链路
    }


    public RubberSystem initRubberSystem(){
        RubberSystem system = new RubberSystem();
        system.setRequestTime(System.currentTimeMillis());
        system.setTraceId("this is traceId");
        return system;
    }


    // 根据 HttpServletRequest 获取 IP
    private static String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        // 对于通过多个代理的情况，分割出第一个 IP
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(SEPARATOR) > 0) {
                ip = ip.substring(0, ip.indexOf(SEPARATOR));
            }
        }
        return LOCALHOST_IPV6.equals(ip) ? LOCALHOST_IP : ip;
    }
}
