package dream.ass.base.service.spring;

import common.bean.User;
import dream.ass.base.dao.AssBasePointValScriptDAO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.service.AssBasePointValScriptService;



/**
 * 평가기준 - 상세 serviceimpl
 * @author youngjoo38
 * @version $Id: AssBasePointValDetailServiceImpl.java,v 1.0 2017/11/06 16:00:51 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBasePointValScriptServiceTarget"
 * @spring.txbn id="assBasePointValScriptService"
 * @spring.property name="assBasePointValScriptDAO" ref="assBasePointValScriptDAO"
 */
public class AssBasePointValScriptServiceImpl implements AssBasePointValScriptService
{
    private AssBasePointValScriptDAO assBasePointValScriptDAO = null;


    public AssBasePointValScriptDAO getAssBasePointValScriptDAO()
    {
        return assBasePointValScriptDAO;
    }

    public void setAssBasePointValScriptDAO(
            AssBasePointValScriptDAO assBasePointValScriptDAO)
    {
        this.assBasePointValScriptDAO = assBasePointValScriptDAO;
    }

    public int updateScript(AssBasePointValDetailDTO assBasePointValDetailDTO, User loginUser)
    {      
        return assBasePointValScriptDAO.updateScript(assBasePointValDetailDTO, loginUser);
    }

	public AssBasePointValDetailDTO findScript(AssBasePointValDetailDTO assBasePointValDetailDTO) {
		return assBasePointValScriptDAO.findScript(assBasePointValDetailDTO);
	}
}

