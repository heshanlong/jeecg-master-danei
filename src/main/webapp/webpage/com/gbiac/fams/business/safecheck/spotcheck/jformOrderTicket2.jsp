<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- <h4>分类标题</h4> -->
	    <div class="row">
	      <div class="col-md-12 layout-header">
	        <button id="addBtn_JformOrderTicket2" type="button" class="btn btn-default">添加</button>
	        <button id="delBtn_JformOrderTicket2" type="button" class="btn btn-default">删除</button>
	        <script type="text/javascript"> 
			$('#addBtn_JformOrderTicket2').bind('click', function(){   
		 		 var tr =  $("#add_jformOrderTicket2_table_template>tr").clone();
			 	 $("#add_jformOrderTicket2_table").append(tr);
			 	 resetTrNum('add_jformOrderTicket2_table');
			 	 return false;
		    });  
			$('#delBtn_JformOrderTicket2').bind('click', function(){   
		       $("#add_jformOrderTicket2_table").find("input[name$='ck']:checked").parent().parent().remove();   
		        resetTrNum('add_jformOrderTicket2_table');
		        return false;
		    });
		    $(document).ready(function(){
		    	if(location.href.indexOf("load=detail")!=-1){
					$(":input").attr("disabled","true");
					$(".datagrid-toolbar").hide();
				}
		    	resetTrNum('add_jformOrderTicket2_table');
		    });
		</script>
	      </div>
	    </div>
<div style="margin: 0; background-color: white;overflow: auto;">    
	    <!-- Table -->
      <table id="jformOrderTicket2_table" class="table table-bordered table-hover" style="margin-bottom: 0;">
		<thead>
	      <tr>
	        <th style="white-space:nowrap;width:50px;">操作</th>
	        <th style="width:40px;">序号</th>
						<th>
							检查人
					    </th>
						<th>
							航班号
					    </th>
					    <th>
							机号
					    </th>
						<th>
							机型
					    </th>
					    <th>
							停机位
					    </th>
						<th>
							检查开始时间
					    </th>
					    <th>
							检查结束时间
					    </th>
						<th>
							检查情况
					    </th>
	      </tr>
	    </thead>
        
	<tbody id="add_jformOrderTicket2_table">	
	<c:if test="${fn:length(jformOrderTicket2List)  <= 0 }">
			<tr>
				<td>
					<input style="width:20px;" type="checkbox" name="ck"/>
					<input name="jformOrderTicket2List[0].id" type="hidden"/>
					<input name="jformOrderTicket2List[0].fckId" type="hidden"/>
				</td>
					
				<td scope="row">
					<div name="xh"></div>
				</td>
				  	<td>
						<input name="jformOrderTicket2List[0].checkPerson" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[0].flightno" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
					<td>
						<input name="jformOrderTicket2List[0].immediately" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[0].models" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
					<td>
						<input name="jformOrderTicket2List[0].slots" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[0].checkDateStart" maxlength="10" type="text"  class="form-control" onClick="WdatePicker({dateFmt:'HH:mm:ss'})"  style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;width:120px;"  datatype="*"  ignore="checked" />
					</td>
					<td>
						<input name="jformOrderTicket2List[0].checkDateEnd" maxlength="10" type="text" class="form-control" onClick="WdatePicker({dateFmt:'HH:mm:ss'})"  style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[0].checkDetail" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(jformOrderTicket2List)  > 0 }">
		<c:forEach items="${jformOrderTicket2List}" var="poVal" varStatus="stuts">
			<tr>
				<td>
					<input style="width:20px;" type="checkbox" name="ck"/>
					<input name="jformOrderTicket2List[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="jformOrderTicket2List[${stuts.index }].fckId" type="hidden" value="${poVal.fckId }"/>
				</td>
					
				<td scope="row">
					<div name="xh">${stuts.index+1 }</div>
				</td>
				   <td>
						<input name="jformOrderTicket2List[${stuts.index }].checkPerson" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[${stuts.index }].flightno" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
					<td>
						<input name="jformOrderTicket2List[${stuts.index }].immediately" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[${stuts.index }].models" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
					<td>
						<input name="jformOrderTicket2List[${stuts.index }].slots" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[${stuts.index }].checkDateStart" maxlength="10" type="text"  class="form-control" onClick="WdatePicker({dateFmt:'HH:mm:ss'})"  style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;width:120px;"  datatype="*"  ignore="checked" />
					</td>
					<td>
						<input name="jformOrderTicket2List[${stuts.index }].checkDateEnd" maxlength="10" type="text" class="form-control" onClick="WdatePicker({dateFmt:'HH:mm:ss'})"  style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;width:120px;"  datatype="*"  ignore="checked" />
					</td>
				  	<td>
						<input name="jformOrderTicket2List[${stuts.index }].checkDetail" maxlength="100" type="text" class="form-control"  style="width:120px;"  datatype="*"  ignore="checked" />
					</td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>