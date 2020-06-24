package cn.stackflow.sgener;

import cn.stackflow.sgener.base.TableDetail;
import cn.stackflow.sgener.connection.AbstractConnection;
import cn.stackflow.sgener.connection.mysql.DefaultMysqlConnection;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-19 14:21
 */
public class VelocityGenerator {

    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();
//        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template entity = ve.getTemplate("/src/main/resources/template/entity.vm");
        Template repository = ve.getTemplate("/src/main/resources/template/repository.vm");


        AbstractConnection connection = new DefaultMysqlConnection();
        List<TableDetail> tableList = connection.getTableList("");

        System.out.println(tableList);
        VelocityContext ctx = new VelocityContext();
        ctx.put("entityPackageName", "cn.stackflow.sgener.entity");
        ctx.put("repostoryPackageName", "cn.stackflow.sgener.repostory");
//        String rootPath = VelocityGenerator.class.getClassLoader().getResource("").getFile() ;
        String rootPath = VelocityGenerator.class.getClassLoader().getResource("").getFile();
        for (TableDetail tableDetail : tableList) {
            ctx.put("table", tableDetail);
            merge(entity, ctx, rootPath +"/"+tableDetail.getClassName()+".java" );
            merge(repository, ctx, rootPath  +"/"+tableDetail.getClassName()+"Repository.java" );
        }
        System.out.println("success...");
    }

    private static void merge(Template template, VelocityContext ctx, String path) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }


}
