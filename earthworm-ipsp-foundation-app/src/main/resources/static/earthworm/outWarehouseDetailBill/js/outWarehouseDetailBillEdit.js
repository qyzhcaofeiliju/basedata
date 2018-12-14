// 一加载页面时候，就执行
$(function () {
    // 获取预出库单编号的输入框元素
    var outwPrebillnumberInputLab = $("[name=outwPrebillnumber]");
    var outwPrebillnumberLoad = outwPrebillnumberInputLab.val();
    // 获取是否预出库的输入框值
    var outwIspreout = $("[name=outwIspreout]:checked").val();
    if (outwIspreout != "0") { // 存在预出库的情况下，使预出库单编号输入框可以操作
        outwPrebillnumberInputLab.attr("readonly", false);
    } else {
        outwPrebillnumberInputLab.attr("readonly", true);
    }

    // 时间戳相关函数
    $('#outwOutdatetime').datetimepicker();

    /**
     * 是否预出库与预出库编号级联相关
     * change事件
     */
    $("[name=outwIspreout]").change(function () {
        // 代表：是否预出库输入框中，选择的是否
        if ($(this).val() == 0) {
            $("[name=outwIspreout][value='true']").removeAttr("checked");
            $("[name=outwIspreout][value='false']").attr("checked", true);
            outwPrebillnumberInputLab.val("不需要预出库");
            outwPrebillnumberInputLab.attr("readonly", true);
        } else {
            $("[name=outwIspreout][value='false']").removeAttr("checked");
            $("[name=outwIspreout][value='true']").attr("checked", true);
            outwPrebillnumberInputLab.val(outwPrebillnumberLoad);
            outwPrebillnumberInputLab.attr("readonly", false);
        }
    })
})