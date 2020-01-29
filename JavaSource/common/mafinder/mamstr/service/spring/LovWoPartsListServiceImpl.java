package common.mafinder.mamstr.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.mafinder.mamstr.dao.LovWoPartsListDAO;
import common.mafinder.mamstr.dto.LovWoPartsListDTO;
import common.mafinder.mamstr.form.LovWoPartsListForm;
import common.mafinder.mamstr.service.LovWoPartsListService;

/**
 * 濛機睡ヶ で機 ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovWoPartsListServiceTarget"
 * @spring.txbn id="lovWoPartsListService"
 * @spring.property name="lovWoPartsListDAO" ref="lovWoPartsListDAO"
 */
public class LovWoPartsListServiceImpl implements LovWoPartsListService
{
    /** 濛機 で機 DAO */
    private LovWoPartsListDAO lovWoPartsListDAO = null;

    public LovWoPartsListDAO getLovWoPartsListDAO() {
		return lovWoPartsListDAO;
	}

	public void setLovWoPartsListDAO(LovWoPartsListDAO lovWoPartsListDAO) {
		this.lovWoPartsListDAO = lovWoPartsListDAO;
	}
	
	@Override
	public List findWoPartsAcList(LovWoPartsListDTO lovWoPartsListDTO, User user, LovWoPartsListForm lovWoPartsListForm) {
		String jsonParam    = lovWoPartsListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovWoPartsListDAO.findWoPartsAcList(lovWoPartsListDTO, user ,conditionMap);
	}

	@Override
	public String findWoPartsListTotalCount(LovWoPartsListDTO lovWoPartsListDTO,User user, LovWoPartsListForm lovWoPartsListForm)
	{
		String jsonParam    = lovWoPartsListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
	    return lovWoPartsListDAO.findWoPartsListTotalCount(lovWoPartsListDTO, user, conditionMap);
	}


}