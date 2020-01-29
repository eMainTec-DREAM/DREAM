package mobile.dream.org.emp.service.spring;

import java.util.List;

import common.bean.User;
import mobile.dream.org.emp.dao.OrgEmpLovListDAO;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;
import mobile.dream.org.emp.service.OrgEmpLovListService;


/**
 * »ç¿øÆË¾÷ ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="orgEmpLovListServiceTarget"
 * @spring.txbn id="orgEmpLovListService"
 * @spring.property name="orgEmpLovListDAO" ref="orgEmpLovListDAO"
 */
public class OrgEmpLovListServiceImpl implements OrgEmpLovListService
{
    /** »ç¿øÆË¾÷ DAO */
    private OrgEmpLovListDAO orgEmpLovListDAO = null;

    public OrgEmpLovListDAO getOrgEmpLovListDAO() 
    {
		return orgEmpLovListDAO;
	}

	public void setOrgEmpLovListDAO(OrgEmpLovListDAO orgEmpLovListDAO) 
	{
		this.orgEmpLovListDAO = orgEmpLovListDAO;
	}

	public List findEmpList(OrgEmpLovListDTO orgEmpLovListDTO, User loginUser)
    {
        List resultList = null;
        resultList = orgEmpLovListDAO.findEmpList(orgEmpLovListDTO,loginUser);
        
        return resultList;
    }
}