$(document).ready(function () {
    outWarehouseBill.bindEvent();
    outWarehouseBill.fixEvent();
    bindTooltipEvent();
    /*ewTimePicker.timePickerFun(["outwOuttime"]);*/
});
var outWarehouseBill = (function () {

    // 校验生产日期与有效日期
    var checkDate = function () {
        var effDate = $("#outWarehouseDetailBill_UptAddForm input[name='outwEffectivedate']");
        var productDate = $("#outWarehouseDetailBill_UptAddForm input[name='outwProduceddate']");
        return effDate.val() < productDate.val() ? "有效日期不能小于生产日期" : undefined;
    };

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (url,jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url: url,
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
        "outwBillsnumber": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                var url = "/ipsp/outWarehouseBill/validIsExist";
                var toBackData = "{'outwBillsnumber':'" + inputValue + "'}";
                return checkUnique(url,toBackData);
            }
        }],
    }

    var selfValid1 = {
        "outwUniquecode": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                var url = "/ipsp/outWarehouseDetailBill/validIsExist";
                var toBackData = "{'outwUniquecode':'" + inputValue + "'}";
                return checkUnique(url,toBackData);
            }
        }],
        "outwPrebillnumber": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                // 如果不需要做预出库，则不需要做预出库单编号的唯一性验证
                var outwIspreout = $("#outWarehouseDetailBill_UptAddForm [name=outwIspreout]:checked").val();
                if (outwIspreout != "0") { // 存在预出库的情况下，使预出库单编号输入框可以操作
                    var url = "/ipsp/outWarehouseDetailBill/validIsExist";
                    var toBackData = "{'outwPrebillnumber':'" + inputValue + "'}";
                    return checkUnique(url,toBackData);
                } else {
                    return undefined; // 如果不是预出库，则不用做唯一性验证
                }
            }
        }],

        // 验证生产日期和有效日期
        "outwEffectivedate": [{
            "type": 2,// type为2代表校验有效日期
            "fun": checkDate
        }],
        "outwProduceddate": [{
            "type": 2,
            "fun": checkDate
        }]
    }

    var cache = {
        "cached": false,
        "mod": null,
        "outwId": null,
        "outwBillsnumber": null,
    }

    //查看出库单
    var viewFile = function () {
        $("#download_outWarehouseBill_UptAdd_OptModal").modal('hide');
        //开始拿到上传文件的数据outwBillsnumber
        var file = document.querySelector("#file_input");
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/importOutExcelList",
            type: "POST",
            dataType: "json",
            data: formData,
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                var outWareDetailInfo = data[0];
                if (outWareDetailInfo != "") {
                    $("#showImportOutWareInfo").modal('show');
                    $("#viewImportOutWarehouse_AddBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/editOut" + outWareDetailInfo, function (dataInfo) {
                        $(this).html(dataInfo);
                        //编辑页面清空按钮
                        $(".cleanBtn").click(findRemove);
                        CleanInput.bindEvent($(".cleanBtn").prev("input"));
                    })
                }
            }
        });
    };
    //验证出库单
    var viewOuteDetail = function () {
        if (FormValidator.commitForm($("#viewOutWarehouseDeatilInfo"), selfValid, "通知")) {
            viewDetailFile();
        }
    };

    var viewDetailFile = function () {
        var file = document.querySelector("#file_input");
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        ajax(formData);
    }
    function ajax(formData) {
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/showDetailInfo",
            method: "POST",
            data: formData,
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                var outWareDetailInfo = data;
                if (outWareDetailInfo != '')
                    $("#showImportOutWareInfo").modal('hide');
                $("#showImportOutWareDetailInfo").modal('show');
                $("#showOutportFileDetailInfo").html(outWareDetailInfo);
            }
        });

    }

    var downFile = function (data) {
        //点击保存按钮的时候，需要获得两个页面的数据,组合成对象或者json格式,一起传给后台
        var outwBillsnumber =  $("#inputOutwBillsnumber").val();
        var outwOuttime = $("#inputOutwOuttime").val();
        var outwCategory = $("#selectOutwCategory option:selected").val();
        var outwStatus =  $("#inputOutwStatus").val();
        var objParam = {outwBillsnumber:outwBillsnumber,
                        outwOuttime:outwOuttime,outwCategory:outwCategory,outwStatus:outwStatus};
        var objData = JSON.stringify(objParam);


        var file = document.querySelector("#file_input");
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        formData.append('objDate',objData);
        outWarehouseBill_UptAdd_Back  = "{ 出库单号:"+outwBillsnumber;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/saveFile",
            method: "post",
            data: formData,
            dataType: "json",
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data == true){
                    outWarehouseBill_UptAdd_Back = outWarehouseBill_UptAdd_Back + " }：导入成功 ！";
                    IdxTip.tipsuc(outWarehouseBill_UptAdd_Back, "成功!");
                    $("#showImportOutWareDetailInfo").modal("hide");
                }else {
                    outWarehouseBill_UptAdd_Back = outWarehouseBill_UptAdd_Back + " }：导入失败的原因：该出库单号已存在" ;
                    IdxTip.tiperr(outWarehouseBill_UptAdd_Back,"失败");
                    $("#showImportOutWareDetailInfo").modal("hide");
                }
            }
        })
    };


    var clickBack = function () {
        $("#showImportOutWareDetailInfo").modal("hide");
        $("#showImportOutWareInfo").modal("show");
    };

    var exportFile =function () {
        $("#tmp").submit();
    };


    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#outWarehouseBill_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#outWarehouseBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 1;
                __loadAddData(callback);
                cancelFun();
            }
        }
        else {
            __loadAddData(callback);
            cancelFun();
        }
    };

    var editFun = function () {
        var callback = function () {
            FormValidator.validForm($("#outWarehouseBill_UptAddForm"), selfValid, $("#outWarehouseBill_UptAddForm").serializeObject());
        }

        var outwId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["outwId"] && cache["outwId"] == outwId) {
                $("#outWarehouseBill_UptAdd_OptModal").modal("show");
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
        $("#outWarehouseBill_OKBtn").data("opt", {"tr": delTr, "outwId": outwId});
        $("#outWarehouseBill_Affirm").modal('show');
    };

    //清空标记
    var cancelFun = function () {
        cache["mod"] = null;
    }

    // 查看详情
    var findBtnFun = function () {
        var outwBillsnumber = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 3 && cache["outwBillsnumber"] && cache["outwBillsnumber"] == outwBillsnumber) {
                $("#outWarehouseBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 3;
                cache["outwBillsnumber"] = outwBillsnumber;
                __loadFindData(outwBillsnumber);
            }
        }
        else {
            __loadFindData(outwBillsnumber);
        }
    };

    //点击上一步
    var prevFunExe = function () {
        $("#outWarehouseDetailBillDouble_UptAdd_OptModal").modal('hide');
        $("#outWarehouseBill_UptAdd_OptModal").modal('show');
    }

    //点击下一步按钮，加载出库单详情页面
    var addDoubleFun = function () {
        var callback = function () {
            // 绑定出库详情单中所需要验证的数据
            FormValidator.validForm($("#outWarehouseDetailBill_UptAddForm"), selfValid1);
        }
        if (cache["mod"] == 1) {
            $("#outWarehouseDetailBillDouble_UptAdd_OptModal").modal("show");
        }
        else {
            cache["mod"] = 1;
            __loadAddDoubleData(callback);
        }
    }

    var __loadAddData = function (callback) {
        $("#outWarehouseBill_AddBtnExe").show();
        $("#outWarehouseBill_UptBtnExe").hide();
        $("#outWarehouseBill_UptAddTitle").text("新增出库单信息");

        $("#outWarehouseBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var __loadEditData = function (outwId, callback) {


        $("#outWarehouseBill_AddBtnExe").hide();
        $("#outWarehouseBill_UptBtnExe").show();
        $("#outWarehouseBill_UptAddTitle").text("编辑出库单信息");

        if (!outwId) outwId = '';
        $("#outWarehouseBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/edit/" + outwId, function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var __loadFindData = function (outwBillsnumber) {
        $("#outWarehouseBill_AddBtnExe").hide();
        $("#outWarehouseBill_UptBtnExe").hide();
        $("#outWarehouseBill_UptAddTitle").text("出库单详情信息");

        if (!outwBillsnumber) outwBillsnumber = '';
        $("#outWarehouseBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/find/" + outwBillsnumber, function (data) {
            $(this).html(data);
            var script = $("#outWarehouseBill_UptAdd_OptBody input[name='script']").val();
            $.getScript(script);
        });
    }

    // 出库单详情编辑页面的的显示
    var __loadAddDoubleData = function (callback) {
        $("#outWarehouseBill_UptAdd_OptModal").modal("hide");
        $("#outWarehouseDetailBillDouble_UptAdd_OptModal").modal("show");
        $("#outWarehouseDetailBillDouble_AddBtnExe").show();
        $("#outWarehouseDetailBillDouble_UptBtnExe").hide();
        $("#outWarehouseDetailBillDouble_UptAddTitle").text("新增出库单详情信息");
        var outwBillsnumber = $("#outWarehouseBill_UptAddForm [name=outwBillsnumber]").val();
        $("#outWarehouseDetailBillDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/detailAdd/" + outwBillsnumber, function (data) {
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
        var formData = JSON.stringify($("#find_outWarehouseBill").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].outwBillsnumber + "</td>" +
                        "<td>" + list[i].catName + "</td>" +
                        "<td>" + EWDate.getFullYMDHM(list[i].outwOuttime) + "</td>"
                    if (list[i].outwStatus == 0) {
                        tbodyHtml += "<td>未出库</td>"
                    }
                    if (list[i].outwStatus == 1) {
                        tbodyHtml += "<td>出库中</td>"
                    }
                    if (list[i].outwStatus == 2) {
                        tbodyHtml += "<td>已出库</td>"
                    }
                    tbodyHtml +=
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='outWarehouseBillBtnVU' " +
                                    "class='btn btn-primary fa fa-edit' " +
                                    "data="+ list[i].outwId +
                                    " data-toggle='modal' data-placement='bottom' " +
                                    "data-target='#outWarehouseBill_UptAdd_OptModal'  " +
                                    "data-original-title='编辑' >"+
                            "</button>"+
                        "</td>"+
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='outWarehouseBillBtnDel' " +
                                    "class='btn btn-danger fa fa-trash-o' " +
                                    "data="+ list[i].outwId +
                                    " data-toggle='modal' data-placement='bottom' " +
                                    "data-original-title='删除' >"+
                            "</button>"+
                        "</td>"+
                        "<td class='ew-tooltip '>"+
                            "<button type='button' name='outWarehouseBillBtnFind' " +
                                    "class='btn btn-info fa fa-eye' " +
                                    "data="+ list[i].outwBillsnumber +
                                    " data-toggle='modal' data-placement='bottom' " +
                                    "data-target='#outWarehouseBill_UptAdd_OptModal'  " +
                                    "data-original-title='查看详情' >"+
                            "</button>"+
                        "</td>"+
                "</tr>"
                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='outWarehouseBillPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='outWarehouseBillPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("#outWarehouseBillTbody").html(tbodyHtml);
                $("#outWarehouseBill_PageList").html(pageListHtml);
                outWarehouseBill.bindEvent();
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

    // 点击下一步按钮时候对出库单做校验
    var nextBtnBeforeValid = function () {
        if (FormValidator.commitForm($("#outWarehouseBill_UptAddForm"), selfValid, "通知")) {
            // 验证通过之后，跳转到加载出库单详情函数
            addDoubleFun();
        }
    }

    // 执行保存之前做相关的前台验证
    var saveFunBeforeValid = function () {
        // 验证出库单详情
        if (FormValidator.commitForm($("#outWarehouseDetailBill_UptAddForm"), selfValid1, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();

            // 验证成功之后，隐藏模态框
            $("#outWarehouseDetailBillDouble_UptAdd_OptModal").modal('hide');
        }

    }
    // save data to database
    // 出库单及出库单详情保存到数据库中
    var saveFunExe = function () {
        var outwBillObj = $("#outWarehouseBill_UptAddForm").serializeObject();
        var outwDetailBillObj = $("#outWarehouseDetailBill_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify({outwDetail: outwDetailBillObj, outw: outwBillObj});

        // 反馈信息
        var uptAdd_Back = $("#outWarehouseBill_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 单据编号：" + outwDetailBillObj.outwBillsnumber + "，物料码：" + outwDetailBillObj.outwCode + "，批次：" + outwDetailBillObj.outwBatch;

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/doubleSave",
            method: "POST",
            data: {data: jsonStrData},
            success: function () {

                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功！";
                crudBackMsgSuccesFun(uptAdd_Back, uptAdd_Back_Msg);
            },
            error: function () {
                uptAdd_Back_Msg = +" }：添加数据失败，请再次尝试添加 ！";
                crudBackMsgFailedFun(uptAdd_Back, uptAdd_Back_Msg);

            }
        });
    }


    //修改显示出库单详情
    var doubelUptFunBefore = function () {
        if (FormValidator.commitForm($("#outWarehouseBill_UptAddForm"), selfValid, "通知")) {
            var callback = function () {
                FormValidator.validForm($("#outWarehouseDetailBill_UptAddForm"), selfValid1, $("#outWarehouseDetailBill_UptAddForm").serializeObject());
            }
            var outWarehouseBill = $("#outWarehouseBill_UptAddForm").serializeObject();
            var outwBillsnumber = outWarehouseBill.outwBillsnumber;
            var outwId = outWarehouseBill.outwId;
            $("#outWarehouseBill_UptAdd_OptModal").modal("hide");
            $("#outWarehouseDetailBillDouble_UptAdd_OptModal").modal("show");
            $("#outWarehouseDetailBillDouble_AddBtnExe").hide();
            $("#outWarehouseDetailBillDouble_UptBtnExe").show();
            $("#outWarehouseDetailBillDouble_UptAddTitle").text("编辑出库单详情信息");
            $("#outWarehouseDetailBillDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/outWarehouseDetailBill/detailUpt/" + outwBillsnumber + "/" + outwId, function (data) {
                $(this).html(data);

                // 如果存在出库单详情页面，则做callback 做验证相关的绑定元素,
                // 此处的81是errorMessageRemind.html页面中的页面长度，
                // 代表没有出库单详情页面，显示errorMessageRemind.html页面中的内容
                if(data.length != 81){
                    callback();
                    //编辑页面清空按钮
                    $(".cleanBtn").click(findRemove);
                    CleanInput.bindEvent($(".cleanBtn").prev("input"));
                }
            });
        }

    }

    var doubelUptFunExeBeforeValid = function () {
        if (FormValidator.commitForm($("#outWarehouseDetailBill_UptAddForm"), selfValid1, "通知")) {
            doubelUptFunExe();
        }
    }

    // update data to database
    var doubelUptFunExe = function () {
        var outwBillObj = $("#outWarehouseBill_UptAddForm").serializeObject();
        var outwDetailBillObj = $("#outWarehouseDetailBill_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify({outwDetail: outwDetailBillObj, outw: outwBillObj});

        // 显示消息
        var uptAdd_Back = $("#outWarehouseBill_UptAdd_Back");
        var uptAdd_Back_Msg = "";
        if(outwDetailBillObj.outwCode == undefined){ // 没有下一步操作，出库详情单的提示信息
            uptAdd_Back_Msg = "{ 单据编号：" + outwBillObj.outwBillsnumber;
        }else { // 存在下一步修改出库详情单的 整体提示信息。
            uptAdd_Back_Msg = "{ 单据编号：" + outwDetailBillObj.outwBillsnumber + "，物料码：" + outwDetailBillObj.outwCode + "，批次：" + outwDetailBillObj.outwBatch;
        };

        // 获取表单中的outwId值
        var outwId = $(":input[name=outwId]").val();

        // 隐藏（新增/编辑）模态框
        $("#outWarehouseBill_UptAdd_OptModal").modal('hide');
        $("#outWarehouseDetailBillDouble_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/doubleUpt",
            method: "POST",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function (mapData) {
                var info = mapData.info;
                var vwOutWarehouseBill = mapData.vwOutWarehouseBill;
                uptAdd_Back_Msg = uptAdd_Back_Msg + " } ：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                crudBackMsgSuccesFun(uptAdd_Back, uptAdd_Back_Msg);

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + outwId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(vwOutWarehouseBill.outwBillsnumber);
                tr.find("td:eq(1)").text(vwOutWarehouseBill.catName);
                tr.find("td:eq(2)").text(EWDate.getFullYMDHM(vwOutWarehouseBill.outwOuttime));
                if(vwOutWarehouseBill.outwStatus==0){
                    tr.find("td:eq(3)").text("未出库");
                }
                if(vwOutWarehouseBill.outwStatus==1){
                    tr.find("td:eq(3)").text("出库中");
                }
                if(vwOutWarehouseBill.outwStatus==2){
                    tr.find("td:eq(3)").text("已出库");
                }
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
        $("#outWarehouseBill_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#outWarehouseBill_OKBtn").data("opt");
        // 获取outwId
        var outwId = opt["outwId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var outwBillsnumber = delTr.find("td:eq(0)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back = $("#outWarehouseBill_UptAdd_Back");
        var uptAdd_Back_Msg = "{ 单据编号：" + outwBillsnumber;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/outWarehouseBill/delete" + outwId,
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
            $("#outWarehouseBillPageNext").click(function () {
                pageFun(parseInt($("#outWarehouseBill_PageList li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#outWarehouseBillPagePrev").click(function () {
                pageFun(parseInt($("#outWarehouseBill_PageList li.paginate_button.active a").text()) - 1);
            });
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_outWarehouseBill]").click(function () {
                pageFun(1);
            });
            // 绑定编辑事件
            $("tbody button[name='outWarehouseBillBtnVU']").click(editFun);
            // 绑定删除事件
            $("tbody button[name='outWarehouseBillBtnDel']").click(delBtnFun);
            // 绑定查看详情事件
            $("tbody button[name='outWarehouseBillBtnFind']").click(findBtnFun);
        },
        fixEvent: function () {
            $("#outWarehouseBill_UptAdd_Back button").click(function (e) {
                $("#outWarehouseBill_UptAdd_Back").hide();
                //阻止默认事件
                return false;
            });

            // 添加按钮被点击
            $("#outWarehouseBill_AddBtn").click(addFun);

            // 取消按钮被点击
            $("#outWarehouseBill_Close").click(cancelFun);
            $("#outWarehouseDetailBill_Close").click(cancelFun);

            // Confirm Delete Operation
            $("#outWarehouseBill_OKBtn").click(deleteFun);

            // 下一步按钮被点击时
            $("#outWarehouseBill_AddBtnExe").click(nextBtnBeforeValid);

            //上一步
            $("#outWarehouseDetailBillDouble_PrevBtnExe").click(prevFunExe);

            //出库单及出库单详情增加
            $("#outWarehouseDetailBillDouble_AddBtnExe").click(saveFunBeforeValid);

            // 点击修改按钮
            $("#outWarehouseBill_UptBtnExe").click(doubelUptFunBefore);

            //出库单及出库单详情编辑
            $("#outWarehouseDetailBillDouble_UptBtnExe").click(doubelUptFunExeBeforeValid);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //查看操作
            $("#importOutWarehouse_save").click(viewFile);

            //查看出库单详细信息操作

            $("#viewOutWarehouseInfo_AddBtnExe").click(viewOuteDetail);

            //导入入库文件
            $("#importFileInfo_Save").click(downFile);

            //详情点击上一步
            $("#importFileInfo_Back").click(clickBack);

            //点击导出操作
            $("#export_outWarehouseBill").click(exportFile);
        }
    }
})();