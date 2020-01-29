package dream.mgr.cal.service.spring;

import common.bean.User;
import dream.mgr.cal.dao.MgrCalCompWkrcalDaysetDetailDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetDetailDTO;
import dream.mgr.cal.service.MgrCalCompWkrcalDaysetDetailService;

/**
 * 휴무일 설정 - 상세 serviceimpl
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDaysetDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalCompWkrcalDaysetDetailServiceTarget"
 * @spring.txbn id="mgrCalCompWkrcalDaysetDetailService"
 * @spring.property name="mgrCalCompWkrcalDaysetDetailDAO" ref="mgrCalCompWkrcalDaysetDetailDAO"
 */
public class MgrCalCompWkrcalDaysetDetailServiceImpl implements MgrCalCompWkrcalDaysetDetailService
{
    private MgrCalCompWkrcalDaysetDetailDAO mgrCalCompWkrcalDaysetDetailDAO = null;

    public MgrCalCompWkrcalDaysetDetailDAO getMgrCalCompWkrcalDaysetDetailDAO() {
		return mgrCalCompWkrcalDaysetDetailDAO;
	}

	public void setMgrCalCompWkrcalDaysetDetailDAO(MgrCalCompWkrcalDaysetDetailDAO mgrCalCompWkrcalDaysetDetailDAO) {
		this.mgrCalCompWkrcalDaysetDetailDAO = mgrCalCompWkrcalDaysetDetailDAO;
	}

	public MgrCalCompWkrcalDaysetDetailDTO findDetail(String wrkcalDaysetId)throws Exception
    {
        return mgrCalCompWkrcalDaysetDetailDAO.findDetail(wrkcalDaysetId);
    }

	public int insertDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user) throws Exception
    {
        return mgrCalCompWkrcalDaysetDetailDAO.insertDetail(mgrCalCompWkrcalDaysetDetailDTO, mgrCalCompWkrcalCommonDTO, user);
    }

	public int updateDetail(MgrCalCompWkrcalDaysetDetailDTO mgrCalCompWkrcalDaysetDetailDTO) throws Exception
    {
        return mgrCalCompWkrcalDaysetDetailDAO.updateDetail(mgrCalCompWkrcalDaysetDetailDTO);
    }
}
