<%@ page contentType="text/html; charset=UTF-8"%>
 <style type="text/css">
#imgdiv{
text-align:center;
vertical-align:middle; 

}
</style> 
<div class="modal open" id="photoModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">车辆图片信息</h4>
      </div>
      <div class="modal-body">
      
       <!--  <form class="form-horizontal"> --> 
        <input type="hidden" name="recordId">
       <div  id="imgdiv" style="width:570px; height:300px; border:solid 1px red;">
        <img alt="无车辆图片"  id="imgShow" style="width:568px; height:298px;"/>
        </div>
        <!-- <div id="photodiv" class="form-group" style="display:none;">
		<div class="pull-right"><form id="uploadForm" action="/report/uploadphoto.do" method="post"><input type="file" id="up_img" name="up_img"></form></div>
		<div class="pull-right"><input type="button" id="upload" value="上传头像" ></div>
		</div> -->
        <!-- </form> -->
      </div>
      
    </div>
  </div>
</div> 