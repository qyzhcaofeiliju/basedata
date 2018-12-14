$(document).ready(function() {
    fixshelfBillinfo.bindEvent();
    fixshelfBillinfo.fixEvent();
    bindTooltipEvent();
});
var fixshelfBillinfo = (function(){

    //自定义验证，此处验证名称和分类是否重复，入库单
    var selfValid = {
        "billNumber": function (billNumber) {
            var rs = true;
            // ajax 后台验证是否存在
            $.ajax({
                url: SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/validIsExist",
                method: "GET",
                data: {"data": "{'billNumber':'" + billNumber + "'}"},
                // 改为同步验证
                async: false,
                success: function (data) {
                    rs = data;
                },
                error: function () {
                    rs = false;
                }
            });
            return rs;
        }
    }
    //自定义验证，此处验证名称和分类是否重复，入库单详情
    var selfValid1 = {
        "fixBillsnumber": function (fixBillsnumber) {
            var rs = true;
            // ajax 后台验证是否存在
            $.ajax({
                url: SysCfg.getFullAddress()+"/ipsp/fixshelfDetailinfo/validIsExist",
                method: "GET",
                data: {"data": "{'fixBillsnumber':'" + fixBillsnumber + "'}"},
                // 改为同步验证
                async: false,
                success: function (data) {
                    rs = data;
                },
                error: function () {
                    rs = false;
                }
            });
            return rs;
        },
        "fixMaterialuniquecode": function (fixMaterialuniquecode) {
            var rs = true;
            // ajax 后台验证是否存在
            $.ajax({
                url: SysCfg.getFullAddress()+"/ipsp/fixshelfDetailinfo/validIsExist",
                method: "GET",
                data: {"data": "{'fixMaterialuniquecode':'" + fixMaterialuniquecode + "'}"},
                // 改为同步验证
                async: false,
                success: function (data) {
                    rs = data;
                },
                error: function () {
                    rs = false;
                }
            });
            return rs;
        }
    }

    var cache = {
        "cached":false,
        "mod": null,
        "billId": null,
        "billNumber":null
    }

    //编辑执行的方法
    var editFun = function() {
        var billId=$(this).attr("data");
        if(cache["cached"]) {
            if(cache["mod"]==2 && cache["billId"] && cache["billId"]==billId) {
                $("#fixshelfBillinfo_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"]=2;
                cache["billId"]=billId;
                __loadEditData(billId);
            }
        }
        else {
            __loadEditData(billId);
        }
    };

    //入库单编辑页面的显示
    var __loadEditData = function (billId) {
        $("#fixshelfBillinfo_AddBtnExe").hide();
        $("#fixshelfBillinfo_UptBtnExe").show();
        $("#fixshelfBillinfo_UptAddTitle").text("编辑固定货架表单信息");

        if(!billId) billId='';
        $("#fixshelfBillinfo_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/edit/"+billId,function(data){
            $(this).html(data);
        });
    }

    // 删除执行的方法
    var delBtnFun = function() {
        var billId=$(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#fixshelfBillinfo_OKBtn").data("opt",{"tr":delTr,"billId":billId});
        $("#fixshelfBillinfo_Affirm").modal('show');
    };

    // 查看详情
    var findBtnFun = function() {
        var billNumber=$(this).attr("data");
        if(cache["cached"]) {
            if(cache["mod"]==3 && cache["billNumber"] && cache["billNumber"]==billNumber) {
                $("#fixshelfBillinfo_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"]=3;
                cache["billNumber"]=billNumber;
                __loadFindData(billNumber);
            }
        }
        else {
            __loadFindData(billNumber);
        }
    };

    //点击增加按钮
    var addFun = function() {
        var callback = function () {
            FormValidator.validForm($("#fixshelfBillinfo_UptAddForm"), selfValid);
        }
        if(cache["cached"]) {
            if(cache["mod"]==1){
                $("#fixshelfBillinfo_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"]=1;
                __loadAddData(callback);
                cancelFun();
            }
        }
        else {
            __loadAddData(callback);
            cancelFun();
        }
    };

    //显示入库单的编辑页面
    var __loadAddData = function(callback){
        $("#fixshelfBillinfo_AddBtnExe").show();
        $("#fixshelfBillinfo_UptBtnExe").hide();
        $("#fixshelfBillinfo_UptAddTitle").text("新增固定货架表单信息");

        $("#fixshelfBillinfo_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/edit",function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    //入库单验证
    var saveFun = function () {
        if (FormValidator.commitForm($("#fixshelfBillinfo_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            addDoubleFun();
        }
    }
    //点击上一步
    var prevFunExe = function(){
        $("#fixshelfDetailinfoDouble_UptAdd_OptModal").modal('hide');
        $("#fixshelfBillinfo_UptAdd_OptModal").modal('show');
    }
    //点击下一步按钮
    var addDoubleFun = function(){
        var callback = function () {
            FormValidator.validForm($("#fixshelfDetailinfo_UptAddForm"), selfValid1);
        }
        if(cache["mod"]==1){
            $("#fixshelfDetailinfoDouble_UptAdd_OptModal").modal("show");
        }
        else {
            cache["mod"]=1;
            __loadAddDoubleData(callback);
        }
    }

    //查询框重置按钮
    var findRemove = function(){
        $(this).prev().val("");
        $(this).attr("style","z-index:1");
    }

    //入库单详情编辑页面的的显示
    var __loadAddDoubleData = function(callback){
        $("#fixshelfBillinfo_UptAdd_OptModal").modal("hide");
        $("#fixshelfDetailinfoDouble_UptAdd_OptModal").modal("show");
        $("#fixshelfDetailinfoDouble_AddBtnExe").show();
        $("#fixshelfDetailinfoDouble_UptBtnExe").hide();
        $("#fixshelfDetailinfoDouble_UptAddTitle").text("新增固定货架详情信息");
        var billNumber = $("#fixshelfBillinfo_UptAddForm [name=billNumber]").val();
        $("#fixshelfDetailinfoDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/fixshelfDetailinfo/detailAdd/"+billNumber,function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    //入库单详情验证
    var doubleSaveFun = function(){
        if (FormValidator.commitForm($("#fixshelfDetailinfo_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            doubleSaveFunExe();
        }
    }
    //执行添加操作
    var doubleSaveFunExe = function(){
        // ajax 保存
        var data = JSON.stringify({inwDetail:$("#fixshelfDetailinfo_UptAddForm").serializeObject(),inw:$("#fixshelfBillinfo_UptAddForm").serializeObject()});
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/doubleSave",
            method:"POST",
            data:{data:data},
            success:function(){

                var dataBack=$("#fixshelfDetailinfo_UptAddForm").serializeObject();
                /*$("#fixshelfDetailinfo_Close").click();*/
                $("#fixshelfDetailinfoDouble_UptAdd_OptModal").modal('hide');
                $("#fixshelfBillinfo_UptAdd_OptModal").modal('hide');

                $("#fixshelfDetailinfo_UptAdd_Back").removeClass('alert-danger');

                $("#fixshelfDetailinfo_UptAdd_Back").addClass('alert-success');
                $("#fixshelfBillinfo_UptAdd_Back").removeAttr("hidden")
                $("#fixshelfBillinfo_UptAdd_Back span").text('编号：'+dataBack.billNumber+'，物料码：'+dataBack.inwCode+'，批次：'+dataBack.fixCurrentsite+'：添加数据成功！');
                $("#fixshelfDetailinfo_UptAdd_Back").show();
                $("#fixshelfDetailinfo_UptAdd_Back").removeAttr("hidden");

            },
            error:function(){
                // alert("请输入有效值......")
                $("#fixshelfDetailinfo_UptAdd_OptModal").modal('hide');
                $("#fixshelfDetailinfo_UptAdd_Back").show();
                $("#fixshelfDetailinfo_UptAdd_Back").removeAttr("hidden");
                $("#fixshelfDetailinfo_UptAdd_Back").removeClass('alert-success');
                $("#fixshelfDetailinfo_UptAdd_Back").addClass('alert-danger');
                $("#fixshelfDetailinfo_UptAdd_Back span").text('数据添加失败，请尝试再添加操作 ！');
                /*location.reload();*/
            }
        });
    }

    //入库单对应的入库单详情列表
    var __loadFindData = function (billNumber) {
        $("#fixshelfBillinfo_AddBtnExe").hide();
        $("#fixshelfBillinfo_UptBtnExe").hide();
        $("#fixshelfBillinfo_UptAddTitle").text("入库单详情信息");

        if(!billNumber) billNumber='';
        $("#fixshelfBillinfo_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/find/"+billNumber,function(data){
            $(this).html(data);
            var script = $("#fixshelfBillinfo_UptAdd_OptBody input[name='script']").val();
            $.getScript(script);
        });
    }
    //修改显示入库单详情
    var doubelUptFun =  function(){
        var dataBack=$("#fixshelfBillinfo_UptAddForm").serializeObject();
        var billNumber = dataBack.billNumber;
        var billId = dataBack.billId;
        $("#fixshelfBillinfo_UptAdd_OptModal").modal("hide");
        $("#fixshelfDetailinfoDouble_UptAdd_OptModal").modal("show");
        $("#fixshelfDetailinfoDouble_AddBtnExe").hide();
        $("#fixshelfDetailinfoDouble_UptBtnExe").show();
        $("#fixshelfDetailinfoDouble_UptAddTitle").text("编辑入库单详情信息");
        $("#fixshelfDetailinfoDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/fixshelfDetailinfo/detailUpt/"+billNumber+"/"+billId,function(data){
            $(this).html(data);
        });
    }
    //执行修改操作
    var  doubleUptFunExe = function(){
        // ajax 编辑
        var data = JSON.stringify({inwDetail:$("#fixshelfDetailinfo_UptAddForm").serializeObject(),inw:$("#fixshelfBillinfo_UptAddForm").serializeObject()});
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/doubleUpt",
            method:"POST",
            data:{data:data},
            success:function(){
                var dataBack=$("#fixshelfDetailinfo_UptAddForm").serializeObject();
                /*$("#fixshelfDetailinfo_Close").click();*/
                $("#fixshelfDetailinfoDouble_UptAdd_OptModal").modal('hide');
                $("#fixshelfBillinfo_UptAdd_OptModal").modal('hide');

                $("#fixshelfDetailinfo_UptAdd_Back").removeClass('alert-danger');

                $("#fixshelfDetailinfo_UptAdd_Back").addClass('alert-success');
                $("#fixshelfBillinfo_UptAdd_Back").removeAttr("hidden")
                $("#fixshelfBillinfo_UptAdd_Back span").text('编号：'+dataBack.billNumber+'，物料码：'+dataBack.inwCode+'，当前站点：'+dataBack.fixCurrentsite+'：修改数据成功！');
                $("#fixshelfDetailinfo_UptAdd_Back").show();
                $("#fixshelfDetailinfo_UptAdd_Back").removeAttr("hidden");
                var seri = $("#fixshelfBillinfo_UptAddForm").serializeObject();
                var tr = $("tbody button[data='"+$("#fixshelfBillinfo_UptAddForm").serializeObject().billId+"']").parents("tr:first");
                tr.find("td:eq(1)").text(seri["billNumber"]);
                tr.find("td:eq(2)").text(seri["billArrivaltime"]);
                tr.find("td:eq(3)").text(seri["billStatus"]);
                tr.find("td:eq(4)").text(seri["billSite"]);
                tr.find("td:eq(5)").text(seri["billCategory"]);


            },
            error:function(){
                $("#fixshelfDetailinfo_UptAdd_OptModal").modal('hide');
                $("#fixshelfDetailinfo_UptAdd_Back").show();
                $("#fixshelfDetailinfo_UptAdd_Back").removeAttr("hidden");
                $("#fixshelfDetailinfo_UptAdd_Back").removeClass('alert-success');
                $("#fixshelfDetailinfo_UptAdd_Back").addClass('alert-danger');
                $("#fixshelfDetailinfo_UptAdd_Back span").text('数据修改失败，请尝试再添加操作 ！');
                /*location.reload();*/
            }
        });
    }

    //清空标记
    var cancelFun = function() {
        cache["mod"]=null;
    }

    //分页相关函数
    var pageFun = function(pageNum) {
        var pageSize = $("[name=selPageSize]").val();
        var pageParam={pageNum:pageNum,pageSize:pageSize};
        var pageData=JSON.stringify(pageParam);
        var formData = JSON.stringify($("#find_fixshelfBillinfo").serializeObject());
        $.ajax({
            method:"get",
            url:SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/page",
            data:{pageData:pageData,formData:formData},
            dataType:"json",
            success:function(data) {
                var page=data.page;
                var pageList =data.pageList;
                var list=data.list;
                var tbodyHtml="";
                for (var i in list){
                    tbodyHtml+="<tr>"+
                        "<td hidden>"+list[i].billId+"</td>"+
                        "<td>" + list[i].billNumber + "</td>" +
                        "<td>" + EWDate.getFullYMD(list[i].billArrivaltime) + "</td>" +
                        "<td>"+list[i].billStatus+"</td>"+
                        "<td>"+list[i].billSite+"</td>"+
                        "<td>"+list[i].billCategory+"</td>"+
                        "<td>"+
                        "<div class='ew-tooltip'>"+
                        "<button name='fixshelfBillinfoBtnVU' data="+list[i].billId+" type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#fixshelfBillinfo_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>\r\n"+
                        "<button name='fixshelfBillinfoBtnDel' type='button' data="+list[i].billId+" class='btn btn-danger fa fa-trash-o' data-toggle='modal' data-placement='bottom' data-original-title='删除' ></button>\r\n"+
                        "<button name='fixshelfBillinfoBtnFind' type='button' data="+list[i].billNumber+" class='btn btn-info fa fa-eye' data-toggle='modal' data-target='#fixshelfBillinfo_UptAdd_OptModal' data-placement='bottom' data-original-title='查看详情' ></button>"+
                        "</div>"+
                        "</td>"
                    "</tr>"

                }
                var pageListHtml="";
                pageListHtml+="<ul class='pagination'>"+
                    "<li class="+(1==page.pageNum ? "'paginate_button disabled'":"'paginate_button'")+" "+(1==page.pageNum ? "":"id='fixshelfBillinfoPagePrev'")+">"+
                    "<span>&laquo;</span>"+
                    "</li>";
                for (i in pageList){
                    pageListHtml+="<li class="+ (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'")+">"+
                        "<a class='page-link' name='pages' >"+pageList[i]+"</a>"+
                        "</li>"
                }
                pageListHtml+="<li class="+(page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'")+" "+(page.totalPageNum == page.pageNum ? "" : "id='fixshelfBillinfoPageNext'")+">"+
                    "<span>&raquo;</span>"+
                    "</li>"+
                    "</ul>"
                $("#fixshelfBillinfoTbody").html(tbodyHtml);
                $("#fixshelfBillinfo_PageList").html(pageListHtml);
                fixshelfBillinfo.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    //执行删除操作
    var deleteFun = function(){

        var opt = $("#fixshelfBillinfo_OKBtn").data("opt");
        var billId=opt["billId"];
        $("#fixshelfBillinfo_Affirm").modal('hide');
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var billNumber = delTr.find("td:eq(1)").text();
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/fixshelfBillinfo/delete"+billId,
            method:"DELETE",
            // data:{"data":data},//输出修改模态框中表单的序列化结果
            success:function(){
                $("#fixshelfBillinfo_UptAdd_Back").removeClass('alert-danger');

                $("#fixshelfBillinfo_UptAdd_Back").addClass('alert-success');

                $("#fixshelfBillinfo_UptAdd_Back span").text('{编号：'+billNumber+'}:数据删除成功 ！');
                $("#fixshelfBillinfo_UptAdd_Back").show();
                $("#fixshelfBillinfo_UptAdd_Back").removeAttr("hidden");
                // delTr.remove();
                var curPage = $("ul.pagination li.active a").text();
                pageFun(curPage);

            },
            error:function () {
                $("#fixshelfBillinfo_UptAdd_OptModal").modal('hide');
                $("#fixshelfBillinfo_UptAdd_Back").show();
                $("#fixshelfBillinfo_UptAdd_Back").removeAttr("hidden");
                $("#fixshelfBillinfo_UptAdd_Back").removeClass('alert-success');
                $("#fixshelfBillinfo_UptAdd_Back").addClass('alert-danger');
                $("#fixshelfBillinfo_UptAdd_Back span").text('数据删除失败...');
            }
        });
    }

    return {
        bindEvent: function(){
            //分页
            $("#fixshelfBillinfo_PageList a[name=pages]").click(function(){
                pageFun($(this).text());
            });
            //下一页
            $("#fixshelfBillinfoPageNext").click(function(){
                pageFun(parseInt($("#fixshelfBillinfo_PageList li.paginate_button.active a").text())+1);
            });
            //上一页
            $("#fixshelfBillinfoPagePrev").click(function(){
                pageFun(parseInt($("#fixshelfBillinfo_PageList li.paginate_button.active a").text())-1);
            });

            // 编辑绑定事件
            $("tbody button[name='fixshelfBillinfoBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='fixshelfBillinfoBtnDel']").click(delBtnFun);
            // 查看详情绑定事件
            $("tbody button[name='fixshelfBillinfoBtnFind']").click(findBtnFun);
        },
        fixEvent: function() {

            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_fixshelfBillinfo]").click(function () {
                pageFun(1);
            });
            // 添加按钮被点击
            $("#fixshelfBillinfo_AddBtn").click(addFun);

            $("#fixshelfBillinfo_Close").click(cancelFun);

            //下一步按钮被点击
            $("#fixshelfBillinfo_AddBtnExe").click(saveFun);

            //上一步
            $("#fixshelfDetailinfoDouble_PrevBtnExe").click(prevFunExe);

            //入库单及入库单详情增加
            $("#fixshelfDetailinfoDouble_AddBtnExe").click(doubleSaveFun);

            // 点击修改按钮
            $("#fixshelfBillinfo_UptBtnExe").click(doubelUptFun);

            //入库单及入库单详情编辑
            $("#fixshelfDetailinfoDouble_UptBtnExe").click(doubleUptFunExe);

            // Confirm Delete Operation
            $("#fixshelfBillinfo_OKBtn").click(deleteFun);
        }
    }
})();