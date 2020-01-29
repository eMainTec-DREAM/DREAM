package dream.mgr.cccd.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.cccd.dao.LovCtCtrListDAO;
import dream.mgr.cccd.dto.LovCtCtrListDTO;
import dream.mgr.cccd.form.LovCtCtrListForm;
import dream.mgr.cccd.service.LovCtCtrListService;

/**
 * CPÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovCtCtrListServiceTarget"
 * @spring.txbn id="lovCtCtrListService"
 * @spring.property name="lovCtCtrListDAO" ref="lovCtCtrListDAO"
 */
public class LovCtCtrListServiceImpl implements LovCtCtrListService
{
    /** CPÆË¾÷ DAO */
    private LovCtCtrListDAO lovCtCtrListDAO = null;

    public LovCtCtrListDAO getLovCtCtrListDAO() 
    {
		return lovCtCtrListDAO;
	}

	public void setLovCtCtrListDAO(LovCtCtrListDAO lovCtCtrListDAO) 
	{
		this.lovCtCtrListDAO = lovCtCtrListDAO;
	}

	public List findCtCtrList(LovCtCtrListDTO lovCtCtrListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovCtCtrListDAO.findCtCtrList(lovCtCtrListDTO, loginUser);
        
        return resultList;
    }

    @Override
    public List findCtCtrACList(LovCtCtrListDTO lovCtCtrListDTO, User user, LovCtCtrListForm lovCtCtrListForm)
    {
        List resultList = null;
        
        String jsonParam    = lovCtCtrListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        resultList = lovCtCtrListDAO.findCtCtrACList(lovCtCtrListDTO, user, conditionMap);
        
        return resultList;
    }

	@Override
	public String findTotalCount(LovCtCtrListDTO lovCtCtrListDTO, User user, LovCtCtrListForm lovCtCtrListForm)
	{
        String jsonParam    = lovCtCtrListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovCtCtrListDAO.findTotalCount(lovCtCtrListDTO, user, conditionMap);
	}
}