-- A-創表
CREATE SCHEMA `cga105g2`;
create table `cga105g2`.employee
(
    EMP__STATUS tinyint   default 0                 not null comment '狀態 0:啟用 1:停權',
    EMP_ID      int auto_increment comment '編號'
        primary key,
    EMP_ACC     varchar(20)                         not null comment '帳號 修改新增',
    EMP_PWD     varchar(20)                         not null comment '密碼',
    EMP_PER     tinyint   default 0                 not null comment '權限  0:員工 1:主管',
    EMP_TIME    datetime  default CURRENT_TIMESTAMP not null comment '新增日期',
    EMP_RTIME   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改日期'
);

create table `cga105g2`.member
(
    MEM_ID          int auto_increment comment '編號'
        primary key,
    MEM_STATUS      tinyint  default 0                 not null comment '狀態',
    MEM_ACC         varchar(50)                        not null comment '帳號',
    MEM_PWD         varchar(50)                        not null comment '密碼',
    MEM_MAIL        varchar(50)                        not null comment '電子信箱',
    MEM_PIC         longblob                           null comment '會員照片',
    MEM_NAME        varchar(50)                        not null comment '用戶名稱',
    MEM_RECIPIENT   varchar(50)                        not null comment '收件姓名',
    MEM_TW_ID       varchar(50)                        not null comment '身分證字號',
    MEM_BIRTHDAY    date                               not null comment '出生日期',
    MEM_PHONE       varchar(50)                        not null comment '手機',
    MEM_POSTAL_CODE int                                not null comment '郵遞區號',
    MEM_CITY        varchar(50)                        not null comment '縣市',
    MEM_DISTRICT    varchar(50)                        not null comment '區域',
    MEM_ADDRESS     varchar(50)                        not null comment '地址',
    MEM_TEXT        varchar(2000)                      null comment '個人簡介',
    MEM_TIME        datetime default CURRENT_TIMESTAMP not null comment '註冊時間',
    MEM_POINT       int      default 0                 not null comment '我的點數',
    constraint MEM_ACC
        unique (MEM_ACC),
    constraint MEM_MAIL
        unique (MEM_MAIL)
);

create table `cga105g2`.follow_mem
(
    FOLLOW_ID int auto_increment comment '編號'
        primary key,
    MEM_ID1   int not null comment 'A會員編號',
    MEM_ID2   int not null comment 'B會員編號',
    constraint FK_FOLLOW_MEM_FOLLOW_MEM_MEM_ID1
        foreign key (MEM_ID1) references member (MEM_ID),
    constraint FK_FOLLOW_MEM_FOLLOW_MEM_MEM_ID2
        foreign key (MEM_ID2) references member (MEM_ID)
);

create table `cga105g2`.message
(
    MES_ID    int auto_increment comment '編號'
        primary key,
    FOLLOW_ID int                                not null comment '追蹤編號',
    MES_TEXT  varchar(2000)                      not null comment '內容',
    MES_TIME  datetime default CURRENT_TIMESTAMP not null comment '時間',
    constraint FK_MESSAGE_FOLLOW_MEM_FOLLOW_ID
        foreign key (FOLLOW_ID) references follow_mem (FOLLOW_ID)
);

create table `cga105g2`.point
(
    POINT_ID     int auto_increment comment '編號'
        primary key,
    MEM_ID       int         not null comment '會員編號',
    POINT_CHANGE varchar(50) not null comment '異動原因',
    POINT_NUMBER int         not null comment '異動點數',
    constraint FK_POINT_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID)
);

create table `cga105g2`.point_goods
(
    PD_ID     int auto_increment comment '編號'
        primary key,
    PD_IMG    longblob                            not null comment '圖片',
    PD_NAME   varchar(50)                         not null comment '名稱',
    PD_PRICE  int                                 not null comment '單價',
    PD_TEXT   varchar(200)                        not null comment '介紹',
    PD_TIME   datetime  default CURRENT_TIMESTAMP not null comment '新增日期',
    PD_RTIME  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改日期',
    PD_STATUS int       default 0                 null comment '狀態;0: 下架 1:上架'
);

