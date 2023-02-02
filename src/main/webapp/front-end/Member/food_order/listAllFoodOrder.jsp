<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>🗃️管理</title>
</head>

<body>
<!-- header start -->
<%@ include file="/front-end/Member/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/Member/01h/nav/navin04.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9">
            <div class="table-responsive" style="overflow: hidden !important;">
                <h1 class="h2">會員預約查詢</h1>
                <table id="code" class="table table-striped display" style="text-align:center;">
                </table>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/Member/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻訂位)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆預約查詢)").closest("a").addClass("active disabled bg-white topage");
</script>

<script>

    //     var data_test = `[
    //       {"REN_ID":"1","REN_NAME":"周盟鎮","REN_PHONE":"0987654321","REN_TIME":"11:00","REN_STATUS":"已預約","REN_DATE":"2023-01-01","REN_HEADCOUNT":"4","MEAL_NAME_LIST":"牛排套餐*1, 豬排套餐*1","REN_FPRICE":"2000","STORE_NAME":"貴族世家"}
    //     ]`;
    var data_test = `${foodorderListInfo}`;

    function filter_state(data) {
        console.log("aaaa");
        console.log(data);
        let filter_data = JSON.parse(data).filter(function (entry) {
            return entry.REN_STATUS === '已預約';
        });
        console.log(filter_data);
        var aa = filter_data;
        return filter_data;
    }


    // function deleteFun(jj){
    //   // post後端取回已修改資料庫訊號
    //   let name = document.getElementById(`name${jj}`).innerHTML;
    //   let shop = document.getElementById(`shop${jj}`).innerHTML;
    //   let count = document.getElementById(`count${jj}`).innerHTML;
    //   let date_time = document.getElementById(`date_time${jj}`).innerHTML;
    //   let time = document.getElementById(`time_frame${jj}`).innerHTML;


    //   //1. 把資料整理往後送
    //   let post_data = {
    //     data:[{"name":`${name}`,"shop":`${shop}`,"count":`${count}`,"date_time":`${date_time}`,"time":`${time}`}]
    //   };
    //   console.log(post_data.data[0]);
    //   alert(`${post_data.data[0]}`);
    //   //取回後送更改狀態是否OK
    //   let return_state = 'ok';
    //   if(return_state==='ok'){
    //       //2. 該筆資料前端隱藏
    //       // document.getElementById(`name${jj}`).parentElement.setAttribute('style','display: none;');
    //       console.log(jj-1);
    //       console.log(filter_data);
    //       filter_data.splice(jj-1,1);
    //       $('#code').DataTable.fnDestroy;
    //       table_test(filter_data);

    //       alert("已取消");
    //   }else{
    //     alert('不能取消');
    //   }

    // }

    function table_test(data_test) {
        let i = 0;
        $('#code').DataTable({
            // 設定資料來源區塊(data or ajax….等),
            data: data_test,
            // 設定資料欄位區塊(columns),
            "columns": [
                {data: null, title: ""},
                {data: 'REN_ID', title: "訂單編號"},
                {data: 'REN_NAME', title: "姓名"},
                {data: 'REN_PHONE', title: "電話"},
                {data: 'STORE_NAME', title: "店家"},
                {data: 'REN_DATE', title: "日期"},
                {data: 'REN_TIME', title: "時段"},
                {data: 'REN_HEADCOUNT', title: "人數"},
                {data: 'MEAL_NAME_LIST', title: "餐點"},
                {data: 'REN_FPRICE', title: "金額"},
                {data: 'REN_STATUS', title: "狀態"},
                {
                    data: null, title: "操作功能"  // 這邊是欄位
                },
            ],
            "columnDefs": [
                {
                    targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11], // 第一欄
                    createdCell: function (cell, cellData, rowData, rowIndex, colIndex) {
                        if (colIndex === 0) {
                            i = i + 1;
                            cell.innerHTML = `\${i}`
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 1) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 2) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 3) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 4) {
                            cell.setAttribute('style', `width:10%`);
                        }
                        if (colIndex === 5) {
                            cell.setAttribute('style', `width:20%`);
                        }
                        if (colIndex === 6) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 7) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 8) {
                            cell.setAttribute('style', `width:20%`);
                        }
                        if (colIndex === 9) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 10) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 11) {
                            cell.innerHTML = `
                          <FORM METHOD="post" ACTION="food_order.do" >
                            <input type="hidden" name="deleteid" value="\${data_test[i-1].REN_ID}">
                            <input type="hidden" name="action" value="Member_order_delete_button">
                            <input type="submit" class="btn btn-danger btn-sm" value="取消">
                          </FORM>
                          `;
                            cell.setAttribute('style', `width:10%`);
                        }
                    }

                },
            ],
            // 設定欄位元素定義區塊(columnDefs),

            /*設定屬性(預設功能)區塊*/
            searching: true, // 預設為true 搜尋功能，若要開啟不用特別設定
            paging: true, // 預設為true 分頁功能，若要開啟不用特別設定
            ordering: true, // 預設為true 排序功能，若要開啟不用特別設定
            sPaginationType: "full_numbers", // 分頁樣式 預設為"full_numbers"，若需其他樣式才需設定
            // 在初始表格的左上有個可選擇的每頁列數的選單設定
            lengthChange: false, // 呈現選單
            lengthMenu: [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ], //顯示筆數設定 預設為[10, 25, 50, 100]
            pageLength: 10, // 預設為'10'，若需更改初始每頁顯示筆數，才需設定
            stateSave: false, // 預設為false 在頁面刷新時，是否要保存當前表格資料與狀態
            destroy: true, // 預設為false 是否銷毀當前暫存資料
            info: true, // 預設為true　是否要顯示"目前有 x  筆資料"
            autoWidth: true, // 預設為true　設置是否要自動調整表格寬度(false代表不要自適應)
            scrollCollapse: false, // 預設為false 是否開始滾軸功能控制X、Y軸
            scrollX: false,
            scrollY: false,
            "dom": "<'row justify-content-start ml-0'f>" + "<'eight wide column'l>" + 'lrtip', // 設置搜尋div、頁碼div...等基本位置/外觀..等，詳細可看官網
            // 語言區塊(language),
            language: {
                lengthMenu: "顯示 MENU 筆資料",
                sProcessing: "處理中...",
                sZeroRecords: "資料不存在",
                sInfo: "共有 _TOTAL_ 筆資料",
                sInfoEmpty: "目前共有 0 筆紀錄",
                sInfoFiltered: " ",
                sInfoPostFix: "",
                sSearch: "收尋",
                sUrl: "",
                sEmptyTable: "尚未有資料紀錄存在",
                sLoadingRecords: "載入資料中...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "第一頁",
                    sPrevious: "上一頁",
                    sNext: "下一頁",
                    sLast: "最後一頁",
                },
                order: [
                    [0, "desc"]
                ],
                oAria: {
                    sSortAscending: ": 以升序排列此列",
                    sSortDescending: ": 以降序排列此列",
                },
            },
        });

    }

    let filter_data = filter_state(data_test);
    $(document).ready(table_test(filter_data));


</script>
<!-- Vue -->
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script>
    const {createApp} = Vue;

    createApp({
        data() {
            return {
                message: "Hello Vue!",
            };
        },
    }).mount("#app");
</script>


<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>

<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>
</body>

</html>