<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style type="text/css">
		.col-md-6 .input-group {
			margin-top:23px;
		}
		.col-md-6 .row .col-md-6 {
			margin-top:23px;
		}	
.data-nav {
    background-color: #A0DDD8;
    height: 34px;
    line-height: 30px;
    border: 1px solid #A0DDD8;
 	-moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;
    
    
    overflow: hidden;
       }

    div[name=accountLog] .log-date {
    float: left;
    margin: 3px 0 2px 0;
    }
    
    div[name=accountLog] .log-amount {
    float: right;
    margin: 5px 10px 0 0;
    font-weight: bold;
    color: #F87808;
    }
    div[name=accountLog] .log-remark {
    color: rgba(113, 111, 111, 0.71);
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
        font-size: 13px;
    }
    div[name=accountLog] .log-title {
    overflow: hidden;
    }
    .typeahead dropdown-menu {
    min-width: 250px;
    }
    
 .account-log-unit {
    background-color: #ffffff;
    border-bottom: 1px solid #acd2d0;
    height: 50px;
    text-indent: 10px;
}
    
    
 .accountLeft{ 	
  	float: left; 
 	height: 538px;
 	width: 60%;
 	margin-left: 26px;
 	
 	border: 1px solid #acd2d0;
 	-moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;
 }   
 
.accountRight {
	height:535px;
	width: 24%;
	margin-left: 1%;
	margin-right: 12%;
	float: right;
	
	border: 1px solid #acd2d0;
 	-moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;
}
.accountTop{
	width: auto;
	height: 80px;
	margin-left: 7px;
/* 	border: 1px solid black; */
} 

.accountInput{
	width: 50%;
	margin-left: 5px;
}   
.accountInputForm {
	padding-left: 15px;
}

.accountSelect{
	height: 32px;
	font-size: 14px;
	margin: -13px;
	padding: 0;
	border: 1px solid #acd2d0;
}

.accountSearch{
	margin-top:23px;
	margin-left: -44%;
}

.accountGap {
    border-top: 1px solid #289b93;

    margin: 15px 1px 2px 1px;
    padding: 10px;
}
.accountRightWord{
	margin-left: 10px; 
}

.accountLeftSpan{
	color: #9b2828;
	font-size: 16px;
}
.rowGap{
	margin: 15px 3px 10px -8px;
}
.rowGap1{
	margin: 15px 3px 10px 4px;
}
</style>
<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					账户管理界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">账户管理</li>
				</ol>
			</section>
			<!-- Main content -->

			<section class="content">
				<div class="box">
					<div class="box-body">

	<div class="accountTop">
                <div class="col-md-12">
                    <div class="row">
                   
                        <div class="col-md-6">
                        <div id="cardIdNotice" class="point-out" style="width: 348px; top: -10px; left: 80px; display: none;"></div>
                            <div class="input-group">
                           
                            <span class="input-group-addon ">
                            <select id="selectinfo" class="accountSelect">
                             <option value="0">车牌号</option>
                             <option value="1">卡号</option>
                             <option value="2">手机号</option>
                            </select>
                            </span>
                          
                            
                            <div class="accountInput">
                           <input type="text" class="form-control accountInputForm" placeholder="" name="searchValue0_search" data-provide="typeahead" id="select">
                           </div> 
                           
                             </div>
                             
                        </div>
                        
                        <div class="col-md-6">
                            <div class="accountSearch">
                                    <input type="button" value="查询" class="btn  btn-default" id="selectphone"> 

                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>

          <div class="accountLeft">
          
            <div class="row">
                <div class="col-md-12">
                    <br/>
                    <div class="row">
                        <div class="col-md-3">车主姓名：<span id="personName" class="accountLeftSpan"></span></div>
                       
                        <div class="col-md-3">卡号：<span id="cardId" class="accountLeftSpan"></span></div>
                        
                          <div class="col-md-6">车牌号：<span id="carNumber" class="accountLeftSpan"></span></div>
                       
                    </div>	
                </div>
            </div>
           
           <div class="accountGap"></div>

            
			<div class="row">
            	<div class="col-md-12 zz">
                	 <p style="font-weight:bold; font-size:18px;">普通账户</p>  
                  <div id="valuediv" class="rowGap1"> 余额(元)：      <label id="bdvalue1" style="color:#F87808;font-weight:bold;font-size:31px"></label></div> 
                    <div id="disableddiv" class="rowGap1"> 到期时间：<span id="strDisableTime"  class="accountLeftSpan"></span></div>
                    <div style="float:right;">
                  <input type="button" value="充值" id="charge"  class="btn   btn-warning" >
                
                    <input type="button" value="退款" id="return"  class="btn   btn-default" > 
                   
                    </div>
                </div>
               </div>	
               
               <div class="accountGap"></div>	

				
				 <div class="row">
                <div class="col-md-12 xx cc">
                   
                     <div class=" row">
                     <div class="col-md-12">车辆类别名称：<span id="typeName" class="accountLeftSpan"></span></div>
                     <!-- <div class="col-md-12">车辆性质：<span id="baoqiType" class="accountLeftSpan"></span></div> -->
                     </div>
                     <div class=" row">
                    
                    <div class="col-md-12">车辆性质：<span id="baoqiType" class="accountLeftSpan"></span></div> 
                     </div>
                     <!-- <div class="rowGap row"><div class="col-md-12">备注：<span id="note" class="accountLeftSpan"></span></div></div> -->
                <div class=" row"><div class="col-md-12">备注：<span id="note" class="accountLeftSpan"></span></div></div>
                </div>
                        
                
            </div>
          
          </div>  

					
			
		

		<div class="accountRight">
            <div class="data-nav" >
				    <div class="pull-left accountRightWord">账户记录 (显示最近10条记录)</div>
			</div>
				  
            <div name="accountLog" id="accountLog">
            </div>
        </div>	
		
					
					

					</div>
				</div>
								
			</section>
		</div>
		
<%@ include file="account_dialog.jsp"%> 
<%@ include file="account_failreturndialog.jsp"%> 
<%@ include file="account_returndialog.jsp"%> 
<%@ include file="account_returnsuccessdialog.jsp"%>
<%@ include file="account_successdialog.jsp"%> 
<%@ include file="/common/jsp/footer.jsp"%>
<%@ include file="/common/jsp/sidebar.jsp"%>
		
<script src="/pages/pfm/account/account.js"></script> 
	
    