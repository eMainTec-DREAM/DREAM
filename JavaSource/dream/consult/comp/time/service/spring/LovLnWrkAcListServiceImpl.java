package dream.consult.comp.time.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.time.dao.LovLnWrkAcListDAO;
import dream.consult.comp.time.dto.LovLnWrkAcListDTO;
import dream.consult.comp.time.form.LovLnWrkAcListForm;
import dream.consult.comp.time.service.LovLnWrkAcListService;

/**
 * 가동달력 LOV - List Service implements
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListServiceImpl.java,v 1.0 2017/12/14 11:09:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="lovLnWrkAcListServiceTarget"
 * @spring.txbn id="lovLnWrkAcListService"
 * @spring.property name="lovLnWrkAcListDAO" ref="lovLnWrkAcListDAO"
 */
public class LovLnWrkAcListServiceImpl implements LovLnWrkAcListService
{
	private LovLnWrkAcListDAO lovLnWrkAcListDAO = null;

	public List findList(LovLnWrkAcListForm lovLnWrkAcListForm, User user) throws Exception
    {      
		LovLnWrkAcListDTO lovLnWrkAcListDTO = lovLnWrkAcListForm.getLovLnWrkAcListDTO();
		
		String code         = lovLnWrkAcListForm.getCode();    //Search Value
        String keyCode      = lovLnWrkAcListForm.getKeyCode();  //Search Column
        String codeType     = lovLnWrkAcListForm.getCodeType(); //Table
        String jsonParam    = lovLnWrkAcListForm.getParam();  //Condition
        String jaonCol      = lovLnWrkAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

		
        return lovLnWrkAcListDAO.findList(lovLnWrkAcListDTO,user, columnMap ,conditionMap);
    }

	public String findTotalCount(LovLnWrkAcListForm lovLnWrkAcListForm, User user) throws Exception
    {      
		LovLnWrkAcListDTO lovLnWrkAcListDTO = lovLnWrkAcListForm.getLovLnWrkAcListDTO();
		
		String code         = lovLnWrkAcListForm.getCode();    //Search Value
        String keyCode      = lovLnWrkAcListForm.getKeyCode();  //Search Column
        String codeType     = lovLnWrkAcListForm.getCodeType(); //Table
        String jsonParam    = lovLnWrkAcListForm.getParam();  //Condition
        String jaonCol      = lovLnWrkAcListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovLnWrkAcListDAO.findTotalCount(lovLnWrkAcListDTO,user, columnMap ,conditionMap);
    }

	public LovLnWrkAcListDAO getLovLnWrkAcListDAO() {
		return lovLnWrkAcListDAO;
	}

	public void setLovLnWrkAcListDAO(LovLnWrkAcListDAO lovLnWrkAcListDAO) {
		this.lovLnWrkAcListDAO = lovLnWrkAcListDAO;
	}
	
}

