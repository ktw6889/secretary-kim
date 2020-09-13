package secretary.common.util;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice {

	public Logger logger = Logger.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public String errorException(Model model, Exception e) {
		logger.info("@ControllerAdvice 방식 \n###exeption : " + e);
		try {
			throw new Exception(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		model.addAttribute("exception", e);

		return "kims:/error/exception";
	}

}
