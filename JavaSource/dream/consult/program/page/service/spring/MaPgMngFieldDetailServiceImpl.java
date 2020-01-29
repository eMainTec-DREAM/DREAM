package dream.consult.program.page.service.spring;

import dream.consult.program.page.dao.MaPgMngFieldDetailDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.service.MaPgMngFieldDetailService;

/**
 * 화면별 필드 상세
 * @author kim2107
 * @version $Id: MaPgMngFieldDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngFieldDetailServiceTarget"
 * @spring.txbn id="maPgMngFieldDetailService"
 * @spring.property name="maPgMngFieldDetailDAO" ref="maPgMngFieldDetailDAO"
 */
public class MaPgMngFieldDetailServiceImpl implements MaPgMngFieldDetailService
{
    private MaPgMngFieldDetailDAO maPgMngFieldDetailDAO = null;
    
    public MaPgMngFieldDetailDAO getMaPgMngFieldDetailDAO() {
		return maPgMngFieldDetailDAO;
	}

	public void setMaPgMngFieldDetailDAO(MaPgMngFieldDetailDAO maPgMngFieldDetailDAO) {
		this.maPgMngFieldDetailDAO = maPgMngFieldDetailDAO;
	}

	public MaPgMngFieldDetailDTO findDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO)
    {
        return maPgMngFieldDetailDAO.findDetail(maPgMngFieldDetailDTO);
    }
    
	public int updateDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
		return maPgMngFieldDetailDAO.updateDetail(maPgMngFieldDetailDTO, maPgMngCommonDTO);
    }
	public int insertDetail(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO) throws Exception
    {        
        return maPgMngFieldDetailDAO.insertDetail( maPgMngFieldDetailDTO, maPgMngCommonDTO);
    }
}
