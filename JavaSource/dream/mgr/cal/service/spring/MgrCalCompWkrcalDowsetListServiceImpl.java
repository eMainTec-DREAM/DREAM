package dream.mgr.cal.service.spring;

import java.util.List;

import dream.mgr.cal.dao.MgrCalCompWkrcalDowsetListDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;
import dream.mgr.cal.service.MgrCalCompWkrcalDowsetListService;

/**
 * 휴무요일 설정  - 목록 serviceimpl
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 *
 * @spring.bean id="mgrCalCompWkrcalDowsetListServiceTarget"
 * @spring.txbn id="mgrCalCompWkrcalDowsetListService"
 * @spring.property name="mgrCalCompWkrcalDowsetListDAO" ref="mgrCalCompWkrcalDowsetListDAO"
 */
public class MgrCalCompWkrcalDowsetListServiceImpl implements MgrCalCompWkrcalDowsetListService
{
    private MgrCalCompWkrcalDowsetListDAO mgrCalCompWkrcalDowsetListDAO = null;

    public MgrCalCompWkrcalDowsetListDAO getMgrCalCompWkrcalDowsetListDAO() {
		return mgrCalCompWkrcalDowsetListDAO;
	}

	public void setMgrCalCompWkrcalDowsetListDAO(MgrCalCompWkrcalDowsetListDAO mgrCalCompWkrcalDowsetListDAO) {
		this.mgrCalCompWkrcalDowsetListDAO = mgrCalCompWkrcalDowsetListDAO;
	}

	public List findDowsetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO)
    {
        return mgrCalCompWkrcalDowsetListDAO.findDowsetList(mgrCalCompWkrcalCommonDTO, mgrCalCompWkrcalDowsetListDTO);
    }

	public int deleteWrkcal(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrCalCompWkrcalDowsetListDAO.deleteWrkcal(id);
            }
        return result;
    }
}

