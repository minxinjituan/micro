<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典名称列表</title>
<script type="text/javascript">
function showDicItems(dicName,sysId){
	var url="<%=basePath%>/jsp/dicItem.jsp?dicName="+dicName+"&sysId="+sysId;
	$("#itemFrame").attr("src",url);
	$("#itemDialog").dialog("open").dialog("setTitle","查看字典项");
}
var url="<%=basePath%>/CommonDicServlet?requestName=ListDic";
$(function(){
	$("#dataList").datagrid({
		url:url,
		
		method:"post",
		fitColumns: true,
		columns:[[
		          {field:"uuid",title:"uuid",hidden:true},
		          {field:"dic_id",title:"字典标识",width:100},
		          {field:"dic_name",title:"字典名称",width:150},
		          {field:"sys_id",title:"系统标识",width:100},
		          {field:"remark",title:"备注",width:200},
		          {field:"operator",title:"操作",width:100,
		        	  formatter:function(val,row){
		        		  var str="<a href='javascript:showDicItems(\"row.dic_id\",\"row.sys_id\")'>查看字典项</a>"
		        		  return str;
		        	  }}
			  		]]
		});
});
</script>
</head>
<body>
<table></table>
<table id="dataList"></table>
<div id="itemDialog" class="easyui-dialog" closed=true  resizable=true style="width:600px">
<iframe id="itemFrame" width="100%" height="100%" frameborder="0" ></iframe>
</div>
</body>
</html>