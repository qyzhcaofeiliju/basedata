$(function () {
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            var val = this.value || '';
            if (val == '')
                val = null;
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(val);
            } else {
                o[this.name] = val;
            }
        });
        return o;
    };

    $(window).resize(function () {
        copyrightMove();
    });
    copyrightMove();
});

// 日期与时间格式化
var EWDate = (function () {
    return {
        getFullYMD: function (ms, separate) {
            var d = new Date(ms);
            var year = d.getFullYear();
            var month = d.getMonth() + 1;
            month = month < 10 ? '0' + month : month;
            var date = d.getDate();
            date = date < 10 ? ('0' + date) : date;
            if (!separate) separate = "-";
            // 格式：2018-01-01
            return year + separate + month + separate + date;
        },

        getFullYMDHM: function (ms, separate) {
            var d = new Date(ms);
            var year = d.getFullYear();

            var month = d.getMonth() + 1;
            month = month < 10 ? '0' + month : month;

            var date = d.getDate();
            date = date < 10 ? ('0' + date) : date;
            var hours = d.getHours();
            hours = hours < 10 ? ('0'+hours):hours;

            var minutes = d.getMinutes();
            minutes = minutes < 10 ? ('0'+minutes):minutes;
            if (!separate) separate = "-";
            // 格式：2018-07-13 14:30
            return year + separate + month + separate + date + " " + hours +":" + minutes;
        }
    }
})();

// 时间控件触发函数
var ewTimePicker = (function () {
    return{
        timePickerFun:function (elementIdArray) {
            // 遍历数组
            for(j = 0,len=elementIdArray.length; j < len; j++) {
                $("#"+elementIdArray[j]).datetimepicker();
            }
        }
    }
});