create table `cga105g2`.point_order
(
    PO_ID     int auto_increment comment '編號'
        primary key,
    MEM_ID    int                                not null comment '會員編號',
    PD_ID     int                                not null comment '商品編號',
    PO_PRICE  int                                not null comment '單價',
    PO_TEXT   varchar(2000)                      null comment '備註',
    PO_STATUS tinyint  default 0                 not null comment '狀態;0: 成立   1:出貨  2:完成',
    PO_TIME   datetime default CURRENT_TIMESTAMP not null comment '新增日期',
    PO_UTIME  datetime                           null comment '出貨日期',
    EMP_ID    int                                null comment '員工編號 修改NOT NULL',
    constraint FK_POINT_ORDER_EMPLOYEE_EMP_ID
        foreign key (EMP_ID) references employee (EMP_ID),
    constraint FK_POINT_ORDER_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID),
    constraint FK_POINT_ORDER_POINT_GOODS_PD_ID
        foreign key (PD_ID) references point_goods (PD_ID)
);

create table `cga105g2`.root
(
    ROOT_ID   int auto_increment comment '編號'
        primary key,
    ROOT_TEXT varchar(20) not null comment '名稱'
);

create table `cga105g2`.employee_root
(
    EMP_ID  int not null comment '員工編號',
    ROOT_ID int not null comment '權限編號',
    primary key (EMP_ID, ROOT_ID),
    constraint FK_EMPLOYEE_ROOT_EMPLOYEE_EMP_ID
        foreign key (EMP_ID) references employee (EMP_ID),
    constraint FK_EMPLOYEE_ROOT_ROOT_ROOT_ID
        foreign key (ROOT_ID) references root (ROOT_ID)
);

create table `cga105g2`.store
(
    STORE_ID          int auto_increment comment '訂單編號'
        primary key,
    EMP_ID            int                                 null comment '員工編號',
    STORE_STATUS      tinyint   default 0                 not null comment '狀態 0:未註冊 1:審核中  2:審核通過 3:停權',
    STORE_NAME        varchar(50)                         not null comment '店家',
    STORE_PHONE1      varchar(50)                         not null comment '電話',
    STORE_HOURS       varchar(500)                        not null comment '營業時間',
    STORE_MAP         varchar(200)                        null comment '地圖經緯度',
    STORE_CITY        varchar(50)                         not null comment '營業縣市',
    STORE_DISTRICT    varchar(50)                         not null comment '營業區域',
    STORE_ADDRESS     varchar(50)                         not null comment '營業地址',
    STORE_URL         varchar(200)                        null comment 'googleMap',
    STORE_WEB         varchar(200)                        null comment 'website',
    STORE_ACC         varchar(50)                         null comment '帳號',
    STORE_PWD         varchar(50)                         null comment '密碼',
    STORE_MAIL        varchar(50)                         null comment '電子信箱',
    STORE_COM_ID      varchar(50)                         null comment '統一編號',
    STORE_COM_ADDRESS varchar(50)                         null comment '代表人姓名',
    STORE_TW_ID       varchar(50)                         null comment '身分證字號',
    STORE_PHONE2      varchar(50)                         null comment '聯絡人電話',
    STORE_TEXT        varchar(2000)                       null comment '店家簡介',
    STORE_PLAN        tinyint                             null comment '目前方案',
    STORE_NPLAN       tinyint                             null comment '次月方案',
    STORE_TIME        datetime  default CURRENT_TIMESTAMP not null comment '新增時間',
    STORE_ONTIME      date                                null comment '註冊時間',
    STORE_RETIME      timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改時間',
    STORE_ETIME       varchar(50)                         null comment '訂位時段',
    STORE_TABLE       int                                 null comment '桌數',
    STORE_ETABLE      int                                 null comment '訂位上限數',
    constraint STORE_ACC
        unique (STORE_ACC),
    constraint STORE_MAP
        unique (STORE_MAP),
    constraint FK_STORE_EMP_ID_EMPLOYEE_EMP_ID
        foreign key (EMP_ID) references employee (EMP_ID)
);

