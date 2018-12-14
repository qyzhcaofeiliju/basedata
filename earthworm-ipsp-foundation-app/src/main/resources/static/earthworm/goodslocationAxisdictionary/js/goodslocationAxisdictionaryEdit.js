
/*goodslocationAxisdictionaryEdit页面的js*/
$(function () {
    /*关于axisdSuitcaseuniquecode 的change事件*/
    $("#goodslocationAxisdictionary_UptAddForm [name=axisdSuitcaseuniquecode]").change(function () {
        var axisdSuitcaseuniquecode = $(" #goodslocationAxisdictionary_UptAddForm [name=axisdSuitcaseuniquecode]").val();
        if (axisdSuitcaseuniquecode != '') {
            var vwSuitcaseProfileStr = $("#goodslocationAxisdictionary_UptAddForm [name=axisdSuitcaseuniquecode] option:selected").attr("data");
            var vwSuitcaseProfile = JSON.parse(vwSuitcaseProfileStr);// 转换为对象
            var funName = vwSuitcaseProfile.funName; // 周转箱功能分类名称
            var proFunccategory = vwSuitcaseProfile.proFunccategory; // 周转箱功能分类
            // 选择了周转箱之后，axisdSuitcasetype对应的显示 文本内容和input中的传输的value值
            $("#axisdSuitcasetypeText").text(funName);// 显示的内容;
            $("#axisdSuitcasetypeValue").val(proFunccategory);// 隐藏的input 中的Value值，用于传输到后台;
        } else {
            $("#axisdSuitcasetypeText").text("---请先选择周转箱---");// 显示的内容;
        }
    });
})