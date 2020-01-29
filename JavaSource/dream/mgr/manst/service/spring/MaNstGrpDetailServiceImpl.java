package dream.mgr.manst.service.spring;

import common.bean.User;
import dream.mgr.manst.dao.MaNstGrpDetailDAO;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;
import dream.mgr.manst.service.MaNstGrpDetailService;

/**
 * 무정지대표라인 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaNstGrpDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maNstGrpDetailServiceTarget"
 * @spring.txbn id="maNstGrpDetailService"
 * @spring.property name="maNstGrpDetailDAO" ref="maNstGrpDetailDAO"
 */
public class MaNstGrpDetailServiceImpl implements MaNstGrpDetailService
{
    private MaNstGrpDetailDAO maNstGrpDetailDAO = null;
    
    public MaNstGrpDetailDAO getMaNstGrpDetailDAO() {
		return maNstGrpDetailDAO;
	}

	public void setMaNstGrpDetailDAO(MaNstGrpDetailDAO maNstGrpDetailDAO) {
		this.maNstGrpDetailDAO = maNstGrpDetailDAO;
	}

	public MaNstGrpDetailDTO findDetail(MaNstGrpCommonDTO maNstGrpCommonDTO, User loginUser)throws Exception
    {
        return maNstGrpDetailDAO.findDetail(maNstGrpCommonDTO,loginUser);
    }
	
	public int insertDetail(MaNstGrpDetailDTO maNstGrpDetailDTO) throws Exception
    {        
        return maNstGrpDetailDAO.insertDetail(maNstGrpDetailDTO);
    }
	
	public int updateDetail(MaNstGrpDetailDTO maNstGrpDetailDTO) throws Exception
    {
	    maNstGrpDetailDAO.updateLineRate(maNstGrpDetailDTO);
	    
        return maNstGrpDetailDAO.updateDetail(maNstGrpDetailDTO);
    }
}
