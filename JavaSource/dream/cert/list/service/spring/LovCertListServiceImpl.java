package dream.cert.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.cert.list.dao.LovCertListDAO;
import dream.cert.list.dto.LovCertListDTO;
import dream.cert.list.form.LovCertListForm;
import dream.cert.list.service.LovCertListService;

/**
 * 자격증LOV ServiceImpl
 * @author  hyosung
 * @version $Id: LovCertListServiceImpl.java,v 1.5 2015/01/09 00:16:42 hyosung Exp $
 * @since   1.0
 *
 * @spring.bean id="lovCertListServiceTarget"
 * @spring.txbn id="lovCertListService"
 * @spring.property name="lovCertListDAO" ref="lovCertListDAO"
 */
public class LovCertListServiceImpl implements LovCertListService
{
    /** 자산팝업 DAO */
    private LovCertListDAO lovCertListDAO = null;

    public LovCertListDAO getLovCertListDAO() {
		return lovCertListDAO;
	}

	public void setLovCertListDAO(LovCertListDAO lovCertListDAO) {
		this.lovCertListDAO = lovCertListDAO;
	}

	public List findCertList(LovCertListDTO lovCertListDTO, User loginUser, LovCertListForm  lovCertListForm)
    {
     
	    String jsonParam    = lovCertListForm.getParam();  //Condition
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
	    
        return lovCertListDAO.findCertList(lovCertListDTO, loginUser, conditionMap);
    }
}