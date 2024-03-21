package com.core.entity;

import lombok.Getter;

@Getter
public enum ErrorTitle {
    CLASS_NOT_FOUND("class不存在錯誤"),
    UNDEF_TITLE("方法未覆寫拋出錯誤"),
    CONNECTION_TITLE("連線錯誤"),
    UNKNOWN_TITLE("未定義例外"),

    DOWNLOAD_TITLE("檔案下載錯誤"),
    CONNECT_TITLE("資料連結錯誤"),
    PROCESS_TITLE("資料處理錯誤"),
    IMPORT_TITLE("匯入處理錯誤"),
    CONTENT_TITLE("資料處理內容"),
    ANALYSIS_TITLE("分析暫存錯誤"),



    UPDATE_TITLE("更新資料錯誤"),
    INSERT_TITLE("新增資料錯誤"),
    SELECT_TITLE("查詢資料錯誤"),
    DELETE_TITLE("刪除資料錯誤"),
    ROLLBACK_TITLE("rollback錯誤"),



    READILY_TITLE("讀取檔案錯誤"),
    PARAMETER_TITLE("內部參數有誤"),
    INTERCESSOR_TITLE("內部處理有誤"),
    FORMAT_TITLE("資料格式有誤"),
    FILE_TITLE("檔案處理錯誤"),
    FILE_CANT_FIND("檔案不存在"),
    METHOD_TITLE("功能函式錯誤");

    private final String title;

    private ErrorTitle(String title) {
        this.title = title;
    }

    public String getTitle(String description) {
        return this.title + "::" + description;
    }
}
