package com.choudou5.base.util.tree;


import com.choudou5.base.util.CollUtil;
import com.choudou5.base.util.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name：树结构 助手
 * @Author：xuhaowende@sina.cn
 * @Date：2018-01-11 22:32
 * @Site：http://solrhome.com
 * @License：MIT
 */
public class TreeHelper {

    public final static String ROOT_ID = "1";

    private static Map<String, Object> getRoot(String rootName){
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("id", ROOT_ID);
        root.put("text", rootName);
        root.put("type", "root");
        return root;
    }


    /**
     * 构建树结构
     * @param list
     * @param rootName 根名称
     * @return
     */
    public static <T extends TreeNode> Map<String, Object> buildTreeData(List<T> list, String rootName){
        Map<String, Object> root = getRoot(rootName);
        if(CollUtil.isEmpty(list))
            return root;
        root.put("children", buildTree(ROOT_ID, list, 0));
        return root;
    }


    /**
     * 构建树结构
     * @param parentId
     * @param datas 要求 已按父节点排好序
     * @param startEachIndex 开始遍历下标
     * @return
     */
    private static <T extends TreeNode> List<Map<String, Map>> buildTree(String parentId, List<T> datas, int startEachIndex){
        if(CollUtil.isEmpty(datas))
            return null;
        List<Map<String, Map>> childrens = new ArrayList<>();
        for (int i = startEachIndex; i < datas.size(); i++) {
            TreeNode node = datas.get(i);
            if(parentId.equals(node.getParentId())){
                Map children = new LinkedHashMap<>();
                children.put("id", node.getId());
                children.put("text", node.getName());
                children.put("children", buildTree(node.getId(), datas, startEachIndex));
                childrens.add(children);
            }else{
//                break; //实际 datas已排好序 则可以使用这个 来优化 对上千个菜单的遍历
            }
        }
        return childrens;
    }

    private Map<String, TreeNode> toMap(List<TreeNode> datas){
        if(CollUtil.isEmpty(datas))
            return null;
        Map<String, TreeNode> mapList = new LinkedHashMap<>();
        for(TreeNode node : datas) {
            mapList.put(node.getId(), node);
        }
        return mapList;
    }

}
