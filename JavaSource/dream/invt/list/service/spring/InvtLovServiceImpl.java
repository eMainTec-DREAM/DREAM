package dream.invt.list.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.invt.list.dao.InvtLovDAO;
import dream.invt.list.dto.InvtLovDTO;
import dream.invt.list.form.InvtLovForm;
import dream.invt.list.service.InvtLovService;

/**
 * 설비투자 ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="invtLovServiceTarget"
 * @spring.txbn id="invtLovService"
 * @spring.property name="invtLovDAO" ref="invtLovDAO"
 */
public class InvtLovServiceImpl implements InvtLovService
{
    /** Task Map 팝업 DAO */
    private InvtLovDAO invtLovDAO = null;

	public InvtLovDAO getInvtLovDAO()
    {
        return invtLovDAO;
    }
	
    public void setInvtLovDAO(InvtLovDAO invtLovDAO)
    {
        this.invtLovDAO = invtLovDAO;
    }


    @Override
    public List findTaskMapAcList(InvtLovDTO invtLovDTO,
            User loginUser, InvtLovForm invtLovForm)
    {

        String jsonParam    = invtLovForm.getParam();  //Condition



        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);


        return invtLovDAO.findTaskMapAcList(invtLovDTO, loginUser, conditionMap);

    }

	@Override
	public String findTotalCount(InvtLovDTO invtLovDTO, User loginUser, InvtLovForm invtLovForm)
	{
        String jsonParam    = invtLovForm.getParam();  //Condition

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);
        
		return invtLovDAO.findTotalCount(invtLovDTO, loginUser, conditionMap);
	}
}