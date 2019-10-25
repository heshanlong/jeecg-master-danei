
<%
	String load = request.getParameter("load");
	request.setAttribute("load", load);
%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<t:base type="common"></t:base>
<link rel="stylesheet"
	href="plug-in/themes/fineui/common/iconfont2/iconfont.css">

<input id="files" name="${param.name}" value="${param.ids}"
	type="hidden"
	<c:if test="${param.required=='required'}">datatype="*"</c:if>
	ignore="checked" nullmsg="请上传相关附件${param.message}！" />
<span class="Validform_checktip"></span>
<label class="Validform_label" style="display: none;">附件</label>
<div style="float: left">

	<c:if test="${!empty param.value}">
		<c:forTokens items="${param.value}" delims=";" var="item"
			varStatus="p">
			<div class="picturebox">
				<div class="top defaultImage">
					<img class="image" src='<c:out value="${item}"/>'
						 onerror="this.style.opacity=0;changeImage(this)" onload="$(this).parent().removeClass('defaultImage')" onclick="open3(this)">
				</div>
				<div class="center">
					<div class="bar">
						<div class="">
							<div class="progress" style="height: 7px; background: #73c9e3;">
								<div
									<%--class="progress-bar progress-bar-info progress-bar-striped active"--%> style="width: 100%;">
								</div>
							</div>
						</div>
					</div>
					<div class="filename">${fn:split(param.fileNames,";")[p.index]}</div>
					<div class="detail">(${fn:split(param.sizes,";")[p.index]})</div>
				</div>
				<div class="bottom">
					<c:forTokens items="${param.types}" delims=";" var="itemType"
						varStatus="pt">
						<c:if test="${ pt.index == p.index}">

							<c:choose>
								<c:when
									test="${itemType =='gif' or itemType =='jpg' or
						 itemType =='jpeg' or  itemType =='png' or  itemType =='dib' or  itemType =='svg' or  itemType =='dds' or  itemType =='tif'or  itemType =='wdp'or  itemType =='emf' or  itemType =='ico'}">
									<dev class="mybutton" onclick="open2(this,'${item}')"> <i
										class="fa fa-search-plus" aria-hidden="true"></i></dev>
								</c:when>
								<c:otherwise>
									<a href="${item}" class="mybutton" download="${fn:split(param.fileNames,";")[p.index]}"> <i
										class="iconfont icon-xiazai"></i></a>
								</c:otherwise>
							</c:choose>

							<dev class="mybutton fileId" fileid="${fn:split(param.ids,"
								;")[p.index]}" onclick="<c:if test="${param.disable!='disable' and load!='detail'}">myRemoveFile(this<c:if test="${param.multiple != 'multiple'}">,true</c:if>)</c:if>">
							<i class="fa fa-trash" aria-hidden="true"></i> </dev>
						</c:if>
					</c:forTokens>
				</div>

			</div>
		</c:forTokens>
	</c:if>
	<c:if
		test="${empty param.value   and(load=='detail' || param.disable=='disable'  )  }">
		<div style="padding: 6px 0">无</div>

	</c:if>
</div>
<div style="float: left; line-height: 30px"
	directory="${param.directory}">
	<%--<input type="file" onchange="myUploadFile(${param.typeSwf!='1'?'0':'1'},this<c:if test="${param.multiple != 'multiple'}">,true</c:if>)" style="display:none" <c:if test="${param.multiple == 'multiple'}">multiple="multiple"</c:if>--%>
	<%--accept="image/gif,image/jpeg,image/png,image/jpg,image/bmp"/>--%>
	<input id="fileId" type="file" class="file"
		onchange="myUploadFile('${param.fileNames}','${param.distinct}','${param.module}','${param.acceptNum}','${param.acceptFileType}','${param.value}',${param.typeSwf!='1'?'0':'1'},this<c:if test="${param.multiple != 'multiple'}">,true</c:if>)"
		style="display: none"
		<c:if test="${param.multiple == 'multiple'}">multiple="multiple"</c:if>
		accept="${param.acceptFileType==''?'image/*':param.acceptFileType}" />
	<c:if test="${param.disable!='disable' and load!='detail'}">
		<c:choose>
			<c:when
				test="${(param.multiple != 'multiple')&&(fn:length(param.value)>0)}">
				<a class="easyui-linkbutton" iconCls="icon-search"
					data-options="disabled:true"
					onclick="$(this).parent().find('input[type=\'file\']').trigger('click')">浏览</a>
			</c:when>
			<c:otherwise>
				<a class="easyui-linkbutton" iconCls="icon-search"
					onclick="$(this).parent().find('input[type=\'file\']').trigger('click')">浏览</a>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>
<script>
    function open2(e,item){
        var s=$(e).parent().parent().find('img').attr('src');
        if(s.indexOf('.gif')!=-1 || s.indexOf('.jpg') !=-1 ||s.indexOf('.jpeg') !=-1 ||s.indexOf('.png') !=-1 ||s.indexOf('.htm') !=-1 || s.indexOf('.html') !=-1 || s.indexOf('.dib') !=-1 || s.indexOf('.svg') !=-1 || s.indexOf('.dds') !=-1 || s.indexOf('.tif') !=-1  || s.indexOf('.wdp') !=-1 || s.indexOf('.emf') !=-1 || s.indexOf('.ico') !=-1 || s.indexOf('.wmf') !=-1){
            if(s!=""){

                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: true,
                    area: [500 + 'px', 500 + 'px'], //宽高
                    content: "<img width='500px' height='500px'  src=" + s + " />"
                });
            }
		}else{

            /* var fileId=$(e).parent().find(".fileId").attr("fileId");
            var a = document.createElement("a");
            a.href=item;
            a.click(); */
            ///createwindow("预览","famsRulesregulationsController.do?openViewFile&fileid="+fileId+"&rulesPathUrl="+s,600,600);
		}


    }
    function onErrorPic(t){
		console.log($(t).parent());
        //$(t).parent().removeClass("defaultImage");
	}
    function open3(e){
        var s=$(e).attr('src');
        if(s.indexOf('.gif')!=-1 || s.indexOf('.jpg') !=-1 ||s.indexOf('.jpeg') !=-1 ||s.indexOf('.png') !=-1 ||s.indexOf('.htm') !=-1 || s.indexOf('.html') !=-1 || s.indexOf('.dib') !=-1 || s.indexOf('.svg') !=-1 || s.indexOf('.dds') !=-1 || s.indexOf('.tif') !=-1  || s.indexOf('.wdp') !=-1 || s.indexOf('.emf') !=-1 || s.indexOf('.ico') !=-1 || s.indexOf('.wmf') !=-1 ) {
            if (s != "") {

                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: true,
                    area: [500 + 'px', 500 + 'px'], //宽高
                    content: "<img width='500px' height='500px'  src=" + s + " />"
                });
            }
        }else{
            //var fileId=$(e).parent().find(".fileId").attr("fileId");
            //createwindow("预览","famsRulesregulationsController.do?openViewFile&fileid="+fileId+"&rulesPathUrl="+s,600,600);
		}
        //console.log(s);
    }
</script>