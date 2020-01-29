package dream.part.pur.buy.service.spring;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.org.emp.dao.LovEmpListDAO;
import dream.org.emp.dto.LovEmpListDTO;
import dream.org.emp.form.LovEmpListForm;
import dream.org.emp.service.LovEmpListService;
import dream.part.pur.buy.dao.LovPtprAcListDAO;
import dream.part.pur.buy.dto.LovPtprAcListDTO;
import dream.part.pur.buy.form.LovPtprAcListForm;
import dream.part.pur.buy.service.LovPtprAcListService;

/**
 * »ç¿øÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPtprAcListServiceTarget"
 * @spring.txbn id="lovPtprAcListService"
 * @spring.property name="lovPtprAcListDAO" ref="lovPtprAcListDAO"
 */
public class LovPtprAcListServiceImpl implements LovPtprAcListService
{
    /** »ç¿øÆË¾÷ DAO */
    private LovPtprAcListDAO lovPtprAcListDAO = null;

	public LovPtprAcListDAO getLovPtprAcListDAO()
    {
        return lovPtprAcListDAO;
    }

    public void setLovPtprAcListDAO(LovPtprAcListDAO lovPtprAcListDAO)
    {
        this.lovPtprAcListDAO = lovPtprAcListDAO;
    }


    @Override
    public List findTaskMapAcList(LovPtprAcListDTO lovPtprAcListDTO, User user,
            LovPtprAcListForm lovPtprAcListForm)
    {
        String jsonParam    = lovPtprAcListForm.getParam();  //Condition
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

        return lovPtprAcListDAO.findTaskMapAcList(lovPtprAcListDTO, user ,conditionMap);
    }
    
    @Override
    public String findTotalCount(LovPtprAcListDTO lovPtprAcListDTO, User user,
            LovPtprAcListForm lovPtprAcListForm)
    {
        String jsonParam    = lovPtprAcListForm.getParam();  //Condition
        
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
        return lovPtprAcListDAO.findTotalCount(lovPtprAcListDTO, user, conditionMap);
    }
    
}