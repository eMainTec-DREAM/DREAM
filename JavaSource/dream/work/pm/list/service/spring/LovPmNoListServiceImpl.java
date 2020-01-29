package dream.work.pm.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.pm.list.dao.LovPmNoListDAO;
import dream.work.pm.list.dto.LovPmNoListDTO;
import dream.work.pm.list.form.LovPmNoListForm;
import dream.work.pm.list.service.LovPmNoListService;

/**
 * 예방점검팝업 ServiceImpl
 * @author  kim21017
 * @version $Id: LovPmNoListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovPmNoListServiceTarget"
 * @spring.txbn id="lovPmNoListService"
 * @spring.property name="lovPmNoListDAO" ref="lovPmNoListDAO"
 */
public class LovPmNoListServiceImpl implements LovPmNoListService
{
    /** 예방점검팝업 DAO */
    private LovPmNoListDAO lovPmNoListDAO = null;

    public LovPmNoListDAO getLovPmNoListDAO() {
		return lovPmNoListDAO;
	}

	public void setLovPmNoListDAO(LovPmNoListDAO lovPmNoListDAO) {
		this.lovPmNoListDAO = lovPmNoListDAO;
	}

	public List findPmList(LovPmNoListDTO lovPmNoListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovPmNoListDAO.findPmList(lovPmNoListDTO, loginUser);
        
        
        return resultList;
    }
	public List findPmAcList(LovPmNoListForm lovPmNoListForm, User loginUser)
    {
		LovPmNoListDTO lovPmNoListDTO = lovPmNoListForm.getLovPmNoListDTO();
		
		String code         = lovPmNoListForm.getCode();    //Search Value
        String keyCode      = lovPmNoListForm.getKeyCode();  //Search Column
        String codeType     = lovPmNoListForm.getCodeType(); //Table
        String jsonParam    = lovPmNoListForm.getParam();  //Condition
        String jaonCol      = lovPmNoListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovPmNoListDAO.findPmAcList(lovPmNoListDTO, loginUser, columnMap ,conditionMap);
    }
	
	public String findTotalCount(LovPmNoListForm lovPmNoListForm, User loginUser)
    {
		LovPmNoListDTO lovPmNoListDTO = lovPmNoListForm.getLovPmNoListDTO();
		
		String code         = lovPmNoListForm.getCode();    //Search Value
        String keyCode      = lovPmNoListForm.getKeyCode();  //Search Column
        String codeType     = lovPmNoListForm.getCodeType(); //Table
        String jsonParam    = lovPmNoListForm.getParam();  //Condition
        String jaonCol      = lovPmNoListForm.getResultCol();  //Result Column
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        Map<String,String> columnMap = gson.fromJson(jaonCol, type);
        
        return lovPmNoListDAO.findTotalCount(lovPmNoListDTO, loginUser, columnMap ,conditionMap);
    }
}