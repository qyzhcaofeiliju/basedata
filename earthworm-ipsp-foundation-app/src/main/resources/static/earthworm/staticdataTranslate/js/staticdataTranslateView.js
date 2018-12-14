$(document).ready(function () {
    staticdataTranslate.bindEvent();
    staticdataTranslate.fixEvent();
    bindTooltipEvent();
});
var staticdataTranslate = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/validIsExist",
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
        "traAppcategory": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'traAppcategory':'" + inputValue + "'}");
            }
        }],
        "traRawvalue": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'traRawvalue':'" + inputValue + "'}");
            }
        }],
        "traOwnername": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'traOwnername':'" + inputValue + "'}");
            }
        }]
    }

    var cache = {
        "cached": false,
        "mod": null,
        "traId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#staticdataTranslate_UptAddForm"), selfValid);
        }
        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#staticdataTranslate_UptAdd_OptModal").modal("show");
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
            var oldData = $("#staticdataTranslate_UptAddForm").serializeObject();
            FormValidator.validForm($("#staticdataTranslate_UptAddForm"), selfValid,oldData);
        }
        var traId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["traId"] && cache["traId"] == traId) {
                $("#staticdataTranslate_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["traId"] = traId;
                __loadEditData(traId,callback);
            }
        }
        else {
            __loadEditData(traId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var traId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#staticdataTranslate_OKBtn").data("opt", {"tr": delTr, "traId": traId});
        $("#staticdataTranslate_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#staticdataTranslate_AddBtnExe").show();
        $("#staticdataTranslate_UptBtnExe").hide();
        $("#staticdataTranslate_UptAddTitle").text("新增静态数据翻译信息");

        $("#staticdataTranslate_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (traId,callback) {
        $("#staticdataTranslate_AddBtnExe").hide();
        $("#staticdataTranslate_UptBtnExe").show();
        $("#staticdataTranslate_UptAddTitle").text("编辑静态数据翻译信息");

        if (!traId) traId = '';
        $("#staticdataTranslate_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/edit/" + traId, function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    //分页相关函数
    var pageFun = function(pageNum) {
        var pageSize = $("[name=selPageSize]").val();
        var pageParam={pageNum:pageNum,pageSize:pageSize};
        var pageData=JSON.stringify(pageParam);
        var formData = JSON.stringify($("#find_staticdataTranslate").serializeObject());
        $.ajax({
            method:"get",
            url:SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/page",
            data:{pageData:pageData,formData:formData},
            dataType:"json",
            success:function(data) {
                var page=data.page;
                var pageList =data.pageList;
                var list=data.list;
                var tbodyHtml="";
                for (var i in list){
                    tbodyHtml+="<tr>"+
                        "<td hidden>"+list[i].traId+"</td>"+
                        "<td>"+list[i].traAppcategory+"</td>"+
                        "<td>"+list[i].traRawvalue+"</td>"+
                        "<td>"+list[i].traTranslatevalue+"</td>"+
                        "<td>"+list[i].traOwnername+"</td>"+
                        "<td  class='ew-tooltip'>"+
                            "<button name='staticdataTranslateBtnVU' data="+list[i].traId+" type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#staticdataTranslate_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>"+
                        "</td>"+
                        "<td  class='ew-tooltip'>"+
                            "<button name='staticdataTranslateBtnDel' type='button' data="+list[i].traId+" class='btn btn-danger fa fa-trash-o ' data-toggle='modal' data-placement='bottom data-original-title='删除' ></button>"+
                        "</td>"+
                    "</tr>"

                }
                var pageListHtml="";
                pageListHtml+="<ul class='pagination'>"+
                    "<li class="+(1==page.pageNum ? "'paginate_button disabled'":"'paginate_button'")+" "+(1==page.pageNum ? "":"id='staticdataTranslatePagePrev'")+">"+
                    "<span>&laquo;</span>"+
                    "</li>";
                for (i in pageList){
                    pageListHtml+="<li class="+ (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'")+">"+
                        "<a class='page-link' name='pages' >"+pageList[i]+"</a>"+
                        "</li>"
                }
                pageListHtml+="<li class="+(page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'")+" "+(page.totalPageNum == page.pageNum ? "":"id='staticdataTranslatePageNext'")+">"+
                    "<span>&raquo;</span>"+
                    "</li>"+
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#staticdataTranslate_PageList").html(pageListHtml);
                staticdataTranslate.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#staticdataTranslate_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#staticdataTranslate_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = '{分类：'+dataObj.traAppcategory+'，原始值：'+dataObj.traRawvalue+'，翻译值：'+dataObj.traTranslatevalue;

        // 隐藏（新增/编辑）模态框
        $("#staticdataTranslate_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/save",
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
    var updateFunExe = function(){
        // Object
        var dataObj = $("#staticdataTranslate_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = '{分类：'+dataObj.traAppcategory+'，原始值：'+dataObj.traRawvalue+'，翻译值：'+dataObj.traTranslatevalue;
        var traId = $(":input[name=traId]").val();

        // 隐藏（新增/编辑）模态框
        $("#staticdataTranslate_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/update" + traId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            success: function () {
                uptAdd_Back_Msg = uptAdd_Back_Msg + "}：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='"+traId+"']").parents("tr:first");
                tr.find("td:eq(1)").text(dataObj["traAppcategory"]);
                tr.find("td:eq(2)").text(dataObj["traRawvalue"]);
                tr.find("td:eq(3)").text(dataObj["traTranslatevalue"]);
                tr.find("td:eq(4)").text(dataObj["traOwnername"]);
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
        if (FormValidator.commitForm($("#staticdataTranslate_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // delete data to database after Confirm Delete Operation
    var deleteFun = function () {
        // 隐藏删除操作时弹出的确认模态框
        $("#staticdataTranslate_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#staticdataTranslate_OKBtn").data("opt");
        // 获取traId
        var traId = opt["traId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var traAppcategory = delTr.find("td:eq(1)").text();
        var traRawvalue = delTr.find("td:eq(2)").text();
        var traTranslatevalue = delTr.find("td:eq(3)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = '{分类：'+traAppcategory+'，原始值：'+traRawvalue+'，翻译值：'+traTranslatevalue;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/staticdataTranslate/delete" + traId,
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
            $("#staticdataTranslatePageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#staticdataTranslatePagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='staticdataTranslateBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='staticdataTranslateBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_staticdataTranslate]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#staticdataTranslate_AddBtn").click(addFun);

            $("#staticdataTranslate_Close").click(cancelFun);

            // Add
            $("#staticdataTranslate_AddBtnExe").click(saveFun);

            // Update
            $("#staticdataTranslate_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#staticdataTranslate_OKBtn").click(deleteFun);

            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //固定表头
            fixTableTH("staticdataTranslate-cont");

        }
    }
})();