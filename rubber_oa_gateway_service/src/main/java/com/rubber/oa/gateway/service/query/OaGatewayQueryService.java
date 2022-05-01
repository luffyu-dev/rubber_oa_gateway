package com.rubber.oa.gateway.service.query;

import com.rubber.oa.gateway.dao.dal.IRubberGatewayConfigDal;
import com.rubber.oa.gateway.dao.entity.RubberGatewayConfigEntity;
import com.rubber.oa.geteway.api.query.OaGatewayQueryApi;
import com.rubber.oa.geteway.api.query.dto.RubberGatewayConfigDto;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luffyu
 * Created on 2022/4/23
 */
@DubboService
@Service
public class OaGatewayQueryService implements OaGatewayQueryApi {

    @Autowired
    private IRubberGatewayConfigDal iRubberGatewayConfigDal;


    /**
     * 通过url查询网关的配置
     *
     * @param url
     * @return
     */
    @Override
    public RubberGatewayConfigDto getGatewayConfig(String url) {
        RubberGatewayConfigEntity gatewayConfig = iRubberGatewayConfigDal.getGatewayConfig(url);
        if (gatewayConfig == null){
            return null;
        }
        RubberGatewayConfigDto dto = new RubberGatewayConfigDto();
        BeanUtils.copyProperties(gatewayConfig,dto);
        return dto;
    }
}
