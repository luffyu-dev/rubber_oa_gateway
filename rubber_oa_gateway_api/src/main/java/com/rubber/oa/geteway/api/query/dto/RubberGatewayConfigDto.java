package com.rubber.oa.geteway.api.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author luffyu
 * Created on 2022/4/23
 */
@Data
public class RubberGatewayConfigDto {

    /**
     * 自增id
     */
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
    private Integer serviceTimeOut;

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
}
