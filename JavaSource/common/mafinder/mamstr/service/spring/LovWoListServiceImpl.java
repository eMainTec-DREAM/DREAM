package common.mafinder.mamstr.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.mafinder.mamstr.dao.LovWoListDAO;
import common.mafinder.mamstr.dto.LovWoListDTO;
import common.mafinder.mamstr.form.LovWoListForm;
import common.mafinder.mamstr.service.LovWoListService;

/**
 * 濛機 で機 ServiceImpl
 * @author  kim21017
 * @version $Id: LovWoListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovWoListServiceTarget"
 * @spring.txbn id="lovWoListService"
 * @spring.property name="lovWoListDAO" ref="lovWoListDAO"
 */
public class LovWoListServiceImpl implements LovWoListService
{
    /** 濛機 で機 DAO */
    private LovWoListDAO lovWoListDAO = null;

    public LovWoListDAO getLovWoListDAO() {
		return lovWoListDAO;
	}

	public void setLovWoListDAO(LovWoListDAO lovWoListDAO) {
		this.lovWoListDAO = lovWoListDAO;
	}

	public List findWoList(LovWoListDTO lovWoListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovWoListDAO.findWoList(lovWoListDTO, loginUser);
        
        
        return resultList;
    }

	@Override
	public List findWoAcList(LovWoListDTO lovWoListDTO, User user, LovWoListForm lovWoListForm) {
		String jsonParam    = lovWoListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovWoListDAO.findWoAcList(lovWoListDTO, user ,conditionMap);
	}

	@Override
	public String findWoListTotalCount(LovWoListDTO lovWoListDTO,User user, LovWoListForm lovWoListForm)
	{
		String jsonParam    = lovWoListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
	    return lovWoListDAO.findWoListTotalCount(lovWoListDTO, user, conditionMap);
	}


}