package dream.consult.comp.warehouse.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.consult.comp.warehouse.dao.LovWhListDAO;
import dream.consult.comp.warehouse.dto.LovWhListDTO;
import dream.consult.comp.warehouse.form.LovWhListForm;
import dream.consult.comp.warehouse.service.LovWhListService;

/**
 * 사용창고 팝업 ServiceImpl
 * @author  kim21017
 * @version $Id: LovWhListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovWhListServiceTarget"
 * @spring.txbn id="lovWhListService"
 * @spring.property name="lovWhListDAO" ref="lovWhListDAO"
 */
public class LovWhListServiceImpl implements LovWhListService
{
    /** 사용창고 팝업 DAO */
    private LovWhListDAO lovWhListDAO = null;

    public LovWhListDAO getLovWhListDAO() {
		return lovWhListDAO;
	}

	public void setLovWhListDAO(LovWhListDAO lovWhListDAO) {
		this.lovWhListDAO = lovWhListDAO;
	}

	public List findWhList(LovWhListDTO lovWhListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovWhListDAO.findWhList(lovWhListDTO, loginUser);
        
        
        return resultList;
    }

    @Override
    public List findWhACList(LovWhListDTO lovWhListDTO, User user,
            LovWhListForm lovWhListForm)
    {
        List resultList = null;
        
        String jsonParam    = lovWhListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        resultList = lovWhListDAO.findWhACList(lovWhListDTO, user,conditionMap);
        
        return resultList;
    }

	@Override
	public String findTotalCount(LovWhListDTO lovWhListDTO, User user, LovWhListForm lovWhListForm)
	{
        String jsonParam    = lovWhListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovWhListDAO.findTotalCount(lovWhListDTO, user, conditionMap);
	}
}