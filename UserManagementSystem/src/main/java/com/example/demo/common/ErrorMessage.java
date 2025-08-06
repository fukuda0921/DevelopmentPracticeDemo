package com.example.demo.common;

public class ErrorMessage {
//	出勤
	public static final String INVALID_DATE = "本日の日付を入力してください（B001）";
	public static final String DUPLICATE_ATTENDANCE = "入力された出勤データははすでに登録済みです。勤怠編集画面から修正してください（B002）";
	
//	退勤
	public static final String NOT_MUCH_WORK_DATE = "入力された退勤日が出勤日と一致しません（B003）";
	public static final String DUPLICATE_LEAVING_WORK = "入力された退勤情報はすでに登録済みです（B004）";
}
