package dream.mgr.cal.service.spring;

import common.bean.User;
import dream.mgr.cal.dao.MgrCalCompWkrcalDowsetDetailDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;
import dream.mgr.cal.service.MgrCalCompWkrcalDowsetDetailService;

/**
 * 휴무요일설정 - 상세 serviceimpl
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalCompWkrcalDowsetDetailServiceTarget"
 * @spring.txbn id="mgrCalCompWkrcalDowsetDetailService"
 * @spring.property name="mgrCalCompWkrcalDowsetDetailDAO" ref="mgrCalCompWkrcalDowsetDetailDAO"
 */
public class MgrCalCompWkrcalDowsetDetailServiceImpl implements MgrCalCompWkrcalDowsetDetailService
{
    private MgrCalCompWkrcalDowsetDetailDAO mgrCalCompWkrcalDowsetDetailDAO = null;

    public MgrCalCompWkrcalDowsetDetailDAO getMgrCalCompWkrcalDowsetDetailDAO() {
		return mgrCalCompWkrcalDowsetDetailDAO;
	}

	public void setMgrCalCompWkrcalDowsetDetailDAO(MgrCalCompWkrcalDowsetDetailDAO mgrCalCompWkrcalDowsetDetailDAO) {
		this.mgrCalCompWkrcalDowsetDetailDAO = mgrCalCompWkrcalDowsetDetailDAO;
	}

	public MgrCalCompWkrcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang)throws Exception
    {
        return mgrCalCompWkrcalDowsetDetailDAO.findDetail(wrkcalDowsetId, lang);
    }

	public int insertDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user) throws Exception
    {
        return mgrCalCompWkrcalDowsetDetailDAO.insertDetail(mgrCalCompWkrcalDowsetDetailDTO, mgrCalCompWkrcalCommonDTO, user);
    }

	public int updateDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO) throws Exception
    {
        return mgrCalCompWkrcalDowsetDetailDAO.updateDetail(mgrCalCompWkrcalDowsetDetailDTO);
    }
}
