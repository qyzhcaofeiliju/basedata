$(document).ready(function () {
    suitcaseFunccategory.bindEvent();
    suitcaseFunccategory.fixEvent();
    bindTooltipEvent();
});
var suitcaseFunccategory = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/validIsExist",
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
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'funCategory':'" + inputValue + "'}");
            }
        }]
    }

    var cache = {
        "cached": false,
        "mod": null,
        "funId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#suitcaseFunccategory_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#suitcaseFunccategory_UptAdd_OptModal").modal("show");
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
            var oldData = $("#suitcaseFunccategory_UptAddForm").serializeObject();
            FormValidator.validForm($("#suitcaseFunccategory_UptAddForm"), selfValid,oldData);
        }
        var funId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["funId"] && cache["funId"] == funId) {
                $("#suitcaseFunccategory_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["funId"] = funId;
                __loadEditData(funId,callback);
            }
        }
        else {
            __loadEditData(funId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var funId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#suitcaseFunccategory_OKBtn").data("opt", {"tr": delTr, "funId": funId});
        $("#suitcaseFunccategory_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#suitcaseFunccategory_AddBtnExe").show();
        $("#suitcaseFunccategory_UptBtnExe").hide();
        $("#suitcaseFunccategory_UptAddTitle").text("新增功能分类信息");

        $("#suitcaseFunccategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (funId,callback) {
        $("#suitcaseFunccategory_AddBtnExe").hide();
        $("#suitcaseFunccategory_UptBtnExe").show();
        $("#suitcaseFunccategory_UptAddTitle").text("编辑功能分类信息");

        if (!funId) funId = '';
        $("#suitcaseFunccategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/edit/" + funId, function (data) {
            $(this).html(data);
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
        var formData = JSON.stringify($("#find_suitcaseFunccategory").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td hidden>" + list[i].funId + "</td>" +
                        "<td>" + list[i].funName + "</td>" +
                        "<td>" + list[i].funCategory + "</td>" +
                        "<td class='ew-tooltip'>" +
                            "<button name='suitcaseFunccategoryBtnVU' data=" + list[i].funId + " type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#suitcaseFunccategory_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>" +
                        "</td>"+
                        "<td class='ew-tooltip'>" +
                            "<button name='suitcaseFunccategoryBtnDel' type='button' data=" + list[i].funId + " class='btn btn-danger fa fa-trash-o' data-toggle='modal' data-placement='bottom' data-original-title='删除'></button>" +
                        "</td>"+
                    "</tr>"

                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='suitcaseFunccategoryPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='suitcaseFunccategoryPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#suitcaseFunccategory_PageList").html(pageListHtml);
                suitcaseFunccategory.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#suitcaseFunccategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#suitcaseFunccategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.funName + "，分类：" + dataObj.funCategory;

        // 隐藏（新增/编辑）模态框
        $("#suitcaseFunccategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/save",
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

    var updateFunExe = function (){
        // Object
        var dataObj = $("#suitcaseFunccategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.funName + "，分类'：" + dataObj.funCategory;
        var funId = $(":input[name=funId]").val();

        // 隐藏（新增/编辑）模态框
        $("#suitcaseFunccategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/update" + funId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + funId + "']").parents("tr:first");
                tr.find("td:eq(1)").text(dataObj["funName"]);
                tr.find("td:eq(2)").text(dataObj["funCategory"]);
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据失败，请再次尝试 ！";
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }
    // update data to database
    var updateFun = function () {
        if (FormValidator.commitForm($("#suitcaseFunccategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // delete data to database after Confirm Delete Operation
    var deleteFun = function () {
        // 隐藏删除操作时弹出的确认模态框
        $("#suitcaseFunccategory_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#suitcaseFunccategory_OKBtn").data("opt");
        // 获取funId
        var funId = opt["funId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var funName = delTr.find("td:eq(1)").text();
        var funCategory = delTr.find("td:eq(2)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 名称：" + funName + "，分类：" + funCategory;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseFunccategory/delete" + funId,
            method: "DELETE",
            success: function (data) {
                //判断是否执行删除操作，如果没有执行，根据错误信息提示。
                if(data==1){
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据成功 ！";
                    // 删除数据成功之后修改样式和添加提示信息
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                    // delTr.remove;
                    var curPage = $("ul.pagination li.active a").text();
                    pageFun(curPage);
                }
                if(data==2){
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据失败，数据被使用，不可删除 ！";
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
            $("#suitcaseFunccategoryPageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#suitcaseFunccategoryPagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='suitcaseFunccategoryBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='suitcaseFunccategoryBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_suitcaseFunccategory]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#suitcaseFunccategory_AddBtn").click(addFun);

            $("#suitcaseFunccategory_Close").click(cancelFun);

            // Add
            $("#suitcaseFunccategory_AddBtnExe").click(saveFun);

            // Update
            $("#suitcaseFunccategory_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#suitcaseFunccategory_OKBtn").click(deleteFun);

            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //表头固定
            fixTableTH("suitcaseFunccategory-cont");
        }
    }
})();