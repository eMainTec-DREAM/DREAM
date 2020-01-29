package dream.mgr.cal.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.cal.dao.MgrCalCompWkrcalListDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.service.MgrCalCompWkrcalListService;

/**
 * 근무일달력 - 목록 serviceimpl
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalListServiceImpl.java,v 1.0 2015/12/02 09:12:51 euna0207 Exp $
 * @since 1.0
 *
 * @spring.bean id="mgrCalCompWkrcalListServiceTarget"
 * @spring.txbn id="mgrCalCompWkrcalListService"
 * @spring.property name="mgrCalCompWkrcalListDAO" ref="mgrCalCompWkrcalListDAO"
 */
public class MgrCalCompWkrcalListServiceImpl implements MgrCalCompWkrcalListService
{
    private MgrCalCompWkrcalListDAO mgrCalCompWkrcalListDAO = null;

    public MgrCalCompWkrcalListDAO getMgrCalCompWkrcalListDAO() {
		return mgrCalCompWkrcalListDAO;
	}

	public void setMgrCalCompWkrcalListDAO(MgrCalCompWkrcalListDAO mgrCalCompWkrcalListDAO) {
		this.mgrCalCompWkrcalListDAO = mgrCalCompWkrcalListDAO;
	}

	public List findWrkcalList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
        return mgrCalCompWkrcalListDAO.findWrkcalList(mgrCalCompWkrcalCommonDTO,user);
    }

	public int deleteWrkcal(String[] deleteRows, String[] deleteRowsExt, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
        	for(int i = 0; i< deleteRows.length; i++){
        		
        		result = result + mgrCalCompWkrcalListDAO.deleteWrkcal(deleteRows[i], user);
        	}
        return result;
    }

    @Override
    public String findTotalCount(
            MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
        // TODO Auto-generated method stub
        return mgrCalCompWkrcalListDAO.findTotalCount(mgrCalCompWkrcalCommonDTO,user);
    }
}

