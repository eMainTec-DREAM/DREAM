package dream.mgr.ptwh.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dao.MgrPtWhListDAO;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.service.MgrPtWhListService;

/**
 * 부품창고 - List Service implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="mgrPtWhListServiceTarget"
 * @spring.txbn id="mgrPtWhListService"
 * @spring.property name="mgrPtWhListDAO" ref="mgrPtWhListDAO"
 */
public class MgrPtWhListServiceImpl implements MgrPtWhListService
{
	private MgrPtWhListDAO mgrPtWhListDAO = null;

	public List findPtWhList(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) throws Exception
    {      
        return mgrPtWhListDAO.findPtWhList(mgrPtWhCommonDTO,user);
    }

	public MgrPtWhListDAO getMgrPtWhListDAO() {
		return mgrPtWhListDAO;
	}

	public void setMgrPtWhListDAO(MgrPtWhListDAO mgrPtWhListDAO) {
		this.mgrPtWhListDAO = mgrPtWhListDAO;
	}
	public String findTotalCount(MgrPtWhCommonDTO mgrPtWhCommonDTO,User user) throws Exception
    {
        return mgrPtWhListDAO.findTotalCount(mgrPtWhCommonDTO, user);
    }
}

