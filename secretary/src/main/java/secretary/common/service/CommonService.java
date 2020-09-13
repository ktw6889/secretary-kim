package secretary.common.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secretary.common.model.MessageVo;

@Transactional
@Service
public class CommonService {

	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	public SqlSession sqlSession;

	public String getNewId(String tableName) throws Exception {
		return sqlSession.selectOne("seckim.user.idGen", tableName);
	}

	public MessageVo getMessageBundle(String messageCode, Locale locale) {
		MessageVo resultVo = sqlSession.selectOne("seckim.com.selectMessage", messageCode);
		if ("en".equals(locale.getLanguage())) {
			resultVo.setValue(resultVo.getLableEn());
		} else {
			resultVo.setValue(resultVo.getLableKo());
		}

		return resultVo;
	}

	public List<Map<String, String>> getColumnInfo(String tableName) throws Exception {
		List<Map<String, String>> resultList = sqlSession.selectList("seckim.com.selectColumn", tableName);
		return resultList;
	}

}
