$(document).ready(function () {
    EWFileInput.init({language:"zh", showUpload:false});
    suitcaseProfile.bindEvent();
    suitcaseProfile.fixEvent();
    bindTooltipEvent();
});
var suitcaseProfile = (function () {

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/validIsExist",
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
        "proUniquecode": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'proUniquecode':'" + inputValue + "'}");
            }
        }],
        "proRfid": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'proRfid':'" + inputValue + "'}");
            }
        }]
    }

    var cache = {
        "cached": false,
        "mod": null,
        "proId": null
    }

    var addFun = function () {
        var callback = function () {
            FormValidator.validForm($("#suitcaseProfile_UptAddForm"), selfValid);
        }

        if (cache["cached"]) {
            if (cache["mod"] == 1) {
                $("#suitcaseProfile_UptAdd_OptModal").modal("show");
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
            var oldData = $("#suitcaseProfile_UptAddForm").serializeObject();
            FormValidator.validForm($("#suitcaseProfile_UptAddForm"), selfValid,oldData);
        }
        var proId = $(this).attr("data");
        if (cache["cached"]) {
            if (cache["mod"] == 2 && cache["proId"] && cache["proId"] == proId) {
                $("#suitcaseProfile_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"] = 2;
                cache["proId"] = proId;
                __loadEditData(proId,callback);
            }
        }
        else {
            __loadEditData(proId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function () {
        var proId = $(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#suitcaseProfile_OKBtn").data("opt", {"tr": delTr, "proId": proId});
        $("#suitcaseProfile_Affirm").modal('show');
    };

    var cancelFun = function () {
        cache["mod"] = null;
    }

    var __loadAddData = function (callback) {
        $("#suitcaseProfile_AddBtnExe").show();
        $("#suitcaseProfile_UptBtnExe").hide();
        $("#suitcaseProfile_UptAddTitle").text("新增功能分类信息");

        $("#suitcaseProfile_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/edit", function (data) {
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    var __loadEditData = function (proId,callback) {
        $("#suitcaseProfile_AddBtnExe").hide();
        $("#suitcaseProfile_UptBtnExe").show();
        $("#suitcaseProfile_UptAddTitle").text("编辑功能分类信息");

        if (!proId) proId = '';
        $("#suitcaseProfile_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/edit/" + proId, function (data) {
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
        var formData = JSON.stringify($("#find_suitcaseProfile").serializeObject());
        $.ajax({
            method:"get",
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/page",
            data:{pageData:pageData,formData:formData},
            dataType:"json",
            success:function(data) {
                var page=data.page;
                var pageList =data.pageList;
                var list=data.list;
                var tbodyHtml="";
                for (var i in list){
                    tbodyHtml+="<tr>"+
                        "<td hidden>"+list[i].proId+"</td>"+
                        "<td>"+list[i].proUniquecode+"</td>"+
                        "<td>"+list[i].proRfid+"</td>"+
                        "<td>"+list[i].proCabintotal+"</td>"+
                        "<td>"+list[i].funName+"</td>"+
                        "<td>"+list[i].appName+"</td>"
                        if(parseInt(list[i].proStatus)==1){
                            tbodyHtml+="<td>非空</td>"
                        }
                        if(parseInt(list[i].proStatus)==2){
                            tbodyHtml+="<td>空</td>"
                        }
                        if(parseInt(list[i].proStatus)==3){
                            tbodyHtml+="<td>异常（RFID无法识别）</td>"
                        }
                        if(parseInt(list[i].proStatus)==4){
                            tbodyHtml+="<td>损坏</td>";
                        }
                    tbodyHtml+="<td>"+(list[i].proIspartition==true?'是':'否')+"</td>"+
                        "<td>"+list[i].proVerticalamount+"</td>"+
                        "<td>"+list[i].proHorizontalamount+"</td>"+
                        "<td>"+list[i].proLength+"</td>"+
                        "<td>"+list[i].proWidth+"</td>"+
                        "<td>"+list[i].proHeight+"</td>"+
                        "<td>"+list[i].proCabinwidth+"</td>"+
                        "<td  class='ew-tooltip'>"+
                            "<button name='suitcaseProfileBtnVU' data="+list[i].proId+" type='button' class='btn btn-primary fa fa-edit ' data-toggle='modal' data-target='#suitcaseProfile_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>"+
                        "</td>"+
                        "<td  class='ew-tooltip'>"+
                            "<button name='suitcaseProfileBtnDel' type='button' data="+list[i].proId+" class='btn btn-danger fa fa-trash-o ' data-toggle='modal' data-placement='bottom' data-original-title='删除' ></button>"+
                        "</td>"+
                    "</tr>"
                }
                var pageListHtml="";
                pageListHtml+="<ul class='pagination'>"+
                    "<li class="+(1==page.pageNum ? "'paginate_button disabled'":"'paginate_button'")+" "+(1==page.pageNum ? "''":"id='suitcaseProfilePagePrev'")+" >"+
                    "<span>&laquo;</span>"+
                    "</li>";
                for (i in pageList){
                    pageListHtml+="<li class="+ (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'")+">"+
                        "<a class='page-link' name='pages' >"+pageList[i]+"</a>"+
                        "</li>"
                }
                pageListHtml+="<li class="+(page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'")+" "+(page.totalPageNum == page.pageNum ? "" : "id='suitcaseProfilePageNext'")+">"+
                    "<span>&raquo;</span>"+
                    "</li>"+
                    "</ul>"
                $("tbody").html(tbodyHtml);
                $("#suitcaseProfile_PageList").html(pageListHtml);
                suitcaseProfile.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#suitcaseProfile_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }

    }
    // save data to database
    var saveFunExe = function () {
        // Object
        var dataObj = $("#suitcaseProfile_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = '{唯一码：'+dataObj.proUniquecode+',RFID：'+dataObj.proRfid;

        // 隐藏（新增/编辑）模态框
        $("#suitcaseProfile_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/save",
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

    var updateFunExe =function (){
        // Object
        var dataObj = $("#suitcaseProfile_UptAddForm").serializeObject();

        //json 格式字符串数据
        var jsonStrData = JSON.stringify(dataObj);

        var uptAdd_Back_Msg = '{唯一码：'+dataObj.proUniquecode+',RFID：'+dataObj.proRfid;
        var proId = $(":input[name=proId]").val();

        // 隐藏（新增/编辑）模态框
        $("#suitcaseProfile_UptAdd_OptModal").modal('hide');

        // ajax 保存
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/update" + proId,
            method: "PUT",
            data: {"data": jsonStrData},//输出修改模态框中表单的序列化结果
            dataType:"json",
            success: function (mapData) {
                var vwSuitcaseProfile = mapData.vwSuitcaseProfile;
                uptAdd_Back_Msg = uptAdd_Back_Msg + "}：修改数据成功 ！";
                // 保存数据成功之后修改样式和添加提示信息
                IdxTip.tipsuc(uptAdd_Back_Msg,"温馨提示");

                // 数据回显到view页面相应的位置
                var tr = $("tbody button[data='"+proId+"']").parents("tr:first");
                tr.find("td:eq(1)").text(vwSuitcaseProfile.proUniquecode);
                tr.find("td:eq(2)").text(vwSuitcaseProfile.proRfid);
                tr.find("td:eq(3)").text(vwSuitcaseProfile.proCabintotal);
                tr.find("td:eq(4)").text(vwSuitcaseProfile.funName);
                tr.find("td:eq(5)").text(vwSuitcaseProfile.appName);
                if(parseInt(vwSuitcaseProfile.proStatus)==1){
                    tr.find("td:eq(6)").text("非空");
                }
                if(parseInt(vwSuitcaseProfile.proStatus)==2){
                    tr.find("td:eq(6)").text("空");
                }
                if(parseInt(vwSuitcaseProfile.proStatus)==3){
                    tr.find("td:eq(6)").text("异常（RFID无法识别）");
                }
                if(parseInt(vwSuitcaseProfile.proStatus)==4){
                    tr.find("td:eq(6)").text("损坏");
                }
                tr.find("td:eq(7)").text(vwSuitcaseProfile.proIspartition=='true'?"是":"否");
                tr.find("td:eq(8)").text(vwSuitcaseProfile.proVerticalamount);
                tr.find("td:eq(9)").text(vwSuitcaseProfile.proHorizontalamount);
                tr.find("td:eq(10)").text(vwSuitcaseProfile.proLength);
                tr.find("td:eq(11)").text(vwSuitcaseProfile.proWidth);
                tr.find("td:eq(12)").text(vwSuitcaseProfile.proHeight);
                tr.find("td:eq(13)").text(vwSuitcaseProfile.proCabinwidth);
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
        if (FormValidator.commitForm($("#suitcaseProfile_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            updateFunExe();
        }
    }

    // delete data to database after Confirm Delete Operation
    var deleteFun = function () {
        // 隐藏删除操作时弹出的确认模态框
        $("#suitcaseProfile_Affirm").modal('hide');

        // 获取删除按钮绑定的数据
        var opt = $("#suitcaseProfile_OKBtn").data("opt");
        // 获取proId
        var proId = opt["proId"];

        // 获取要删除的tr
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var proUniquecode = delTr.find("td:eq(1)").text();
        var proRfid = delTr.find("td:eq(2)").text();

        // 获取回显信息的div和回显信息
        var uptAdd_Back_Msg = '{唯一码：'+proUniquecode+',RFID：'+proRfid;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/delete" + proId,
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

    //清空input框
    var findRemove = function(){
        $(this).prev().val("");
        $(this).attr("style","z-index:1");
    }

    //回填需要确认的值
    var funAndAppFind = function(){
        var callback = function () {
            FormValidator.validForm($("#suitcaseProfile_ImportForm"), selfValid);
        }
        // 都通过验证的话，就触发保存操作
        $("#download_suitcaseProfile_UptAdd_OptModal").modal('hide');
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/funAndAppFind",
            type: "GET",
            success: function (data) {
                $("#showImportSuitcaseProfile").modal('show');
                $("#viewImportSuitcaseProfile_AddBody").html(data);
                callback();
                //编辑页面清空按钮
                $(".cleanBtn").click(findRemove);
                CleanInput.bindEvent($(".cleanBtn").prev("input"));
            }
        });
    }



    //查看导入数据详情操作
    var importDataFind = function(){
        if (FormValidator.commitForm($("#suitcaseProfile_ImportForm"), selfValid, "通知")) {
            //隐藏周转箱描述填写信息
            $("#showImportSuitcaseProfile").modal('hide');
            //获取导入的文件
            var file = document.querySelector("#file_input");
            //获取周转箱描述填写的信息
            var data = JSON.stringify($("#suitcaseProfile_ImportForm").serializeObject());
            var fileObj = file.files[0];
            var formData = new FormData();
            formData.append('file', fileObj);
            formData.append('data',data);
            ajax(formData);
        }
    }

    function ajax(formData){
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/showImportData",
            method: "POST",
            data: formData,
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data != ''){
                    $("#showImportSuitcaseProfileInfo").modal('show');
                    $("#showImportData").html(data);
                }
            }
        });
    }

    //保存excel数据
    var saveImportData = function () {
        //点击保存按钮的时候，需要获得两个页面的数据,组合成对象或者json格式,一起传给后台
        var value1 =  $("#proFunccategory").val();
        var value2 = $("#proAppcategory").val();
        var value3 = $("[name=proIspartition]").val();
        var value4 =  $("#proVerticalamount").val();
        var value5 =  $("#proHorizontalamount").val();
        var objParam = {value1:value1,value2:value2,value3:value3,value4:value4,value5:value5};
        var objDate = JSON.stringify(objParam);
        //获取导入的数据
        var file = document.querySelector("#file_input");
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        formData.append('objDate',objDate);
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/suitcaseProfile/saveImportData",
            method: "post",
            data: formData,
            dataType: "json",
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data == true){
                    IdxTip.tipsuc("导入成功！","温馨提示");
                    $("#showImportSuitcaseProfileInfo").modal('hide');
                }else {
                    IdxTip.tiperr("导入失败，请重试！","温馨提示");
                    $("#showImportSuitcaseProfileInfo").modal('hide');
                }
            }
        })
    }

    //上一步
    var suitcaseImportBack = function(){
        //隐藏excel数据展示页面
        $("#showImportSuitcaseProfileInfo").modal('hide');
        //显示描述信息填选页面
        $("#showImportSuitcaseProfile").modal('show');

    }

    return {
        bindEvent: function () {
            //分页
            $("a[name=pages]").click(function () {
                pageFun($(this).text());
            });
            //下一页
            $("#suitcaseProfilePageNext").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) + 1);
            });
            //上一页
            $("#suitcaseProfilePagePrev").click(function () {
                pageFun(parseInt($("li.paginate_button.active a").text()) - 1);
            });

            // 编辑绑定事件
            $("tbody button[name='suitcaseProfileBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='suitcaseProfileBtnDel']").click(delBtnFun);
        },
        fixEvent: function () {
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_suitcaseProfile]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#suitcaseProfile_AddBtn").click(addFun);

            $("#suitcaseProfile_Close").click(cancelFun);

            // Add
            $("#suitcaseProfile_AddBtnExe").click(saveFun);

            // Update
            $("#suitcaseProfile_UptBtnExe").click(updateFun);

            // Confirm Delete Operation
            $("#suitcaseProfile_OKBtn").click(deleteFun);

            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //回填需要确认的值
            $("#importSuitcaseProfile_save").click(funAndAppFind);

            //查看导入数据详情操作
            $("#viewSuitcaseProfileInfo_AddBtnExe").click(importDataFind);

            //保存excel数据
            $("#importFileInfo_Save").click(saveImportData);

            //上一步
            $("#importFileInfo_Back").click(suitcaseImportBack);

            //表头固定
            fixTableTH("suitcaseProfile-cont");
        }
    }
})();