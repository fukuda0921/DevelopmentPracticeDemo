package com.example.demo.common;

public class ErrorMessage {
	
    /** 出勤関連 */
	public static final String INVALID_DATE = "本日の日付を入力してください（B001）";
	public static final String DUPLICATE_ATTENDANCE = "入力された出勤データははすでに登録済みです。勤怠編集画面から修正してください（B002）";
	
    /** 退勤関連 */
	public static final String NOT_MUCH_WORK_DATE = "入力された退勤日が出勤日と一致しません（B003）";
	public static final String DUPLICATE_LEAVING_WORK = "入力された退勤情報はすでに登録済みです（B004）";
	
	
	// 勤怠情報関連のエラーメッセージ
    public static final String ATTENDANCE_NOT_FOUND = "勤怠情報が見つかりません";
}
