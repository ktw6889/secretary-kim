package secretary.common.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secretary.common.model.ComDdVO;
import secretary.common.model.ComDdValueVO;
import secretary.common.service.DdService;

@Controller
@RequestMapping(value = "/dd")
public class DdController {

	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private DdService ddService;

	@RequestMapping(value = "/list")
	public String list(Model model, ComDdVO ddVO, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range) throws Exception {

		try {
			// 전체 게시글 개수
			int listCnt = ddService.getListCnt(ddVO);

			ddVO.pageInfo(page, range, listCnt);
			model.addAttribute("pagination", ddVO);
			model.addAttribute("masterList", ddService.getList(ddVO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "kims:/common/dd/ddList";
	}

	@RequestMapping(value = "/view")
	public String view(Model model, @RequestParam("id") String id) throws Exception {
		model.addAttribute("masterVO", ddService.getSelect(id));
		model.addAttribute("itemVO", new ComDdValueVO());
		return "kims:/common/dd/ddView";
	}

	@RequestMapping("/edit")
	public String edit(Model model, ComDdVO ddVO) throws Exception {
		ComDdVO resultVo = ddService.getSelect(ddVO.getId());
		if (resultVo == null || StringUtils.isEmpty(resultVo.getId())) {
			resultVo = new ComDdVO();
		}
		model.addAttribute("masterVO", resultVo);
		return "kims:/common/dd/ddEdit";
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public ComDdVO save(@RequestBody ComDdVO ddVO) throws Exception {
		ComDdVO returnVo = ddService.save(ddVO);
		return returnVo;
	}

	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("id") String id) throws Exception {
		ddService.delete(id);
		return "kims:/common/dd/list.do";
	}

}
