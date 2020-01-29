package dream.mgr.cal.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dao.MgrCalCompWkrcalDaysetListDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDaysetListDTO;
import dream.mgr.cal.service.MgrCalCompWkrcalDaysetListService;

/**
 * 휴무일 설정  - 목록 serviceimpl
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDaysetListServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 *
 * @spring.bean id="mgrCalCompWkrcalDaysetListServiceTarget"
 * @spring.txbn id="mgrCalCompWkrcalDaysetListService"
 * @spring.property name="mgrCalCompWkrcalDaysetListDAO" ref="mgrCalCompWkrcalDaysetListDAO"
 */
public class MgrCalCompWkrcalDaysetListServiceImpl implements MgrCalCompWkrcalDaysetListService
{
    private MgrCalCompWkrcalDaysetListDAO mgrCalCompWkrcalDaysetListDAO = null;

    public MgrCalCompWkrcalDaysetListDAO getMgrCalCompWkrcalDaysetListDAO() {
		return mgrCalCompWkrcalDaysetListDAO;
	}

	public void setMgrCalCompWkrcalDaysetListDAO(MgrCalCompWkrcalDaysetListDAO mgrCalCompWkrcalDaysetListDAO) {
		this.mgrCalCompWkrcalDaysetListDAO = mgrCalCompWkrcalDaysetListDAO;
	}

	public List findDaysetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO)
    {
        return mgrCalCompWkrcalDaysetListDAO.findDaysetList(mgrCalCompWkrcalCommonDTO, mgrCalCompWkrcalDaysetListDTO);
    }

	public int deleteWrkcal(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrCalCompWkrcalDaysetListDAO.deleteWrkcal(id);
            }
        return result;
    }

    @Override
    public String findTotalCount(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO,  MgrCalCompWkrcalDaysetListDTO mgrCalCompWkrcalDaysetListDTO)
    {
        return mgrCalCompWkrcalDaysetListDAO.findTotalCount(mgrCalCompWkrcalCommonDTO, mgrCalCompWkrcalDaysetListDTO);
    }
}

