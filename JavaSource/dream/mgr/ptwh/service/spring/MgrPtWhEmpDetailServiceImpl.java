package dream.mgr.ptwh.service.spring;

import common.bean.User;
import dream.mgr.ptwh.dao.MgrPtWhEmpDetailDAO;
import dream.mgr.ptwh.dto.MgrPtWhEmpDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhEmpListDTO;
import dream.mgr.ptwh.service.MgrPtWhEmpDetailService;

/**
 * 부품창고 담당자 - Detail Service implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrPtWhEmpDetailServiceTarget"
 * @spring.txbn id="mgrPtWhEmpDetailService"
 * @spring.property name="mgrPtWhEmpDetailDAO" ref="mgrPtWhEmpDetailDAO"
 */
public class MgrPtWhEmpDetailServiceImpl implements MgrPtWhEmpDetailService
{
    private MgrPtWhEmpDetailDAO mgrPtWhEmpDetailDAO = null;
    
    public MgrPtWhEmpDetailDTO findPtWhEmpDetail(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, User user) throws Exception
    {
    	return mgrPtWhEmpDetailDAO.findPtWhEmpDetail(mgrPtWhEmpListDTO, user);
    }
    
    public int insertPtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception
    {
    	return mgrPtWhEmpDetailDAO.insertPtWhEmpDetail(mgrPtWhEmpDetailDTO, user);
    }
    
    public int updatePtWhEmpDetail(MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception
    {
    	 return mgrPtWhEmpDetailDAO.updatePtWhEmpDetail(mgrPtWhEmpDetailDTO, user);
    }

	public MgrPtWhEmpDetailDAO getMgrPtWhEmpDetailDAO() {
		return mgrPtWhEmpDetailDAO;
	}

	public void setMgrPtWhEmpDetailDAO(MgrPtWhEmpDetailDAO mgrPtWhEmpDetailDAO) {
		this.mgrPtWhEmpDetailDAO = mgrPtWhEmpDetailDAO;
	}

	public String validEmpNo(MgrPtWhEmpListDTO mgrPtWhEmpListDTO, MgrPtWhEmpDetailDTO mgrPtWhEmpDetailDTO, User user) throws Exception
	{
        return mgrPtWhEmpDetailDAO.validEmpNo(mgrPtWhEmpListDTO, mgrPtWhEmpDetailDTO, user);
	}
}
