/**
 * 让IE8，IE7等非HTML5的浏览器支持placeholder
 */
$(function() {
	var v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');
    while (
        div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
        all[0]
    );
    var ieflag= v > 4 ? v : false ;
	if(ieflag){
		$('input, textarea').placeholder();
		var html;
		if ($.fn.placeholder.input && $.fn.placeholder.textarea) {
			html = '<strong>Your current browser natively supports <code>placeholder</code> for <code>input</code> and <code>textarea</code> elements.</strong> The plugin won’t run in this case, since it’s not needed. If you want to test the plugin, use an older browser ;)';
		} else if ($.fn.placeholder.input) {
			html = '<strong>Your current browser natively supports <code>placeholder</code> for <code>input</code> elements, but not for <code>textarea</code> elements.</strong> The plugin will only do its thang on the <code>textarea</code>s.';
		}
		if (html) {
			$('<p class="note">' + html + '</p>').insertAfter('form');
		}
	}
   });

//var oldAlert=window.alert;
///**
// * 不停顿alert
// * @param msg 提示内容
// * @param title 提示标题
// * @param callback 回调方法
// */
//window.alert=function(msg,title,callback){
//	if(!title){
//		title="消息提示";
//	}
//	var d=dialog({
//		id:"messageDialog",
//	    title: title,
//	    width:200,
//	    height:50,
//	    content: msg,
//	    onshow: function(){
//	    	//去掉右上角x
//	    	$(".ui-dialog-close").remove();
//	    	$(".ui-dialog-content").css("background","transparent");
//	    },
//	    okValue: '确定',
//	    ok: function () {
//	    	//点击确定后执行的方法
//	    	$(callback);
//	    }
//	});
//	d.show();
//};
///**
// * 带取消的alert
// * @param msg 提示内容
// * @param title 提示标题
// * @param callback 回调方法
// */
//window.cacelAlert=function(msg,title,w,h,callback){
//	if(!title){
//		title="消息提示";
//	}
//	if(!w){
//		w=200;
//	}
//	if(!h){
//		h=50;
//	}
//	var d=dialog({
//		id:"messageDialog",
//	    title: title,
//	    width:w,
//	    height:h,
//	    content: msg,
//	    onshow: function(){
//	    	//去掉x
//	    	$(".ui-dialog-close").remove();
//	    },
//	    cancelValue: '取消',
//	    cancel: function () {
//	    },
//	    okValue: '确定',
//	    ok: function () {
//	    	//点击确定后执行的方法
//	    	$(callback);
//	    }
//	});
//	d.show();
//};
/** hashMap start*/
function HashMap() {
    /** Map大小* */
    var size = 0;
    /** 对象* */
    var entry = new Object();
    /** Map的存put方法* */
    this.put = function(key, value) {
        if (!this.containsKey(key)) {
            size++;
            entry[key] = value;
        }
    };
    /** Map取get方法* */
    this.get = function(key) {
        return this.containsKey(key) ? entry[key] : null;
    };
    /** Map删除remove方法* */
    this.remove = function(key) {
        if (this.containsKey(key) && (delete entry[key])) {
            size--;
        }
    };
    /** 是否包含Key* */
    this.containsKey = function(key) {
        return (key in entry);
    };
    /** 是否包含Value* */
    this.containsValue = function(value) {
        for ( var prop in entry) {
            if (entry[prop] == value) {
                return true;
            }
        }
        return false;
    };
    /** 所有的Value* */
    this.values = function() {
        var values = new Array();
        for ( var prop in entry) {
            values.push(entry[prop]);
        }
        return values;
    };
    /** 所有的 Key* */
    this.keys = function() {
        var keys = new Array();
        for ( var prop in entry) {
            keys.push(prop);
        }
        return keys;
    };
    /** Map size* */
    this.size = function() {
        return size;
    };
    /** 清空Map* */
    this.clear = function() {
        size = 0;
        entry = new Object();
    };
}
/** hashMap end*/
/**身份证验证 start*/
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
/**
 * 校验身份证号码，并将对应的省份，生日，性别设置到对应的容器里
 * @param sId 身份证号
 * @param pronvinceId  省份select的id
 * @param birthdayId 出生日期的id
 * @param sexName  性别的name
 */