var FormValidator = (function () {
    // 定义变量，用于存编辑时的初始数据值
    var $oldData;
    // 验证空值
    var nullValidate = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv) {
        // 指定哪些元素不需要做空值验证
        // 去掉textarea 的空值验证
        var textTagName = inputLab.prop("nodeName");// 获取标签类型
        if (textTagName == "TEXTAREA") {
            return true;
        }

        // 验证输入的值是否为空
        if (!inputLabValue || inputLabValue == '') {
            // 验证失败时需要修改样式 和 提示验证失败信息
            validateFailed(iconsDiv, inputLab, showValidMsgDiv, "输入不能为空...");
            return false;
        }
        return true;
    };

    var requiredValidate = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv) {
        // 获取input中required属性
        var required = inputLab.attr('required');
        // 验证required
        if (required) {
            // 进行空值验证
            nullValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv);
        }
        return true;
    }

    // 验证是否包含特殊字符
    var specCharacterValid = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv) {

        //指定哪些元素不需要做特殊字符的过滤
        // 去掉textarea 的特殊字符验证
        var textTagName = inputLab.prop("nodeName");// 获取标签类型
        // 判断是否过滤特殊字符验证
        var excludeSpecValid = inputLab.attr("excludeSpecValid");
        if (textTagName == "TEXTAREA" || excludeSpecValid == "") {
            return true;
        }

        // 过滤掉以下特殊字符
        var pattern = new RegExp("[`~!@#$^&*%()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        if (pattern.test(inputLabValue)) {
            // 如果包含特殊字符时候
            // 修改样式 和 提示验证失败信息
            validateFailed(iconsDiv, inputLab, showValidMsgDiv, "输入值包含特殊字符...");
            return false;
        }
        return true;
    }

    // 验证正则表达式
    var regexValidate = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv) {
        // 验证正则表达式
        var regex = inputLab.attr('pattern');
        // 正则表达式不为空
        if (regex) {
            // 输入值与正则表达式不匹配
            var reg = new RegExp(regex);

            // 验证正则表达式
            if (!reg.test(inputLabValue)) {
                // 验证正则表达式失败时候修改样式
                validateFailed(iconsDiv, inputLab, showValidMsgDiv, "输入格式错误.....");
                return false;
            }
        }
        return true;

    };

    // 验证长度
    var lenValidate = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv) {
        // 验证长度
        var maxLen = inputLab.attr('maxlength');
        // maxLen表达式不为空
        if (maxLen) {
            // 进行长度验证
            if (inputLabValue.length > maxLen) {
                // 验证长度超范围时候需要修改样式
                validateFailed(iconsDiv, inputLab, showValidMsgDiv, "输入的长度超出范围.....");
                return false;
            }
        }
        return true;
    };

    // 最大值验证
    var maxValueValidate = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv, selfValid) {
        // 验证最大值
        var max = inputLab.attr('max');
        var maxNum = parseInt(max);
        var compareNum = parseInt(inputLabValue);

        if (max) {
            // 进行最大值验证
            if (compareNum > maxNum) {
                // 超范围时候需要修改样式，并提示信息
                validateFailed(iconsDiv, inputLab, showValidMsgDiv, "输入值超出最大值范围.....");
                return false;
            }
        }
        return true;

    }

    // 自定义验证
    var selfValidate = function (inputLab, inputLabValue, iconsDiv, showValidMsgDiv, selfValid,form) {
        var rs = true;

        // 后台校验,比如名称不能重复，此时，需要查询数据库
        var name = inputLab.attr("name");
        var tip = undefined;
        // 判断是否需要做后台验证
        var selfValidArr = selfValid && selfValid[name];
        if(selfValidArr && selfValidArr.length>0) {
            $.each(selfValidArr, function(i,v){
                var fun_ = v["fun"];
                var type_ = v["type"];
                if (fun_) {
                    // ajax以同步形式做后台验证
                    tip = $oldData && (inputLabValue == $oldData[name]) && type_==1 ? undefined : fun_(inputLabValue);
                    if (tip) {
                        validateFailed(iconsDiv, inputLab, showValidMsgDiv, tip);
                        return rs=false;
                    }
                }
            });
        }
        return rs;
    }

    var validateSuccess = function (iconsDiv, inputLab, showValidMsgDiv) {
        // 修改icons，
        iconsDiv.removeClass('glyphicon-remove')
            .removeClass('ew-feedbakIcon-error')
            .addClass('glyphicon-ok')
            .addClass('ew-feedbakIcon-ok');

        // 修改input标签中显示的样式，
        inputLab.removeClass("ew-form-input-error")
            .addClass("ew-form-input-ok");
        //  清掉错误信息提示内容
        showValidMsgDiv.text("");

    }

    var validateFailed = function (iconsDiv, inputLab, showValidMsgDiv, msg) {
        // 提示错误信息，改图标div显示的样式，
        iconsDiv.removeClass('glyphicon-ok')
            .addClass('glyphicon-remove')
            .addClass('ew-feedbakIcon-error');

        // input标签样式改动
        inputLab.removeClass("ew-form-input-ok")
            .addClass("ew-form-input-error");

        // 验证出错时的显示信息
        showValidMsgDiv.addClass('ew-valid-feedback-error')
            .text(msg);
    }

    var validSigInput = function (inputLab, selfValid,form) {
        // 获取input 中的value值
        var inputLabValue = inputLab.val();

        // 寻找input 相邻的下一个div，即是显示小图标的div
        var iconsDiv = inputLab.find("~div");

        // 显示验证信息的小Div
        var showValidMsgDiv = inputLab.parents('div:first').find("~div:first");

        // 验证空值与是否包含特殊字符
        var bool = nullValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv)?
                specCharacterValid(inputLab, inputLabValue, iconsDiv, showValidMsgDiv):false;
        if (bool) {
            // 验证 required 和Patter 正则表达式
            bool = requiredValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv) ?
                regexValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv) : false;

            if (bool) {
                // 验证输入的字符串长度 和最大值验证
                bool = lenValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv) ?
                    maxValueValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv, selfValid) : false;

                if (bool) {
                    // 自定义验证
                    bool = selfValidate(inputLab, inputLabValue, iconsDiv, showValidMsgDiv, selfValid,form);
                    if (bool) {
                        // 都验证通过之后，代表通过验证
                        validateSuccess(iconsDiv, inputLab, showValidMsgDiv);
                    }
                }
            }
        }
        return bool;
    }

    return {
        commitForm: function (form, selfValid, commitFaildMsg) {
            var formEles = form.find("input.ew-validate,select.ew-validate,textarea.ew-validate");
            var rs = true;
            $.each(formEles, function () {
                if (!validSigInput($(this), selfValid,form,$oldData)) {
                    rs = false;
                }
            });
            return rs;
        },
        // Validate include commonValidate and selfValidate
        validForm: function (form, selfValid, oldData) {
            // 解除input绑定
            form.off("focusout", "input.ew-validate,select.ew-validate,textarea.ew-validate");
            $oldData = oldData;
            form.find("input.ew-validate,select.ew-validate,textarea.ew-validate").focusout(function () {
                validSigInput($(this), selfValid,form);
            });
        }
    };
})();

