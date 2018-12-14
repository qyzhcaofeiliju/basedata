$(document).ready(function () {
    goodslocationAxisdictionary.bindEvent();
    goodslocationAxisdictionary.fixEvent();
    bindTooltipEvent();
});
var goodslocationAxisdictionary = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/validIsExist",
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
        "axisdNumber": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'axisdNumber':'" + inputValue + "'}");
            }
        }],
    }

    var cache = {
        "cached": false,
        "mod": null,
        "axisdId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid);
        }

        var equId = $(this).attr("data");

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#goodslocationAxisdictionary_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 1;
                __loadAddData(equId, callback);
            }
        }
        else {
            __loadAddData(equId, callback);
        }
    };

    var editFun = function () {

        var callback = function () {
            FormValidator.validForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid, $("#goodslocationAxisdictionary_UptAddForm").serializeObject());
        }

        var axisdId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["axisdId"] && cache["axisdId"] == axisdId) {
                $("#goodslocationAxisdictionary_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["axisdId"] = axisdId;
                __loadEditData(axisdId, callback);
            }
        }
        else {
            __loadEditData(axisdId, callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var axisdId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#goodslocationAxisdictionary_OKBtn").data("opt", {"tr": delTr, "axisdId": axisdId});
        $("#goodslocationAxisdictionary_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (equId, callback) {
        $("#goodslocationAxisdictionary_AddBtnExe").show();
        $("#goodslocationAxisdictionary_UptBtnExe").hide();
        $("#goodslocationAxisdictionary_UptAddTitle").text("新增货架货位坐标信息表");

        $("#goodslocationAxisdictionary_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/detailAdd/" + equId, function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var __loadEditData = function (axisdId, callback) {
        $("#goodslocationAxisdictionary_AddBtnExe").hide();
        $("#goodslocationAxisdictionary_UptBtnExe").show();
        $("#goodslocationAxisdictionary_UptAddTitle").text("编辑货架货位坐标信息表");

        var equId = $("#goodslocationAxisdictionary_AddBtn").attr("data");

        if (!axisdId) axisdId = '';
        $("#goodslocationAxisdictionary_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/edit/" + equId + "/" + axisdId, function (data) {
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
        var formData = JSON.stringify($("#find_goodslocationAxisdictionary").serializeObject());
        var equId = $("#goodslocationAxisdictionary_AddBtn").attr("data");
        $.ajax({
            method: "get",
            url:SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/page",
            data: {pageData: pageData, formData: formData, equId: equId},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td>" + list[i].axisdNumber + "</td>"
                        if(parseInt(list[i].axisdStatus)==0){
                            tbodyHtml+="<td>空闲</td>"
                        }
                        if(parseInt(list[i].axisdStatus)==1){
                            tbodyHtml+="<td>占用</td>"
                        }
                        if(parseInt(list[i].axisdStatus)==2){
                            tbodyHtml+="<td>故障不可用</td>"
                        }
                        tbodyHtml+= "<td>" + list[i].axisdRank + "</td>" +
                        "<td>" + list[i].axisdTier + "</td>" +
                        "<td>" + list[i].axisdColumn + "</td>" +
                        "<td>" + list[i].axisdX + "</td>" +
                        "<td>" + list[i].axisdY + "</td>" +
                        "<td>" + list[i].axisdZ + "</td>" +
                        "<td>" + list[i].axisdSuitcaseuniquecode + "</td>" +
                        "<td>" + list[i].funName + "</td>"
                        tbodyHtml+="<td>"+(list[i].axisdIsautoload==true?'是':'否')+"</td>"+
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='goodslocationAxisdictionaryBtnVU'" +
                        "class='btn btn-primary fa fa-edit'" +
                        "data=" + list[i].axisdId + " data-toggle='modal'" +
                        "data-target='#goodslocationAxisdictionary_UptAdd_OptModal'" +
                        "data-placement='bottom' data-original-title='编辑'>" +
                        "</button>" +
                        "</td>" +
                        "<td class='ew-tooltip '>" +
                        "<button type='button' name='goodslocationAxisdictionaryBtnDel'" +
                        "class='btn btn-danger fa fa-trash-o'" +
                        "data=" + list[i].axisdId + " data-toggle='modal'" +
                        "data-placement='bottom' data-original-title='删除'>" +
                        "</button>" +
                        "</td>" +
                        "</tr>"
                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='goodslocationAxisdictionaryPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='goodslocationAxisdictionaryPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("#goodslocationAxisdictionaryTbody").html(tbodyHtml);
                $("#goodslocationAxisdictionary_PageList").html(pageListHtml);
                goodslocationAxisdictionary.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var goodslocationAxisdictionaryObj = $("#goodslocationAxisdictionary_UptAddForm").serializeObject();
        var equId = $("#goodslocationAxisdictionary_UptAddForm").serializeObject().equId;

        //json 格式字符串数据 ,传输到后台
        var jsonStrData = JSON.stringify({
            goodslocationAxisdictionary: goodslocationAxisdictionaryObj,
            equId: equId
        });

        var uptAdd_Back_Msg = "{ 货位编号：" + goodslocationAxisdictionaryObj.axisdNumber ;

        // 隐藏（新增/编辑）模态框
        $("#goodslocationAxisdictionary_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/save",
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

    var updateFun = function () {
        if (FormValidator.commitForm($("#goodslocationAxisdictionary_UptAddForm"), selfValid, "通知")) {
            updateFunExe();
        }
    }

    // update data to database
    var updateFunExe = function () {
        // Object
        var goodslocationAxisdictionaryObj = $("#goodslocationAxisdictionary_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(goodslocationAxisdictionaryObj);

        var uptAdd_Back_Msg = "{ 货位编号：" + goodslocationAxisdictionaryObj.axisdNumber ;
        var axisdId = $(":input[name=axisdId]").val();

        // 隐藏（新增/编辑）模态框
        $("#goodslocationAxisdictionary_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/update" + axisdId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function (mapData) {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var vwGoodslocationAxisdictionary = mapData.vwGoodslocationAxisdictionary;
                var tr = $("tbody button[data='" + axisdId + "']").parents("tr:first");
                tr.find("td:eq(0)").text(vwGoodslocationAxisdictionary["axisdNumber"]);
                if(parseInt(vwGoodslocationAxisdictionary.axisdStatus)==0){
                    tr.find("td:eq(1)").text("空闲");
                }
                if(parseInt(vwGoodslocationAxisdictionary.axisdStatus)==1){
                    tr.find("td:eq(1)").text("占用");
                }
                if(parseInt(vwGoodslocationAxisdictionary.axisdStatus)==2){
                    tr.find("td:eq(1)").text("故障不可用");
                }
                tr.find("td:eq(2)").text(vwGoodslocationAxisdictionary["axisdRank"]);
                tr.find("td:eq(3)").text(vwGoodslocationAxisdictionary["axisdTier"]);
                tr.find("td:eq(4)").text(vwGoodslocationAxisdictionary["axisdColumn"]);
                tr.find("td:eq(5)").text(vwGoodslocationAxisdictionary["axisdX"]);
                tr.find("td:eq(6)").text(vwGoodslocationAxisdictionary["axisdY"]);
                tr.find("td:eq(7)").text(vwGoodslocationAxisdictionary["axisdZ"]);
                tr.find("td:eq(8)").text(vwGoodslocationAxisdictionary["axisdSuitcaseuniquecode"]);
                tr.find("td:eq(9)").text(vwGoodslocationAxisdictionary["funName"]);
                tr.find("td:eq(10)").text(vwGoodslocationAxisdictionary["axisdIsautoload"]=='true'?"是":"否");
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
        $("#goodslocationAxisdictionary_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#goodslocationAxisdictionary_OKBtn").data("opt");
        // 获取axisdId
        var axisdId = opt["axisdId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var axisdNumber = delTr.find("td:eq(0)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 货架编号：" + axisdNumber ;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/goodslocationAxisdictionary/delete" + axisdId,
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
    var findRemove = function () {
        $(this).prev().val("");
        $(this).attr("style","z-index:1");
    }

    return {
        bindEvent: function () {
            //分页
            $(" #goodslocationAxisdictionary_PageList a[name=pages]").click(function () {
                pageFun($(this).text());
            });
            //下一页
            $("#goodslocationAxisdictionaryPageNext").click(function () {
                pageFun(parseInt($(" #goodslocationAxisdictionary_PageList li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#goodslocationAxisdictionaryPagePrev").click(function () {
                pageFun(parseInt($(" #goodslocationAxisdictionary_PageList li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='goodslocationAxisdictionaryBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='goodslocationAxisdictionaryBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_goodslocationAxisdictionary]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#goodslocationAxisdictionary_AddBtn").click(addFun);

            $("#goodslocationAxisdictionary_Close").click(cancelFun);

            // Add
            $("#goodslocationAxisdictionary_AddBtnExe").click(saveFun);

            // Update
            $("#goodslocationAxisdictionary_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#goodslocationAxisdictionary_OKBtn").click(deleteFun);

            // 点击清空查询条件的输入框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //固定表头
            fixTableTH("goodslocationAxisdictionary-cont");
        }
    }
})();




