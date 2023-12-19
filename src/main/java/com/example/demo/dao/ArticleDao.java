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
					, boardId = 1
					, title = #{title}
					, body = #{body}
			""")
	public void writeArticle(String title, String body, int memberId);
	
	@Select("""
			<script>	
				SELECT A.*, M.name AS writerName
					FROM article AS A
					INNER JOIN `member` AS M
					ON A.memberId = M.id
					<if test="boardId != 0">
						WHERE A.boardId = #{boardId}
					</if>
					ORDER BY A.id DESC
			</script>
			""")
	public List<Article> getArticles(int boardId);
	
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
				<if test="boardId != 0">
					WHERE boardId = #{boardId}
				</if>
			</script>
			""")
	public int getArticlesCut(int boardId);
}
