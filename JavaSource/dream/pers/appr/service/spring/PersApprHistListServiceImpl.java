package dream.pers.appr.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.appr.dao.PersApprHistListDAO;
import dream.pers.appr.dto.PersApprHistCommonDTO;
import dream.pers.appr.service.PersApprHistListService;

/**
 * 결재이력 - List Service implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="persApprHistListServiceTarget"
 * @spring.txbn id="persApprHistListService"
 * @spring.property name="persApprHistListDAO" ref="persApprHistListDAO"
 */
public class PersApprHistListServiceImpl implements PersApprHistListService
{
	private PersApprHistListDAO persApprHistListDAO = null;

	public List findApprHistList(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception
    {      
        return persApprHistListDAO.findApprHistList(persApprHistCommonDTO,user);
    }

	public int deletePgmGuideList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + persApprHistListDAO.deletePgmGuideList(id, user);
            }
        return result;
    }

	public PersApprHistListDAO getPersApprHistListDAO() {
		return persApprHistListDAO;
	}

	public void setPersApprHistListDAO(PersApprHistListDAO persApprHistListDAO) {
		this.persApprHistListDAO = persApprHistListDAO;
	}
	public String findTotalCount(PersApprHistCommonDTO persApprHistCommonDTO,User user) throws Exception
    {
        return persApprHistListDAO.findTotalCount(persApprHistCommonDTO, user);
    }
}

