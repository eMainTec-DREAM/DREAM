package dream.part.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;
import dream.part.list.dao.LovPartListBinListDAO;
import dream.part.list.dto.LovPartListBinListDTO;
import dream.part.list.form.LovPartListBinListForm;
import dream.part.list.service.LovPartListBinListService;

/**
 * 점검항목 ServiceImpl
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPartListBinListServiceTarget"
 * @spring.txbn id="lovPartListBinListService"
 * @spring.property name="lovPartListBinListDAO" ref="lovPartListBinListDAO"
 */
public class LovPartListBinListServiceImpl implements LovPartListBinListService
{
    /** 설비종류별부품 팝업 DAO */
    private LovPartListBinListDAO lovPartListBinListDAO = null;

	public LovPartListBinListDAO getLovPartListBinListDAO()
    {
        return lovPartListBinListDAO;
    }

    public void setLovPartListBinListDAO(LovPartListBinListDAO lovPartListBinListDAO)
    {
        this.lovPartListBinListDAO = lovPartListBinListDAO;
    }

	@Override
	public List findEqCtgPartAcList(LovPartListBinListDTO lovPartListBinListDTO, User user, LovPartListBinListForm lovPartListBinListForm) throws Exception {

		String jsonParam = lovPartListBinListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovPartListBinListDAO.findEqCtgPartAcList(lovPartListBinListDTO, user, conditionMap);
	}
	
	@Override
	public String findTotalCount(LovPartListBinListDTO lovPartListBinListDTO, User loginUser, LovPartListBinListForm lovPartListBinListForm) throws Exception 
	{
		String jsonParam    = lovPartListBinListForm.getParam();  //Condition
		
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
		
		return lovPartListBinListDAO.findTotalCount(lovPartListBinListDTO, loginUser ,conditionMap);
	}

}