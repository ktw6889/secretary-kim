package secretary.common.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
 
    @Autowired
	private SqlSession sqlSession;
    
    public String selectNow() {
        return sqlSession.selectOne("seckim.test.selectNow");
    }
}

