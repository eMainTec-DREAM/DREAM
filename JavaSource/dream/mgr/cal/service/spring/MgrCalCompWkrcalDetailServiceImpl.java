package dream.mgr.cal.service.spring;

import common.bean.User;
import dream.mgr.cal.dao.MgrCalCompWkrcalDetailDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDetailDTO;
import dream.mgr.cal.service.MgrCalCompWkrcalDetailService;

/**
 * 근무일달력 - 상세 serviceimpl
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalCompWkrcalDetailServiceTarget"
 * @spring.txbn id="mgrCalCompWkrcalDetailService"
 * @spring.property name="mgrCalCompWkrcalDetailDAO" ref="mgrCalCompWkrcalDetailDAO"
 */
public class MgrCalCompWkrcalDetailServiceImpl implements MgrCalCompWkrcalDetailService
{
    private MgrCalCompWkrcalDetailDAO mgrCalCompWkrcalDetailDAO = null;

    public MgrCalCompWkrcalDetailDAO getMgrCalCompWkrcalDetailDAO() {
		return mgrCalCompWkrcalDetailDAO;
	}

	public void setMgrCalCompWkrcalDetailDAO(MgrCalCompWkrcalDetailDAO mgrCalCompWkrcalDetailDAO) {
		this.mgrCalCompWkrcalDetailDAO = mgrCalCompWkrcalDetailDAO;
	}

	public MgrCalCompWkrcalDetailDTO findDetail(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)throws Exception
    {
        return mgrCalCompWkrcalDetailDAO.findDetail(mgrCalCompWkrcalCommonDTO, user);
    }

	public int insertDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user) throws Exception
    {
        int result = mgrCalCompWkrcalDetailDAO.insertDetail(mgrCalCompWkrcalDetailDTO, user);

        mgrCalCompWkrcalDetailDAO.SP_SETDEFAULT_WRKCAL_BYONE(mgrCalCompWkrcalDetailDTO, user);
        
        return result;
    }

	public int updateDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user) throws Exception
    {
        return mgrCalCompWkrcalDetailDAO.updateDetail(mgrCalCompWkrcalDetailDTO, user);
    }
}
