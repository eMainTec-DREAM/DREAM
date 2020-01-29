package dream.part.iss.wo.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.part.iss.wo.dao.LovSerialListDAO;
import dream.part.iss.wo.dto.LovSerialListDTO;
import dream.part.iss.wo.form.LovSerialListForm;
import dream.part.iss.wo.service.LovSerialListService;

/**
 * 설비부위팝업 ServiceImpl
 * @author  hyosung
 * @version $Id: LovSerialListServiceImpl.java,v 1.5 2015/01/09 00:16:42 hyosung Exp $
 * @since   1.0
 *
 * @spring.bean id="lovSerialListServiceTarget"
 * @spring.txbn id="lovSerialListService"
 * @spring.property name="lovSerialListDAO" ref="lovSerialListDAO"
 */
public class LovSerialListServiceImpl implements LovSerialListService
{
    /** 순환자재 DAO */
    private LovSerialListDAO lovSerialListDAO = null;
    
    public LovSerialListDAO getLovSerialListDAO()
    {
        return lovSerialListDAO;
    }
    public void setLovSerialListDAO(LovSerialListDAO lovSerialListDAO)
    {
        this.lovSerialListDAO = lovSerialListDAO;
    }

    public List findSerialList(LovSerialListDTO lovSerialListDTO, User loginUser, LovSerialListForm lovSerialListForm)
    {
        
        String jsonParam    = lovSerialListForm.getParam();  //Condition
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
       
        return lovSerialListDAO.findSerialList(lovSerialListDTO, loginUser,conditionMap);
    }
}