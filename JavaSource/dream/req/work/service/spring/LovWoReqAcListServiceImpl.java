package dream.req.work.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.req.work.dao.LovWoReqAcListDAO;
import dream.req.work.dto.LovWoReqAcListDTO;
import dream.req.work.form.LovWoReqAcListForm;
import dream.req.work.service.LovWoReqAcListService;

/**
 * 濛機蹂羶 で機 ServiceImpl
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 *
 * @spring.bean id="lovWoReqAcListServiceTarget"
 * @spring.txbn id="lovWoReqAcListService"
 * @spring.property name="lovWoReqAcListDAO" ref="lovWoReqAcListDAO"
 */
public class LovWoReqAcListServiceImpl implements LovWoReqAcListService
{
    /** 濛機 で機 DAO */
    private LovWoReqAcListDAO lovWoReqAcListDAO = null;

    public LovWoReqAcListDAO getLovWoReqAcListDAO() {
		return lovWoReqAcListDAO;
	}
	public void setLovWoReqAcListDAO(LovWoReqAcListDAO lovWoReqAcListDAO) {
		this.lovWoReqAcListDAO = lovWoReqAcListDAO;
	}

	@Override
	public List findWoReqAcList(LovWoReqAcListDTO lovWoReqAcListDTO, User user, LovWoReqAcListForm lovWoReqAcListForm)
	{
		String jsonParam    = lovWoReqAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovWoReqAcListDAO.findWoReqAcList(lovWoReqAcListDTO, user ,conditionMap);
	}
	
	public String findTotalCount(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser, LovWoReqAcListForm lovWoReqAcListForm)  throws Exception
    {
		String jsonParam    = lovWoReqAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

        return lovWoReqAcListDAO.findTotalCount(lovWoReqAcListDTO, loginUser, conditionMap);
    }

}