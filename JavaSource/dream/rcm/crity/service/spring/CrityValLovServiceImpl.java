package dream.rcm.crity.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.rcm.crity.dao.CrityValLovDAO;
import dream.rcm.crity.dto.CrityValLovDTO;
import dream.rcm.crity.form.CrityValLovForm;
import dream.rcm.crity.service.CrityValLovService;

/**
 * Criticality Matrix Page - List Service implements
 * @author hyosung
 * @version $Id: CrityValLovServiceImpl.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 * @spring.bean id="crityValLovServiceTarget"
 * @spring.txbn id="crityValLovService"
 * @spring.property name="crityValLovDAO" ref="crityValLovDAO"
 */
public class CrityValLovServiceImpl implements CrityValLovService
{
    private CrityValLovDAO crityValLovDAO=null;

    public CrityValLovDAO getCrityValLovDAO()
    {
        return crityValLovDAO;
    }

    public void setCrityValLovDAO(CrityValLovDAO crityValLovDAO)
    {
        this.crityValLovDAO = crityValLovDAO;
    }
    
    public List findList(CrityValLovDTO crityValLovDTO,User loginUser,CrityValLovForm crityValLovForm)
    {
        
        String jsonParam    = crityValLovForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
     
        return crityValLovDAO.findList(crityValLovDTO, loginUser,conditionMap);
    }
    
    
    
}

