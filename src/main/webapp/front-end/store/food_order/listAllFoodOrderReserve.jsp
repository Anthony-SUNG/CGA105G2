<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>ðï¸ç®¡ç</title>
</head>
<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/front-end/store/01h/nav/navin01.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9">
            <div class="table-responsive" style="overflow: hidden !important;">
                <h1 class="h2">ðè¨ä½é ç´</h1>
                <table id="code" class="table table-striped display" style="text-align:center;">
                </table>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(ð©è¨ä½)").closest("a").addClass("active disabled topage");
    $("a:contains(ð»è¨ä½)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(ðè¨ä½é ç´)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    var data_test = `${foodorderListInfo}`;

    function filter_state(data) {
        let filter_data = JSON.parse(data).filter(function (entry) {
            return entry.REN_STATUS === 'å·²é ç´';
        });
        return filter_data;
    }

    function table_test(data_test) {
        let i = 0;
        $('#code').DataTable({
            // è¨­å®è³æä¾æºåå¡(data or ajaxâ¦.ç­),
            data: data_test,
            // è¨­å®è³ææ¬ä½åå¡(columns),
            "columns": [
                {data: null, title: ""},
                {data: 'REN_ID', title: "è¨å®ç·¨è"},
                {data: 'MEM_NAME', title: "æå¡å§å"},
                {data: 'MEM_PHONE', title: "æå¡é»è©±"},
                {data: 'REN_NAME', title: "å§å"},
                {data: 'REN_PHONE', title: "é»è©±"},
                {data: 'REN_DATE', title: "æ¥æ"},
                {data: 'REN_TIME', title: "ææ®µ"},
                {data: 'REN_HEADCOUNT', title: "äººæ¸"},
                {data: 'MEAL_NAME_LIST', title: "é¤é»"},
                {data: 'REN_FPRICE', title: "éé¡"},
                {data: 'REN_STATUS', title: "çæ"},
                {
                    data: null, title: "æä½åè½"  // ééæ¯æ¬ä½
                },
            ],
            "columnDefs": [
                {
                    targets: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], // ç¬¬ä¸æ¬
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
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 5) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 6) {
                            cell.setAttribute('style', `width:15%`);
                        }
                        if (colIndex === 7) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 8) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 9) {
                            cell.setAttribute('style', `width:20%`);
                        }
                        if (colIndex === 10) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 11) {
                            cell.setAttribute('style', `width:5%`);
                        }
                        if (colIndex === 12) {
                            cell.innerHTML = `
                              <FORM METHOD="post" ACTION="food_order.do" >
                                <input type="hidden" name="deleteid" value="\${data_test[i-1].REN_ID}">
                                <input type="hidden" name="action" value="Store_order_delete_button">
                                <input type="submit" class="btn btn-danger btn-sm" value="åæ¶">
                              </FORM>
                           `;
                            cell.setAttribute('style', `width:15%`);
                        }
                    }
                },
            ],
            // è¨­å®æ¬ä½åç´ å®ç¾©åå¡(columnDefs),
            /*è¨­å®å±¬æ§(é è¨­åè½)åå¡*/
            searching: true, // é è¨­çºtrue æå°åè½ï¼è¥è¦éåä¸ç¨ç¹å¥è¨­å®
            paging: true, // é è¨­çºtrue åé åè½ï¼è¥è¦éåä¸ç¨ç¹å¥è¨­å®
            ordering: true, // é è¨­çºtrue æåºåè½ï¼è¥è¦éåä¸ç¨ç¹å¥è¨­å®
            sPaginationType: "full_numbers", // åé æ¨£å¼ é è¨­çº"full_numbers"ï¼è¥éå¶ä»æ¨£å¼æéè¨­å®
            // å¨åå§è¡¨æ ¼çå·¦ä¸æåå¯é¸æçæ¯é åæ¸çé¸å®è¨­å®
            lengthChange: false, // åç¾é¸å®
            lengthMenu: [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ], //é¡¯ç¤ºç­æ¸è¨­å® é è¨­çº[10, 25, 50, 100]
            pageLength: 10, // é è¨­çº'10'ï¼è¥éæ´æ¹åå§æ¯é é¡¯ç¤ºç­æ¸ï¼æéè¨­å®
            stateSave: false, // é è¨­çºfalse å¨é é¢å·æ°æï¼æ¯å¦è¦ä¿å­ç¶åè¡¨æ ¼è³æèçæ
            destroy: true, // é è¨­çºfalse æ¯å¦é·æ¯ç¶åæ«å­è³æ
            info: true, // é è¨­çºtrueãæ¯å¦è¦é¡¯ç¤º"ç®åæ x  ç­è³æ"
            autoWidth: true, // é è¨­çºtrueãè¨­ç½®æ¯å¦è¦èªåèª¿æ´è¡¨æ ¼å¯¬åº¦(falseä»£è¡¨ä¸è¦èªé©æ)
            scrollCollapse: false, // é è¨­çºfalse æ¯å¦éå§æ»¾è»¸åè½æ§å¶XãYè»¸
            scrollX: false,
            scrollY: false,
            "dom": "<'row justify-content-start ml-0'f>" + "<'eight wide column'l>" + 'lrtip', // è¨­ç½®æå°divãé ç¢¼div...ç­åºæ¬ä½ç½®/å¤è§..ç­ï¼è©³ç´°å¯çå®ç¶²
            // èªè¨åå¡(language),
            language: {
                lengthMenu: "é¡¯ç¤º MENU ç­è³æ",
                sProcessing: "èçä¸­...",
                sZeroRecords: "è³æä¸å­å¨",
                sInfo: "å±æ _TOTAL_ ç­è³æ",
                sInfoEmpty: "ç®åå±æ 0 ç­ç´é",
                sInfoFiltered: " ",
                sInfoPostFix: "",
                sSearch: "æ¶å°",
                sUrl: "",
                sEmptyTable: "å°æªæè³æç´éå­å¨",
                sLoadingRecords: "è¼å¥è³æä¸­...",
                sInfoThousands: ",",
                oPaginate: {
                    sFirst: "ç¬¬ä¸é ",
                    sPrevious: "ä¸ä¸é ",
                    sNext: "ä¸ä¸é ",
                    sLast: "æå¾ä¸é ",
                },
                order: [
                    [0, "desc"]
                ],
                oAria: {
                    sSortAscending: ": ä»¥ååºæåæ­¤å",
                    sSortDescending: ": ä»¥éåºæåæ­¤å",
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