package com.rubber.oa.gateway.dao.dal.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rubber.oa.gateway.dao.entity.RubberGatewayConfigEntity;
import com.rubber.oa.gateway.dao.mapper.RubberGatewayConfigMapper;
import com.rubber.oa.gateway.dao.dal.IRubberGatewayConfigDal;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网关配置表 服务实现类
 * </p>
 *
 * @author luffyu
 * @since 2022-04-23
 */
@Service
public class RubberGatewayConfigDalImpl extends ServiceImpl<RubberGatewayConfigMapper, RubberGatewayConfigEntity> implements IRubberGatewayConfigDal {

    /**
     * 通过url查询网关的配置
     *
     * @param url
     * @return
     */
    @Override
    public RubberGatewayConfigEntity getGatewayConfig(String url) {
        if (StrUtil.isEmpty(url)) {
            return null;
        }
        QueryWrapper<RubberGatewayConfigEntity> qw = new QueryWrapper<>();
        qw.lambda().eq(RubberGatewayConfigEntity::getRequestUrl,url);
        return getOne(qw);
    }
}
