package com.alibaba.dingtalk.openapi.message;

import com.alibaba.dingtalk.openapi.utils.HttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.choudou5.base.util.StrUtil;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.MessageSendResult;
import com.dingtalk.open.client.api.service.corp.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送消息
 */
public class MessageHelper {

    /**
     * 发送 通知给用户
     * @param accessToken
     * @param appId
     * @param userIdlist 用户列表
     * @param message
     * @return
     * @throws Exception
     */
    public static MessageSendResult sendToUserList(String accessToken, String appId, List<String> userIdlist, Message message)
            throws Exception {
        String url = "/message/send?access_token="+accessToken;
        Map params = new HashMap<>();
        params.put("touser", StrUtil.join("|", userIdlist));
        params.put("msgtype", message.type());
        params.put("agentid", appId);
        params.put(message.type(), JSON.toJSONString(message));
        JSONObject response = HttpHelper.httpPost(url, params);
        return response.toJavaObject(MessageSendResult.class);
    }


    /**
     * 发送 通知给部门
     * @param accessToken
     * @param appId
     * @param deptIdlist 部门ID列表
     * @param message
     * @return
     * @throws Exception
     */
    public static MessageSendResult sendToDeptList(String accessToken, String appId, List<String> deptIdlist, Message message)
            throws Exception {
        String url = "/message/send?access_token="+accessToken;
        Map params = new HashMap<>();
        params.put("toparty", StrUtil.join("|", deptIdlist));
        params.put("msgtype", message.type());
        params.put("agentid", appId);
        params.put(message.type(), JSON.toJSONString(message));
        JSONObject response = HttpHelper.httpPost(url, params);
        return response.toJavaObject(MessageSendResult.class);
    }


    /**
     * 发送 普通消息  (与微应用前端结合)
     * @param accessToken
     * @param delivery  通过JSAPI之pickConversation接口唤起联系人界面选择之后即可拿到会话cid
     * @doc https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.G2vugB&treeId=374&articleId=104974&docType=1
     * @return
     * @throws Exception
     */
    private static String sendNormal(String accessToken, ConversationMessageDelivery delivery)
            throws Exception {
        MessageService messageService = ServiceFactory.getInstance().getOpenService(MessageService.class);
        return messageService.sendToNormalConversation(accessToken, delivery.sender, delivery.cid, delivery.msgType, delivery.message);
    }
}
