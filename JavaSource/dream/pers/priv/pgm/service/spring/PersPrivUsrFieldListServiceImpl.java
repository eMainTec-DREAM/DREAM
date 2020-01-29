package dream.pers.priv.pgm.service.spring;

import java.util.List;

import com.fins.org.json.JSONArray;
import com.fins.org.json.JSONObject;
import common.bean.User;
import common.util.CommonUtil;
import dream.main.service.MainService;
import dream.pers.priv.pgm.dao.PersPrivUsrFieldListDAO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldCommonDTO;
import dream.pers.priv.pgm.dto.PersPrivUsrFieldDetailDTO;
import dream.pers.priv.pgm.service.PersPrivUsrFieldDetailService;
import dream.pers.priv.pgm.service.PersPrivUsrFieldListService;

/**
 * 화면별 필드 목록    
 * @author kim21017
 * @version $Id: MaPgUsrFieldListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="persPrivUsrFieldListServiceTarget"
 * @spring.txbn id="persPrivUsrFieldListService"
 * @spring.property name="persPrivUsrFieldListDAO" ref="persPrivUsrFieldListDAO"
 */

public class PersPrivUsrFieldListServiceImpl implements PersPrivUsrFieldListService
{
    private PersPrivUsrFieldListDAO persPrivUsrFieldListDAO = null;
	
	
	public PersPrivUsrFieldListDAO getPersPrivUsrFieldListDAO() {
		return persPrivUsrFieldListDAO;
	}

	public void setPersPrivUsrFieldListDAO(
			PersPrivUsrFieldListDAO persPrivUsrFieldListDAO) {
		this.persPrivUsrFieldListDAO = persPrivUsrFieldListDAO;
	}

	public List findFieldList(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{      	    
	    return persPrivUsrFieldListDAO.findFieldList(persPrivUsrFieldCommonDTO, user);
	}
	
	public int deleteFieldList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + persPrivUsrFieldListDAO.deleteFieldList(id);
            }
        
        return result;
    }

    @Override
    public void createFieldList(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user)
    {
        String jsonData = persPrivUsrFieldCommonDTO.getFieldData();

        JSONArray ja = new JSONArray(jsonData);
        for (int i = 0; i < ja.length(); i++){
           JSONObject order = ja.getJSONObject(i);
           persPrivUsrFieldListDAO.createField(order, persPrivUsrFieldCommonDTO.getPageId(), user);
        }
    }

    @Override
    public int hideFieldList(String[] deleteRows, User user, boolean hide) throws Exception
    {
        int result = 0;

        PersPrivUsrFieldDetailService persPrivUsrFieldDetailService= (PersPrivUsrFieldDetailService)CommonUtil.getBean("persPrivUsrFieldDetailService",user);
        PersPrivUsrFieldDetailDTO persPrivUsrFieldDetailDTO = new PersPrivUsrFieldDetailDTO();
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                persPrivUsrFieldDetailDTO.setPgFieldId(id);
                PersPrivUsrFieldDetailDTO rsltDTO = persPrivUsrFieldDetailService.findDetail(persPrivUsrFieldDetailDTO, user);
                
                String displayYn = hide?"N":"Y";
                rsltDTO.setDisplayYn(displayYn);
                
                //숨김일때는 항목이 필수가 아닌것만, 보일때는 그냥
                if((hide && !"Y".equals(rsltDTO.getCheckYn())) || !hide)
                {
                    if("".equals(rsltDTO.getUsrPgFieldId())) 
                        persPrivUsrFieldDetailService.insertDetail(rsltDTO, user);
                    else
                        persPrivUsrFieldDetailService.updateDetail(rsltDTO, new PersPrivUsrFieldCommonDTO());
                }
            }
        
        return result;
    }

    @Override
    public String findTotalCount(PersPrivUsrFieldCommonDTO persPrivUsrFieldCommonDTO, User user)
    {
        return persPrivUsrFieldListDAO.findTotalCount(persPrivUsrFieldCommonDTO, user);
    }
}

