package dream.work.pm.list.service.spring;

import dream.work.pm.list.dao.MaPmMstrPartDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;
import dream.work.pm.list.service.MaPmMstrPartDetailService;

/**
 * 사용자재
 * @author jung7126
 * @version $Id: MaPmMstrPartDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmMstrPartDetailServiceTarget"
 * @spring.txbn id="maPmMstrPartDetailService"
 * @spring.property name="maPmMstrPartDetailDAO" ref="maPmMstrPartDetailDAO"
 */
public class MaPmMstrPartDetailServiceImpl implements MaPmMstrPartDetailService
{
    private MaPmMstrPartDetailDAO maPmMstrPartDetailDAO = null;
    
    public MaPmMstrPartDetailDAO getMaPmMstrPartDetailDAO() {
		return maPmMstrPartDetailDAO;
	}

	public void setMaPmMstrPartDetailDAO(MaPmMstrPartDetailDAO maPmMstrPartDetailDAO) {
		this.maPmMstrPartDetailDAO = maPmMstrPartDetailDAO;
	}

	public MaPmMstrPartDetailDTO findDetail(String pmId, String pmPartId, String compNo)throws Exception
    {
        return maPmMstrPartDetailDAO.findDetail(pmId, pmPartId, compNo);
    }
    
	public int updateDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO) throws Exception
    {        
        return maPmMstrPartDetailDAO.updateDetail(maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO);
    }
	public int insertDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO) throws Exception
    {        
        return maPmMstrPartDetailDAO.insertDetail( maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO);
    }
}
