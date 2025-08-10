package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.example.demo.entity.UserEditEntity;
import com.example.demo.entity.UserEditProjectEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserEditDto {

	private Integer userId; // ユーザーID

	@NotBlank(message = "氏名を入力してください")
	@Size(max = 20, message = "20文字以内で入力してください")
	@Pattern(regexp = "^[^\\x00-\\x7F]+$", message = "全角で入力してください")
	private String name; // 氏名

	@NotBlank(message = "フリガナを入力してください")
	@Size(max = 20, message = "20文字以内で入力してください")
	@Pattern(regexp = "^[^\\x00-\\x7F]+$", message = "全角で入力してください")
	private String kana; // フリガナ

	@NotBlank(message = "メールアドレスを入力してください")
	@Size(max = 60, message = "60文字以下で入力してください")
	@Email(regexp = "[\\w\\-._]+@[\\w\\-._]+\\.[A-Za-z]+", message = "メールアドレスの形式で入力してください")
	//	@Pattern(regexp = "^[a-zA-Z0-9._-]+$",message = "半角で入力してください")
	private String address; // メールアドレス

	private String password; // パスワード

	@NotNull(message = "権限を選択してください")
	private Integer role; // 権限

	// 案件情報は複数の可能性を考慮しListで管理
	private List<UserEditProjectDto> projects = new ArrayList<>();
	
	 public UserEditDto() {
	        
	    }

	// コンストラクタの追加
	public UserEditDto(UserEditEntity entity) {
		this.userId = entity.getUserId();
		this.name = entity.getName();
		this.kana = entity.getKana();
		this.address = entity.getAddress();
		this.password = entity.getPassword();
		this.role = entity.getRole();
		
		// ProjectEntityリストからDTOリストへ変換
		if (entity.getProjects() != null) {
			for (UserEditProjectEntity p : entity.getProjects()) {
				this.projects.add(new UserEditProjectDto(p));
			}
		}
	}
}