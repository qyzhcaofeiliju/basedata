$("#file_input").data("language","zh");
$(document).ready(function() {
    //导入语言包处理
    var $input = $('input.file[type=file]');
    if ($input.length) {
        $input.fileinput({language:"zh"});
    }

    inWareCate.bindEvent();
    inWareCate.fixEvent();
    bindTooltipEvent();
});
var inWareCate = (function(){

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
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/validIsExist",
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

    // 校验唯一性值，不能让其出现重复值。
    var checkUnique1 = function (jsonStrData) {
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

    //自定义验证，此处验证名称和分类是否重复，入库单
    var selfValid = {
        "inwBillsnumber": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique("{'inwBillsnumber':'" + inputValue + "'}");
            }
        }]
    };
    //自定义验证，此处验证名称和分类是否重复，入库单详情
    var selfValid1 = {
        "inwUniquecode": [{
            "type": 1,// ajax异步校验后台数据库是否存在相同输入值
            "fun": function (inputValue) {
                return checkUnique1("{'inwUniquecode':'" + inputValue + "'}");
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
    };

    var cache = {
        "cached":false,
        "mod": null,
        "inwId": null,
        "inwBillsnumber":null
    };
    //查看入库单
    var viewFile = function () {
        $("#download_inWarehouseBill_UptAdd_OptModal").modal('hide');
        //开始拿到上传文件的数据outwBillsnumber
        var file = document.querySelector("#file_input");
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/importExcelList",
            type: "POST",
            dataType: "json",
            data: formData,
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                var inWareDetailInfo = data[0];
                if (inWareDetailInfo != "") {
                    $("#showImportInWareInfo").modal('show');
                    $("#viewImportInWarehouse_AddBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/edit" + inWareDetailInfo, function (dataInfo) {
                        $(this).html(dataInfo);
                        //编辑页面清空按钮
                        $(".cleanBtn").click(findRemove);
                        CleanInput.bindEvent($(".cleanBtn").prev("input"));
                    })
                }
            }
        });
    };

    //验证入库单
    var viewFileDetail = function () {
        if (FormValidator.commitForm($("#inWarehouseBill_ViewDetailForm"), selfValid, "通知")) {
            viewDetailFile();
        }
    };

    var viewDetailFile = function () {
        var file = document.querySelector("#file_input");
        var data = JSON.stringify($("#inWarehouseBill_ViewDetailForm").serializeObject());
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        formData.append('data',data);
        ajax(formData);
    };
    function ajax(formData) {
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/showDetailInfo",
            method: "POST",
            data: formData,
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                var inWareDetailInfo = data;
                if (inWareDetailInfo != '')
                    $("#showImportInWareInfo").modal('hide');
                $("#showImportInWareDetailInfo").modal('show');
                $("#showInportFileDetailInfo").html(inWareDetailInfo);
            }
        });

    }

    var downFile = function (data) {
        //点击保存按钮的时候，需要获得两个页面的数据,组合成对象或者json格式,一起传给后台
        var value1 =  $("#inputID").val();
        var value2 = $("#inputDateId").val();
        var value3 = $("#selectValue option:selected").val();
        var value4 =  $("#inputState").val();
        var objParam = {value1:value1,value2:value2,value3:value3,value4:value4};
        var objDate = JSON.stringify(objParam);


        var file = document.querySelector("#file_input");
        var fileObj = file.files[0];
        var formData = new FormData();
        formData.append('file', fileObj);
        formData.append('objDate',objDate);
        inWarehouseBill_UptAdd_Back = "{ 入库单号:"+value1;
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/saveFile",
            method: "post",
            data: formData,
            dataType: "json",
            cache: false,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data == true){
                    inWarehouseBill_UptAdd_Back = inWarehouseBill_UptAdd_Back + " }：导入成功 ！";
                    IdxTip.tipsuc(inWarehouseBill_UptAdd_Back, "成功!");
                    $("#showImportInWareDetailInfo").modal("hide");
                }else {
                    inWarehouseBill_UptAdd_Back = inWarehouseBill_UptAdd_Back + " }：导入失败的原因：该入库单号已存在";
                    IdxTip.tiperr(inWarehouseBill_UptAdd_Back,"失败");
                    $("#showImportInWareDetailInfo").modal("hide");
                }
            }
        })
    }


    var clickBack = function () {
        $("#showImportInWareDetailInfo").modal("hide");
        $("#showImportInWareInfo").modal("show");
    }

    var exportFile =function () {
        $("#tmp").submit();
    }

    //编辑执行的方法
    var editFun = function() {
        var callback = function () {
            FormValidator.validForm($("#inWarehouseBill_UptAddForm"), selfValid,$("#inWarehouseBill_UptAddForm").serializeObject());
        }
        var inwId=$(this).attr("data");
        if(cache["cached"]) {
            if(cache["mod"]==2 && cache["inwId"] && cache["inwId"]==inwId) {
                $("#inWarehouseBill_UptAdd_OptModal").modal("show");
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

    //入库单编辑页面的显示
    var __loadEditData = function (inwId,callback) {
        $("#inWarehouseBill_AddBtnExe").hide();
        $("#inWarehouseBill_UptBtnExe").show();
        $("#inWarehouseBill_UptAddTitle").text("编辑入库单信息");

        if(!inwId) inwId='';
        $("#inWarehouseBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/edit/"+inwId,function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }

    // 删除执行的方法
    var delBtnFun = function() {
        var inwId=$(this).attr("data");
        // show Affirm Modal
        var delTr = $(this).parents("tr:first");
        $("#inWarehouseBill_OKBtn").data("opt",{"tr":delTr,"inwId":inwId});
        $("#inWarehouseBill_Affirm").modal('show');
    };

    // 查看详情
    var findBtnFun = function() {
        var inwBillsnumber=$(this).attr("data");
        if(cache["cached"]) {
            if(cache["mod"]==3 && cache["inwBillsnumber"] && cache["inwBillsnumber"]==inwBillsnumber) {
                $("#inWarehouseBill_UptAdd_OptModal").modal("show");
            }
            else {
                cache["mod"]=3;
                cache["inwBillsnumber"]=inwBillsnumber;
                __loadFindData(inwBillsnumber);
            }
        }
        else {
            __loadFindData(inwBillsnumber);
        }
    };

    //点击增加按钮
    var addFun = function() {
        var callback = function () {
            FormValidator.validForm($("#inWarehouseBill_UptAddForm"), selfValid);
        }
        if(cache["cached"]) {
            if(cache["mod"]==1){
                $("#inWarehouseBill_UptAdd_OptModal").modal("show");
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
        $("#inWarehouseBill_AddBtnExe").show();
        $("#inWarehouseBill_UptBtnExe").hide();
        $("#inWarehouseBill_UptAddTitle").text("新增入库单信息");

        $("#inWarehouseBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/edit",function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    //入库单验证
    var saveFun = function () {
        if (FormValidator.commitForm($("#inWarehouseBill_UptAddForm"), selfValid, "通知")) {
            // 都通过验证的话，就触发保存操作
            addDoubleFun();
        }
    }
    //点击上一步
    var prevFunExe = function(){
        $("#inWarehouseDetailBillDouble_UptAdd_OptModal").modal('hide');
        $("#inWarehouseBill_UptAdd_OptModal").modal('show');
    }
    //点击下一步按钮
    var addDoubleFun = function(){
        var callback = function () {
            FormValidator.validForm($("#inWarehouseDetailBill_UptAddForm"), selfValid1);
        }
        if(cache["mod"]==1){
            $("#inWarehouseDetailBillDouble_UptAdd_OptModal").modal("show");
            $("#inWarehouseBill_UptAdd_OptModal").modal("hide");
        }
        else {
            cache["mod"]=1;
            __loadAddDoubleData(callback);
        }
    }
    //入库单详情编辑页面的的显示
    var __loadAddDoubleData = function(callback){
        $("#inWarehouseBill_UptAdd_OptModal").modal("hide");
        $("#inWarehouseDetailBillDouble_UptAdd_OptModal").modal("show");
        $("#inWarehouseDetailBillDouble_AddBtnExe").show();
        $("#inWarehouseDetailBillDouble_UptBtnExe").hide();
        $("#inWarehouseDetailBillDouble_UptAddTitle").text("新增入库单详情信息");
        var inwBillsnumber = $("#inWarehouseBill_UptAddForm [name=inwBillsnumber]").val();
        $("#inWarehouseDetailBillDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/detailAdd/"+inwBillsnumber,function(data){
            $(this).html(data);
            callback();
            //编辑页面清空按钮
            $(".cleanBtn").click(findRemove);
            CleanInput.bindEvent($(".cleanBtn").prev("input"));
        });
    }
    //入库单详情验证
    var doubleSaveFun = function(){
        if (FormValidator.commitForm($("#inWarehouseDetailBill_UptAddForm"), selfValid1, "通知")) {
            // 都通过验证的话，就触发保存操作
            doubleSaveFunExe();
        }
    }
    //执行添加操作
    var doubleSaveFunExe = function(){
        // ajax 保存
        var data = JSON.stringify({inwDetail:$("#inWarehouseDetailBill_UptAddForm").serializeObject(),inw:$("#inWarehouseBill_UptAddForm").serializeObject()});
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/doubleSave",
            method:"POST",
            data:{data:data},
            success:function(){
                var dataBack=$("#inWarehouseDetailBill_UptAddForm").serializeObject();
                /*$("#inWarehouseDetailBill_Close").click();*/
                $("#inWarehouseDetailBillDouble_UptAdd_OptModal").modal('hide');
                $("#inWarehouseBill_UptAdd_OptModal").modal('hide');

                $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-danger');

                $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-success');
                $("#inWarehouseBill_UptAdd_Back").removeAttr("hidden")
                $("#inWarehouseBill_UptAdd_Back span").text('{ 编号：'+dataBack.inwBillsnumber+'，物料码：'+dataBack.inwCode+'，批次：'+dataBack.inwBatch+' }：添加数据成功！');
                $("#inWarehouseDetailBill_UptAdd_Back").show();
                $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");

            },
            error:function(){
                // alert("请输入有效值......")
                var dataBack=$("#inWarehouseDetailBill_UptAddForm").serializeObject();
                $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');
                $("#inWarehouseDetailBill_UptAdd_Back").show();
                $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-success');
                $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-danger');
                $("#inWarehouseDetailBill_UptAdd_Back span").text('{ 编号：'+dataBack.inwBillsnumber+'，物料码：'+dataBack.inwCode+'，批次：'+dataBack.inwBatch+' }：数据添加失败，请尝试再添加操作 ！');
                /*location.reload();*/
            }
        });
    }

    //入库单对应的入库单详情列表
    var __loadFindData = function (inwBillsnumber) {
        $("#inWarehouseBill_AddBtnExe").hide();
        $("#inWarehouseBill_UptBtnExe").hide();
        $("#inWarehouseBill_UptAddTitle").text("入库单详情信息");

        if(!inwBillsnumber) inwBillsnumber='';
        $("#inWarehouseBill_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/find/"+inwBillsnumber,function(data){
            $(this).html(data);
            var script = $("#inWarehouseBill_UptAdd_OptBody input[name='script']").val();
            $.getScript(script);
        });
    }

    //修改显示入库单详情
    var doubelUptFun =  function(){
        if (FormValidator.commitForm($("#inWarehouseBill_UptAddForm"), selfValid, "通知")) {
            var callback = function () {
                FormValidator.validForm($("#inWarehouseDetailBill_UptAddForm"), selfValid1,$("#inWarehouseDetailBill_UptAddForm").serializeObject());
            }
            var dataBack=$("#inWarehouseBill_UptAddForm").serializeObject();
            var inwBillsnumber = dataBack.inwBillsnumber;
            var inwId = dataBack.inwId;
            $("#inWarehouseBill_UptAdd_OptModal").modal("hide");
            $("#inWarehouseDetailBillDouble_UptAdd_OptModal").modal("show");
            $("#inWarehouseDetailBillDouble_AddBtnExe").hide();
            $("#inWarehouseDetailBillDouble_UptBtnExe").show();
            $("#inWarehouseDetailBillDouble_UptAddTitle").text("编辑入库单详情信息");
            $("#inWarehouseDetailBillDouble_UptAdd_OptBody").load(SysCfg.getFullAddress()+"/ipsp/inWarehouseDetailBill/detailUpt/"+inwBillsnumber+"/"+inwId,function(data){
                $(this).html(data);
                if(data.length!=81){
                    callback();
                    //编辑页面清空按钮
                    $(".cleanBtn").click(findRemove);
                    CleanInput.bindEvent($(".cleanBtn").prev("input"));
                }
            });
        }
    }
    //执行修改操作
    var  doubleUptFunExe = function(){
        if (FormValidator.commitForm($("#inWarehouseDetailBill_UptAddForm"), selfValid1, "通知")) {
            // ajax 编辑
            var data = JSON.stringify({
                inwDetail: $("#inWarehouseDetailBill_UptAddForm").serializeObject(),
                inw: $("#inWarehouseBill_UptAddForm").serializeObject()
            });
            $.ajax({
                url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/doubleUpt",
                method: "POST",
                data: {data: data},
                success: function (mapData) {
                    var info = mapData.info;
                    var vwInWarehouseBill = mapData.vwInWarehouseBill;
                    var dataBack = $("#inWarehouseDetailBill_UptAddForm").serializeObject();
                    /*$("#inWarehouseDetailBill_Close").click();*/
                    $("#inWarehouseDetailBillDouble_UptAdd_OptModal").modal('hide');
                    $("#inWarehouseBill_UptAdd_OptModal").modal('hide');

                    $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-danger');

                    $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-success');
                    $("#inWarehouseBill_UptAdd_Back").removeAttr("hidden");
                    if(dataBack.inwBillsnumber==null&&dataBack.inwCode==null&&dataBack.inwBatch==null){
                        var inwDataBack=$("#inWarehouseBill_UptAddForm").serializeObject();
                        $("#inWarehouseBill_UptAdd_Back span").text('{ 编号：'+inwDataBack.inwBillsnumber+'}：修改数据成功！');
                    }else{
                        $("#inWarehouseBill_UptAdd_Back span").text('{ 编号：' + dataBack.inwBillsnumber + '，物料码：' + dataBack.inwCode + '，批次：' + dataBack.inwBatch+' }：修改数据成功！');
                    }
                    $("#inWarehouseDetailBill_UptAdd_Back").show();
                    $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                    var seri = $("#inWarehouseBill_UptAddForm").serializeObject();
                    var tr = $("tbody button[data='" + $("#inWarehouseBill_UptAddForm").serializeObject().inwId + "']").parents("tr:first");
                    tr.find("td:eq(1)").text(vwInWarehouseBill.inwBillsnumber);
                    tr.find("td:eq(2)").text(vwInWarehouseBill.catName);
                    tr.find("td:eq(3)").text(EWDate.getFullYMDHM(vwInWarehouseBill.inwIntime));
                    if(vwInWarehouseBill.inwStatus==0){
                        tr.find("td:eq(4)").text("未入库");
                    }
                    if(vwInWarehouseBill.inwStatus==1){
                        tr.find("td:eq(4)").text("入库中");
                    }
                    if(vwInWarehouseBill.inwStatus==2){
                        tr.find("td:eq(4)").text("已入库");
                    }

                },
                error: function () {
                    var dataBack = $("#inWarehouseDetailBill_UptAddForm").serializeObject();
                    $("#inWarehouseDetailBill_UptAdd_OptModal").modal('hide');
                    $("#inWarehouseDetailBill_UptAdd_Back").show();
                    $("#inWarehouseDetailBill_UptAdd_Back").removeAttr("hidden");
                    $("#inWarehouseDetailBill_UptAdd_Back").removeClass('alert-success');
                    $("#inWarehouseDetailBill_UptAdd_Back").addClass('alert-danger');
                    $("#inWarehouseDetailBill_UptAdd_Back span").text('{ 编号：' + dataBack.inwBillsnumber + '，物料码：' + dataBack.inwCode + '，批次：' + dataBack.inwBatch+' }：数据修改失败，请尝试再添加操作 ！');
                    /*location.reload();*/
                }
            });
        }
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
        var formData = JSON.stringify($("#find_inWarehouseBill").serializeObject());
        $.ajax({
            method:"get",
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/page",
            data:{pageData:pageData,formData:formData},
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
                        "<td>"+list[i].catName+"</td>"+
                        "<td>"+EWDate.getFullYMDHM(list[i].inwIntime)+"</td>"
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
                            "<button name='inWarehouseBillBtnVU' data="+list[i].inwId+" type='button' class='btn btn-primary fa fa-edit' data-toggle='modal' data-target='#inWarehouseBill_UptAdd_OptModal' data-placement='bottom' data-original-title='编辑'></button>"+
                        "</td>"+
                        "<td class='ew-tooltip'>"+
                            "<button name='inWarehouseBillBtnDel' type='button' data="+list[i].inwId+" class='btn btn-danger fa fa-trash-o' data-toggle='modal' data-placement='bottom' data-original-title='删除' ></button>"+
                        "</td>"+
                        "<td class='ew-tooltip'>"+
                            "<button name='inWarehouseBillBtnFind' type='button' data="+list[i].inwBillsnumber+" class='btn btn-info fa fa-eye' data-toggle='modal' data-target='#inWarehouseBill_UptAdd_OptModal' data-placement='bottom' data-original-title='查看详情' ></button>"+
                        "</td>"+
                    "</tr>"
                }
                var pageListHtml="";
                pageListHtml+="<ul class='pagination'>"+
                    "<li class="+(1==page.pageNum ? "'paginate_button disabled'":"'paginate_button'")+" "+(1==page.pageNum ? "":"id='inWarehouseBillPagePrev'")+">"+
                    "<span>&laquo;</span>"+
                    "</li>";
                for (i in pageList){
                    pageListHtml+="<li class="+ (pageList[i] == page.pageNum ? "'paginate_button active'" : "'paginate_button'")+">"+
                        "<a class='page-link' name='pages' >"+pageList[i]+"</a>"+
                        "</li>"
                }
                pageListHtml+="<li class="+(page.totalPageNum == page.pageNum ? "'paginate_button disabled'" : "'paginate_button'")+" "+(page.totalPageNum == page.pageNum ? "" : "id='inWarehouseBillPageNext'")+">"+
                    "<span>&raquo;</span>"+
                    "</li>"+
                    "</ul>"
                $("#inWarehouseBillTbody").html(tbodyHtml);
                $("#inWarehouseBill_PageList").html(pageListHtml);
                inWareCate.bindEvent();
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

    //执行删除操作
    var deleteFun = function(){

        var opt = $("#inWarehouseBill_OKBtn").data("opt");
        var inwId=opt["inwId"];
        $("#inWarehouseBill_Affirm").modal('hide');
        var delTr = opt["tr"];
        // 获取要删除当前行的属性两个记录值,用于显示此条数据是否成功被删除
        var inwBillsnumber = delTr.find("td:eq(1)").text();
        $.ajax({
            url:SysCfg.getFullAddress()+"/ipsp/inWarehouseBill/delete"+inwId,
            method:"DELETE",
            // data:{"data":data},//输出修改模态框中表单的序列化结果
            success:function(){
                $("#inWarehouseBill_UptAdd_Back").removeClass('alert-danger');

                $("#inWarehouseBill_UptAdd_Back").addClass('alert-success');

                $("#inWarehouseBill_UptAdd_Back span").text('{编号：'+inwBillsnumber+'}:数据删除成功 ！');
                $("#inWarehouseBill_UptAdd_Back").show();
                $("#inWarehouseBill_UptAdd_Back").removeAttr("hidden");
                // delTr.remove();
                var curPage = $("ul.pagination li.active a").text();
                pageFun(curPage);

            },
            error:function () {
                $("#inWarehouseBill_UptAdd_OptModal").modal('hide');
                $("#inWarehouseBill_UptAdd_Back").show();
                $("#inWarehouseBill_UptAdd_Back").removeAttr("hidden");
                $("#inWarehouseBill_UptAdd_Back").removeClass('alert-success');
                $("#inWarehouseBill_UptAdd_Back").addClass('alert-danger');
                $("#inWarehouseBill_UptAdd_Back span").text('{编号：'+inwBillsnumber+'}:数据删除失败...');
            }
        });
    }

    var findRemove = function(){
        var prev = $(this).prev();
        prev.val("");
        $(this).attr("style","z-index:1");
        prev.focus();
    }

    return {
        bindEvent: function(){
            //分页
            $("#inWarehouseBill_PageList a[name=pages]").click(function(){
                pageFun($(this).text());
            });
            //下一页
            $("#inWarehouseBillPageNext").click(function(){
                pageFun(parseInt($("#inWarehouseBill_PageList li.paginate_button.active a").text())+1);
            });
            //上一页
            $("#inWarehouseBillPagePrev").click(function(){
                pageFun(parseInt($("#inWarehouseBill_PageList li.paginate_button.active a").text())-1);
            });
            //获取每页显示总页数
            $("[name=selPageSize]").change(function () {
                pageFun(1);
            });
            //查询条件
            $(":input[id=query_inWarehouseBill]").click(function () {
                pageFun(1);
            });
            // 编辑绑定事件
            $("tbody button[name='inWarehouseBillBtnVU']").click(editFun);
            // 删除绑定事件
            $("tbody button[name='inWarehouseBillBtnDel']").click(delBtnFun);
            // 查看详情绑定事件
            $("tbody button[name='inWarehouseBillBtnFind']").click(findBtnFun);
        },
        fixEvent: function() {
            $("#inWarehouseBill_UptAdd_Back button").click(function (e) {
                $("#inWarehouseBill_UptAdd_Back").hide();
                //阻止默认事件
                return false;
            });
            // 添加按钮被点击
            $("#inWarehouseBill_AddBtn").click(addFun);

            $("#inWarehouseBill_Close").click(cancelFun);

            //下一步按钮被点击
            $("#inWarehouseBill_AddBtnExe").click(saveFun);

            //上一步
            $("#inWarehouseDetailBillDouble_PrevBtnExe").click(prevFunExe);

            //入库单及入库单详情增加
            $("#inWarehouseDetailBillDouble_AddBtnExe").click(doubleSaveFun);

            // 点击修改按钮
            $("#inWarehouseBill_UptBtnExe").click(doubelUptFun);

            //入库单及入库单详情编辑
            $("#inWarehouseDetailBillDouble_UptBtnExe").click(doubleUptFunExe);

            // Confirm Delete Operation
            $("#inWarehouseBill_OKBtn").click(deleteFun);

            //清空查询框内容
            $(".ew-form-search a").click(findRemove);

            //控制清空查询框按钮的显示
            CleanInput.bindEvent($(".ew-form-search input"));

            //查看操作
            $("#importInWarehouse_save").click(viewFile);

            //查看出库单详细信息操作

            $("#viewInWarehouseInfo_AddBtnExe").click(viewFileDetail);

            //导入入库文件
            $("#importFileInfo_Save").click(downFile);

            //详情点击上一步
            $("#importFileInfo_Back").click(clickBack);

            //点击导出操作
            $("#export_inWarehouseBill").click(exportFile);

        }
    }
})();