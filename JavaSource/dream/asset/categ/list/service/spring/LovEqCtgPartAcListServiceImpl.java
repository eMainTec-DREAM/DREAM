package dream.asset.categ.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.asset.categ.list.dao.LovEqCtgPartAcListDAO;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.asset.categ.list.form.LovEqCtgPartAcListForm;
import dream.asset.categ.list.service.LovEqCtgPartAcListService;

/**
 * 설비종류별부품 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovEqCtgPartAcListServiceTarget"
 * @spring.txbn id="lovEqCtgPartAcListService"
 * @spring.property name="lovEqCtgPartAcListDAO" ref="lovEqCtgPartAcListDAO"
 */
public class LovEqCtgPartAcListServiceImpl implements LovEqCtgPartAcListService
{
    /** 설비종류별부품 팝업 DAO */
    private LovEqCtgPartAcListDAO lovEqCtgPartAcListDAO = null;

	public LovEqCtgPartAcListDAO getLovEqCtgPartAcListDAO()
    {
        return lovEqCtgPartAcListDAO;
    }

    public void setLovEqCtgPartAcListDAO(LovEqCtgPartAcListDAO lovEqCtgPartAcListDAO)
    {
        this.lovEqCtgPartAcListDAO = lovEqCtgPartAcListDAO;
    }

    public List findEqCtgPartAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovEqCtgPartAcListDAO.findEqCtgPartAcList(lovEqCtgPartAcListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findEqCtgPartAcAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user, LovEqCtgPartAcListForm lovEqCtgPartAcListForm) throws Exception {
		String jsonParam    = lovEqCtgPartAcListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovEqCtgPartAcListDAO.findEqCtgPartAcAcList(lovEqCtgPartAcListDTO, user ,conditionMap);
	}

	@Override
	public String findTotalCount(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User loginUser, LovEqCtgPartAcListForm lovEqCtgPartAcListForm) throws Exception 
	{
		String jsonParam    = lovEqCtgPartAcListForm.getParam();  //Condition
		
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
		
		return lovEqCtgPartAcListDAO.findTotalCount(lovEqCtgPartAcListDTO, loginUser ,conditionMap);
	}
}