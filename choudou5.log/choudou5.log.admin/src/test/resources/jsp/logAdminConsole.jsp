<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/assets"/>

<!doctype html>
<html lang="en">
<head>
    <title>系统日志[控制台]</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" >
    <script type="text/javascript">
        var logging_url = '${ctx}/framework/logAdminRemote/ajaxPrintLog';
        var logDomain = "${domain}";
    </script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="card">
            <div class="card-content">
                <div class="toolbar">
                    <h4 align="center">服务地址：${application}-${domain}</h4>
                </div>
                <div id="content">
                    <div id="logging" class="clearfix">
                        <div id="frame">
                            <div id="viewer">
                                <div class="block">
                                    <h2><span>&nbsp;</span></h2>
                                </div>
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th class="time">Time (<span>Local</span>)</th>
                                            <th class="level">Level</th>
                                            <th class="logger">Logger</th>
                                            <th class="message">Message</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <td colspan="4">No Events available</td>
                                        </tr>
                                    </thead>
                                </table>
                                <div id="footer" class="clearfix">
                                    <div id="state" class="loader">&nbsp;</div>
                                    <div id="date-format"><a>Show dates in UTC</a></div>
                                </div>
                            </div>
                        </div>
                        <h4 align="center">服务地址：${application}-${domain}</h4>
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
<script type="text/javascript" src="${ctxStatic }/logAdmin/logging-view.js"></script>

</html>