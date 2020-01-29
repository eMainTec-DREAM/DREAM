package dream.tool.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.tool.list.dao.LovToolsListDAO;
import dream.tool.list.dto.LovToolsListDTO;
import dream.tool.list.form.LovToolsListForm;
import dream.tool.list.service.LovToolsListService;

/**
 * 濠營で機 ServiceImpl
 * @author  kim21017
 * @version $Id: LovToolsListServiceImpl.java,v 1.5 2015/01/09 00:16:42 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovToolsListServiceTarget"
 * @spring.txbn id="lovToolsListService"
 * @spring.property name="lovToolsListDAO" ref="lovToolsListDAO"
 */
public class LovToolsListServiceImpl implements LovToolsListService
{
    /** 濠營で機 DAO */
    private LovToolsListDAO lovToolsListDAO = null;

    public LovToolsListDAO getLovToolsListDAO() {
		return lovToolsListDAO;
	}

	public void setLovToolsListDAO(LovToolsListDAO lovToolsListDAO) {
		this.lovToolsListDAO = lovToolsListDAO;
	}

	public List findPartsList(LovToolsListDTO lovToolsListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovToolsListDAO.findPartsList(lovToolsListDTO, loginUser);
        
        
        return resultList;
    }

	@Override
	public List findToolAcList(LovToolsListDTO lovToolsListDTO, User loginUser, LovToolsListForm lovToolsListForm) {
		String jsonParam    = lovToolsListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovToolsListDAO.findToolAcList(lovToolsListDTO, loginUser ,conditionMap);
	}

	@Override
	public String findTotalCount(LovToolsListDTO lovToolsListDTO, User loginUser, LovToolsListForm lovToolsListForm)
			throws Exception {
		String jsonParam    = lovToolsListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovToolsListDAO.findTotalCount(lovToolsListDTO, loginUser ,conditionMap);
	}
}