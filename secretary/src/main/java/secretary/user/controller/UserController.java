package secretary.user.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import secretary.user.model.UserVO;
import secretary.user.service.UserService;

@RequestMapping("/user/")
@Controller
public class UserController {

	// private org.slf4j.Logger logger =
	// org.slf4j.LoggerFactory.getLogger(UserController.class);
	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService2;

	@RequestMapping(value = "/test.do")
	public String test() throws Exception {
		logger.info("id: " + userService2.getNewId("ST_USER_INFO_ITEM"));
		return "/admin/admin";
	}

	@RequestMapping(value = "/view.do")
	public String userView(Model model, UserVO userVo) throws Exception {
		logger.info("userEdit");
		UserVO resultVo = userService2.getUserInfo(userVo);

		model.addAttribute("userVo", resultVo);
		return "kims:/user/userView";
	}

	@RequestMapping(value = "/edit.do")
	public String userEdit(Model model, UserVO userVo) throws Exception {
		logger.info("userEdit");
		UserVO resultVo = userService2.getUserInfo(userVo);

		model.addAttribute("userVo", resultVo);
		return "kims:/user/userEdit";
	}

	@RequestMapping(value = "/list.do")
	public String userList(Model model, UserVO userVo) throws Exception {
		logger.info("userList");
		List<UserVO> resultList = userService2.getUserList(userVo);

		model.addAttribute("userList", resultList);
		return "kims:/user/userList";
	}

	@RequestMapping(value = "/save.do")
	public String userSave(UserVO userVo) throws Exception {
		logger.info("userSave");
		userService2.saveUser(userVo);
		return "forward:/user/edit.do";
	}

	@ResponseBody
	@RequestMapping(value = "/userInfoJson.do")
	public UserVO userInfoJson(Model model, UserVO userVo) throws Exception {
		logger.info("userInfoJson");
		UserVO resultVo = userService2.getUserInfo(userVo);
		model.addAttribute("userVo", resultVo);
		return resultVo;
	}

	@ResponseBody
	@RequestMapping(value = "/checkUserId.do")
	public UserVO checkUserId(Model model, UserVO userVo) throws Exception {
		logger.info("checkUserId");
		String resultMsg = "";
		List<UserVO> resultList = userService2.getUserList(userVo);
		if (resultList.size() == 1)
			resultMsg = "success";
		userVo.setUserId(resultMsg);
		return userVo;
	}

	@RequestMapping(value = "/editJson.do")
	public String userEditJson(Model model, UserVO userVo) throws Exception {
		logger.info("userEditJson");
		return "kims:/user/userEditJson";
	}

	@RequestMapping(value = "/saveJson.do")
	public String userSaveJson(@RequestBody UserVO userVo) throws Exception {
		logger.info("userEditJson:" + userVo.getUserName() + ":" + userVo.getUserItemList().size());
		userService2.saveUser(userVo);
		return "forward:/user/edit.do";
	}

}
