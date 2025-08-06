package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.security.LoginUser;

@Repository
public class LoginUserRepository {
	
	private final DataSource dataSource;
	
	@Autowired
    public LoginUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }     

	 public LoginUser findByUserId(Integer userIdParam) {
        String sql = """
            SELECT a.user_id, a.role, b.password
            FROM user_permission_tbl AS a
            LEFT OUTER JOIN user_tbl AS b ON a.user_id = b.user_id
            WHERE a.user_id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userIdParam); // 文字列型の userId をセット

            try (ResultSet rs = ps.executeQuery()) {
                String password = null;
                Set<String> roleSet = new HashSet<>();
                Integer userId = null;

                while (rs.next()) {
                    if (userId == null) {
                        userId = rs.getInt("user_id");
                        password = rs.getString("password");
                    }
                    String role = rs.getString("role");
                    if (role != null) {
                        roleSet.add(role);
                    }
                }

                if (userId == null) {
                    return null;
                }

                return new LoginUser(userId, password, new ArrayList<>(roleSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("ログインユーザー取得エラー", e);
        }
    }
}
             
	//	private static final String SQL_FIND_BY_USERID = """
	//			SELECT a.user_id, a.role, b.password
	//			FROM user_permission_tbl AS a
	//			LEFT OUTER JOIN user_tbl AS b
	//			ON a.user_id = b.user_id
	//			WHERE a.user_id = :userId
	//			""";

	//	private static final ResultSetExtractor<LoginUser> LOGIN_USER_EXTRACTOR = rs -> {
	//		Integer userId = null;
	//		String password = null;
	////		List<String> roleList = new ArrayList<>();
	//		Set<String> roleSet = new HashSet<>();

//	while(rs.next())
//	{
//		if (userId == null) {
//			userId = rs.getObject("user_id", Integer.class); // Integer 型で取得
//			password = rs.getString("password");
//		}
//
//		String role = rs.getString("role");
//		if (role != null) {
//			roleSet.add(role);
//		}
//
//		//			roleList.add(rs.getString("role"));
//	}
//
//	if(userId==null)
//	{
//		return null;// データがなければ null を返す
//	}return new LoginUser(userId,password,new ArrayList<>(roleSet));};
//
//	private final NamedParameterJdbcTemplate jdbc;
//
//	// コンストラクタでNamedParameterJdbcTemplateを注入
//	public LoginUserRepository(NamedParameterJdbcTemplate jdbc) {
//		this.jdbc = jdbc;
//	}
//
//	// Optionalでユーザー情報を返す
//	public Optional<LoginUser> findByUserId(String userId) {
//		MapSqlParameterSource params = new MapSqlParameterSource("userId", userId);
//		LoginUser loginUser = jdbc.query(SQL_FIND_BY_USERID, params, LOGIN_USER_EXTRACTOR);
//		return Optional.ofNullable(loginUser);
//
//	}
//
//}
