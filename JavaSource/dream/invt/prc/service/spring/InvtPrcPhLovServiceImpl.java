package dream.invt.prc.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import dream.invt.prc.dao.InvtPrcPhLovDAO;
import dream.invt.prc.dto.InvtPrcPhLovDTO;
import dream.invt.prc.form.InvtPrcPhLovForm;
import dream.invt.prc.service.InvtPrcPhLovService;

/**
 * 구매절차 소분류 ServiceImpl
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="invtPrcPhLovServiceTarget"
 * @spring.txbn id="invtPrcPhLovService"
 * @spring.property name="invtPrcPhLovDAO" ref="invtPrcPhLovDAO"
 */
public class InvtPrcPhLovServiceImpl implements InvtPrcPhLovService
{
    /** Task Map 팝업 DAO */
    private InvtPrcPhLovDAO invtPrcPhLovDAO = null;

	public InvtPrcPhLovDAO getInvtPrcPhLovDAO()
    {
        return invtPrcPhLovDAO;
    }
	
    public void setInvtPrcPhLovDAO(InvtPrcPhLovDAO invtPrcPhLovDAO)
    {
        this.invtPrcPhLovDAO = invtPrcPhLovDAO;
    }


    @Override
    public List findTaskMapAcList(InvtPrcPhLovDTO invtPrcPhLovDTO, User loginUser, InvtPrcPhLovForm invtPrcPhLovForm)
    {

        String jsonParam    = invtPrcPhLovForm.getParam();  //Condition



        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> conditionMap = gson.fromJson(jsonParam, type);


        return invtPrcPhLovDAO.findTaskMapAcList(invtPrcPhLovDTO, loginUser, conditionMap);

    }
}