package dream.part.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.part.list.dao.LovPartsListDAO;
import dream.part.list.dto.LovPartsListDTO;
import dream.part.list.form.LovPartsListForm;
import dream.part.list.service.LovPartsListService;

/**
 * 濠營で機 ServiceImpl
 * @author  kim21017
 * @version $Id: LovPartsListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovPartsListServiceTarget"
 * @spring.txbn id="lovPartsListService"
 * @spring.property name="lovPartsListDAO" ref="lovPartsListDAO"
 */
public class LovPartsListServiceImpl implements LovPartsListService
{
    /** 濠營で機 DAO */
    private LovPartsListDAO lovPartsListDAO = null;

    public LovPartsListDAO getLovPartsListDAO() {
		return lovPartsListDAO;
	}

	public void setLovPartsListDAO(LovPartsListDAO lovPartsListDAO) {
		this.lovPartsListDAO = lovPartsListDAO;
	}

	public List findPartsList(LovPartsListDTO lovPartsListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovPartsListDAO.findPartsList(lovPartsListDTO, loginUser);
        
        
        return resultList;
    }
	public List findPartsAcList(LovPartsListForm lovPartsListForm, User loginUser)
    {
		LovPartsListDTO lovPartsListDTO = lovPartsListForm.getLovPartsListDTO();
		
		String code         = lovPartsListForm.getCode();    //Search Value
        String keyCode      = lovPartsListForm.getKeyCode();  //Search Column
        String codeType     = lovPartsListForm.getCodeType(); //Table
        String jsonParam    = lovPartsListForm.getParam();  //Condition
        String jaonCol      = lovPartsListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);

        return lovPartsListDAO.findPartsAcList(lovPartsListDTO, loginUser, columnMap ,conditionMap);
    }
	
	public String findTotalCount(LovPartsListForm lovPartsListForm, User user)  throws Exception
    {
	    LovPartsListDTO lovPartsListDTO = lovPartsListForm.getLovPartsListDTO();
	    
	    String code         = lovPartsListForm.getCode();    //Search Value
        String keyCode      = lovPartsListForm.getKeyCode();  //Search Column
        String codeType     = lovPartsListForm.getCodeType(); //Table
        String jsonParam    = lovPartsListForm.getParam();  //Condition
        String jaonCol      = lovPartsListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
	    
        return lovPartsListDAO.findTotalCount(lovPartsListDTO, user, columnMap ,conditionMap);
    }
}