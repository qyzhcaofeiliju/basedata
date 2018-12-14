$(document).ready(function () {
    billsCategory.bindEvent();
    billsCategory.fixEvent();
    bindTooltipEvent();
});
var billsCategory = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url: SysCfg.getFullAddress()+"/ipsp/billsCategory/validIsExist",
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
        "catName": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'catName':'" + inputValue + "'}");
            }
        }],
        "catCategory": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'catCategory':'" + inputValue + "'}");
            }
        }]
    }

    var cache = {
        "cached": false,
        "mod": null,
        "catId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#billsCategory_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#billsCategory_UptAdd_OptModal").modal("show");
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
        var callback = function(){
            FormValidator.validForm($("#billsCategory_UptAddForm"),selfValid,
                $("#billsCategory_UptAddForm").serializeObject());
        }
        var catId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["catId"] && cache["catId"] == catId) {
                $("#billsCategory_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["catId"] = catId;
                __loadEditData(catId,callback);
            }
        }
        else {
            __loadEditData(catId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var catId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#billsCategory_OKBtn").data("opt", {"tr": delTr, "catId": catId});
        $("#billsCategory_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    };

    var __loadAddData = function (callback) {
        $("#billsCategory_AddBtnExe").show();
        $("#billsCategory_UptBtnExe").hide();
        $("#billsCategory_UptAddTitle").text("新增功能分类信息");

        $("#billsCategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/billsCategory/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (catId,callback) {
        $("#billsCategory_AddBtnExe").hide();
        $("#billsCategory_UptBtnExe").show();
        $("#billsCategory_UptAddTitle").text("编辑功能分类信息");

        if (!catId) catId = '';
        $("#billsCategory_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/billsCategory/edit/" + catId, function (data) {
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
        var formData = JSON.stringify($("#find_billsCategory").serializeObject());
        $.ajax({
            method: "get",
            url: SysCfg.getFullAddress()+"/ipsp/billsCategory/page",
            data: {pageData: pageData, formData: formData},
            dataType: "json",
            success: function (data) {
                var page = data.page;
                var pageList = data.pageList;
                var list = data.list;
                var tbodyHtml = "";
                for (var i in list) {
                    tbodyHtml += "<tr>" +
                        "<td hidden>" + list[i].catId + "</td>" +
                        "<td>" + list[i].catName + "</td>" +
                        "<td>" + list[i].catCategory + "</td>"
                        if(parseInt(list[i].catOwnercategory)==1){
                            tbodyHtml+="<td>入库单</td>"
                        }
                        if(parseInt(list[i].catOwnercategory)==2){
                            tbodyHtml+="<td>出库单</td>"
                        }
                    tbodyHtml+="<td class='ew-tooltip'>" +
                            "<button name='billsCategoryBtnVU' data=" + list[i].catId + " type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#billsCategory_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>" +
                        "</td>"+
                        "<td class='ew-tooltip'>" +
                            "<button name='billsCategoryBtnDel' type='button' data=" + list[i].catId + " class='btn btn-danger fa fa-trash-o' data-toggle='modal' data-placement='bottom' data-original-title='删除'></button>" +
                        "</td>"+
                    "</tr>"

                }
                var pageListHtml = "";
                pageListHtml += "<ul class='pagination'>" +
                    "<li class=" + (1 == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + "  " + (1 == page.pageNum ? "" : "id='billsCategoryPagePrev'") + " >" +
                    "<span>&laquo;</span>" +
                    "</li>";
                for (i in pageList) {
                    pageListHtml += "<li class=" + (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'") + ">" +
                        "<a class='page-link' name='pages' >" + pageList[i] + "</a>" +
                        "</li>"
                }
                pageListHtml += "<li class=" + (page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'") + " " + (page.totalPageNum == page.pageNum ? "" : "id='billsCategoryPageNext'") + ">" +
                    "<span>&raquo;</span>" +
                    "</li>" +
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#billsCategory_PageList").html(pageListHtml);
                billsCategory.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#billsCategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#billsCategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.catName + "，分类：" + dataObj.catCategory;

        // 隐藏（新增/编辑）模态框
        $("#billsCategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url: SysCfg.getFullAddress()+"/ipsp/billsCategory/save",
            method: "POST",
            data: {"data": jsonStrData},
            success: function (data) {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");
            },
            error: function (data) {
                uptAdd_Back_Msg = uptAdd_Back_Msg + " }：添加数据失败，请再次添加 ！";
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }

    var updateFunExe =function (){
        // Object
        var dataObj = $("#billsCategory_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = "{ 名称：" + dataObj.catName + "，分类'：" + dataObj.catCategory;
        var catId = $(":input[name=catId]").val();

        // 隐藏（新增/编辑）模态框
        $("#billsCategory_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url: SysCfg.getFullAddress()+"/ipsp/billsCategory/update" + catId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + "}：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='" + catId + "']").parents("tr:first");
                tr.find("td:eq(1)").text(dataObj["catName"]);
                tr.find("td:eq(2)").text(dataObj["catCategory"]);
                if(dataObj["catOwnercategory"]==1){
                    tr.find("td:eq(3)").text("入库单");
                }
                if(dataObj["catOwnercategory"]==2){
                    tr.find("td:eq(3)").text("出库单");
                }
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + "}：修改数据失败，请再次尝试 ！";
                // 保存数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }
    // update data to database
    var updateFun = function () {
        if (FormValidator.commitForm($("#billsCategory_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // delete data to database after Confirm Delete Operation
    var deleteFun = function () {
        // 隐藏删除操作时弹出的确认模态框
        $("#billsCategory_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#billsCategory_OKBtn").data("opt");
        // 获取catId
        var catId = opt["catId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var catName = delTr.find("td:eq(1)").text();
        var catCategory = delTr.find("td:eq(2)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = "{ 名称：" + catName + "，分类：" + catCategory;
        $.ajax({
            url: SysCfg.getFullAddress()+"/ipsp/billsCategory/delete" + catId,
            method: "DELETE",
            success: function (data) {
                //判断是否执行删除操作，如果没有执行，根据错误信息提示。
                if (data==1){
                    uptAdd_Back_Msg = uptAdd_Back_Msg + "}：删除数据成功 ！";
                    // 删除数据成功之后修改样式和添加提示信息
                    IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                    // delTr.remove;
                    var curPage = $("ul.pagination li.active a").text();
                    pageFun(curPage);
                }
                if(data==2){
                    uptAdd_Back_Msg = uptAdd_Back_Msg + "}：删除数据失败，此单据使用中，不可删除 ！";
                    // 删除数据失败之后修改样式和添加提示信息
                    IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
                }
            },
            error: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + "}：删除数据失败，请再次尝试 ！";
                // 删除数据失败之后修改样式和添加提示信息
                IdxTip.tiperr(uptAdd_Back_Msg,"温馨提示");
            }
        });
    }
    //查询框重置按钮
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
            $("#billsCategoryPageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#billsCategoryPagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='billsCategoryBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='billsCategoryBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_billsCategory]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#billsCategory_AddBtn").click(addFun);

            $("#billsCategory_Close").click(cancelFun);

            // Add
            $("#billsCategory_AddBtnExe").click(saveFun);

            // Update
            $("#billsCategory_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#billsCategory_OKBtn").click(deleteFun);

            //清空查询框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //表头固定
            fixTableTH("billsCategory-cont");
        }
    }
})();