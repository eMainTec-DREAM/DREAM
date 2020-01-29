package dream.mgr.manst.service.spring;

import java.util.List;

import dream.mgr.manst.dao.MaNstGrpListDAO;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.service.MaNstGrpListService;

/**
 * 무정지대표라인 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaNstGrpListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maNstGrpListServiceTarget"
 * @spring.txbn id="maNstGrpListService"
 * @spring.property name="maNstGrpListDAO" ref="maNstGrpListDAO"
 */
public class MaNstGrpListServiceImpl implements MaNstGrpListService
{
    private MaNstGrpListDAO maNstGrpListDAO = null;

    public MaNstGrpListDAO getMaNstGrpListDAO() {
		return maNstGrpListDAO;
	}

	public void setMaNstGrpListDAO(MaNstGrpListDAO maNstGrpListDAO) {
		this.maNstGrpListDAO = maNstGrpListDAO;
	}

	public List findNstGrpList(MaNstGrpCommonDTO maNstGrpCommonDTO)
    {      
        List list =  maNstGrpListDAO.findNstGrpList(maNstGrpCommonDTO);
        return list;
    }
	
	public int deleteNstGrp(String[] deleteRows, MaNstGrpCommonDTO maNstGrpCommonDTO) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maNstGrpListDAO.deleteNstGrp(id,maNstGrpCommonDTO);
            }
        return result;
    }
}

