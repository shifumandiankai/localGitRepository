<%@ page contentType="text/html; charset=UTF-8"%>
 <style type="text/css">
/* #imgdivin,#imgdivout{
text-align:center;
vertical-align:middle; 
} */
</style> 
<div class="modal open" id="ChargephotoModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">车辆图片信息</h4>
      </div>
      <div class="modal-body">
      

        <input type="hidden" name="recordId">
       <div  id="imgdivin" style="width:570px; height:300px; border:solid 1px red;">
        <img alt="无进场车辆图片"  id="imgShowin" style="width:568px; height:298px;"/>
        </div>
        
        
        
      </div>
      
    </div>
  </div>
</div> 