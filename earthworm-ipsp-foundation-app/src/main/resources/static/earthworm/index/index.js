var Idx = (function () {
    return {
        bindEvent: function () {
            // idxModal 模态框的关闭按钮
            $("#btnIdxModal").click(function () {
                // 点击index页面的 idxModal 模态框的关闭按钮，就隐藏该提示模态框
                $("#idxModal").modal("hide");
                // 既可以阻止默认事件的发生，也可以阻止事件传递。
                return false;
            });

            // 点击菜单栏
            $("#side-menu a[href != '#']").click(function (e) {
                $.ajax({
                    url: SysCfg.getFullAddress()+$(this).attr("href"),
                    success: function (data) {
                        /*加载数据*/
                        $("#page-wrapper").html(data);
                        /*获取加载进来页面的js文件，存在js文件就加载，不存在就不走下面的if方法*/
                        var script = $("#page-wrapper input[name='script']").val();
                        if(script&&script!='') {
                            if(Array.isArray(script)) {
                                $.each(script, function(i,v){
                                    $.getScript(v);
                                });
                            }
                            else
                                $.getScript(script);
                        }
                    },
                    error: function (arg1, arg2, arg3) {
                        // 显示模态框
                        // 并修改alert模态框中的内容
                        IdxTip.tiperr(arg1.responseText,"Warning!")
                    }
                });

                // 如果存在默认事件，就阻止，否则返回false值
                if (e && e.preventDefault()) {
                    e.preventDefault();
                }
                else {
                    window.event.returnValue = false;
                }
                return false;
            });
        }
    }
})();

$(function () {
    Idx.bindEvent();
});