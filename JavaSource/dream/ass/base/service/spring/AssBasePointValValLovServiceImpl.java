package dream.ass.base.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.ass.base.dao.AssBasePointValValLovDAO;
import dream.ass.base.dto.AssBasePointValValLovDTO;
import dream.ass.base.form.AssBasePointValValLovForm;
import dream.ass.base.service.AssBasePointValValLovService;

/**
 * 설비등급 평가기준 LOV - List Service implements
 * @author kim21017
 * @version $Id: AssBasePointValValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="assBasePointValValLovServiceTarget"
 * @spring.txbn id="assBasePointValValLovService"
 * @spring.property name="assBasePointValValLovDAO" ref="assBasePointValValLovDAO"
 */
public class AssBasePointValValLovServiceImpl implements AssBasePointValValLovService
{
	private AssBasePointValValLovDAO assBasePointValValLovDAO = null;

	public List findList(AssBasePointValValLovForm assBasePointValValLovForm, User user) throws Exception
    {      
		AssBasePointValValLovDTO assBasePointValValLovDTO = assBasePointValValLovForm.getAssBasePointValValLovDTO();
		
		String code         = assBasePointValValLovForm.getCode();    //Search Value
        String keyCode      = assBasePointValValLovForm.getKeyCode();  //Search Column
        String codeType     = assBasePointValValLovForm.getCodeType(); //Table
        String jsonParam    = assBasePointValValLovForm.getParam();  //Condition
        String jaonCol      = assBasePointValValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return assBasePointValValLovDAO.findList(assBasePointValValLovDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(AssBasePointValValLovForm assBasePointValValLovForm, User user) throws Exception
    {      
		AssBasePointValValLovDTO assBasePointValValLovDTO = assBasePointValValLovForm.getAssBasePointValValLovDTO();
		
		String code         = assBasePointValValLovForm.getCode();    //Search Value
        String keyCode      = assBasePointValValLovForm.getKeyCode();  //Search Column
        String codeType     = assBasePointValValLovForm.getCodeType(); //Table
        String jsonParam    = assBasePointValValLovForm.getParam();  //Condition
        String jaonCol      = assBasePointValValLovForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return assBasePointValValLovDAO.findTotalCount(assBasePointValValLovDTO,user, columnMap ,conditionMap);
    }

	public AssBasePointValValLovDAO getAssBasePointValValLovDAO() {
		return assBasePointValValLovDAO;
	}

	public void setAssBasePointValValLovDAO(AssBasePointValValLovDAO assBasePointValValLovDAO) {
		this.assBasePointValValLovDAO = assBasePointValValLovDAO;
	}
	
}

