package dream.ass.base.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.ass.base.dao.AssBaseValLovDAO;
import dream.ass.base.dto.AssBaseValLovDTO;
import dream.ass.base.form.AssBaseValLovForm;
import dream.ass.base.service.AssBaseValLovService;

/**
 * 설비등급 평가기준 LOV - List Service implements
 * @author kim21017
 * @version $Id: AssBaseValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBaseValLovServiceTarget"
 * @spring.txbn id="assBaseValLovService"
 * @spring.property name="assBaseValLovDAO" ref="assBaseValLovDAO"
 */
public class AssBaseValLovServiceImpl implements AssBaseValLovService
{
	private AssBaseValLovDAO assBaseValLovDAO = null;

	public List findList(AssBaseValLovForm assBaseValLovForm, User user) throws Exception
    {      
		AssBaseValLovDTO assBaseValLovDTO = assBaseValLovForm.getAssBaseValLovDTO();
		
		String code         = assBaseValLovForm.getCode();    //Search Value
        String keyCode      = assBaseValLovForm.getKeyCode();  //Search Column
        String codeType     = assBaseValLovForm.getCodeType(); //Table
        String jsonParam    = assBaseValLovForm.getParam();  //Condition
        String jaonCol      = assBaseValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return assBaseValLovDAO.findList(assBaseValLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(AssBaseValLovForm assBaseValLovForm, User user) throws Exception
    {      
		AssBaseValLovDTO assBaseValLovDTO = assBaseValLovForm.getAssBaseValLovDTO();
		
		String code         = assBaseValLovForm.getCode();    //Search Value
        String keyCode      = assBaseValLovForm.getKeyCode();  //Search Column
        String codeType     = assBaseValLovForm.getCodeType(); //Table
        String jsonParam    = assBaseValLovForm.getParam();  //Condition
        String jaonCol      = assBaseValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return assBaseValLovDAO.findTotalCount(assBaseValLovDTO,user, columnMap ,conditionMap);
    }

	public AssBaseValLovDAO getAssBaseValLovDAO() {
		return assBaseValLovDAO;
	}

	public void setAssBaseValLovDAO(AssBaseValLovDAO assBaseValLovDAO) {
		this.assBaseValLovDAO = assBaseValLovDAO;
	}
	
}

