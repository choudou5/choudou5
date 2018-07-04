<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/assets"/>

<html lang="en">
<head>
    <title>系统日志[管理]</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" >
    <script type="text/javascript">
        var logging_change_url = '${ctx}/framework/logAdminRemote/setting';
        var logDomain = "${domain}";
    </script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="card">
            <div class="card-content">
                <%--<div class="toolbar">
                    <a href="javascript:;" onclick="window.open('${ctx}/framework/logAdminRemote?page=Console')" class="btn btn-success btn-sm">查看Console</a>
                </div>--%>
                <div id="content">
                    <div id="logging" class="clearfix">
                        <h4 align="center">服务地址：${domain}</h4>
                        <div id="frame"></div>
                    </div>
                </div>
            </div>
            <!-- end content-->
        </div>
        <!--  end card  -->
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxStatic }/logAdmin/logging.css">
<script type="text/javascript" src="${ctxStatic }/logAdmin/logging-level.js"></script>
<script type="text/javascript">
    $(function () {
        loadData();
    });
    function loadData(){
        var content_element = $( '#content' );
        var url = "${ctx}/framework/logAdminRemote/ajaxLogTree?domain=${domain}";
        $.ajax({
            url : url,
            dataType : 'json',
            context : $( '#frame', content_element ),
            beforeSend : function( xhr, settings ){
                this.html( '<div class="loader">Loading ...</div>' );
            },
            success : logging_handler
        });
    }
</script>
</html>