create table `cga105g2`.advertise
(
    ADV_ID     int auto_increment comment '編號'
        primary key,
    STORE_ID   int                                not null comment '店家編號',
    EMP_ID     int                                null comment '員工編號 修改NOT NULL',
    ADV_STATUS tinyint  default 1                 not null comment '狀態 1:審核中  2:審核通過  3:失效',
    ADV_IMG    longblob                           null comment '圖片 修改not null',
    ADV_TEXT   varchar(2000)                      not null comment '內容',
    ADV_TIME   datetime default CURRENT_TIMESTAMP not null comment '新增日期',
    ADV_STIME  date                               not null comment '開始日期',
    ADV_NTIME  date                               not null comment '到期日期',
    constraint FK_ADVERTISE_EMPLOYEE_EMP_ID
        foreign key (EMP_ID) references employee (EMP_ID),
    constraint FK_ADVERTISE_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.article
(
    ART_ID      int auto_increment comment '編號'
        primary key,
    MEM_ID      int                                 not null comment '會員編號',
    STORE_ID    int                                 not null comment '店家編號',
    ART_HEADER  varchar(50)                         not null comment '標題',
    ART_TEXT    varchar(2000)                       not null comment '內容',
    ART_IMG     longblob                            not null comment '圖片 修改not null',
    ART_TAG     varchar(200)                        null comment '標籤',
    ART_STATUS  tinyint   default 0                 not null comment '狀態;0:顯示  1:刪除',
    ART_TIME    datetime  default CURRENT_TIMESTAMP not null comment '新增日期',
    ART_RETIME  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改時間',
    ART_SUMLIKE int       default 0                 not null comment '總按讚數',
    ART_SCORE   int                                 not null comment '評分',
    constraint FK_ARTICLE_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID),
    constraint FK_ARTICLE_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.code
(
    CODE_ID     int auto_increment comment '編號'
        primary key,
    STORE_ID    int                                not null comment '店家編號',
    EMP_ID      int                                null comment '員工編號 修改新增',
    CODE_NUM    varchar(50)                        not null comment '優惠券碼',
    CODE_OFF    int                                not null comment '折扣',
    CODE_STATUS tinyint  default 1                 not null comment '狀態 1:審核  2:通過  3:失效',
    CODE_TEXT   varchar(200)                       not null comment '備註',
    CODE_TIME   datetime default CURRENT_TIMESTAMP not null comment '新增日期',
    CODE_RTIME  date                               null comment '通過日期',
    CODE_NTIME  date                               not null comment '到期日期',
    constraint FK_CODE_EMPLOYEE_EMP_ID
        foreign key (EMP_ID) references employee (EMP_ID),
    constraint FK_CODE_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.goods
(
    GOODS_ID     int auto_increment comment '編號'
        primary key,
    STORE_ID     int                                 not null comment '店家編號',
    GOODS_IMG    longblob                            not null comment '圖片',
    GOODS_NAME   varchar(50)                         not null comment '名稱',
    GOODS_STATUS tinyint   default 0                 not null comment '狀態  0:下架1:上架 2:審核中 新增修改狀態為：2 審核完畢後改狀態為：0(讓店家選擇是否要上架)',
    GOODS_PRICE  int                                 not null comment '價格',
    GOODS_TEXT   varchar(2000)                       not null comment '介紹',
    GOODS_TIME   datetime  default CURRENT_TIMESTAMP not null comment '新增日期',
    GOODS_RTIME  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改日期',
    constraint FK_GOODS_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.like_art
(
    LIKE_ID int auto_increment comment '編號'
        primary key,
    ART_ID  int not null comment '文章編號',
    MEM_ID  int not null comment '會員編號',
    constraint FK_LIKE_ART_ARTICLE_ART_ID
        foreign key (ART_ID) references article (ART_ID),
    constraint FK_LIKE_ART_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID)
);

create table `cga105g2`.meal
(
    MEAL_ID     int auto_increment comment '編號'
        primary key,
    STORE_ID    int               not null comment '店家編號',
    MEAL_NAME   varchar(50)       not null comment '名稱',
    MEAL_PRICE  int               not null comment '價格',
    MEAL_STATUS tinyint default 0 null comment '狀態 0: 下架  1:上架',
    constraint FK_MEAL_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.mem_code
(
    CODE_ID int not null comment '優惠券編號',
    MEM_ID  int not null comment '會員編號',
    primary key (CODE_ID, MEM_ID),
    constraint FK_MEM_CODE_CODE_CODE_ID
        foreign key (CODE_ID) references code (CODE_ID),
    constraint FK_MEM_CODE_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID)
);

create table `cga105g2`.`order`
(
    ORDER_ID     int auto_increment comment '編號'
        primary key,
    MEM_ID       int                                 not null comment '會員編號',
    STORE_ID     int                                 not null comment '店家編號',
    ORDER_PRICE  int                                 not null comment '總金額',
    CODE_ID      int                                 null comment '優惠券編號',
    ORDER_FRE    tinyint   default 80                not null comment '運費  80: 超商  120: 宅配',
    ORDER_FPRICE int                                 not null comment '支付金額',
    ORDER_TEXT   varchar(2000)                       null comment '備註',
    ORDER_STATUS tinyint   default 0                 not null comment '狀態  0: 成立   1: 取消   2: 出貨  3:完成   ',
    ORDER_TIME   datetime  default CURRENT_TIMESTAMP not null comment '新增日期',
    ORDER_OTIME  date                                null comment '出貨日期',
    ORDER_RTIME  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改日期',
    constraint FK_ORDER_CODE_CODE_ID
        foreign key (CODE_ID) references code (CODE_ID),
    constraint FK_ORDER_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID),
    constraint FK_ORDER_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.order_detail
(
    ORDER_ID        int not null comment '訂單編號',
    GOODS_ID        int not null comment '商品編號',
    DETAIL_QUANTITY int not null comment '商品數量',
    DETAILPRICE     int not null comment '商品價格',
    primary key (ORDER_ID, GOODS_ID),
    constraint FK_ORDER_DETAIL_GOODS_GOODS_ID
        foreign key (GOODS_ID) references goods (GOODS_ID),
    constraint FK_ORDER_DETAIL_ORDER_ORDER_ID
        foreign key (ORDER_ID) references `order` (ORDER_ID)
);

create table `cga105g2`.reserva
(
    REN_ID        int auto_increment comment '編號'
        primary key,
    STORE_ID      int                                not null comment '店家編號',
    MEM_ID        int                                not null comment '會員編號',
    REN_NAME      varchar(50)                        not null comment '姓名',
    REN_PHONE     varchar(50)                        not null comment '電話',
    REN_TIME      varchar(50)                        not null comment '時段',
    REN_STATUS    tinyint  default 0                 not null comment '狀態  0: 成立   1:取消  2:報到  3:完成',
    REN_TABLE     int                                null comment '桌號',
    REN_DATE      date                               not null comment '訂位日期',
    REN_CURDATE   datetime default CURRENT_TIMESTAMP not null comment '時間',
    REN_HEADCOUNT int                                not null comment '人數',
    CODE_ID       int                                null comment '優惠券編號',
    REN_PRICE     int                                null comment '總金額',
    REN_FPRICE    int                                null comment '支付金額',
    constraint FK_RESERVA_CODE_CODE_ID
        foreign key (CODE_ID) references code (CODE_ID),
    constraint FK_RESERVA_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID),
    constraint FK_RESERVA_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.reserva_detail
(
    REN_ID      int not null comment '訂位編號',
    MEAL_ID     int not null comment '餐點編號',
    RD_QUANTITY int not null comment '數量',
    PD_PRICE    int not null comment '總金額',
    primary key (REN_ID, MEAL_ID),
    constraint FK_RESERVA_DETAIL_MEAL_MEAL_ID
        foreign key (MEAL_ID) references meal (MEAL_ID),
    constraint FK_RESERVA_DETAIL_RESERVA_REN_ID
        foreign key (REN_ID) references reserva (REN_ID)
);

create table `cga105g2`.save_art
(
    ART_ID int not null comment '文章編號',
    MEM_ID int not null comment '會員編號',
    primary key (ART_ID, MEM_ID),
    constraint FK_SAVE_ART_ARTICLE_ART_ID
        foreign key (ART_ID) references article (ART_ID),
    constraint FK_SAVE_ART_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID)
);

create table `cga105g2`.standby
(
    STA_ID     int auto_increment comment '編號'
        primary key,
    STORE_ID   int                                not null comment '店家編號',
    STA_NAME   varchar(50)                        not null comment '姓名',
    STA_PHONE  varchar(50)                        not null comment '電話',
    STA_NUMBER int                                not null comment '人數',
    STA_TIME   datetime default CURRENT_TIMESTAMP not null comment '新增時間',
    STA_STATUS tinyint  default 0                 not null comment '狀態;0:候位中   1:已通知   2:已報到',
    constraint FK_STANDBY_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.subscribe
(
    SUB_ID   int auto_increment comment '編號'
        primary key,
    STORE_ID int not null comment '店家編號',
    MEM_ID   int not null comment '會員編號',
    constraint FK_SUBSCRIBE_MEMBER_MEM_ID
        foreign key (MEM_ID) references member (MEM_ID),
    constraint FK_SUBSCRIBE_STORE_STORE_ID
        foreign key (STORE_ID) references store (STORE_ID)
);

create table `cga105g2`.smessage
(
    SMESSAGE_ID   int auto_increment comment '編號'
        primary key,
    SUB_ID        int                                not null comment '訂閱編號',
    SMESSAGE_TXET varchar(50)                        not null comment '內容',
    SMESSAGE_TIME datetime default CURRENT_TIMESTAMP not null comment '新增時間',
    constraint FK_SMESSAGE_SUBSCRIBE_SUB_ID
        foreign key (SUB_ID) references subscribe (SUB_ID)
);

