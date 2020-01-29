package dream.work.service.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.service.dao.LovWorkServiceListDAO;
import dream.work.service.dto.LovWorkServiceListDTO;
import dream.work.service.form.LovWorkServiceListForm;
import dream.work.service.service.LovWorkServiceListService;

/**
 * ¼­ºñ½º LOV - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="lovWorkServiceListServiceTarget"
 * @spring.txbn id="lovWorkServiceListService"
 * @spring.property name="lovWorkServiceListDAO" ref="lovWorkServiceListDAO"
 */
public class LovWorkServiceListServiceImpl implements LovWorkServiceListService
{
	private LovWorkServiceListDAO lovWorkServiceListDAO = null;

	public List findList(LovWorkServiceListForm lovWorkServiceListForm, User user) throws Exception
    {      
		LovWorkServiceListDTO lovWorkServiceListDTO = lovWorkServiceListForm.getLovWorkServiceListDTO();
		
		String code         = lovWorkServiceListForm.getCode();    //Search Value
        String keyCode      = lovWorkServiceListForm.getKeyCode();  //Search Column
        String codeType     = lovWorkServiceListForm.getCodeType(); //Table
        String jsonParam    = lovWorkServiceListForm.getParam();  //Condition
        String jaonCol      = lovWorkServiceListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovWorkServiceListDAO.findList(lovWorkServiceListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovWorkServiceListForm lovWorkServiceListForm, User user) throws Exception
    {      
		LovWorkServiceListDTO lovWorkServiceListDTO = lovWorkServiceListForm.getLovWorkServiceListDTO();
		
		String code         = lovWorkServiceListForm.getCode();    //Search Value
        String keyCode      = lovWorkServiceListForm.getKeyCode();  //Search Column
        String codeType     = lovWorkServiceListForm.getCodeType(); //Table
        String jsonParam    = lovWorkServiceListForm.getParam();  //Condition
        String jaonCol      = lovWorkServiceListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovWorkServiceListDAO.findTotalCount(lovWorkServiceListDTO,user, columnMap ,conditionMap);
    }

	public LovWorkServiceListDAO getLovWorkServiceListDAO() {
		return lovWorkServiceListDAO;
	}

	public void setLovWorkServiceListDAO(LovWorkServiceListDAO lovWorkServiceListDAO) {
		this.lovWorkServiceListDAO = lovWorkServiceListDAO;
	}
	
}

