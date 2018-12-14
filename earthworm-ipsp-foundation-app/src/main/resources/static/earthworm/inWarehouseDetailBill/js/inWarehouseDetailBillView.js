$(document).ready(function() {
    inWareDetailCate.bindEvent();
    inWareDetailCate.fixEvent();
    bindTooltipEvent();
});
var inWareDetailCate = (function(){

    // 校验生产日期与有效日期
    var checkDate = function () {
        var effDate = $("#inWarehouseDetailBill_UptAddForm input[name='inwEffectivedate']");
        var productDate = $("#inWarehouseDetailBill_UptAddForm input[name='inwProduceddate']");
        return effDate.val() < productDate.val() ? "有效日期不能小于生产日期" : undefined;
    };

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique = function (jsonStrData) {
        var rs = true;
        // ajax 后台验证是否存在
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/validIsExist",
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

    //自定义验证，此处验证名称和分类是否重复，入库单详情
    var selfValid = {
        "inwUniquecode": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'inwUniquecode':'" + inputValue + "'}");
            }
        }],
        // 验证生产日期和有效日期
        "inwEffectivedate": [{
            "type": 2,// type为2代表校验有效日期
            "fun": checkDate
        }],
        "inwProduceddate": [{
            "type": 2,
            "fun": checkDate
        }]
    }

    var cache = {
        "cached":false,
        "mod": null,
        "inwId": null
    }

    var addFun = function() {
        var callback = function () {
            FormValidator.validForm($("#inWarehouseDetailBill_UptAddForm"), selfValid);
        }
        var inwBillsnumber = $(this).attr("data");
        if(cache["cached"]) {
            if(cache["mod"]==1){
                $("#inWarehouseDetailBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"]=1;
                __loadAddData(inwBillsnumber,callback);
            }
        }
        else {
            __loadAddData(inwBillsnumber,callback);
        }
    };

    var editFun = function() {
        var callback = function () {
            FormValidator.validForm($("#inWarehouseDetailBill_UptAddForm"), selfValid,$("#inWarehouseDetailBill_UptAddForm").serializeObject());
        }
        var inwId=$(this).attr("data");
        if(cache["cached"]) {
            if(cache["mod"]==2 && cache["inwId"] && cache["inwId"]==inwId) {
                $("#inWarehouseDetailBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"]=2;
                cache["inwId"]=inwId;
                __loadEditData(inwId,callback);
            }
        }
        else {
            __loadEditData(inwId,callback);
        }
    };

    // 删除按钮
    var delBtnFun = function() {
        var inwId=$(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#inWarehouseDetailBill_OKBtn").data("opt",{"tr":delTr,"inwId":inwId});
        $("#inWarehouseDetailBill_Affirm").modal('show');
    };

    var cancelFun = function() {
        cache["mod"]=null;
    }

    var __loadAddData = function(inwBillsnumber,callback){
        $("#inWarehouseDetailBill_AddBtnExe").show();
        $("#inWarehouseDetailBill_UptBtnExe").hide();
        $("#inWarehouseDetailBill_UptAddTitle").text("新增入库单详情信息");
        $("#inWarehouseDetailBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/detailAdd/"+inwBillsnumber,function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var __loadEditData = function (inwId,callback) {
        $("#inWarehouseDetailBill_AddBtnExe").hide();
        $("#inWarehouseDetailBill_UptBtnExe").show();
        $("#inWarehouseDetailBill_UptAddTitle").text("编辑入库单详情信息");
        var inwBillsnumber = $("#inWarehouseDetailBill_AddBtn").attr("data");
        if(!inwId) inwId='';
        $("#inWarehouseDetailBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/edit/"+inwBillsnumber+"/"+inwId,function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    var saveFun = function () {
        if (FormValidator.commitForm($("#inWarehouseDetailBill_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            saveFunExe();
        }
    }

    var saveFunExe = function(){
        // ajax 保存
        var data = JSON.stringify($("#inWarehouseDetailBill_UptAddForm").serializeObject());
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/save",
            method:"POST",
            data:{"data":data},
            success:function(){
                var dataBack=$("#inWarehouseDetailBill_UptAddForm").serializeObject();
                $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');

                $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-danger');

                $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-success');
                $("#inWarehouseDetailBill_UptAdd_Back span").text('{编号：'+dataBack.inwBillsnumber+'，物料码：'+dataBack.inwCode+'，批次：'+dataBack.inwBatch+'}：添加数据成功！');
                $("#inWarehouseDetailBill_UptAdd_Back").show();
                $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");

            },
            error:function(){
                var dataBack=$("#inWarehouseDetailBill_UptAddForm").serializeObject();
                $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');
                $("#inWarehouseDetailBill_UptAdd_Back").show();
                $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-success');
                $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-danger');
                $("#inWarehouseDetailBill_UptAdd_Back span").text('{编号：'+dataBack.inwBillsnumber+'，物料码：'+dataBack.inwCode+'，批次：'+dataBack.inwBatch+'}：数据添加失败，请尝试再添加操作 ！');
            }
        });
    }

    //分页相关函数
    var pageFun = function(pageNum) {
        var pageSize = $("[name=selPageSize]").val();
        var inwBillsnumber=$("#inWarehouseDetailBill_AddBtn").attr("data");
        var pageParam={pageNum:pageNum,pageSize:pageSize};
        var pageData=JSON.stringify(pageParam);
        var formData = JSON.stringify($("#find_inWarehouseDetailBill").serializeObject());
        $.ajax({
            method:"get",
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/page",
            data:{pageData:pageData,formData:formData,inwBillsnumber:inwBillsnumber},
            dataType:"json",
            success:function(data) {
                var page=data.page;
                var pageList =data.pageList;
                var list=data.list;
                var tbodyHtml="";
                for (var i in list){
                    tbodyHtml+="<tr>"+
                        "<td hidden>"+list[i].inwId+"</td>"+
                        "<td>"+list[i].inwBillsnumber+"</td>"+
                        "<td>"+list[i].inwCode+"</td>"+
                        "<td>"+list[i].inwUniquecode+"</td>"+
                        "<td>"+list[i].inwUnit+"</td>"+
                        "<td>"+list[i].inwBatch+"</td>"+
                        "<td>"+list[i].inwAmount+"</td>"+
                        "<td>"+list[i].inwActualcount+"</td>"
                        if(list[i].inwStatus==0){
                            tbodyHtml+="<td>未入库</td>"
                        }
                        if(list[i].inwStatus==1){
                            tbodyHtml+="<td>入库中</td>"
                        }
                        if(list[i].inwStatus==2){
                            tbodyHtml+="<td>已入库</td>"
                        }
                        tbodyHtml+="<td class='ew-tooltip'>"+
                            "<button name='inWarehouseDetailBillBtnVU'data="+list[i].inwId+" type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#inWarehouseDetailBill_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>"+
                        "</td>"+
                        "<td class='ew-tooltip'>"+
                            "<button name='inWarehouseDetailBillBtnDel' type='button' data="+list[i].inwId+" class='btn btn-danger fa fa-trash-o' data-toggle='modal' data-placement='bottom' data-original-title='删除' ></button>"+
                        "</td>"+
                    "</tr>"

                }
                var pageListHtml="";
                pageListHtml+="<ul class='pagination'>"+
                    "<li class="+(1==page.pageNum ? "'paginate_button disabled'":"'paginate_button'")+" "+(1==page.pageNum ? "":"id='inWarehouseDetailBillPagePrev'")+" >"+
                    "<span>&laquo;</span>"+
                    "</li>";
                for (i in pageList){
                    pageListHtml+="<li class="+ (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'")+">"+
                        "<a class='page-link' name='pages' >"+pageList[i]+"</a>"+
                        "</li>"
                }
                pageListHtml+="<li class="+(page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'")+"  "+(page.totalPageNum == page.pageNum ? "" : "id='inWarehouseDetailBillPageNext'")+" >"+
                    "<span>&raquo;</span>"+
                    "</li>"+
                    "</ul>"
                $("#inWarehouseDetailBillTbody").html(tbodyHtml);
                $("#inWarehouseDetailBill_PageList").html(pageListHtml);
                inWareDetailCate.bindEvent();
                bindTooltipEvent();
            }
        });
    }

    var findRemove = function(){
        $(this).prev().val("");
        $(this).attr("style","z-index:1");
    }

    return {
        bindEvent: function(){
            //分页
            $("#inWarehouseDetailBill_PageList a[name=pages]").click(function(){
                pageFun($(this).text());
            });
            //下一页
            $("#inWarehouseDetailBillPageNext").click(function(){
                pageFun(parseInt($("#inWarehouseDetailBill_PageList li.paginate_button.active a").text())+1);
            });
            //上一页
            $("#inWarehouseDetailBillPagePrev").click(function(){
                pageFun(parseInt($("#inWarehouseDetailBill_PageList li.paginate_button.active a").text())-1);
            });
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_inWarehouseDetailBill]").click(function () {
                pageFun(1);
            });
            // 编辑绑定事件
            $("tbody button[name='inWarehouseDetailBillBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='inWarehouseDetailBillBtnDel']").click(delBtnFun);
        },
        fixEvent: function() {
            $("#inWarehouseDetailBill_UptAdd_Back button").click(function (e) {
                $("#inWarehouseDetailBill_UptAdd_Back").hide();
                //阻止默认事件
                return false;
            });
            // 添加按钮被点击
            $("#inWarehouseDetailBill_AddBtn").click(addFun);

            $("#inWarehouseDetailBill_Close").click(cancelFun);

            $("#inWarehouseDetailBill_AddBtnExe").click(saveFun);

            // Update
            $("#inWarehouseDetailBill_UptBtnExe").click(function () {
                if (FormValidator.commitForm($("#inWarehouseDetailBill_UptAddForm"), selfValid, "通知")) {
                    var dataBack = $("#inWarehouseDetailBill_UptAddForm").serializeObject();
                    // ajax修改
                    var inwId = $(":input[name=inwId]").val();
                    var seri = $("#inWarehouseDetailBill_UptAddForm").serializeObject();
                    var data = JSON.stringify(seri);
                    $.ajax({
                        url:SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/update" + inwId,
                        method: "PUT",
                        data: {"data": data},//输出修改模态框中表单的序列化结果
                        success: function () {
                            /*$("#inWarehouseDetailBill_Close").click();*/
                            $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');
                            $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-danger');
                            $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-success');
                            $("#inWarehouseDetailBill_UptAdd_Back span").text('{编号：' + dataBack.inwBillsnumber + '，物料码：' + dataBack.inwCode + '，批次：' + dataBack.inwBatch + '}：修改数据成功！');
                            $("#inWarehouseDetailBill_UptAdd_Back").show();
                            $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");

                            var tr = $("tbody button[data='" + inwId + "']").parents("tr:first");
                            tr.find("td:eq(1)").text(seri["inwBillsnumber"]);
                            tr.find("td:eq(2)").text(seri["inwCode"]);
                            tr.find("td:eq(3)").text(seri["inwUniquecode"]);
                            tr.find("td:eq(4)").text(seri["inwUnit"]);
                            tr.find("td:eq(5)").text(seri["inwBatch"]);
                            tr.find("td:eq(6)").text(seri["inwAmount"]);
                            tr.find("td:eq(7)").text(seri["inwActualcount"]);
                            if(seri["inwStatus"]==0){
                                tr.find("td:eq(8)").text("未入库");
                            }
                            if(seri["inwStatus"]==1){
                                tr.find("td:eq(8)").text("入库中");
                            }
                            if(seri["inwStatus"]==2){
                                tr.find("td:eq(8)").text("已入库");
                            }


                        },
                        error: function () {
                            // alert("修改失败，请检查重新输入......");
                            // location.reload();
                            $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');
                            $("#inWarehouseDetailBill_UptAdd_Back").show();
                            $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                            $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-success');
                            $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-danger');
                            $("#inWarehouseDetailBill_UptAdd_Back span").text('{编号：' + dataBack.inwBillsnumber + '，物料码：' + dataBack.inwCode + '，批次：' + dataBack.inwBatch + '}：修改操作失败 ！');
                        }
                    });
                }
            });

            // Confirm Delete Operation
            $("#inWarehouseDetailBill_OKBtn").click(function () {
                var opt = $("#inWarehouseDetailBill_OKBtn").data("opt");
                var inwId=opt["inwId"];
                $("#inWarehouseDetailBill_Affirm").modal('hide');
                var delTr = opt["tr"];
                // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
                var inwBillsnumber = delTr.find("td:eq(1)").text();
                var inwCode = delTr.find("td:eq(2)").text();
                var inwBatch = delTr.find("td:eq(4)").text();
                $.ajax({
                    url:SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/delete"+inwId,
                    method:"DELETE",
                    // data:{"data":data},//输出修改模态框中表单的序列化结果
                    success:function(){
                        $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-danger');

                        $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-success');

                        $("#inWarehouseDetailBill_UptAdd_Back span").text('{编号：'+inwBillsnumber+'，物料码：'+inwCode+'，批次：'+inwBatch+'}：数据删除成功');
                        $("#inWarehouseDetailBill_UptAdd_Back").show();
                        $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                        // delTr.remove();
                        var curPage = $("ul.pagination li.active a").text();
                        pageFun(curPage);

                    },
                    error:function () {
                        $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');
                        $("#inWarehouseDetailBill_UptAdd_Back").show();
                        $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                        $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-success');
                        $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-danger');
                        $("#inWarehouseDetailBill_UptAdd_Back span").text('{编号：'+inwBillsnumber+'，物料码：'+inwCode+'，批次：'+inwBatch+'}：数据删除失败...');
                    }
                });
            });
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));
        }
    }
})();