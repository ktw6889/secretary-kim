package secretary.common.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import secretary.common.annotation.LogExecutionTime;
import secretary.common.service.CommonService;
import secretary.common.service.TestService;
import secretary.common.util.CommonUtil;

@Controller
public class MainController {

	private Logger log = LogManager.getLogger(this.getClass());

	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainController.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private TestService testService;

	@Autowired
	SessionLocaleResolver localeResolver;

	@Autowired
	MessageSource messageSource;

	@Value("#{configProperties['props.name']}")
	private String PROPS_NAME;

	@RequestMapping(value = "/main.do")
	@LogExecutionTime
	public String main(Locale locale, Model model) {
		String result = testService.selectNow();
		log.info("=======>main22222: " + result + ":" + PROPS_NAME);
		encryptTest();
		return "kims:/common/main";
	}

	@RequestMapping(value = "/test.do")
	public String test(@RequestParam Map<String, String> param) throws Exception {
		String tableName = param.get("tableName");
		List<Map<String, String>> list = commonService.getColumnInfo(tableName);

		CommonUtil commonUtil = new CommonUtil();
		commonUtil.printColumn(tableName, list);

		return "admin/admin";
	}

	@RequestMapping(value = "/jsrender/commonTable.do")
	public String testColumn(Model model) throws Exception {
		return "kims:/test/jsrender/commonTable";
	}

	@RequestMapping(value = "/jsrender/jsonCombine.do")
	public String jsonCombine(Model model) throws Exception {
		return "kims:/test/jsrender/jsonCombine";
	}

	@RequestMapping(value = "/i18n.do", method = RequestMethod.GET)
	public String i18n(Locale locale, HttpServletRequest request, Model model) {
		// RequestMapingHandler로 부터 받은 Locale 객체를 출력해 봅니다.
		logger.info("Welcome i18n! The client locale is {}.", locale);
		// localeResolver 로부터 Locale 을 출력해 봅니다.
		logger.info("Session locale is {}.", localeResolver.resolveLocale(request),
				localeResolver.resolveLocale(request));
		//
		logger.info("LABEL.APPNAME", messageSource.getMessage("LABEL.APPNAME", null, locale));
		// JSP 페이지에서 EL 을 사용해서 arguments 를 넣을 수 있도록 값을 보낸다.
		model.addAttribute("siteCount", messageSource.getMessage("LABEL.APPNAME", null, locale));
		return "common/main";
	}

	@RequestMapping(value = "/admin/admin.do")
	public String admin(Locale locale, Model model) {
		log.info("=======>admin ");

		return "admin/admin";
	}

	@RequestMapping(value = "/login.do")
	public String login(Locale locale, Model model) {
		log.info("=======>login ");

		return "auth/login";
	}

	@RequestMapping(value = "/tilesMain.do")
	public String tilesMain(Locale locale, Model model) {
		log.info("tilesMain");

		return "kims:/common/main";
	}

	public void encryptTest() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("encryptpassword");
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		// encryptor.setSaltGenerator(new StringFixedSaltGenerator("someFixedSalt"));

		String str = "1234";
		String encStr = encryptor.encrypt(str);
		String decStr = encryptor.decrypt(encStr);
		log.info("encStr : " + encStr);
	}

}
