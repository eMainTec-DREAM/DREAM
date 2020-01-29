package dream.mgr.ptwh.service.spring;

import common.bean.User;
import dream.mgr.ptwh.dao.MgrPtWhBinDetailDAO;
import dream.mgr.ptwh.dto.MgrPtWhBinDetailDTO;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
import dream.mgr.ptwh.service.MgrPtWhBinDetailService;

/**
 * 부품창고 보관위치 - Detail Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrPtWhBinDetailServiceTarget"
 * @spring.txbn id="mgrPtWhBinDetailService"
 * @spring.property name="mgrPtWhBinDetailDAO" ref="mgrPtWhBinDetailDAO"
 */
public class MgrPtWhBinDetailServiceImpl implements MgrPtWhBinDetailService
{
    private MgrPtWhBinDetailDAO mgrPtWhBinDetailDAO = null;
    
    public MgrPtWhBinDetailDTO findPtWhEmpDetail(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception
    {
    	return mgrPtWhBinDetailDAO.findPtWhEmpDetail(mgrPtWhBinListDTO, user);
    }
    
    public int insertPtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception
    {
    	return mgrPtWhBinDetailDAO.insertPtWhEmpDetail(mgrPtWhBinDetailDTO, user);
    }
    
    public int updatePtWhEmpDetail(MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception
    {
    	 return mgrPtWhBinDetailDAO.updatePtWhEmpDetail(mgrPtWhBinDetailDTO, user);
    }

	public MgrPtWhBinDetailDAO getMgrPtWhBinDetailDAO() {
		return mgrPtWhBinDetailDAO;
	}

	public void setMgrPtWhBinDetailDAO(MgrPtWhBinDetailDAO mgrPtWhBinDetailDAO) {
		this.mgrPtWhBinDetailDAO = mgrPtWhBinDetailDAO;
	}

	public String validEmpNo(MgrPtWhBinListDTO mgrPtWhBinListDTO, MgrPtWhBinDetailDTO mgrPtWhBinDetailDTO, User user) throws Exception
	{
        return mgrPtWhBinDetailDAO.validEmpNo(mgrPtWhBinListDTO, mgrPtWhBinDetailDTO, user);
	}
}
