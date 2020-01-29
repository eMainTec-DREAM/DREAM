package dream.work.pm.list.service.spring;

import dream.work.pm.list.dao.MaPmEqClnDetailDAO;
import dream.work.pm.list.dto.MaPmEqClnDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.service.MaPmEqClnDetailService;

/**
 * 예방설비
 * @author kim21017
 * @version $Id: MaPmEqClnDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmEqClnDetailServiceTarget"
 * @spring.txbn id="maPmEqClnDetailService"
 * @spring.property name="maPmEqClnDetailDAO" ref="maPmEqClnDetailDAO"
 */
public class MaPmEqClnDetailServiceImpl implements MaPmEqClnDetailService
{
    private MaPmEqClnDetailDAO maPmEqClnDetailDAO = null;
    
    public MaPmEqClnDetailDAO getMaPmEqClnDetailDAO() {
		return maPmEqClnDetailDAO;
	}

	public void setMaPmEqClnDetailDAO(MaPmEqClnDetailDAO maPmEqClnDetailDAO) {
		this.maPmEqClnDetailDAO = maPmEqClnDetailDAO;
	}

	public MaPmEqClnDetailDTO findDetail(String pmId, String pmEquipId, String compNo)throws Exception
    {
        return maPmEqClnDetailDAO.findDetail(pmId, pmEquipId, compNo);
    }
    
	public int updateDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return maPmEqClnDetailDAO.updateDetail(maPmEqClnDetailDTO, maPmMstrCommonDTO);
    }
	public int insertDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception
    {        
        return maPmEqClnDetailDAO.insertDetail( maPmEqClnDetailDTO, maPmMstrCommonDTO);
    }
}
