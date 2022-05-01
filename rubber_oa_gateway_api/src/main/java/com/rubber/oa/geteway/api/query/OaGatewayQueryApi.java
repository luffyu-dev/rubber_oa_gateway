package com.rubber.oa.geteway.api.query;

import com.rubber.oa.geteway.api.query.dto.RubberGatewayConfigDto;

/**
 * @author luffyu
 * Created on 2022/4/23
 */
public interface OaGatewayQueryApi {

    /**
     * 通过url查询网关的配置
     * @param url
     * @return
     */
    RubberGatewayConfigDto getGatewayConfig(String url);
}
