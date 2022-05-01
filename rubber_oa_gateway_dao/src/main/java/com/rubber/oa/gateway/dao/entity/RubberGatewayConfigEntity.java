package com.rubber.oa.gateway.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 网关配置表
 * </p>
 *
 * @author luffyu
 * @since 2022-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_rubber_gateway_config")
public class RubberGatewayConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 域名
     */
    private String realmName;

    /**
     * 请求到地址
     */
    private String requestUrl;

    /**
     * 登录标记 0表示不登录 1表示要求登录
     */
    private Integer checkLoginFlag;

    /**
     * dubbo / http
     */
    private String serviceType;

    /**
     * 服务接口
     */
    private String serviceInterface;

    /**
     * 服务方法
     */
    private String serviceMethod;

    /**
     * 服务版本
     */
    private String serviceVersion;

    /**
     * 服务组
     */
    private String serviceGroup;

    /**
     * 服务超时时间
     */
    private String serviceTimeOut;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 管理者，多个管理者逗号隔开
     */
    private String manager;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 版本号
     */
    @Version
    private Integer version;


}
