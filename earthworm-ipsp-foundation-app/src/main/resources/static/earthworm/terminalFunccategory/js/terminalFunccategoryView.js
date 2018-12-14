$(document).ready(function () {
    terminalFunccategory.bindEvent();
    terminalFunccategory.fixEvent();
    bindTooltipEvent();
});
var terminalFunccategory = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/validIsExist",
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
        "funName": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'funName':'" + inputValue + "'}");
            }
        }],
        "funCategory": [{
            "type": 1,
            "fun": function (inputValue) {
                return checkUnique("{'funCategory':'" + inputValue + "'}");
            }
        }],
    }

    var cache = {
        "cached": false,
        "mod": null,
        "funId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#terminalFunccategory_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#terminalFunccategory_UptAdd_OptModal").modal("show");
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
            var oldData = $("#terminalFunccategory_UptAddForm").serializeObject();
            FormValidator.validForm($("#terminalFunccategory_UptAddForm"), selfValid, oldData);
        }

        var funId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["funId"] && cache["funId"] == funId) {
                $("#terminalFunccategory_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["funId"] = funId;
                __loadEditData(funId, callback);
            }
        }
        else {
            __loadEditData(funId, callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var funId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#terminalFunccategory_OKBtn").data("opt", {"tr": delTr, "funId": funId});
        $("#terminalFunccategory_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#terminalFunccategory_AddBtnExe").show();
        $("#terminalFunccategory_UptBtnExe").hide();
        $("#terminalFunccategory_UptAddTitle").text("新增功能分类信息");

        $("#terminalFunccategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/edit", function (data) {
            $(this).html(data);
            callback();
            // 编辑页面清空按钮,
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (funId, callback) {
        $("#terminalFunccategory_AddBtnExe").hide();
        $("#terminalFunccategory_UptBtnExe").show();
        $("#terminalFunccategory_UptAddTitle").text("编辑功能分类信息");

        if (!funId) funId = '';
        $("#terminalFunccategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/edit/" + funId, function (data) {
            $(this).html(data);
            callback();
            // 编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var pageFun = function (pageNum) {
        var pageSize = $("[name=selPageSize]").val();
        var pageParam = {pageNum: pageNum, pageSize: pageSize};
        var pageData = JSON.stringify(pageParam);
        var formData = JSON.stringify($("#find_terminalFunccategory").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].funName + "</td>" +
                        "<td>" + list[i].funCategory + "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='terminalFunccategoryBtnVU'" +
                        "class='btn btn-primary fa fa-edit'" +
                        "data=" + list[i].funId + " data-toggle='modal'" +
                        "data-target='#terminalFunccategory_UptAdd_OptModal'" +
                        "data-placement='bottom' data-original-title='编辑'>" +
                        "</button>" +
                        "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='terminalFunccategoryBtnDel'" +
                        "class='btn btn-danger fa fa-trash-o'" +
                        "data=" + list[i].funId + " data-toggle='modal'" +
                        "data-placement='bottom' data-original-title='删除'>" +
                        "</button>" +
                        "</td>" +
                        "</tr>"
                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='terminalFunccategoryPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='terminalFunccategoryPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#terminalFunccategory_PageList").html(pageListHtml);
                terminalFunccategory.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    //增加方法
    var saveFun = function () {
        if (FormValidator.commitForm($("#terminalFunccategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#terminalFunccategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);
        var uptAdd_Back_Msg = "{ 名称：" + dataObj.funName + "，分类：" + dataObj.funCategory;

        // 隐藏（新增/编辑）模态框
        $("#terminalFunccategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/save",
            method: "POST",
            data: {"data": jsonStrData},
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");
            },
            error: function (data) {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据失败,出错原因：" + data.responseText;
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }

    // 执行更新操作之前加上前台验证
    var updateFun = function () {
        if (FormValidator.commitForm($("#terminalFunccategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // update data to database
    var updateFunExe = function () {
        // Object
        var dataObj = $("#terminalFunccategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.funName + "，分类：" + dataObj.funCategory;
        var funId = $(":input[name=funId]").val();

        // 隐藏（新增/编辑）模态框
        $("#terminalFunccategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/update" + funId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + funId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(dataObj["funName"]);
                tr.find("td:eq(1)").text(dataObj["funCategory"]);
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
        $("#terminalFunccategory_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#terminalFunccategory_OKBtn").data("opt");
        // 获取funId
        var funId = opt["funId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var funName = delTr.find("td:eq(0)").text();
        var funCategory = delTr.find("td:eq(1)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 名称：" + funName + "，分类：" + funCategory;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalFunccategory/delete" + funId,
            method: "DELETE",
            success: function (data) {
                if( data == 1) {
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据成功 ！";
                    // 删除数据成功之后修改样式和添加提示信息
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                    // delTr.remove;
                    var curPage = $("ul.pagination li.active a").text();
                    pageFun(curPage);
                }else {
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除失败，设备表中关联此数据 ！";
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
    var findRemove = function () {
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
            $("#terminalFunccategoryPageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#terminalFunccategoryPagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='terminalFunccategoryBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='terminalFunccategoryBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_terminalFunccategory]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#terminalFunccategory_AddBtn").click(addFun);

            $("#terminalFunccategory_Close").click(cancelFun);

            // Add
            $("#terminalFunccategory_AddBtnExe").click(saveFun);

            // Update
            $("#terminalFunccategory_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#terminalFunccategory_OKBtn").click(deleteFun);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //固定表头
            fixTableTH("terminalFunccategory-cont");
        }
    }
})();