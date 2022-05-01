package com.rubber.oa.gateway.dao;


import com.rubber.base.components.mysql.utils.RubberSqlCodeGenerator;

/**
 * @author luffyu
 * Created on 2021/6/14
 */
public class RubberUserCodeGenerator {


    public static void main(String[] args) {

        RubberSqlCodeGenerator.CodeGeneratorConfigBean configBean = new RubberSqlCodeGenerator.CodeGeneratorConfigBean();
        configBean.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/rubber_oa_admin_db?useUnicode=true;characterEncoding=utf-8");
        configBean.setUserName("root");
        configBean.setPassword("root");
        configBean.setModelName("rubber-oa-gateway-dao");
        configBean.setPackageParent("com.rubber.oa.gateway.dao");
        configBean.setAuthor("luffyu");
        configBean.setDriverName("com.mysql.cj.jdbc.Driver");

        RubberSqlCodeGenerator rubberSqlCodeGenerator =  new RubberSqlCodeGenerator(configBean);
        rubberSqlCodeGenerator.create("t_rubber_gateway_config");
    }

}
