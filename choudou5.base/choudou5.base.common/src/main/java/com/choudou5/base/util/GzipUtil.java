package com.choudou5.base.util;

import cn.hutool.core.codec.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Name：GzipUtil 说明
 * @Author：xuhaowen
 * @Date：2018-03-04
 */
public class GzipUtil {

    public static void main(String[] args) throws IOException {
        String str = "columnStyleList[0].id=[969834278114508800];columnStyleList[0].table=[sys_role];columnStyleList[0].fieldType=[String];columnStyleList[0].column=[id];columnStyleList[0].desc=[id];columnStyleList[0].fieldName=[id];columnStyleList[0].isList=[on];columnStyleList[0].showType=[input];columnStyleList[0].queryType=[eq];columnStyleList[0].dicType=[];columnStyleList[1].id=[969834278114508801];columnStyleList[1].table=[sys_role];columnStyleList[1].fieldType=[String];columnStyleList[1].column=[office_id];columnStyleList[1].desc=[归属机构];columnStyleList[1].fieldName=[officeId];columnStyleList[1].isEdit=[on];columnStyleList[1].isList=[on];columnStyleList[1].showType=[select];columnStyleList[1].isQuery=[on];columnStyleList[1].queryType=[eq];columnStyleList[1].dicType=[];columnStyleList[2].id=[969834278114508802];columnStyleList[2].table=[sys_role];columnStyleList[2].fieldType=[String];columnStyleList[2].column=[group_name];columnStyleList[2].desc=[分组名];columnStyleList[2].fieldName=[groupName];columnStyleList[2].isEdit=[on];columnStyleList[2].isList=[on];columnStyleList[2].showType=[input];columnStyleList[2].isQuery=[on];columnStyleList[2].queryType=[eq];columnStyleList[2].dicType=[];columnStyleList[3].id=[969834278114508803];columnStyleList[3].table=[sys_role];columnStyleList[3].fieldType=[String];columnStyleList[3].column=[name];columnStyleList[3].desc=[角色名称];columnStyleList[3].fieldName=[name];columnStyleList[3].isEdit=[on];columnStyleList[3].isList=[on];columnStyleList[3].showType=[input];columnStyleList[3].isQuery=[on];columnStyleList[3].queryType=[like];columnStyleList[3].dicType=[];columnStyleList[4].id=[969834278114508804];columnStyleList[4].table=[sys_role];columnStyleList[4].fieldType=[String];columnStyleList[4].column=[data_scope];columnStyleList[4].desc=[数据范围: 0=个人，1=本机构，2=所有];columnStyleList[4].fieldName=[dataScope];columnStyleList[4].isEdit=[on];columnStyleList[4].isList=[on];columnStyleList[4].showType=[select];columnStyleList[4].isQuery=[on];columnStyleList[4].queryType=[eq];columnStyleList[4].dicType=[];columnStyleList[5].id=[969834278114508805];columnStyleList[5].table=[sys_role];columnStyleList[5].fieldType=[String];columnStyleList[5].column=[remarks];columnStyleList[5].desc=[备注信息];columnStyleList[5].fieldName=[remarks];columnStyleList[5].isEdit=[on];columnStyleList[5].isList=[on];columnStyleList[5].showType=[input];columnStyleList[5].queryType=[eq];columnStyleList[5].dicType=[];columnStyleList[6].id=[969834278114508806];columnStyleList[6].table=[sys_role];columnStyleList[6].fieldType=[String];columnStyleList[6].column=[create_by];columnStyleList[6].desc=[创建者];columnStyleList[6].fieldName=[createBy];columnStyleList[6].isList=[on];columnStyleList[6].showType=[input];columnStyleList[6].queryType=[eq];columnStyleList[6].dicType=[];columnStyleList[7].id=[969834278114508807];columnStyleList[7].table=[sys_role];columnStyleList[7].fieldType=[Date];columnStyleList[7].column=[create_time];columnStyleList[7].desc=[创建时间];columnStyleList[7].fieldName=[createTime];columnStyleList[7].isList=[on];columnStyleList[7].showType=[input];columnStyleList[7].queryType=[eq];columnStyleList[7].dicType=[];columnStyleList[8].id=[969834278114508808];columnStyleList[8].table=[sys_role];columnStyleList[8].fieldType=[String];columnStyleList[8].column=[update_by];columnStyleList[8].desc=[创建者];columnStyleList[8].fieldName=[updateBy];columnStyleList[8].showType=[input];columnStyleList[8].queryType=[eq];columnStyleList[8].dicType=[];columnStyleList[9].id=[969834278114508809];columnStyleList[9].table=[sys_role];columnStyleList[9].fieldType=[Date];columnStyleList[9].column=[update_time];columnStyleList[9].desc=[更新时间];columnStyleList[9].fieldName=[updateTime];columnStyleList[9].showType=[input];columnStyleList[9].queryType=[eq];columnStyleList[9].dicType=[];columnStyleList[10].id=[969834278114508810];columnStyleList[10].table=[sys_role];columnStyleList[10].fieldType=[String];columnStyleList[10].column=[status];columnStyleList[10].desc=[状态: 0=禁用，1=正常];columnStyleList[10].fieldName=[status];columnStyleList[10].isEdit=[on];columnStyleList[10].isList=[on];columnStyleList[10].showType=[input];columnStyleList[10].queryType=[eq];columnStyleList[10].dicType=[];columnStyleList[11].id=[969834278114508811];columnStyleList[11].table=[sys_role];columnStyleList[11].fieldType=[String];columnStyleList[11].column=[is_sys_data];columnStyleList[11].desc=[是否系统数据: 0=否，1=是];columnStyleList[11].fieldName=[isSysData];columnStyleList[11].isEdit=[on];columnStyleList[11].isList=[on];columnStyleList[11].showType=[input];columnStyleList[11].queryType=[eq];columnStyleList[11].dicType=[];columnStyleList[12].id=[969834278114508812];columnStyleList[12].table=[sys_role];columnStyleList[12].fieldType=[String];columnStyleList[12].column=[del_flag];columnStyleList[12].desc=[删除标记：0=已删除,1=正常];columnStyleList[12].fieldName=[delFlag];columnStyleList[12].showType=[input];columnStyleList[12].queryType=[eq];columnStyleList[12].dicType=[];author=[xuhaowen];moduleName=[sys];table=[sys_role]";
        System.out.println("原长度：" + str.length());

        String compress = GzipUtil.compress(str);
        System.out.println("压缩后长度：" + compress.length());
        System.out.println("压缩后内容：" + compress);

        System.out.println("解压后内容：" + GzipUtil.uncompress(compress));
    }

    /**
     * 压缩
     * @param str
     */
    public static String compress(String str) {
        if (StrUtil.isEmpty(str))
            return str;
        ByteArrayOutputStream out = null;
        GZIPOutputStream gzip = null;
        String compress = "";
        try {
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            // 这里增加base64编码
            compress = Base64.encode(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return compress;
    }

    /**
     * 解压缩
     * @param str
     */
    public static String uncompress(String str) {
        if (StrUtil.isEmpty(str))
            return str;
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        GZIPInputStream gzip = null;
        String uncompress = "";
        try {
            out = new ByteArrayOutputStream();
            // 这里增加base64解码
            in = new ByteArrayInputStream(Base64.decode(str));
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = gzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            uncompress = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != gzip) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uncompress;
    }

}
