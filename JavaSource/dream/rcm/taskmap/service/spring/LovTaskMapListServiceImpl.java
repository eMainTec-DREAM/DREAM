package dream.rcm.taskmap.service.spring;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.rcm.taskmap.dao.LovTaskMapListDAO;
import dream.rcm.taskmap.dto.LovTaskMapListDTO;
import dream.rcm.taskmap.form.LovTaskMapListForm;
import dream.rcm.taskmap.service.LovTaskMapListService;

/**
 * 설비부위팝업 ServiceImpl
 * @author  hyosung
 * @version $Id: LovTaskMapListServiceImpl.java,v 1.5 2015/01/09 00:16:42 hyosung Exp $
 * @since   1.0
 *
 * @spring.bean id="lovTaskMapListServiceTarget"
 * @spring.txbn id="lovTaskMapListService"
 * @spring.property name="lovTaskMapListDAO" ref="lovTaskMapListDAO"
 */
public class LovTaskMapListServiceImpl implements LovTaskMapListService
{
    /** 질의 DAO */
    private LovTaskMapListDAO lovTaskMapListDAO = null;

    public LovTaskMapListDAO getLovTaskMapListDAO() {
        return lovTaskMapListDAO;
    }

    public void setLovTaskMapListDAO(LovTaskMapListDAO lovTaskMapListDAO) {
        this.lovTaskMapListDAO = lovTaskMapListDAO;
    }

    public List findTaskMapList(LovTaskMapListDTO lovTaskMapListDTO, User loginUser, LovTaskMapListForm lovTaskMapListForm)
    {
        
        String jsonParam    = lovTaskMapListForm.getParam();  //Condition
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovTaskMapListDAO.findTaskMapList(lovTaskMapListDTO, loginUser,conditionMap);
    }
}