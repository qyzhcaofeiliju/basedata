$(document).ready(function () {
    terminalEquipment.bindEvent();
    terminalEquipment.fixEvent();
    bindTooltipEvent();
});
var terminalEquipment = (function () {
    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (url, jsonStrData) {
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
        "equUniquecode": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                var url = "/ipsp/terminalEquipment/validIsExist";
                var toBackData = "{'equUniquecode':'" + inputValue + "'}";
                return checkUnique(url, toBackData);
            }
        }],
        "equNumber": [{
            "type": 1,
            "fun": function (inputValue) {
                var url = "/ipsp/terminalEquipment/validIsExist";
                var toBackData = "{'equNumber':'" + inputValue + "'}";
                return checkUnique(url, toBackData);
            }
        }],
        "equIp": [{
            "type": 1,
            "fun": function (inputValue) {
                var url = "/ipsp/terminalEquipment/validIsExist";
                var toBackData = "{'equIp':'" + inputValue + "'}";
                return checkUnique(url, toBackData);
            }
        }]
    }

    // 自定义验证，用于货架货位信息的验证。
    var selfValid1 = {
        "axisdNumber": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                var url = "/ipsp/goodslocationAxisdictionary/validIsExist";
                var toBackData = "{'axisdNumber':'" + inputValue + "'}";
                return checkUnique(url, toBackData);
            }
        }],
    }

    // 缓存
    var cache = {
        "cached": false,
        "mod": null,
        "equId": null,
    }
    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#terminalEquipment_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#terminalEquipment_UptAdd_OptModal").modal("show");
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
            FormValidator.validForm($("#terminalEquipment_UptAddForm"), selfValid, $("#terminalEquipment_UptAddForm").serializeObject());
        }

        var equId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["equId"] && cache["equId"] == equId) {
                $("#terminalEquipment_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["equId"] = equId;
                __loadEditData(equId, callback);
            }
        }
        else {
            cache["mod"] == null; // 重置mod
            __loadEditData(equId, callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var equId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#terminalEquipment_OKBtn").data("opt", {"tr": delTr, "equId": equId});
        $("#terminalEquipment_Affirm").modal('show');
    };

    //清空标记
    var cancelFun = function () {
        cache["mod"] = null;
    }

    // 查看货架货位信息
    var findBtnFun = function () {
        var equId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 3 && cache["equId"] && cache["equId"] == equId) {
                $("#terminalEquipment_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 3;
                cache["equId"] = equId;
                __loadFindData(equId);
            }
        }
        else {
            __loadFindData(equId);
        }
    };

    //点击上一步
    var prevFunExe = function () {
        // 判定terminalEquipment_UptAddForm 中的元素是否被修改，如果没有被修改，则不做重复值验证。
        FormValidator.validForm($("#terminalEquipment_UptAddForm"), selfValid, $("#terminalEquipment_UptAddForm").serializeObject());
        $("#goodslocationAxisdictionaryDouble_UptAdd_OptModal").modal('hide');
        $("#terminalEquipment_UptAdd_OptModal").modal('show');
    }

    //点击下一步按钮，加载货架货位信息view页面
    var addDoubleFun = function () {
        var callback = function () {
            // 绑定货架货位信息中所需要验证的数据
            FormValidator.validForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid1);
        }
        if (cache["mod"] == 1) {
            $("#goodslocationAxisdictionaryDouble_UptAdd_OptModal").modal("show");
            $("#terminalEquipment_UptAdd_OptModal").modal('hide');
        }
        else {
            cache["mod"] = 1;
            __loadAddDoubleData(callback);
        }
    }

    var __loadAddData = function (callback) {
        $("#terminalEquipment_AddBtnExe").show();
        $("#terminalEquipment_DirectAddCompletion").show();
        $("#terminalEquipment_DirectEditCompletion").hide();
        $("#terminalEquipment_UptBtnExe").hide();
        $("#terminalEquipment_UptAddTitle").text("新增站点设备信息");

        $("#terminalEquipment_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalEquipment/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var __loadEditData = function (equId, callback) {
        $("#terminalEquipment_AddBtnExe").hide();
        $("#terminalEquipment_DirectAddCompletion").hide();
        $("#terminalEquipment_DirectEditCompletion").show();
        $("#terminalEquipment_UptBtnExe").show();
        $("#terminalEquipment_UptAddTitle").text("编辑站点设备信息");
        if (!equId) equId = '';
        $("#terminalEquipment_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalEquipment/edit/" + equId, function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    // 展示货架货位信息的函数
    var __loadFindData = function (equId) {
        $("#terminalEquipment_AddBtnExe").hide();
        $("#terminalEquipment_UptBtnExe").hide();
        $("#terminalEquipment_UptAddTitle").text("货架货位信息");
        if (!equId) equId = '';
        $("#terminalEquipment_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/terminalEquipment/find/" + equId, function (data) {
            $(this).html(data);
            var script = $("#terminalEquipment_UptAdd_OptBody input[name='script']").val();
            $.getScript(script);
        });
    }

    // 货架货位信息编辑页面的的显示
    var __loadAddDoubleData = function (callback) {
        $("#terminalEquipment_UptAdd_OptModal").modal("hide");
        $("#goodslocationAxisdictionaryDouble_UptAdd_OptModal").modal("show");
        $("#goodslocationAxisdictionaryDouble_AddBtnExe").show();
        $("#goodslocationAxisdictionaryDouble_UptBtnExe").hide();
        $("#goodslocationAxisdictionaryDouble_UptAddTitle").text("新增货架货位信息");
        $("#goodslocationAxisdictionaryDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/addPage", function (data) {
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
        var formData = JSON.stringify($("#find_terminalEquipment").serializeObject());
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/terminalEquipment/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].catName + "</td>" +
                        "<td>" + list[i].staName + "</td>" +
                        "<td>" + list[i].equUniquecode + "</td>" +
                        "<td>" + list[i].equNumber + "</td>"
                    if (parseInt(list[i].equCurrentstatus) == 0) {
                        tbodyHtml += "<td>停机</td>"
                    }
                    if (parseInt(list[i].equCurrentstatus) == 1) {
                        tbodyHtml += "<td>正常</td>"
                    }
                    if (parseInt(list[i].equCurrentstatus) == 2) {
                        tbodyHtml += "<td>报警</td>"
                    }
                    if (parseInt(list[i].equCurrentstatus) == 3) {
                        tbodyHtml += "<td>故障</td>"
                    }
                    tbodyHtml += "<td>" + list[i].equIp + "</td>" +
                        "<td>" + list[i].equPort + "</td>"
                    tbodyHtml +=
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='terminalEquipmentBtnVU' " +
                        "class='btn btn-primary fa fa-edit' " +
                        "data=" + list[i].equId +
                        " data-toggle='modal' data-placement='bottom' " +
                        "data-target='#terminalEquipment_UptAdd_OptModal'  " +
                        "data-original-title='编辑' >" +
                        "</button>" +
                        "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='terminalEquipmentBtnDel' " +
                        "class='btn btn-danger fa fa-trash-o' " +
                        "data=" + list[i].equId +
                        " data-toggle='modal' data-placement='bottom' " +
                        "data-original-title='删除' >" +
                        "</button>" +
                        "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='terminalEquipmentBtnFind' " +
                        "class='btn btn-info fa fa-eye' " +
                        "data=" + list[i].equId +
                        " data-toggle='modal' data-placement='bottom' " +
                        "data-target='#terminalEquipment_UptAdd_OptModal'  " +
                        "data-original-title='查看详情' >" +
                        "</button>" +
                        "</td>" +
                        "</tr>"
                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='terminalEquipmentPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='terminalEquipmentPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("#terminalEquipmentTbody").html(tbodyHtml);
                $("#terminalEquipment_PageList").html(pageListHtml);
                terminalEquipment.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    // 点击下一步按钮时候对出库单做校验
    var nextBtnBeforeValid = function () {
        if (FormValidator.commitForm($("#terminalEquipment_UptAddForm"), selfValid, "通知")) {
            // 验证通过之后，跳转到加载货架货位信息的函数
            addDoubleFun();
        }
    }

    // 直接添加站点设备不添加详情
    var directAddComplete = function() {
        if (FormValidator.commitForm($("#terminalEquipment_UptAddForm"), selfValid, "通知")) {
            // 验证通过之后，添加站点设备

            var terminalEquipmentObj = $("#terminalEquipment_UptAddForm").serializeObject();

            //json 格式字符串数据
            var jsonStrData = JSON.stringify(terminalEquipmentObj);

            // 反馈信息
            var uptAdd_Back_Msg = "{ 唯一码：" + terminalEquipmentObj.equUniquecode + "，编号："
                + terminalEquipmentObj.equNumber;

            // ajax 保存
            $.ajax({
                url:SysCfg.getFullAddress()+"/ipsp/terminalEquipment/save",
                method: "POST",
                data: {data: jsonStrData},
                success: function () {
                    $("#terminalEquipment_UptAdd_OptModal").modal("hide");

                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功！";
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");
                },
                error: function () {
                    uptAdd_Back_Msg = +" }：添加数据失败，请再次尝试添加 ！";
                    IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
                }
            });
        }
    }

    // 直接修改站点设备不修改详情
    var directEditComplete = function() {
        var terminalEquipmentObj = $("#terminalEquipment_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(terminalEquipmentObj);

        // 显示消息
        var uptAdd_Back_Msg = "{ 设备唯一码：" + terminalEquipmentObj.equUniquecode + "，设备编号："
            + terminalEquipmentObj.equNumber;

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalEquipment/update",
            method: "POST",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function (vwTerminalEquipment) {
                $("#terminalEquipment_UptAdd_OptModal").modal("hide");

                if(vwTerminalEquipment) {
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                    // 保存数据成功之后修改样式和添加提示信息
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                    // 数据回显到view页面相应的位置
                    var tr = $("tbody button[data='" + vwTerminalEquipment.equId + "']").parents("tr:first");
                    tr.find("td:eq(0)").text(vwTerminalEquipment.catName);
                    tr.find("td:eq(1)").text(vwTerminalEquipment.staName);
                    tr.find("td:eq(2)").text(vwTerminalEquipment.equUniquecode);
                    tr.find("td:eq(3)").text(vwTerminalEquipment.equNumber);
                    if (parseInt(vwTerminalEquipment.equCurrentstatus) == 0) {
                        tr.find("td:eq(4)").text("停机");
                    }
                    if (parseInt(vwTerminalEquipment.equCurrentstatus) == 1) {
                        tr.find("td:eq(4)").text("正常");
                    }
                    if (parseInt(vwTerminalEquipment.equCurrentstatus) == 2) {
                        tr.find("td:eq(4)").text("报警");
                    }
                    if (parseInt(vwTerminalEquipment.equCurrentstatus) == 3) {
                        tr.find("td:eq(4)").text("故障");
                    }
                    tr.find("td:eq(5)").text(vwTerminalEquipment.equIp);
                    tr.find("td:eq(6)").text(vwTerminalEquipment.equPort);
                }
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据失败，请再次尝试 ！";
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }

    // 执行保存之前做相关的前台验证
    var saveFunBeforeValid = function () {
        // 验证货架货位信息
        if (FormValidator.commitForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid1, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();

            // 验证成功之后，隐藏模态框
            $("#goodslocationAxisdictionaryDouble_UptAdd_OptModal").modal('hide');
        }

    }
    // save data to database
    // 站点设备及货架货位信息保存到数据库中
    var saveFunExe = function () {
        var terminalEquipmentObj = $("#terminalEquipment_UptAddForm").serializeObject();
        var goodslocationAxisdictionaryObj = $("#goodslocationAxisdictionary_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify({
            goodslocationAxisdictionary: goodslocationAxisdictionaryObj,
            terminalEquipment: terminalEquipmentObj
        });

        // 反馈信息
        var uptAdd_Back_Msg = "";
        if (goodslocationAxisdictionaryObj.axisdNumber == undefined) { // 如果没有货架货位信息的情况
            uptAdd_Back_Msg = "{ 唯一码：" + terminalEquipmentObj.equUniquecode + "，编号："
                + terminalEquipmentObj.equNumber;
        } else { // 存在货架货位信息：ter
            uptAdd_Back_Msg = "{ 设备唯一码：" + terminalEquipmentObj.equUniquecode + "，设备编号：" +
                terminalEquipmentObj.equNumber + "，货架编号：" + goodslocationAxisdictionaryObj.axisdNumber;
        }
        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalEquipment/doubleSave",
            method: "POST",
            data: {data: jsonStrData},
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功！";
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");
            },
            error: function () {
                uptAdd_Back_Msg = +" }：添加数据失败，请再次尝试添加 ！";
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }


    //修改显示货架货位信息，即点击下一步之后。
    var doubelUptFunBefore = function () {
        if (FormValidator.commitForm($("#terminalEquipment_UptAddForm"), selfValid, "通知")) {
            var callback = function () {
                FormValidator.validForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid1, $("#goodslocationAxisdictionary_UptAddForm").serializeObject());
            }
            var terminalEquipment = $("#terminalEquipment_UptAddForm").serializeObject();
            var equId = terminalEquipment.equId;
            $("#terminalEquipment_UptAdd_OptModal").modal("hide");
            $("#goodslocationAxisdictionaryDouble_UptAdd_OptModal").modal("show");
            $("#goodslocationAxisdictionaryDouble_AddBtnExe").hide();
            $("#goodslocationAxisdictionaryDouble_UptBtnExe").show();
            $("#goodslocationAxisdictionaryDouble_UptAddTitle").text("编辑货架货位信息");

            // 4 :代表在编辑模式下，点击上一步的时候，再返回下一步时候，货架货位中的数值应该都存在；
            $("#goodslocationAxisdictionaryDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/detailUpt/" + equId, function (data) {
                // $(this).html(data);
                // 如果存在货架货位view页面，则执行callback函数， 绑定货架货位中所需要做验证的标签元素,
                // 此处的83是errorMessageRemind.html页面中的页面长度，
                // 代表没有货架货位view页面，显示errorMessageRemind.html页面中的内容,此时不需要做验证
                if (data.length != 83) {// 此处的83是errorMessageRemind.html 页面的内容长度；
                    callback();
                    //编辑页面清空按钮
                    $(".cleanBtn").click(findRemove);
                    CleanInput.bindEvent($(".cleanBtn").prev("input"));
                }
            });
        }
    }

    var doubelUptFunExeBeforeValid = function () {
        if (FormValidator.commitForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid1, "通知")) {
            doubelUptFunExe();
        }
    }

    // update data to database
    var doubelUptFunExe = function () {
        var terminalEquipmentObj = $("#terminalEquipment_UptAddForm").serializeObject();
        var goodslocationAxisdictionaryObj = $("#goodslocationAxisdictionary_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify({
            goodslocationAxisdictionary: goodslocationAxisdictionaryObj,
            terminalEquipment: terminalEquipmentObj
        });

        // 显示消息
        var uptAdd_Back_Msg = "";
        if (goodslocationAxisdictionaryObj.axisdNumber == undefined) { // 没有下一步操作，出库详情单的提示信息
            uptAdd_Back_Msg = "{ 设备唯一码：" + terminalEquipmentObj.equUniquecode + "，设备编号："
                + terminalEquipmentObj.equNumber;
        } else { // 存在下一步修改出库详情单的 整体提示信息。
            uptAdd_Back_Msg = "{ 设备唯一码：" + terminalEquipmentObj.equUniquecode + "，设备编号："
                + terminalEquipmentObj.equNumber + "，货架编号：" + goodslocationAxisdictionaryObj.axisdNumber;
        }

        // 获取表单中的equId值
        var equId = $(":input[name=equId]").val();

        // 隐藏（新增/编辑）模态框
        $("#terminalEquipment_UptAdd_OptModal").modal('hide');
        $("#goodslocationAxisdictionaryDouble_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalEquipment/doubleUpt",
            method: "POST",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function (mapData) {
                var info = mapData.info;
                var vwTerminalEquipment = mapData.vwTerminalEquipment;
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + equId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(vwTerminalEquipment.catName);
                tr.find("td:eq(1)").text(vwTerminalEquipment.staName);
                tr.find("td:eq(2)").text(vwTerminalEquipment.equUniquecode);
                tr.find("td:eq(3)").text(vwTerminalEquipment.equNumber);
                if (parseInt(vwTerminalEquipment.equCurrentstatus) == 0) {
                    tr.find("td:eq(4)").text("停机");
                }
                if (parseInt(vwTerminalEquipment.equCurrentstatus) == 1) {
                    tr.find("td:eq(4)").text("正常");
                }
                if (parseInt(vwTerminalEquipment.equCurrentstatus) == 2) {
                    tr.find("td:eq(4)").text("报警");
                }
                if (parseInt(vwTerminalEquipment.equCurrentstatus) == 3) {
                    tr.find("td:eq(4)").text("故障");
                }
                tr.find("td:eq(5)").text(vwTerminalEquipment.equIp);
                tr.find("td:eq(6)").text(vwTerminalEquipment.equPort);
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
        $("#terminalEquipment_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#terminalEquipment_OKBtn").data("opt");
        // 获取equId
        var equId = opt["equId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var equUniquecode = delTr.find("td:eq(2)").text();
        var equNumber = delTr.find("td:eq(3)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 唯一码：" + equUniquecode + "，编号：" + equNumber;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/terminalEquipment/delete" + equId,
            method: "DELETE",
            success: function (data) {
                if(data ==1){
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除数据成功 ！";
                    // 删除数据成功之后修改样式和添加提示信息
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                    // delTr.remove;
                    var curPage = $("ul.pagination li.active a").text();
                    pageFun(curPage);
                }else {
                    uptAdd_Back_Msg = uptAdd_Back_Msg + " }：删除失败，此数据被其他地方所使用，不能删除 ";
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
            $("#terminalEquipmentPageNext").click(function () {
                pageFun(parseInt($("#terminalEquipment_PageList li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#terminalEquipmentPagePrev").click(function () {
                pageFun(parseInt($("#terminalEquipment_PageList li.paginate_button.active a").text()) - 1);
            });

            // 绑定编辑事件
            $("tbody button[name='terminalEquipmentBtnVU']").click(editFun);
            // 绑定删除事件
            $("tbody button[name='terminalEquipmentBtnDel']").click(delBtnFun);
            // 绑定查看详情事件
            $("tbody button[name='terminalEquipmentBtnFind']").click(findBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_terminalEquipment]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#terminalEquipment_AddBtn").click(addFun);

            // 取消按钮被点击
            $("#terminalEquipment_Close").click(cancelFun);
            $("#goodslocationAxisdictionaryDouble_Close").click(cancelFun);

            // Confirm Delete Operation
            $("#terminalEquipment_OKBtn").click(deleteFun);

            // 下一步按钮被点击时
            $("#terminalEquipment_AddBtnExe").click(nextBtnBeforeValid);

            $("#terminalEquipment_DirectAddCompletion").click(directAddComplete);
            $("#terminalEquipment_DirectEditCompletion").click(directEditComplete);

            //上一步
            $("#goodslocationAxisdictionaryDouble_PrevBtnExe").click(prevFunExe);

            //站点设备及货架货位信息增加
            $("#goodslocationAxisdictionaryDouble_AddBtnExe").click(saveFunBeforeValid);

            // 点击修改按钮
            $("#terminalEquipment_UptBtnExe").click(doubelUptFunBefore);

            //站点设备及货架货位信息编辑
            $("#goodslocationAxisdictionaryDouble_UptBtnExe").click(doubelUptFunExeBeforeValid);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //表头固定
            fixTableTH("terminalEquipment-cont");
        }
    }
})();