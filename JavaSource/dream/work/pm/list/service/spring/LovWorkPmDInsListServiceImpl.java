package dream.work.pm.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;
import dream.work.pm.list.dao.LovWorkPmDInsListDAO;
import dream.work.pm.list.dto.LovWorkPmDInsListDTO;
import dream.work.pm.list.form.LovWorkPmDInsListForm;
import dream.work.pm.list.service.LovWorkPmDInsListService;

/**
 * 점검항목 ServiceImpl
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovWorkPmDInsListServiceTarget"
 * @spring.txbn id="lovWorkPmDInsListService"
 * @spring.property name="lovWorkPmDInsListDAO" ref="lovWorkPmDInsListDAO"
 */
public class LovWorkPmDInsListServiceImpl implements LovWorkPmDInsListService
{
    /** 설비종류별부품 팝업 DAO */
    private LovWorkPmDInsListDAO lovWorkPmDInsListDAO = null;

	public LovWorkPmDInsListDAO getLovWorkPmDInsListDAO()
    {
        return lovWorkPmDInsListDAO;
    }

    public void setLovWorkPmDInsListDAO(LovWorkPmDInsListDAO lovWorkPmDInsListDAO)
    {
        this.lovWorkPmDInsListDAO = lovWorkPmDInsListDAO;
    }

	@Override
	public List findEqCtgPartAcList(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User user, LovWorkPmDInsListForm lovWorkPmDInsListForm) throws Exception {

		String jsonParam = lovWorkPmDInsListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovWorkPmDInsListDAO.findEqCtgPartAcList(lovWorkPmDInsListDTO, user, conditionMap);
	}
	
	@Override
	public String findTotalCount(LovWorkPmDInsListDTO lovWorkPmDInsListDTO, User loginUser, LovWorkPmDInsListForm lovWorkPmDInsListForm) throws Exception 
	{
		String jsonParam    = lovWorkPmDInsListForm.getParam();  //Condition
		
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
		
		return lovWorkPmDInsListDAO.findTotalCount(lovWorkPmDInsListDTO, loginUser ,conditionMap);
	}

}