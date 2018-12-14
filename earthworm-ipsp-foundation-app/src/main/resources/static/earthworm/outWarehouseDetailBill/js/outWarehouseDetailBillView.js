$(document).ready(function () {
    outWarehouseDetailBill.bindEvent();
    outWarehouseDetailBill.fixEvent();
    bindTooltipEvent();
    /*ewTimePicker.timePickerFun(["outwOutdatetime"]);*/
});
var outWarehouseDetailBill = (function () {
    // 校验生产日期与有效日期
    var checkDate = function () {
        var effDateInputLab = $("#outWarehouseDetailBill_UptAddForm input[name='outwEffectivedate']");
        var productDateInputLab = $("#outWarehouseDetailBill_UptAddForm input[name='outwProduceddate']");
        var effDate = effDateInputLab.val();
        var productDate = productDateInputLab.val();
        if (effDate != "" && productDate != "") {
            return effDate < productDate ? "有效日期不能小于生产日期" : undefined;
        }
    };

    var changeStateCheckDate = function () {
        $("#outWarehouseDetailBill_UptAddForm input[name='outwEffectivedate']").change(checkDate);
        $("#outWarehouseDetailBill_UptAddForm input[name='outwProduceddate']").change(checkDate);
    }

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/validIsExist",
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

    var selfValid = {
        "outwUniquecode": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'outwUniquecode':'" + inputValue + "'}");
            }
        }],
        "outwPrebillnumber": [{
            "type": 1,
            "fun": function (inputValue) {
                // 如果不需要做预出库，则不需要做预出库单编号的唯一性验证
                var outwIspreout = $("#outWarehouseDetailBill_UptAddForm [name=outwIspreout]:checked").val();
                if (outwIspreout != "0") { // 存在预出库的情况下，使预出库单编号输入框可以操作
                    return checkUnique("{'outwPrebillnumber':'" + inputValue + "'}");
                } else {
                    return undefined; // 如果不是预出库，则不用做唯一性验证
                }
            }
        }],
        // 验证生产日期和有效日期
        "outwEffectivedate": [{
            "type": 2,// type为2代表校验有效日期
            "fun": checkDate
        }, {
            "type": 3,// type为3代表change事件时候去触发校验生产日期和有效日期的函数
            "fun": changeStateCheckDate
        }],
        "outwProduceddate": [{
            "type": 2,
            "fun": checkDate
        }, {
            "type": 3,// type为3代表change事件时候去触发校验生产日期和有效日期的函数
            "fun": changeStateCheckDate
        }]
    }

    var cache = {
        "cached": false,
        "mod": null,
        "outwId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#outWarehouseDetailBill_UptAddForm"), selfValid);
        }

        var outwBillsnumber = $(this).attr("data");

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#outWarehouseDetailBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 1;
                __loadAddData(outwBillsnumber, callback);
            }
        }
        else {
            __loadAddData(outwBillsnumber, callback);
        }
    };

    var editFun = function () {

        var callback = function () {
            FormValidator.validForm($("#outWarehouseDetailBill_UptAddForm"), selfValid, $("#outWarehouseDetailBill_UptAddForm").serializeObject());
        }

        var outwId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["outwId"] && cache["outwId"] == outwId) {
                $("#outWarehouseDetailBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["outwId"] = outwId;
                __loadEditData(outwId, callback);
            }
        }
        else {
            __loadEditData(outwId, callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var outwId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#outWarehouseDetailBill_OKBtn").data("opt", {"tr": delTr, "outwId": outwId});
        $("#outWarehouseDetailBill_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (outwBillsnumber, callback) {
        $("#outWarehouseDetailBill_AddBtnExe").show();
        $("#outWarehouseDetailBill_UptBtnExe").hide();
        $("#outWarehouseDetailBill_UptAddTitle").text("新增出库单详情表");

        $("#outWarehouseDetailBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/detailAdd/" + outwBillsnumber, function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var __loadEditData = function (outwId, callback) {
        $("#outWarehouseDetailBill_AddBtnExe").hide();
        $("#outWarehouseDetailBill_UptBtnExe").show();
        $("#outWarehouseDetailBill_UptAddTitle").text("编辑出库单详情表");

        var outwBillsnumber = $("#outWarehouseDetailBill_AddBtn").attr("data");

        if (!outwId) outwId = '';
        $("#outWarehouseDetailBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/edit/" + outwBillsnumber + "/" + outwId, function (data) {
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
        var formData = JSON.stringify($("#find_outWarehouseDetailBill").serializeObject());
        var outwBillsnumber = $("#outWarehouseDetailBill_AddBtn").attr("data");
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/page",
            data: {pageData: pageData, formData: formData, outwBillsnumber: outwBillsnumber},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].outwBillsnumber + "</td>" +
                        "<td>" + list[i].outwModel + "</td>" +
                        "<td>" + list[i].outwCode + "</td>" +
                        "<td>" + list[i].outwUniquecode + "</td>" +
                        "<td>" + list[i].outwManufacturer + "</td>" +
                        "<td>" + list[i].outwUnit + "</td>" +
                        "<td>" + list[i].outwBatch + "</td>" +
                        "<td>" + EWDate.getFullYMD(list[i].outwEffectivedate) + "</td>" +
                        "<td>" + EWDate.getFullYMD(list[i].outwProduceddate) + "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='outWarehouseDetailBillBtnVU'" +
                        "class='btn btn-primary fa fa-edit'" +
                        "data=" + list[i].outwId + " data-toggle='modal'" +
                        "data-target='#outWarehouseDetailBill_UptAdd_OptModal'" +
                        "data-placement='bottom' data-original-title='编辑'>" +
                        "</button>" +
                        "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='outWarehouseDetailBillBtnDel'" +
                        "class='btn btn-danger fa fa-trash-o'" +
                        "data=" + list[i].outwId + " data-toggle='modal'" +
                        "data-placement='bottom' data-original-title='删除'>" +
                        "</button>" +
                        "</td>" +
                        "</tr>"
                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='outWarehouseDetailBillPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='outWarehouseDetailBillPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("#outWarehouseDetailBillTbody").html(tbodyHtml);
                $("#outWarehouseDetailBill_PageList").html(pageListHtml);
                outWarehouseDetailBill.bindEvent();
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
        if (FormValidator.commitForm($("#outWarehouseDetailBill_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#outWarehouseDetailBill_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back = $("#outWarehouseDetailBill_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 单据编号：" + dataObj.outwBillsnumber + "，物料码：" + dataObj.outwCode + ",批次：" + dataObj.outwBatch;

        // 隐藏（新增/编辑）模态框
        $("#outWarehouseDetailBill_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/save",
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

    var updateFun = function () {
        if (FormValidator.commitForm($("#outWarehouseDetailBill_UptAddForm"), selfValid, "通知")) {
            updateFunExe();
        }
    }

    // update data to database
    var updateFunExe = function () {
        // Object
        var dataObj = $("#outWarehouseDetailBill_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back = $("#outWarehouseDetailBill_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 单据编号：" + dataObj.outwBillsnumber + "，物料码'：" + dataObj.outwCode;
        var outwId = $(":input[name=outwId]").val();

        // 隐藏（新增/编辑）模态框
        $("#outWarehouseDetailBill_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/update" + outwId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                crudBackMsgSuccesFun(uptAdd_Back, uptAdd_Back_Msg);

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + outwId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(dataObj["outwBillsnumber"]);
                tr.find("td:eq(1)").text(dataObj["outwModel"]);
                tr.find("td:eq(2)").text(dataObj["outwCode"]);
                tr.find("td:eq(3)").text(dataObj["outwUniquecode"]);
                tr.find("td:eq(4)").text(dataObj["outwManufacturer"]);
                tr.find("td:eq(5)").text(dataObj["outwUnit"]);
                tr.find("td:eq(6)").text(dataObj["outwBatch"]);
                tr.find("td:eq(7)").text(dataObj["outwEffectivedate"]);
                tr.find("td:eq(8)").text(dataObj["outwProduceddate"]);
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
        $("#outWarehouseDetailBill_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#outWarehouseDetailBill_OKBtn").data("opt");
        // 获取outwId
        var outwId = opt["outwId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var outwBillsnumber = delTr.find("td:eq(0)").text();
        var outwCode = delTr.find("td:eq(2)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back = $("#outWarehouseDetailBill_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 单据编号：" + outwBillsnumber + "，物料码：" + outwCode;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/delete" + outwId,
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

    // 清空查询条件输入框的内容
    var findRemove = function () {
        $(this).prev().val("");
        $(this).attr("style","z-index:1");
    }

    return {
        bindEvent: function () {
            //分页
            $(" #outWarehouseDetailBill_PageList a[name=pages]").click(function () {
                pageFun($(this).text());
            });
            //下一页
            $("#outWarehouseDetailBillPageNext").click(function () {
                pageFun(parseInt($(" #outWarehouseDetailBill_PageList li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#outWarehouseDetailBillPagePrev").click(function () {
                pageFun(parseInt($(" #outWarehouseDetailBill_PageList li.paginate_button.active a").text()) - 1);
            });
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_outWarehouseDetailBill]").click(function () {
                pageFun(1);
            });
            // 编辑绑定事件
            $("tbody button[name='outWarehouseDetailBillBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='outWarehouseDetailBillBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            $("#outWarehouseDetailBill_UptAdd_Back button").click(function (e) {
                $("#outWarehouseDetailBill_UptAdd_Back").hide();
                //阻止默认事件
                return false;
            });

            // 添加按钮被点击
            $("#outWarehouseDetailBill_AddBtn").click(addFun);

            $("#outWarehouseDetailBill_Close").click(cancelFun);

            // Add
            $("#outWarehouseDetailBill_AddBtnExe").click(saveFun);

            // Update
            $("#outWarehouseDetailBill_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#outWarehouseDetailBill_OKBtn").click(deleteFun);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));
        }
    }
})();

