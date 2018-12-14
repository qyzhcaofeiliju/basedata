$(document).ready(function () {
    moveshelfLocation.bindEvent();
    moveshelfLocation.fixEvent();
    bindTooltipEvent();
});
var moveshelfLocation = (function () {

    var cache = {
        "cached": false,
        "mod": null,
        "mslId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#moveshelfLocation_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#moveshelfLocation_UptAdd_OptModal").modal("show");
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
        var mslId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["mslId"] && cache["mslId"] == mslId) {
                $("#moveshelfLocation_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["mslId"] = mslId;
                __loadEditData(mslId);
            }
        }
        else {
            __loadEditData(mslId);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var mslId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#moveshelfLocation_OKBtn").data("opt", {"tr": delTr, "mslId": mslId});
        $("#moveshelfLocation_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#moveshelfLocation_AddBtnExe").show();
        $("#moveshelfLocation_UptBtnExe").hide();
        $("#moveshelfLocation_UptAddTitle").text("新增移动货架位置");

        $("#moveshelfLocation_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/moveshelfLocation/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (mslId) {
        $("#moveshelfLocation_AddBtnExe").hide();
        $("#moveshelfLocation_UptBtnExe").show();
        $("#moveshelfLocation_UptAddTitle").text("编辑移动货架位置");

        if (!mslId) mslId = '';
        $("#moveshelfLocation_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/moveshelfLocation/edit/" + mslId, function (data) {
            $(this).html(data);
        });
    }

    var pageFun = function (pageNum) {
        var pageSize = $("[name=selPageSize]").val();
        var pageParam = {pageNum: pageNum, pageSize: pageSize};
        var pageData = JSON.stringify(pageParam);
        var formData = JSON.stringify($("#find_moveshelfLocation").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/moveshelfLocation/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td hidden>" + list[i].mslId + "</td>" +
                        "<td>" + list[i].mslEquId + "</td>" +
                        "<td>" + list[i].mslEquNumber + "</td>" +
                        "<td>" + list[i].mslCurStation + "</td>" +
                        "<td>" + list[i].mslPreStation + "</td>" +
                        "<td>" +
                        "<div class='ew-tooltip'>" +
                        "<button name='moveshelfLocationBtnVU' data=" + list[i].mslId + " type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#moveshelfLocation_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>\r\n" +
                        "<button name='moveshelfLocationBtnDel' type='button' data=" + list[i].mslId + " class='btn btn-danger fa fa-trash-o' data-toggle='modal' data-placement='bottom' data-original-title='删除'></button>" +
                        "</div>" +
                        "</td>"
                    "</tr>"

                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='moveshelfLocationPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='moveshelfLocationPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#moveshelfLocation_PageList").html(pageListHtml);
                moveshelfLocation.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    // CRUD操作成功之后，需要显示的信息
    var crudBackMsgSuccesFun = function (uptAdd_Back, uptAdd_Back_Msg) {
        // 修改显示CRUD操作成功的样式和信息
        uptAdd_Back.show();
        uptAdd_Back.removeAttr("hidden");
        uptAdd_Back.removeClass('alert-danger').addClass('alert-success')
        uptAdd_Back.find('span').text(uptAdd_Back_Msg);

    }

    // CRUD操作失败之后，需要显示的信息
    var crudBackMsgFailedFun = function (uptAdd_Back, uptAdd_Back_Msg) {
        // 修改显示CRUD操作成功的样式和信息
        uptAdd_Back.show();
        uptAdd_Back.removeAttr("hidden");
        uptAdd_Back.removeClass('alert-success').addClass('alert-danger')
        uptAdd_Back.find('span').text(uptAdd_Back_Msg);

    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#moveshelfLocation_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#moveshelfLocation_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back = $("#moveshelfLocation_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 编号：" + dataObj.mslEquNumber + "，上一站点：" + dataObj.mslPreStation;

        // 隐藏（新增/编辑）模态框
        $("#moveshelfLocation_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/moveshelfLocation/save",
            method: "POST",
            data: {"data": jsonStrData},
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                crudBackMsgSuccesFun(uptAdd_Back, uptAdd_Back_Msg);
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据失败，请再次添加 ！";
                // 保存数据失败之后修改样式和添加提示信息
                crudBackMsgFailedFun(uptAdd_Back, uptAdd_Back_Msg);
            }
        });
    }

    // update data to database
    var updateFun = function () {
        // Object
        var dataObj = $("#moveshelfLocation_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back = $("#moveshelfLocation_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 编号：" + dataObj.mslEquNumber + "，上一站点：" + dataObj.mslPreStation;
        var mslId = $(":input[name=mslId]").val();

        // 隐藏（新增/编辑）模态框
        $("#moveshelfLocation_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/moveshelfLocation/update" + mslId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                crudBackMsgSuccesFun(uptAdd_Back, uptAdd_Back_Msg);

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + mslId + "']").parents("tr:first");
                tr.find("td:eq(1)").text(dataObj["mslEquId"]);
                tr.find("td:eq(2)").text(dataObj["mslEquNumber"]);
                tr.find("td:eq(3)").text(dataObj["mslCurStation"]);
                tr.find("td:eq(4)").text(dataObj["mslPreStation"]);
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据失败，请再次尝试 ！";
                // 保存数据失败之后修改样式和添加提示信息
                crudBackMsgFailedFun(uptAdd_Back, uptAdd_Back_Msg);
            }
        });
    }

    // delete data to database after Confirm Delete Operation
    var deleteFun = function () {
        // 隐藏删除操作时弹出的确认模态框
        $("#moveshelfLocation_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#moveshelfLocation_OKBtn").data("opt");
        // 获取mslId
        var mslId = opt["mslId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var mslEquNumber = delTr.find("td:eq(2)").text();
        var mslPreStation = delTr.find("td:eq(4)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back = $("#moveshelfLocation_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 编号：" + mslEquNumber + "，上一站点：" + mslPreStation;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/moveshelfLocation/delete" + mslId,
            method: "DELETE",
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据成功 ！";
                // 删除数据成功之后修改样式和添加提示信息
                crudBackMsgSuccesFun(uptAdd_Back, uptAdd_Back_Msg);

                // delTr.remove;
                var curPage = $("ul.pagination li.active a").text();
                pageFun(curPage);
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据失败，请再次尝试 ！";
                // 删除数据失败之后修改样式和添加提示信息
                crudBackMsgFailedFun(uptAdd_Back, uptAdd_Back_Msg);
            }
        });
    }

    return {
        bindEvent: function () {
            //分页
            $("a[name=pages]").click(function () {
                pageFun($(this).text());
            });
            //下一页
            $("#moveshelfLocationPageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#moveshelfLocationPagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='moveshelfLocationBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='moveshelfLocationBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            $("#moveshelfLocation_UptAdd_Back button").click(function (e) {
                $("#moveshelfLocation_UptAdd_Back").hide();
                //阻止默认事件
                return false;
            });

            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_moveshelfLocation]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#moveshelfLocation_AddBtn").click(addFun);

            $("#moveshelfLocation_Close").click(cancelFun);

            // Add
            $("#moveshelfLocation_AddBtnExe").click(saveFun);

            // Update
            $("#moveshelfLocation_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#moveshelfLocation_OKBtn").click(deleteFun);


        }
    }
})();