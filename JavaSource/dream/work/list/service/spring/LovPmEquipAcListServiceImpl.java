package dream.work.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.list.dao.LovPmEquipAcListDAO;
import dream.work.list.dto.LovPmEquipAcListDTO;
import dream.work.list.form.LovPmEquipAcListForm;
import dream.work.list.service.LovPmEquipAcListService;

/**
 * 积魂前格 Lov ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPmEquipAcListServiceTarget"
 * @spring.txbn id="lovPmEquipAcListService"
 * @spring.property name="lovPmEquipAcListDAO" ref="lovPmEquipAcListDAO"
 */
public class LovPmEquipAcListServiceImpl implements LovPmEquipAcListService
{
    /** 积魂前格 扑诀 DAO */
    private LovPmEquipAcListDAO lovPmEquipAcListDAO = null;

	public LovPmEquipAcListDAO getLovPmEquipAcListDAO()
    {
        return lovPmEquipAcListDAO;
    }

    public void setLovPmEquipAcListDAO(LovPmEquipAcListDAO lovPmEquipAcListDAO)
    {
        this.lovPmEquipAcListDAO = lovPmEquipAcListDAO;
    }

    public List findPmEquipAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovPmEquipAcListDAO.findPmEquipAcList(lovPmEquipAcListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findPmEquipAcAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User user, LovPmEquipAcListForm lovPmEquipAcListForm) {
		String jsonParam    = lovPmEquipAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovPmEquipAcListDAO.findPmEquipAcAcList(lovPmEquipAcListDTO, user ,conditionMap);
	}
}