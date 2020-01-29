package dream.comm.revision.service.spring;

import common.bean.User;
import dream.comm.revision.dao.CommRevHistDetailDAO;
import dream.comm.revision.dto.CommRevHistCommonDTO;
import dream.comm.revision.dto.CommRevHistDetailDTO;
import dream.comm.revision.service.CommRevHistDetailService;
import dream.work.pm.list.dao.MaPmMstrListDAO;

/**
 * »ó¼¼ serviceimpl 
 * @author  jung7126
 * @version $Id: CommRevHistDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="commRevHistDetailServiceTarget"
 * @spring.txbn id="commRevHistDetailService"
 * @spring.property name="commRevHistDetailDAO" ref="commRevHistDetailDAO"
 * @spring.property name="maPmMstrListDAO" ref="maPmMstrListDAO"
 */
public class CommRevHistDetailServiceImpl implements CommRevHistDetailService
{
    private CommRevHistDetailDAO commRevHistDetailDAO = null;
    
    private MaPmMstrListDAO maPmMstrListDAO = null;
    
    
    public MaPmMstrListDAO getMaPmMstrListDAO() {
		return maPmMstrListDAO;
	}

	public void setMaPmMstrListDAO(MaPmMstrListDAO maPmMstrListDAO) {
		this.maPmMstrListDAO = maPmMstrListDAO;
	}

	public CommRevHistDetailDAO getCommRevHistDetailDAO() {
		return commRevHistDetailDAO;
	}

	public void setCommRevHistDetailDAO(CommRevHistDetailDAO commRevHistDetailDAO) {
		this.commRevHistDetailDAO = commRevHistDetailDAO;
	}

	public CommRevHistDetailDTO findDetail(CommRevHistCommonDTO commRevHistCommonDTO, User user) throws Exception
	{
		CommRevHistDetailDTO commRevHistDetailDTO = new CommRevHistDetailDTO();
		
		String revisionhistId = commRevHistCommonDTO.getRevisionhistId();
		
		if("PMSTD".equals(commRevHistCommonDTO.getRevisionObjType()))
		{
			commRevHistDetailDTO = commRevHistDetailDAO.findPmDetail(revisionhistId, user);
		} 
		else if("ASSET".equals(commRevHistCommonDTO.getRevisionObjType()))
		{
			commRevHistDetailDTO = commRevHistDetailDAO.findAssetDetail(revisionhistId, user);
		}
		
		return commRevHistDetailDTO;
	}
	
	public int updateRevHist(CommRevHistDetailDTO commRevHistDetailDTO) throws Exception
    {   
		return commRevHistDetailDAO.updateRevHist(commRevHistDetailDTO);
    }
}