//  定义温馨提示函数，提示内容
function bindTooltipEvent() {
    $(".ew-tooltip").tooltip({
        selector: "[data-toggle=tooltip],[data-toggle=modal]",
        container: "body"
    });
}

function copyrightMove() {
    if ($(".ew-copyright").html()) {
        var h = $("body").height();
        var w = $("body").width();
        $(".ew-copyright").offset({top: h - 20, left: w * 0.5});
    }
}

function selectSelVal(selectEle, val) {
    if (val) {
        var isMultiple = selectEle.attr("multiple");
        if (isMultiple) {
            var isArray = Array.isArray(val);
            // Is array?
            if (!isArray) {
                var arrreg = /^\[('|")?[\w\d]+\1(?:,('|")?[\w\d]+\2)*\]$/g;
                if (/*typeof(val)=="string" && */arrreg.test(val)) {
                    val = eval(val);
                    isArray = true;
                }
            }
            // Is comma separation
            if (!isArray) {
                arrreg = /^('|")?[\w\d]+\1(?:,('|")?[\w\d]+\2)*$/g;
                if (arrreg.test(val)) {
                    val = eval("[" + val + "]");
                    isArray = true;
                }
            }

            if (isArray) {
                selectEle.val(val);
            }
        }
        else {
            selectEle.children("option[value='" + val + "']").attr("selected", "selected");
        }
    }
}

function oneFileSelect($src) {
    var file = $src[0].files[0];
    var fname = file ? file.name : 'Choose file...';

    var id = $src.attr("id");
    var flable = $src.next();
    var lable = undefined;
    if (flable.html()) {
        if (flable.attr("for") == id) {
            flable.text(fname);
            return;
        }
    }
    flable = $("label[for='" + id + "']");
    if (flable.length > 0) {
        $.each(flable, function () {
            $(this).text(fname);
        })
    }
}

var CleanInput = (function(){
    return {
        bindEvent:function(eles) {
            var temp = 0;
            eles.on("input",function(){
                temp=1;
                var val = $(this).val();
                if (val!=""){
                    $(this).next().attr("style","z-index:100");
                }else{
                    $(this).next().attr("style","z-index:1");
                }
            });
            eles.each(function(){
                if(temp==0) {
                    var val = $(this).val();
                    if (val != "") {
                        $(this).next().attr("style", "z-index:100");
                    } else {
                        $(this).next().attr("style", "z-index:1");
                    }
                }
            })
            /*if(temp==0){
                var val = eles.val();
                if (val!=""){
                    eles.next().attr("style","z-index:100");
                }else{
                    eles.next().attr("style","z-index:1");
                }
            }*/
        }
    }
})();

var IdxTip = (function(){
    var dfcss = "alert alert-dismissible ew-idx-alert";
    var tip = function(content,css,title) {
        var tipCssDiv = $("#idxModal .modal-content div:first");
        tipCssDiv.attr("class",dfcss);
        tipCssDiv.addClass(css);
        $("#idxTipTitle").html(title);
        $("#idxContent").html(content);
        $("#idxModal").modal("show");
    }

    return {
        // 多css以空格分隔
        tip: tip,
        tipsuc:function(content,title){
            tip(content,"alert-success",title);
        },
        tiperr:function(content,title){
            tip(content,"alert-danger",title);
        },
        tipwarn:function(content,title){
            tip(content,"alert-warning",title);
        }
    }
})();

/**
 *
 * @param divID：包围table的外层div
 */
function fixTableTH(divID) {
    var $divID = document.querySelector('#' + divID);

    function scrollHandle(e) {
        var scrollTop = this.scrollTop;
        this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
    }
    $divID.addEventListener('scroll', scrollHandle);
}

var SysCfg = (function(){
    var addrCfg = {
        port:null,
        scheme:"http",
        addr:null
    };
    var fullAddress = '';

    return {
        setAddrCfg: function(scheme,addr,port){
            addrCfg.scheme=scheme;
            addrCfg.addr=addr;
            addrCfg.port=port;
            fullAddress = scheme+'://'+addr+':'+port;
        },
        getFullAddress: function() {
            return fullAddress;
        }
    }
})();

var EWFileInput = (function(){
    return {
        init: function(settings) {
            var $input = $('input[type=file]');
            if ($input.length) {
                $input.fileinput(settings);
            }
        }
    }
})();


