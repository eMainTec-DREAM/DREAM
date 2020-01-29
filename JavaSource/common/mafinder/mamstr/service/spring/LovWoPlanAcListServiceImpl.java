package common.mafinder.mamstr.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.mafinder.mamstr.dao.LovWoPlanAcListDAO;
import common.mafinder.mamstr.dto.LovWoPlanAcListDTO;
import common.mafinder.mamstr.form.LovWoPlanAcListForm;
import common.mafinder.mamstr.service.LovWoPlanAcListService;

/**
 * 濛機 で機 ServiceImpl
 * @author  kim21017
 * @version $Id: LovWoPlanAcListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovWoPlanAcListServiceTarget"
 * @spring.txbn id="lovWoPlanAcListService"
 * @spring.property name="lovWoPlanAcListDAO" ref="lovWoPlanAcListDAO"
 */
public class LovWoPlanAcListServiceImpl implements LovWoPlanAcListService
{
    /** 濛機 で機 DAO */
    private LovWoPlanAcListDAO lovWoPlanAcListDAO = null;

    public LovWoPlanAcListDAO getLovWoPlanAcListDAO() {
		return lovWoPlanAcListDAO;
	}

	public void setLovWoPlanAcListDAO(LovWoPlanAcListDAO lovWoPlanAcListDAO) {
		this.lovWoPlanAcListDAO = lovWoPlanAcListDAO;
	}

	public List findWoPlanAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovWoPlanAcListDAO.findWoPlanAcList(lovWoPlanAcListDTO, loginUser);
        
        
        return resultList;
    }

	@Override
	public List findWoAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user, LovWoPlanAcListForm lovWoPlanAcListForm) {
		String jsonParam    = lovWoPlanAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovWoPlanAcListDAO.findWoAcList(lovWoPlanAcListDTO, user ,conditionMap);
	}

	@Override
	public String findWoListTotalCount(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user,
			LovWoPlanAcListForm lovWoPlanAcListForm) {
		String jsonParam    = lovWoPlanAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
	    return lovWoPlanAcListDAO.findWoListTotalCount(lovWoPlanAcListDTO, user, conditionMap);
	}

}