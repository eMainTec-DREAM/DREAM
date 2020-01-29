package dream.mgr.usage.cal.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.usage.cal.dao.LovUsageWrkAcListDAO;
import dream.mgr.usage.cal.dto.LovUsageWrkAcListDTO;
import dream.mgr.usage.cal.form.LovUsageWrkAcListForm;
import dream.mgr.usage.cal.service.LovUsageWrkAcListService;

/**
 * 사용달력 LOV - List Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="lovUsageWrkAcListServiceTarget"
 * @spring.txbn id="lovUsageWrkAcListService"
 * @spring.property name="lovUsageWrkAcListDAO" ref="lovUsageWrkAcListDAO"
 */
public class LovUsageWrkAcListServiceImpl implements LovUsageWrkAcListService
{
	private LovUsageWrkAcListDAO lovUsageWrkAcListDAO = null;

	public List findList(LovUsageWrkAcListForm lovUsageWrkAcListForm, User user) throws Exception
    {      
		LovUsageWrkAcListDTO lovUsageWrkAcListDTO = lovUsageWrkAcListForm.getLovUsageWrkAcListDTO();
		
		String code         = lovUsageWrkAcListForm.getCode();    //Search Value
        String keyCode      = lovUsageWrkAcListForm.getKeyCode();  //Search Column
        String codeType     = lovUsageWrkAcListForm.getCodeType(); //Table
        String jsonParam    = lovUsageWrkAcListForm.getParam();  //Condition
        String jaonCol      = lovUsageWrkAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovUsageWrkAcListDAO.findList(lovUsageWrkAcListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovUsageWrkAcListForm lovUsageWrkAcListForm, User user) throws Exception
    {      
		LovUsageWrkAcListDTO lovUsageWrkAcListDTO = lovUsageWrkAcListForm.getLovUsageWrkAcListDTO();
		
		String code         = lovUsageWrkAcListForm.getCode();    //Search Value
        String keyCode      = lovUsageWrkAcListForm.getKeyCode();  //Search Column
        String codeType     = lovUsageWrkAcListForm.getCodeType(); //Table
        String jsonParam    = lovUsageWrkAcListForm.getParam();  //Condition
        String jaonCol      = lovUsageWrkAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovUsageWrkAcListDAO.findTotalCount(lovUsageWrkAcListDTO,user, columnMap ,conditionMap);
    }

	public LovUsageWrkAcListDAO getLovUsageWrkAcListDAO() {
		return lovUsageWrkAcListDAO;
	}

	public void setLovUsageWrkAcListDAO(LovUsageWrkAcListDAO lovUsageWrkAcListDAO) {
		this.lovUsageWrkAcListDAO = lovUsageWrkAcListDAO;
	}
	
}

