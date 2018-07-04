<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>日志管理服务列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">

    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/framework/logAdminRemote/serviceList">日志接口列表</a></li>
    </ul>
    <sys:message content="${message}"/>
    <div class="row-fluid">
        <table id="contentTable" class="table table-bordered">
            <thead>
            <tr>
                <th width="200">操作</th>
                <th>应用</th>
                <th>服务名称</th>
                <th width="150">提供者地址</th>
                <th>服务地址</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="entity">
                <tr>
                    <td style="text-align: center;">
                        <a href="javascript:;" onclick="window.open('${ctx}/framework/logAdminRemote?page=Console&domain=${entity.address}&application=${entity.application }')" class="btn btn-success btn-mini">查看日志</a>
                        &nbsp;
                        <a href="javascript:;" onclick="window.open('${ctx}/framework/logAdminRemote?page=List&domain=${entity.address}&application=${entity.application }')" class="btn btn-danger btn-mini">管理日志</a>
                    </td>
                    <td>${entity.application }</td>
                    <td>${entity.service }</td>
                    <td>${entity.address }</td>
                    <td>${entity.url }</td>
                </tr>
            </c:forEach>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="6" style="text-align: center;">没有找到相关数据</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</body>
</html>