package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;
import com.example.demo.vo.Board;

@Mapper
public interface ArticleDao {
	
	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, boardId = #{boardId}
					, title = #{title}
					, body = #{body}
			""")
	public void writeArticle(String title, String body, int memberId, int boardId);
	
	@Select("""
			<script>	
				SELECT A.*, M.name AS writerName
					FROM article AS A
					INNER JOIN `member` AS M
					ON A.memberId = M.id
					WHERE 1=1
					<if test="boardId != 0">
						AND A.boardId = #{boardId}
					</if>
					<if test="searchKeyword != ''">
						<choose>
							<when test="searchKeywordType == 'keywordTitle'">
								AND title LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<when test="searchKeywordType == 'keywordBody'">
								AND body LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<otherwise>
								AND (
									title LIKE CONCAT('%', #{searchKeyword}, '%')
									OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
									)
							</otherwise>
						</choose>
					</if>
					ORDER BY A.id DESC
					LIMIT #{limitStart}, #{itemsInAPage}
			</script>
			""")
	public List<Article> getArticles(int boardId, int limitStart, int itemsInAPage, String searchKeyword, String searchKeywordType);
	
	@Select("""
			SELECT *
				FROM article 
				WHERE A.id = #{id}
			""")
	public Article forPrintArticle(int id);
	
	@Select("""
			SELECT A.*, M.name AS writerName
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				WHERE A.id = #{id}
			""")
	public Article getArticleById(int id);
	
	
	@Update("""
			<script>
				UPDATE article
					SET updateDate = NOW()
						<if test="title != null and title != ''">
							, title = #{title}
						</if>
						<if test="body != null and body != ''">
							, `body` = #{body}
						</if>
					WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);
	
	@Delete("""
			DELETE
				FROM article
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastInsertId();

	@Select("""
			<script>
				SELECT COUNT(*)
					FROM article
					WHERE boardId = #{boardId}
					<if test="searchKeyword != ''">
						<choose>
							<when test="searchKeywordType == 'keywordTitle'">
								AND title LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<when test="searchKeywordType == 'keywordBody'">
								AND body LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<otherwise>
								AND (
									title LIKE CONCAT('%', #{searchKeyword}, '%')
									OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
									)
							</otherwise>
						</choose>
					</if>
			</script>
			""")
	public int getArticlesCnt(int boardId, String searchKeyword, String searchKeywordType);

	@Select("""
			SELECT authLevel
			FROM `member`
			WHERE id = #{loginedMemberId}
			""")
	public int getWriterAuthLevel(int loginedMemberId);
	
	@Update("""
			UPDATE article
				SET id
			""")
	public void incrementHitCount(int id);
}
