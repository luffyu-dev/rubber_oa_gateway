package com.rubber.oa.gateway.dao.dal;

import com.rubber.oa.gateway.dao.entity.RubberGatewayConfigEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网关配置表 服务类
 * </p>
 *
 * @author luffyu
 * @since 2022-04-23
 */
public interface IRubberGatewayConfigDal extends IService<RubberGatewayConfigEntity> {


    /**
     * 通过url查询网关的配置
     * @param url
     * @return
     */
    RubberGatewayConfigEntity  getGatewayConfig(String url);

}
