package dream.scheduler.list.service.spring;

import dream.scheduler.list.dao.MaBatchMngDetailDAO;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.dto.MaBatchMngDetailDTO;
import dream.scheduler.list.service.MaBatchMngDetailService;

/**
 * 버튼 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaBatchMngDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBatchMngDetailServiceTarget"
 * @spring.txbn id="maBatchMngDetailService"
 * @spring.property name="maBatchMngDetailDAO" ref="maBatchMngDetailDAO"
 */
public class MaBatchMngDetailServiceImpl implements MaBatchMngDetailService
{
    private MaBatchMngDetailDAO maBatchMngDetailDAO = null;
    
    public MaBatchMngDetailDAO getMaBatchMngDetailDAO() {
		return maBatchMngDetailDAO;
	}

	public void setMaBatchMngDetailDAO(MaBatchMngDetailDAO maBatchMngDetailDAO) {
		this.maBatchMngDetailDAO = maBatchMngDetailDAO;
	}

	public MaBatchMngDetailDTO findDetail(MaBatchMngCommonDTO maBatchMngCommonDTO)throws Exception
    {
        return maBatchMngDetailDAO.findDetail(maBatchMngCommonDTO);
    }
    
	public int insertDetail(MaBatchMngDetailDTO maBatchMngDetailDTO) throws Exception
    {        
        return maBatchMngDetailDAO.insertDetail(maBatchMngDetailDTO);
    }
	
	public int updateDetail(MaBatchMngDetailDTO maBatchMngDetailDTO) throws Exception
    {        
        return maBatchMngDetailDAO.updateDetail(maBatchMngDetailDTO);
    }
}
