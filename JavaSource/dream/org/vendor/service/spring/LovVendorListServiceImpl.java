package dream.org.vendor.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.org.vendor.dao.LovVendorListDAO;
import dream.org.vendor.dto.LovVendorListDTO;
import dream.org.vendor.form.LovVendorListForm;
import dream.org.vendor.service.LovVendorListService;
import dream.part.list.form.LovPartsListForm;

/**
 * °Å·¡Ã³ÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovVendorListServiceTarget"
 * @spring.txbn id="lovVendorListService"
 * @spring.property name="lovVendorListDAO" ref="lovVendorListDAO"
 */
public class LovVendorListServiceImpl implements LovVendorListService
{
    /** °Å·¡Ã³ÆË¾÷ DAO */
    private LovVendorListDAO lovVendorListDAO = null;

    public LovVendorListDAO getLovVendorListDAO() 
    {
		return lovVendorListDAO;
	}

	public void setLovVendorListDAO(LovVendorListDAO lovVendorListDAO) 
	{
		this.lovVendorListDAO = lovVendorListDAO;
	}

	public List findVendorList(LovVendorListDTO lovVendorListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovVendorListDAO.findVendorList(lovVendorListDTO,loginUser);
        
        return resultList;
    }

	@Override
	public List findVendorAcList(LovVendorListDTO lovVendorListDTO, User user, LovVendorListForm lovVendorListForm) {
		String jsonParam    = lovVendorListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);

		return lovVendorListDAO.findVendorAcList(lovVendorListDTO, user ,conditionMap);
	}
	public String findTotalCount(LovVendorListForm lovVendorListForm, User user){
		LovVendorListDTO lovVendorListDTO = lovVendorListForm.getLovVendorListDTO();
		String jsonParam    = lovVendorListForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return lovVendorListDAO.findTotalCount(lovVendorListDTO, user, conditionMap);
    }
}