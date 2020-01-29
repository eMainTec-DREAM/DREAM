package dream.pers.priv.info.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.pers.priv.info.dao.LovMsgCompListDAO;
import dream.pers.priv.info.dto.LovMsgCompListDTO;
import dream.pers.priv.info.form.LovMsgCompListForm;
import dream.pers.priv.info.service.LovMsgCompListService;

/**
 * 메시지타입 Lov ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovMsgCompListServiceTarget"
 * @spring.txbn id="lovMsgCompListService"
 * @spring.property name="lovMsgCompListDAO" ref="lovMsgCompListDAO"
 */
public class LovMsgCompListServiceImpl implements LovMsgCompListService
{
    /** 메시지타입 팝업 DAO */
    private LovMsgCompListDAO lovMsgCompListDAO = null;

	public LovMsgCompListDAO getLovMsgCompListDAO()
    {
        return lovMsgCompListDAO;
    }

    public void setLovMsgCompListDAO(LovMsgCompListDAO lovMsgCompListDAO)
    {
        this.lovMsgCompListDAO = lovMsgCompListDAO;
    }

	@Override
	public List findMsgCompAcList(LovMsgCompListDTO lovMsgCompListDTO, User user, LovMsgCompListForm lovMsgCompListForm)
	{
		String jsonParam    = lovMsgCompListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovMsgCompListDAO.findMsgCompAcList(lovMsgCompListDTO, user, conditionMap);
	}

	@Override
	public String findTotalCount(LovMsgCompListDTO lovMsgCompListDTO, User user, LovMsgCompListForm lovMsgCompListForm) throws Exception
	{
		String jsonParam    = lovMsgCompListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovMsgCompListDAO.findTotalCount(lovMsgCompListDTO, user, conditionMap);
	}
	
}