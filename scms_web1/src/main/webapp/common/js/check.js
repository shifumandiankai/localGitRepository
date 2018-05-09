

//==========获取邮箱验证码，或手机验证码，倒计时统一调用函数=============
var isinerval;
var mobilephonetime = 120;
var emailtime = 10;

//验证字符最大长度
function checkStrMaxLen(value,len){
	var result = "";
	if(len == null){
		len =128;
	}
	if(value.length > len){
		result = "数据长度不能大于"+len+"位";
	} else {
		result = 100;
	}
	return result;
}

//验证邮箱
function cgeckEmail(value) {
	var result = "";
	if (value == '') {
		result = "邮箱不能为空";
	} else if (RegExpObj.yahooemail(value)) {
		result = '建议不要使用雅虎邮箱，该邮箱已停止服务';
	} else if (RegExpObj.email(value)) {
		result = 100;
	} else {
		result = "邮箱格式不正确";
	}
	return result;
}

//验证用户名
function checkUsername(value) {
	var result = '';
	if (value == '') {
		result = "用户名不能为空";
	} else if (value.length > 32) {
		result = "用户名长度不能大于32位";
	} else if (!RegExpObj.isCharOrNum(value.substr(0, 1))) {
		result = "用户名必须以字母或者数字开头";
	} else if(!RegExpObj.loginUserName(value)) {
		result = "用户名仅支持中英文、数字和下划线";
	} else {
		result = 100;
	}
	return result;
}
//验证角色
function checkRoleName(value) {
	var result = "";
	if (value == '') {
		result = "角色名称不能为空";
	} else if (value.length > 32) {
		result = "角色名称长度不能大于32位";
	} else if (!RegExpObj.isCharOrNum(value.substr(0, 1))) {
		result = "角色名称必须以字母或者数字开头";
	} else if(!RegExpObj.loginUserName(value)) {
		result = "角色名称仅支持中英文、数字和下划线";
	} else {
		result = 100;
	}
	return result;
}

//验证手机号
function checkPhone(value) {
	var result = "";
	if (value == '') {
		result = "手机号不能为空";
	} else if (RegExpObj.phone(value)) {
		result = 100;
	} else {
		result = "手机号格式不正确";
	}
	return result;
}
//验证手机获取到的验证码  register
function checkPhonecode(value) {
	var result = "";
	//未获取验证码
	if (value == '') {
		result = '验证码不能为空';
	} else if (value.length < 6) {
		result = '验证码不正确';
	} else if (RegExpObj.isNumber(value)) {
		result = 100;
	} else {
		result = '验证码不正确';
	}
	return result;
}
//密码正则表达式验证 
function chkPassword(value) {
	var password = value;
	var result = '';
	if (password == '') {
		result = '密码不能为空';     //密码为空
	} else if (password.length < 6) {
		result = '密码长度不能少于6';    //密码长度小于6
	} else if (password.length > 16) {
		result = '密码长度不能大于16';     //密码长度大于16
	} else if (password.indexOf(" ") != -1) {
		result = '密码里不能有空格';     //密码空格判断
	} else if (pwdGroup(password) > 1) {
		result = 100;     //密码格式符合规定
	} else {
		result = '至少字母加数字一种组合方式以上，区分大小写';     //密码格式不符合规定
	}
	return result;
}
//验证两次密码是否输入一致  
function checkPasswordtwo(password1, password2) {
	var msg;
	if (password2 == "") {
		msg = "确认密码不能为空";
	} else if (password1 != password2) {
		msg = "密码不一致，请重新输入";
	} else {
		msg = 100;
	}
	return msg;
}
//验证普通验证码
function checkCode(value) {
	var result = '';
	if (value == "") {
		// $('#addcodent').html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i></span>').show();
		result = '验证码不能为空';
	} else if (value.length < 4) {
		result = '验证码不正确';
	} else {
		result = 100;
	}
	return result;
}
//检查密码组合
function pwdGroup(password) {
	var reg = /\s/;
	var level = 0;
	if (RegExpObj.hasCapital(password)) {
		level = 1;//有大写字母
	}
	if (RegExpObj.hasLowercase(password)) {
		level = 1;//有小写字母
	}
	if (RegExpObj.hasNumber(password)) {
		level++;//有数字
	}
	if (RegExpObj.hasOther(password)) {
		level++;//有其他字符
	}
	if (reg.exec(password) != null) {
		level = 1;//有空格
	}
	return level;
}

