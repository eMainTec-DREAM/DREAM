package dream.mgr.usrcd.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrcd.dao.LovCdUsrdAcListDAO;
import dream.mgr.usrcd.dto.LovCdUsrdAcListDTO;
import dream.mgr.usrcd.form.LovCdUsrdAcListForm;
import dream.mgr.usrcd.service.LovCdUsrdAcListService;

/**
 * 상세코드 ServiceImpl
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovCdUsrdAcListServiceTarget"
 * @spring.txbn id="lovCdUsrdAcListService"
 * @spring.property name="lovCdUsrdAcListDAO" ref="lovCdUsrdAcListDAO"
 */
public class LovCdUsrdAcListServiceImpl implements LovCdUsrdAcListService
{
    /** 상세코드팝업 DAO */
    private LovCdUsrdAcListDAO lovCdUsrdAcListDAO = null;

	public LovCdUsrdAcListDAO getLovCdUsrdAcListDAO()
    {
        return lovCdUsrdAcListDAO;
    }

    public void setLovCdUsrdAcListDAO(LovCdUsrdAcListDAO lovCdUsrdAcListDAO)
    {
        this.lovCdUsrdAcListDAO = lovCdUsrdAcListDAO;
    }

    public List findCdUsrdList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovCdUsrdAcListDAO.findCdUsrdList(lovCdUsrdAcListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findCdUsrdAcList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User user, LovCdUsrdAcListForm lovCdUsrdAcListForm) {
	    String jsonParam    = lovCdUsrdAcListForm.getParam();  //Condition
	    
	    Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
	    List list =  lovCdUsrdAcListDAO.findCdUsrdAcList(lovCdUsrdAcListDTO, user , conditionMap);
	    return CommonUtil.makeTreeList(list, "P_CDUSRD_ID", "CDUSRD_ID", false);
    }
}