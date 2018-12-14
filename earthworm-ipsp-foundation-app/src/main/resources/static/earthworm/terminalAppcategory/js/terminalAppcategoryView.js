$(document).ready(function () {
    terminalAppcategory.bindEvent();
    terminalAppcategory.fixEvent();
    bindTooltipEvent();
});
var terminalAppcategory = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/validIsExist",
            method: "GET",
            data: {"data": jsonStrData},
            // 改为同步验证
            async: false,
            success: function (data) {
                rs = data;
            },
            error: function () {
                rs = false;
            }
        });
        return rs ? undefined : "输入值已经被使用....";
    }

    //自定义验证，此处验证名称和分类是否重复
    var selfValid = {
        "appName": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'appName':'" + inputValue + "'}");
            }
        }],
        "appCategory": [{
            "type": 1,
            "fun": function (inputValue) {
                return checkUnique("{'appCategory':'" + inputValue + "'}");
            }
        }],
    }

    var cache = {
        "cached": false,
        "mod": null,
        "appId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#terminalAppcategory_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#terminalAppcategory_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 1;
                __loadAddData(callback);
            }
        }
        else {
            __loadAddData(callback);
        }
    };

    var editFun = function () {

        var callback = function () {
            var oldData = $("#terminalAppcategory_UptAddForm").serializeObject();
            FormValidator.validForm($("#terminalAppcategory_UptAddForm"), selfValid,oldData);
        }

        var appId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["appId"] && cache["appId"] == appId) {
                $("#terminalAppcategory_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["appId"] = appId;
                __loadEditData(appId,callback);
            }
        }
        else {
            __loadEditData(appId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var appId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#terminalAppcategory_OKBtn").data("opt", {"tr": delTr, "appId": appId});
        $("#terminalAppcategory_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#terminalAppcategory_AddBtnExe").show();
        $("#terminalAppcategory_UptBtnExe").hide();
        $("#terminalAppcategory_UptAddTitle").text("新增应用分类信息");

        $("#terminalAppcategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (appId,callback) {
        $("#terminalAppcategory_AddBtnExe").hide();
        $("#terminalAppcategory_UptBtnExe").show();
        $("#terminalAppcategory_UptAddTitle").text("编辑应用分类信息");

        if (!appId) appId = '';
        $("#terminalAppcategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/edit/" + appId, function (data) {
            $(this).html(data);
            // 绑定要做验证的表单元素
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var pageFun = function (pageNum) {
        var pageSize = $("[name=selPageSize]").val();
        var pageParam = {pageNum: pageNum, pageSize: pageSize};
        var pageData = JSON.stringify(pageParam);
        var formData = JSON.stringify($("#find_terminalAppcategory").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].appName + "</td>" +
                        "<td>" + list[i].appCategory + "</td>" +
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='terminalAppcategoryBtnVU'"+
                                    "class='btn btn-primary fa fa-edit'"+
                                    "data="+list[i].appId +" data-toggle='modal'"+
                                    "data-target='#terminalAppcategory_UptAdd_OptModal'"+
                                    "data-placement='bottom' data-original-title='编辑'>"+
                            "</button>"+
                        "</td>"+
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='terminalAppcategoryBtnDel'"+
                                    "class='btn btn-danger fa fa-trash-o'"+
                                    "data="+list[i].appId +" data-toggle='modal'"+
                                    "data-placement='bottom' data-original-title='删除'>"+
                            "</button>"+
                        "</td>"+
                    "</tr>"
                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='terminalAppcategoryPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='terminalAppcategoryPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#terminalAppcategory_PageList").html(pageListHtml);
                terminalAppcategory.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    //增加方法
    var saveFun = function () {
        if (FormValidator.commitForm($("#terminalAppcategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }
    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#terminalAppcategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.appName + "，分类：" + dataObj.appCategory;

        // 隐藏（新增/编辑）模态框
        $("#terminalAppcategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/save",
            method: "POST",
            data: {"data": jsonStrData},
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");
            },
            error: function (data) {
                // uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据失败，请再次添加 ！";
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据失败,失败原因："+data.responseText;
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示")
            }
        });
    }


    var  updateFun = function () {
        if (FormValidator.commitForm($("#terminalAppcategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // update data to database
    var updateFunExe = function () {
        // Object
        var dataObj = $("#terminalAppcategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.appName + "，分类：" + dataObj.appCategory;
        var appId = $(":input[name=appId]").val();

        // 隐藏（新增/编辑）模态框
        $("#terminalAppcategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/update" + appId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + appId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(dataObj["appName"]);
                tr.find("td:eq(1)").text(dataObj["appCategory"]);
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据失败，请再次尝试 ！";
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }

    // delete data to database after Confirm Delete Operation
    var deleteFun = function () {
        // 隐藏删除操作时弹出的确认模态框
        $("#terminalAppcategory_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#terminalAppcategory_OKBtn").data("opt");
        // 获取appId
        var appId = opt["appId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var appName = delTr.find("td:eq(0)").text();
        var appCategory = delTr.find("td:eq(1)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 名称：" + appName + "，分类：" + appCategory;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalAppcategory/delete" + appId,
            method: "DELETE",
            success: function (data) {
                if(data == 1){
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据成功 ！";
                    // 删除数据成功之后修改样式和添加提示信息
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                    var curPage = $("ul.pagination li.active a").text();
                    pageFun(curPage);
                }else {
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除失败，设备表中关联了此条数据 ！";
                    // 删除数据失败之后修改样式和添加提示信息
                    IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
                }
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据失败，请再次尝试 ！";
                // 删除数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }

    // 清空查询条件输入框的内容
    var findRemove = function(){
        $(this).prev().val("");
        $(this).attr("style","z-index:1");
    }

    return {
        bindEvent: function () {
            //分页
            $("a[name=pages]").click(function () {
                pageFun($(this).text());
            });
            //下一页
            $("#terminalAppcategoryPageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#terminalAppcategoryPagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='terminalAppcategoryBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='terminalAppcategoryBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_terminalAppcategory]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#terminalAppcategory_AddBtn").click(addFun);

            $("#terminalAppcategory_Close").click(cancelFun);

            // Add
            $("#terminalAppcategory_AddBtnExe").click(saveFun);

            // Update
            $("#terminalAppcategory_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#terminalAppcategory_OKBtn").click(deleteFun);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //固定表头
            fixTableTH("termianlAppcategory-cont")
        }
    }
})();