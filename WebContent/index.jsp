<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>网盘主页</title>
<link rel='stylesheet' type='text/css' href='css/overrall.css'/>
<link rel='stylesheet' type='text/css' href='css/bootstrap.css'/>
<style>
#invisFrame{
display:none;
}
</style>
<script>
	function expand(){
		var options=null;
		options=document.getElementById('fileListDropDown');
		if(options.style.display=='none')
			options.style.display='block';
		else
			options.style.display='none';
	}
	function mkdir(){
		var mkDir=document.getElementById("mkDir")
		mkDir.href+=document.getElementById("mkDirInput").value;
	}

</script>
</head>
<body>
	<%
	String currentPath=(String)session.getAttribute("currentPath");
	String uname=null;
	Object obj=session.getAttribute("uname");
	if(obj==null||(uname=(String)obj).equals(""))
		response.sendRedirect("login.jsp");
	%>
	<iframe id="invisFrame"></iframe>
     <div class="container">
		<!-- 页面标题栏 -->
		<div class="row">
			<div class="col-md-12">
				<div class="titlebox">
					<span class="titletext"> <!-- 主标题，如需修改请替换后方标签内的文字 --> <em>
							网盘管理系统 <small> <!-- 副标题，如需修改请替换后方标签内的文字 --> <span
								class="graytext">NetDisk</span>
						</small>
					</em>
					</span> <span id="tb" class="rightbtn"></span>
					<a href="GetServlet">
					<button class="btn btn-link rightbtn" onclick="">刷新 <span class="glyphicon glyphicon-repeat" aria-hidden="true"><img src='images/refresh.png'/></span></button>
					</a>
					<a href="LogoutServlet">
					<button class="btn btn-link rightbtn" onclick="">注销[<%=(String)session.getAttribute("uname")%>]<span class="glyphicon glyphicon-repeat" aria-hidden="true"><img src='images/logout.png'/></span></button>
					</a>
				</div>
				<hr />
			</div>
		</div>
		<!-- 信息栏、操作栏与文件列表 -->
		<div class="row">
			<div class="col-md-12">
				<p id="vicetbbox" class="subtitle" style="display: none;">
					<span id="tb2"></span>
					<button class="btn btn-link" onclick="">
						刷新 <span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
					</button>
				</p>
				<p class="subtitle">
					文件同步时间：<span id="publishTime">
					<%
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String nowTime = sdf.format(d);
					sdf=new SimpleDateFormat("yyyy-MM-dd");
					String nowDay=sdf.format(d);
					out.print(nowTime);
					%>
					</span>
				</p>
				<div id="filetable" class="panel panel-default" unselectable="on"
					onselectstart="return false;" style="-moz-user-select: none;">
					<!-- 文件列表头部，也就是操作栏 -->
					<div class="panel-heading">
						<div class="heading">
							
							<div class="collapse navbar-collapse" id="filetableoptmenu">
								<ul class="nav navbar-nav">
									<li class="dropdown"><a href="UplevelServlet"
										class="dropdown-toggle" data-toggle="dropdown" role="button"
										aria-haspopup="true" aria-expanded="false">上一级 <span
											class="caret"></span></a>
										<ul class="dropdown-menu" id="parentFolderList"></ul></li>
								</ul>
								<form id="filetableoptmenusreach"
									class="navbar-form navbar-left">
									<div class="form-group">
										<input id="mkDirInput" type="text" class="form-control"
											placeholder="请输入一个新文件夹名...">
									</div>
									<a id="mkDir" href="MkdirServlet?dirName=" onclick="mkdir();"><button type="button" class="btn btn-default">新建文件夹</button></a>
								</form>
								<ul class="nav navbar-nav navbar-right" >
									<li id="packageDownloadBox"></li>
									<li class="dropdown" id="operationMenuBox"
										data-toggle="popover"><a class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false" onclick="expand()"><span
											class="glyphicon glyphicon-cog"><img src='images/options.png'></span> 操作 <span
											class="caret"></span></a>
										<ul class="dropdown-menu" id="fileListDropDown">
											<li id="uploadFileButtonLi"><a href="upload.jsp">上传文件 <span
													class="pull-right"><span
														class="glyphicon glyphicon-arrow-up" aria-hidden="true"></a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th onclick="sortbyfn()">文件名<span id="sortByFN"
									aria-hidden="true" style="float: right"></span></th>
								<th class="hiddenColumn" onclick="sortbycd()">创建日期<span
									id="sortByCD" aria-hidden="true" style="float: right"></span></th>
								<th onclick="sortbyfs()">大小<span id="sortByFS"
									aria-hidden="true" style="float: right"></span></th>
								<th class="hiddenColumn" onclick="sortbycn()">创建者<span
									id="sortByCN" aria-hidden="true" style="float: right"></span></th>
								<th onclick="showOriginFolderView()">操作</th>
							</tr>
						</thead>
						<tbody id="foldertable">
						<%
						ArrayList<File> files=(ArrayList<File>)request.getAttribute("files");
						if(files!=null){
							for(File file:files){
								out.print("<tr id='"+file.getName()+"' class='filerow info'>");
								if(file.isDirectory())
									out.print("<td><a href='SwitchdirServlet?targetDir="+file.getName()+"'>"+file.getName()+"[文件夹]"+"</a></td>");
								else
									out.print("<td>"+file.getName()+"</td>");
								out.print("<td class='hiddenColumn' style='display:table-cell;'>"+nowDay+"</td>");
								if(!file.isDirectory())
									out.print("<td>"+String.format("%.2f", ((double)file.length()/(1024*1024)))+"MB"+"</td>");
								else
									out.print("<td>"+"文件夹"+"</td>");
								out.print("<td class='hiddenColumn' style='display:table-cell;'>"+uname+"</td>");
								if(file.isFile())
									out.print("<td><a href='DownloadServlet?fileName="+file.getName()+"' target='_blank'><img src='images/download.png'/><button class='btn btn-link btn-xs'>下载</button></a>");
								out.print("<td><a href='DeleteServlet?fileName="+file.getName()+"'><img src='images/delete.png'/><button class='btn btn-link btn-xs'>删除</button></a>");
								out.print("</td>");
								out.print("</tr>");
							}
						}
						%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
</body>
</body>
</html>