/**
 * @param string type 验证码类型email邮箱，phone手机
 * @param object obj  需要绑定获取验证码的对象
 * @param function eventFunc 绑定的函数
 * @returns {Boolean}
 */
function getVerifyCode(type, obj, eventFunc) {
	var time, limit, msg;
	if (type == 'email') {
		time = emailtime;
		limit = 10;
		msg = '重新发送';
	} else {
		time = mobilephonetime;
		limit = 120;
		msg = '发送验证码';
	}
	if (time < 0) {
		type == 'email' ? emailtime = 10 : mobilephonetime = 120;
		clearInterval(isinerval);
		type == 'email' ? obj.attr("class", "").val(msg).css('width', '74px').html(msg).bind("click", eventFunc) : obj.attr("class", "btn").html(msg).bind("click", eventFunc); //绑定发送验证码的触发事件
		return;
	}
	if (type == 'email') {
		obj.attr("class", "btn disabled").css('width', '108px').val(time + "秒后重新获取").html(time + "秒后重新获取");
	} else {
		obj.attr("class", "btn disabled").html(time + "秒后重新获取");
	}
	type == 'email' ? emailtime-- : mobilephonetime--;
}


//验证姓名

function checkName(data){
	var result;
	if(data==""){
		result="姓名不能为空"
	}else if(!RegExpObj.isName(data)){
		result="2-8位由字母、数字、_或汉字组成！"
	}else {

		result=100;
	}

	return result
}
//验证车牌
function checkCarNum(data){

	var result=""
		if(data==""){

			result="车牌号不能为空"
		}else if(data.indexOf(" ")!=-1){
			result="车牌号中请不要携带空格"
		}
		else if(!RegExpObj.isCarNum(data)){
			result="车牌格式不符"
		}
		else
			result=100;
	return result;
}
function checkMobile(data){
	var result=""
		if(data==""){
			result="手机号码不能为空"
		}else if(!RegExpObj.phone(data)){
			result="手机号格式错误";
		}else
		{
			result=100;
		}
	return result;
}
function checkMoney(data){
	var result = "";
	if(data==""){
		result="不能为空！"

	}
	else if(!RegExpObj.checkMoney(data)){
		result="请输入正确的金额格式，最大9999999.99"
	}else{
		result=100;
	}
	return result;

}
function checkIp(ip)   
{   var result="";

var re =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/   

	if(ip==""){
		result="不能为空！";
	}else if(ip.indexOf(" ")!=-1){
		result="请不要携带空格！"
	}else if(!re.test(ip)){
		result="请输入127.0.0.1的ip格式"
	}else {
		result=100;
	}
return result;   
}  
function checkPort(port){
	var result="";
	var re=/^([1-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/
	if(port==""){
		result="不能为空！";
	}else if(port.indexOf(" ")!=-1){
		result="请不要携带空格！"
	}else if(!re.test(port)){
		result="请输入1-65535的端口号"
	}else {
		result=100;
	}
return result;   
	
	
}

//车位数量
function checkCarNumber(value) {
	var re = /^\+?[1-9]\d*$/;
	var result="";
	if(!re.test(value)||value==''){
		result='请输入大于0的正整数';
	}else if(value!=''&&value>10000){
		result='车位数量不能超过10000辆';
	}else{
		result=100;
	}
	return result; 
}

//车场序号
function checkParkingLotCode(value) {
	//var re = /^\+?[1-9]\d*$/;
	var result="";
	if(value==''){
		result='车场序号不能为空';
	}
	/*else if(!re.test(value)){
		result='请输入大于0的正整数';
	}*/else if(value!=''&&value.length>32){
		result='车位序号长度不能超过32';
	}else{
		result=100;
	}
	return result; 
}
//名称
function checkAllName(value,strName) {
	var result = '';
	if (value == '') {
		result = strName+'不能为空';
	} else if (value.length > 32) {
		result = strName+'长度不能大于32位';
	
	} else {
		result = 100;
	}
	return result;	
}

//开始时间和结束时间
function checkStartAndEndTime(startTime,endTime){
	//alert(startTime);
	
	var result='';
	if(startTime>endTime){
		result='开始日期要小于结束时间';
	}else {
		result=100;
	}
	return result;
	
}
//身份证验证
function IDCard(value){
	var result='';
	var re=/^(\d{18}|\d{17}x)$/;
	if(value==''){
		result="身份证号不能为空";
	}else{
		if(!re.test(value)){
			result='身份证号不正确';
		}else{
			result=100;
		}
	}
	return result;
}
