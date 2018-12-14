$(document).ready(function () {
    suitcaseProfileEdit.fixEvent();
});
var suitcaseProfileEdit = (function(){
    var uptFun = function(){
        if ($(this).val()=="false"){
            $("[name=proIspartition][value='true']").removeAttr("checked");
            $("[name=proIspartition][value='false']").attr("checked",true);
            $("[name=proVerticalamount]").val("0");
            $("[name=proVerticalamount]").attr("readonly",true);
            $("[name=proHorizontalamount]").val("0");
            $("[name=proHorizontalamount]").attr("readonly",true);
        }else{
            $("[name=proIspartition][value='false']").removeAttr("checked");
            $("[name=proIspartition][value='true']").attr("checked",true);
            $("[name=proVerticalamount]").val("");
            $("[name=proVerticalamount]").attr("readonly",false);
            $("[name=proHorizontalamount]").val("");
            $("[name=proHorizontalamount]").attr("readonly",false);
        }
    }
    var updateProIspartition = function(){
        var val = $("[name=proVerticalamount]").val();
        var val2 = $("[name=proHorizontalamount]").val();
        if (val==0&&val2==0){
            var radioTrue = $("[name=proIspartition][value='true']").get(0);
            var radioFalse = $("[name=proIspartition][value='false']").get(0);
            radioTrue.checked=false;
            radioFalse.checked=true;
        }else{
            var radioTrue = $("[name=proIspartition][value='true']").get(0);
            var radioFalse = $("[name=proIspartition][value='false']").get(0);
            radioTrue.checked=true;
            radioFalse.checked=false;
        }
    }

    return {
        fixEvent: function() {
            //是否分区内容改变触发，用来改变垂直和水平的值
            $("[name=proIspartition]").change(uptFun);
            //水平或垂直失去焦点触发，改变是否分区值
            $("[name=proVerticalamount]").blur(updateProIspartition);
            $("[name=proHorizontalamount]").blur(updateProIspartition);
        }
    }
})();