function cidInfo(sId,pronvinceId,birthdayId,sexName){ 
    var iSum=0 ;
    if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/i.test(sId)){// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
    	alert("您输入的身份证号码不正确,请重新输入!");
    	return;
    }
    if(/^\d{17}(\d|x)$/i.test(sId)){//18位身份证
        sId=sId.replace(/x$/i,"a"); 
        if(aCity[parseInt(sId.substr(0,2))]==null){
        	alert("您输入的身份证地区非法!");
        	return;
        }
        sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
        var d=new Date(sBirthday.replace(/-/g,"/"));
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
        	alert("您输入的身份证号的生日非法");
        	return ;
        }
        var month=Number(sId.substr(10,2));
        if(month<10){
        	month="0"+month;
        }
        var day=Number(sId.substr(12,2));
        if(day<10){
        	day="0"+day;
        }
        $("#"+birthdayId).val(sId.substr(6,4)+"-"+month+"-"+day);
        for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11);
        if(iSum%11!=1){
        	alert("您输入的身份证号码非法!");
        	return;
        } ;
        $("#"+sexName+sId.substr(16,1)%2).attr("checked",true);
        //$("#csny").val(sBirthday);
       // sId.substr(16,1)%2?$("#xb").find("option[value=0]").attr("selected",true):$("#xb").find("option[value=1]").attr("selected",true);
    };
} 
/**身份证验证 end*/
/**时间格式化 start*/
/**
 * 给Date的原型添加格式化时间的方法
 * @param {Object} format  要格式化的类型
 * @param {Object} daynum  要加减的时间的天数,加时间填正整数，减时间填负整数
 * 例如：document.write(new Date().format("YYYY-MM-dd"));
 * @return {TypeName}  格式化以后的时间
 */
Date.prototype.format = function(format,daynum) {
    /* 
     * eg:format="YYYY-MM-dd hh:mm:ss"; 
     */
    if(daynum){
       this.setDate(this.getDate()+daynum);
    }
    var o = {
        "M+" : this.getMonth() + 1, // month  
        "d+" : this.getDate(), // day  
        "h+" : this.getHours(), // hour  
        "m+" : this.getMinutes(), // minute  
        "s+" : this.getSeconds(), // second  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S" : this.getMilliseconds()   // millisecond 
    };
    if (/(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - (RegExp.$1).length));
    }
                                                          
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
/**时间格式化 end*/
/**时间运算 start*/
/**
* 给Date的原型添加天数运算的方法
* @param {Object} num  要加减的时间的数量,加时间填正整数，减时间填负整数
*/
Date.prototype.opDays = function(num){
   this.setDate(this.getDate() + num);
   return this;
};
/**
* 给Date的原型添加周运算的方法
* @param {Object} num  要加减的时间的数量,加时间填正整数，减时间填负整数
*/
Date.prototype.opWeeks = function(num){
   this.opDays(num * 7);
   return this;
};
/**
* 给Date的原型添加月运算的方法
* @param {Object} num  要加减的时间的数量,加时间填正整数，减时间填负整数
*/
Date.prototype.opMonths= function(num){
   this.setMonth(this.getMonth() + num);
   return this;
};
/**
* 给Date的原型添加年运算的方法
* @param {Object} num  要加减的时间的数量,加时间填正整数，减时间填负整数
*/
Date.prototype.opYears = function(num){
    this.setFullYear(this.getFullYear() +num);
    return this;
};
/**测试用例
 * document.write(new Date().format("YYYY-MM-dd")+"</br>");//当前日期减去7天，并格式化
   document.write(new Date().opWeeks(-1).format("YYYY-MM-dd")+"</br>");//当前日期减去一周，并格式化
   document.write(new Date().opMonths(5).format("YYYY-MM-dd")+"</br>");//当前日期减去5月，并格式化
   document.write(new Date().opYears(-1).format("YYYY-MM-dd")+"</br>");//当前日期减去一年，并格式化

 * */
