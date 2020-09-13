package secretary.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import secretary.common.service.CommonService;
import secretary.user.model.UserItemVO;
import secretary.user.model.UserVO;

@Service("userService2")
public class UserService extends CommonService {

	public UserVO getUserInfo(UserVO userVo) throws Exception {
		// 사용자 정보 가져오기
		UserVO resultVo = getUser(userVo);
		// 취미
		if (resultVo.getHobby() != null)
			resultVo.setStrsHobby(resultVo.getHobby().split(","));

		// item 정보 가져오기
		resultVo.setUserItemList(getUserItemList(resultVo));

		return resultVo;
	}

	public UserVO getUser(UserVO vo) throws Exception {
		UserVO resultVo = new UserVO();
		if (!StringUtils.isEmpty(vo.getId()))
			resultVo = sqlSession.selectOne("seckim.user.select", vo);
		return resultVo;
	}

	public List<UserItemVO> getUserItemList(UserVO vo) throws Exception {
		List<UserItemVO> userItemList = sqlSession.selectList("seckim.user.itemSelect", vo);
		return userItemList;
	}

	public List<UserVO> getUserList(UserVO vo) throws Exception {
		List<UserVO> list = sqlSession.selectList("seckim.user.select", vo);
		return list;
	}

	public void saveUser(UserVO userVo) throws Exception {
		// userInfo 저장
		userVo.setHobby(userVo.convertStrsHooby());

		if (userVo != null && StringUtils.isEmpty(userVo.getId())) {
			logger.info("seckim.user.insert");
			String id = sqlSession.selectOne("seckim.user.idGen", "ST_USER_INFO");
			userVo.setId(id);
			sqlSession.insert("seckim.user.insert", userVo);
		} else {
			logger.info("seckim.user.update");
			sqlSession.update("seckim.user.update", userVo);
		}

		// item 저장
		saveUserItem(userVo);
	}

	public void saveUserItem(UserVO userVo) throws Exception {
		List<UserItemVO> userItemList = userVo.getUserItemList();
		System.out.println("userItemList.size():" + userItemList.size());

		for (int i = 0; i < userItemList.size(); i++) {
			UserItemVO userItemVO = userItemList.get(i);
			userItemVO.setApplyModule(userItemVO.convertStrsApplyModule());
			System.out.println("projectName[" + i + "]:" + userItemVO.getProjectName() + ":" + userItemVO.getRowFlag());
			if ("I".equals(userItemVO.getRowFlag())) {
				String objId = this.getNewId("ST_USER_INFO_ITEM");
				userItemVO.setId(objId);
				userItemVO.setUserInfoId(userVo.getId());
				sqlSession.insert("seckim.user.itemInsert", userItemVO);
			} else if ("U".equals(userItemVO.getRowFlag())) {
				sqlSession.update("seckim.user.itemUpdate", userItemVO);
			} else if ("D".equals(userItemVO.getRowFlag())) {
				sqlSession.update("seckim.user.itemDelete", userItemVO);
			}
		}
		// userRegistVO.setUserItemList(commonDao.getList("useritem.view",
		// userRegistVO.getUserInfoVO()) );
	}

}
