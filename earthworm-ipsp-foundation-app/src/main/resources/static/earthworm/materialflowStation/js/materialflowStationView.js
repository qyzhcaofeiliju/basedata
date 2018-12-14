$(document).ready(function () {
    materialflowStation.bindEvent();
    materialflowStation.fixEvent();
    bindTooltipEvent();
});
var materialflowStation = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/materialflowStation/validIsExist",
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
        "staName": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'staName':'" + inputValue + "'}");
            }
        }],
        "staNumber": [{
            "type": 1,
            "fun": function (inputValue) {
                return checkUnique("{'staNumber':'" + inputValue + "'}");
            }
        }],
    }

    var cache = {
        "cached": false,
        "mod": null,
        "staId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#materialflowStation_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#materialflowStation_UptAdd_OptModal").modal("show");
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
            var oldData = $("#materialflowStation_UptAddForm").serializeObject();
            FormValidator.validForm($("#materialflowStation_UptAddForm"), selfValid,oldData);
        }

        var staId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["staId"] && cache["staId"] == staId) {
                $("#materialflowStation_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["staId"] = staId;
                __loadEditData(staId, callback);
            }
        }
        else {
            __loadEditData(staId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var staId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#materialflowStation_OKBtn").data("opt", {"tr": delTr, "staId": staId});
        $("#materialflowStation_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#materialflowStation_AddBtnExe").show();
        $("#materialflowStation_UptBtnExe").hide();
        $("#materialflowStation_UptAddTitle").text("新增物流站点信息");

        $("#materialflowStation_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/materialflowStation/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (staId,callback) {
        $("#materialflowStation_AddBtnExe").hide();
        $("#materialflowStation_UptBtnExe").show();
        $("#materialflowStation_UptAddTitle").text("编辑物流站点信息");

        if (!staId) staId = '';
        $("#materialflowStation_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/materialflowStation/edit/" + staId, function (data) {
            $(this).html(data);
            // 绑定要验证的元素
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
        var formData = JSON.stringify($("#find_materialflowStation").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/materialflowStation/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].staName + "</td>" +
                        "<td>" + list[i].staNumber + "</td>" +
                        "<td>" + list[i].staX + "</td>" +
                        "<td>" + list[i].staY + "</td>" +
                        "<td>" + list[i].staZ + "</td>" +
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='materialflowStationBtnVU'"+
                                    "class='btn btn-primary fa fa-edit'"+
                                    "data="+list[i].staId +" data-toggle='modal'"+
                                    "data-target='#materialflowStation_UptAdd_OptModal'"+
                                    "data-placement='bottom' data-original-title='编辑'>"+
                            "</button>"+
                        "</td>"+
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='materialflowStationBtnDel'"+
                                    "class='btn btn-danger fa fa-trash-o'"+
                                    "data="+list[i].staId +" data-toggle='modal'"+
                                    "data-placement='bottom' data-original-title='删除'>"+
                            "</button>"+
                        "</td>"+
                    "</tr>"

                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='materialflowStationPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='materialflowStationPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#materialflowStation_PageList").html(pageListHtml);
                materialflowStation.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    // 执行保存之前先要做前台校验
    var saveFun = function () {
        if (FormValidator.commitForm($("#materialflowStation_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#materialflowStation_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.staName + "，编号：" + dataObj.staNumber;

        // 隐藏（新增/编辑）模态框
        $("#materialflowStation_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/materialflowStation/save",
            method: "POST",
            data: {"data": jsonStrData},
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据失败，请再次添加 ！";
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }

    // 执行更新操作之前加上前台验证
    var updateFun = function () {
        if (FormValidator.commitForm($("#materialflowStation_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // update data to database
    var updateFunExe = function () {
        // Object
        var dataObj = $("#materialflowStation_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.staName + "，编号：" + dataObj.staNumber;
        var staId = $(":input[name=staId]").val();

        // 隐藏（新增/编辑）模态框
        $("#materialflowStation_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/materialflowStation/update" + staId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + staId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(dataObj["staName"]);
                tr.find("td:eq(1)").text(dataObj["staNumber"]);
                tr.find("td:eq(2)").text(dataObj["staX"]);
                tr.find("td:eq(3)").text(dataObj["staY"]);
                tr.find("td:eq(4)").text(dataObj["staZ"]);
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
        $("#materialflowStation_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#materialflowStation_OKBtn").data("opt");
        // 获取staId
        var staId = opt["staId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var staName = delTr.find("td:eq(0)").text();
        var staNumber = delTr.find("td:eq(1)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 名称：" + staName + "，编号：" + staNumber;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/materialflowStation/delete" + staId,
            method: "DELETE",
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据成功 ！";
                // 删除数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // delTr.remove;
                var curPage = $("ul.pagination li.active a").text();
                pageFun(curPage);
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
            $("#materialflowStationPageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#materialflowStationPagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='materialflowStationBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='materialflowStationBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_materialflowStation]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#materialflowStation_AddBtn").click(addFun);

            $("#materialflowStation_Close").click(cancelFun);

            // Add
            $("#materialflowStation_AddBtnExe").click(saveFun);

            // Update
            $("#materialflowStation_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#materialflowStation_OKBtn").click(deleteFun);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //固定表头
            fixTableTH("materialflowStation-cont");

        }
    }
})();