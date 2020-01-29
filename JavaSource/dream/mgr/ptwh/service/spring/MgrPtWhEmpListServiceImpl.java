package dream.mgr.ptwh.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dao.MgrPtWhEmpListDAO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
import dream.mgr.ptwh.service.MgrPtWhEmpListService;

/**
 * 부품창고 담당자 - List Service implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrPtWhEmpListServiceTarget"
 * @spring.txbn id="mgrPtWhEmpListService"
 * @spring.property name="mgrPtWhEmpListDAO" ref="mgrPtWhEmpListDAO"
 */
public class MgrPtWhEmpListServiceImpl implements MgrPtWhEmpListService
{
	private MgrPtWhEmpListDAO mgrPtWhEmpListDAO = null;

	public List findPtWhEmpList(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception
    {      
        return mgrPtWhEmpListDAO.findPtWhEmpList(mgrPtWhEmpListDTO,user);
    }

	public int deletePtWhEmpList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrPtWhEmpListDAO.deletePtWhEmpList(id, user);
            }
        return result;
    }

	public MgrPtWhEmpListDAO getMgrPtWhEmpListDAO() {
		return mgrPtWhEmpListDAO;
	}

	public void setMgrPtWhEmpListDAO(MgrPtWhEmpListDAO mgrPtWhEmpListDAO) {
		this.mgrPtWhEmpListDAO = mgrPtWhEmpListDAO;
	}
	public String findTotalCount(MgrPtWhEmpListDTO mgrPtWhEmpListDTO,User user) throws Exception
    {
        return mgrPtWhEmpListDAO.findTotalCount(mgrPtWhEmpListDTO, user);
    }
}

