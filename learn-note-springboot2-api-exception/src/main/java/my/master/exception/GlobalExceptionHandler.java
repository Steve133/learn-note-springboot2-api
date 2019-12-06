package my.master.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.master.pojo.ErrorInfo;

import javax.servlet.http.HttpServletRequest;

//全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");//最后将Exception对象和请求URL映射到error.html中
		return mav;
	}

	@ExceptionHandler(value = MyException.class)//@ExceptionHandler用来定义函数针对的异常类型
//	@ResponseBody
	public ModelAndView jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", r.getMessage());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");//最后将Exception对象和请求URL映射到error.html中
		return mav;
	}

}