/**时间运算 start*/
/**判断是否是IE start*/
var isIE = (function(){
    var undef,
        v = 3,
        div = document.createElement('div'),
        all = div.getElementsByTagName('i');
    while (
        div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
        all[0]
    );
    return v > 4 ? v : undef;
}());
/**判断是否是IE end*/
/**字符串去左右空格 start*/
//删除左右两端的空格

String.prototype.trim=function(){
   return this.replace(/(^\s*)|(\s*$)/g, "");
};
//删除左边的空格
String.prototype.ltrim=function(){
	return this.replace(/(^\s*)/g,"");
};
//删除右边的空格
String.prototype.rtrim=function(){
    return this.replace(/(\s*$)/g,"");
};
function trim(str){ 
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ 
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ 
    return str.replace(/(\s*$)/g,"");
}
/**字符串去左右空格 end*/
/**数值精确计算 start*/
//浮点数相加
function dcmAdd(arg1,arg2){
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
    try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
    m=Math.pow(10,Math.max(r1,r2));
    return (dcmMul(arg1,m)+dcmMul(arg2,m))/m;
}
//浮点数相减
function dcmSub(arg1,arg2){
    return dcmAdd(arg1,-arg2);
}
//浮点数相乘
function dcmMul(arg1,arg2){
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length;}catch(e){}
    try{m+=s2.split(".")[1].length;}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
//浮点数除
function dcmDiv(arg1,arg2){
    return dcmMul(arg1,1/arg2);
}
Number.prototype.add=function (arg){
    return dcmAdd(this, arg);
};
Number.prototype.sub=function(arg){
    return dcmSub(this,arg);
};
Number.prototype.mul=function(arg){
	return dcmMul(this,arg);
};
Number.prototype.div=function(arg){
    return dcmDiv(this,arg);
};
/**数值精确计算 end*/

/**
 * uuid 生成器
 * 使用方法：
 *   Math.uuid() 无参数，默认是32位的uuid 如：92329D39-6F5C-4520-ABFC-AAB64544E172
 *   Math.uuid(15) 有参数，生成的长度 如：VcydxgltxrVZSTV
 *   Math.uuid(8, 2)  长度8的二进制ID  如：01001010
 *   Math.uuid(8, 10) 长度8的十进制ID 如：47473046
 *   Math.uuid(8, 16) 长度8的十六进制ID 如：098F4D35
 */
(function() { 
  // Private array of chars to use 
  var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split(''); 
  
  Math.uuid = function (len, radix) { 
    var chars = CHARS, uuid = [], i; 
    radix = radix || chars.length; 
  
    if (len) { 
      // Compact form 
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix]; 
    } else { 
      // rfc4122, version 4 form 
      var r; 
  
      // rfc4122 requires these characters 
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-'; 
      uuid[14] = '4'; 
  
      // Fill in random data.  At i==19 set the high bits of clock sequence as 
      // per rfc4122, sec. 4.1.5 
      for (i = 0; i < 36; i++) { 
        if (!uuid[i]) { 
          r = 0 | Math.random()*16; 
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r]; 
        } 
      } 
    } 
  
    return uuid.join(''); 
  }; 
  
  // A more performant, but slightly bulkier, RFC4122v4 solution.  We boost performance 
  // by minimizing calls to random() 
  Math.uuidFast = function() { 
    var chars = CHARS, uuid = new Array(36), rnd=0, r; 
    for (var i = 0; i < 36; i++) { 
      if (i==8 || i==13 ||  i==18 || i==23) { 
        uuid[i] = '-'; 
      } else if (i==14) { 
        uuid[i] = '4'; 
      } else { 
        if (rnd <= 0x02) rnd = 0x2000000 + (Math.random()*0x1000000)|0; 
        r = rnd & 0xf; 
        rnd = rnd >> 4; 
        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r]; 
      } 
    } 
    return uuid.join(''); 
  }; 
  
  // A more compact, but less performant, RFC4122v4 solution: 
  Math.uuidCompact = function() { 
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) { 
      var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8); 
      return v.toString(16); 
    }); 
  }; 
})(); 