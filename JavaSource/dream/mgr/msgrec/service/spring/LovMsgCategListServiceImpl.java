package dream.mgr.msgrec.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.mgr.msgrec.dao.LovMsgCategListDAO;
import dream.mgr.msgrec.dto.LovMsgCategListDTO;
import dream.mgr.msgrec.form.LovMsgCategListForm;
import dream.mgr.msgrec.service.LovMsgCategListService;

/**
 * 메시지타입 Lov ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovMsgCategListServiceTarget"
 * @spring.txbn id="lovMsgCategListService"
 * @spring.property name="lovMsgCategListDAO" ref="lovMsgCategListDAO"
 */
public class LovMsgCategListServiceImpl implements LovMsgCategListService
{
    /** 메시지타입 팝업 DAO */
    private LovMsgCategListDAO lovMsgCategListDAO = null;

	public LovMsgCategListDAO getLovMsgCategListDAO()
    {
        return lovMsgCategListDAO;
    }

    public void setLovMsgCategListDAO(LovMsgCategListDAO lovMsgCategListDAO)
    {
        this.lovMsgCategListDAO = lovMsgCategListDAO;
    }

	@Override
	public List findMsgCategAcList(LovMsgCategListDTO lovMsgCategListDTO, User user, LovMsgCategListForm lovMsgCategListForm)
	{
		String jsonParam    = lovMsgCategListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovMsgCategListDAO.findMsgCategAcList(lovMsgCategListDTO, user, conditionMap);
	}

	@Override
	public String findTotalCount(LovMsgCategListDTO lovMsgCategListDTO, User user, LovMsgCategListForm lovMsgCategListForm) throws Exception
	{
		String jsonParam    = lovMsgCategListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovMsgCategListDAO.findTotalCount(lovMsgCategListDTO, user, conditionMap);
	}
	
}