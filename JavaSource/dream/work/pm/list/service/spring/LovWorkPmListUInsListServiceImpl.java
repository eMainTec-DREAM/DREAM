package dream.work.pm.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.work.pm.list.dao.LovWorkPmListUInsListDAO;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;
import dream.work.pm.list.form.LovWorkPmListUInsListForm;
import dream.work.pm.list.service.LovWorkPmListUInsListService;

/**
 * 에너지 측정기준주기 Lov ServiceImpl
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovWorkPmListUInsListServiceTarget"
 * @spring.txbn id="lovWorkPmListUInsListService"
 * @spring.property name="lovWorkPmListUInsListDAO" ref="lovWorkPmListUInsListDAO"
 */
public class LovWorkPmListUInsListServiceImpl implements LovWorkPmListUInsListService
{
    /** 설비종류별부품 팝업 DAO */
    private LovWorkPmListUInsListDAO lovWorkPmListUInsListDAO = null;

	public LovWorkPmListUInsListDAO getLovWorkPmListUInsListDAO()
    {
        return lovWorkPmListUInsListDAO;
    }

    public void setLovWorkPmListUInsListDAO(LovWorkPmListUInsListDAO lovWorkPmListUInsListDAO)
    {
        this.lovWorkPmListUInsListDAO = lovWorkPmListUInsListDAO;
    }

	@Override
	public List findPmUInsListAcList(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, LovWorkPmListUInsListForm lovWorkPmListUInsListForm) throws Exception {

		String jsonParam = lovWorkPmListUInsListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovWorkPmListUInsListDAO.findPmUInsListAcList(lovWorkPmListUInsListDTO, user, conditionMap);
	}
	
	@Override
	public String findTotalCount(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User loginUser, LovWorkPmListUInsListForm lovWorkPmListUInsListForm) throws Exception 
	{
		String jsonParam    = lovWorkPmListUInsListForm.getParam();  //Condition
		
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
		
		return lovWorkPmListUInsListDAO.findTotalCount(lovWorkPmListUInsListDTO, loginUser ,conditionMap);
	}

}