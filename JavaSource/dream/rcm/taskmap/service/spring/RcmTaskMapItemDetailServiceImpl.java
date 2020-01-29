package dream.rcm.taskmap.service.spring;

import common.bean.User;
import dream.rcm.taskmap.dao.RcmTaskMapItemDetailDAO;
import dream.rcm.taskmap.dto.RcmTaskMapItemDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.service.RcmTaskMapItemDetailService;

/**
 * 답변 - 수신자
 * @author kim2107
 * @version $Id: RcmTaskMapItemDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmTaskMapItemDetailServiceTarget"
 * @spring.txbn id="rcmTaskMapItemDetailService"
 * @spring.property name="rcmTaskMapItemDetailDAO" ref="rcmTaskMapItemDetailDAO"
 */
public class RcmTaskMapItemDetailServiceImpl implements RcmTaskMapItemDetailService
{
    private RcmTaskMapItemDetailDAO rcmTaskMapItemDetailDAO = null;
    
    public RcmTaskMapItemDetailDAO getRcmTaskMapItemDetailDAO() {
		return rcmTaskMapItemDetailDAO;
	}

	public void setRcmTaskMapItemDetailDAO(RcmTaskMapItemDetailDAO rcmTaskMapItemDetailDAO) {
		this.rcmTaskMapItemDetailDAO = rcmTaskMapItemDetailDAO;
	}

	public RcmTaskMapItemDetailDTO findDetail(RcmTaskMapItemListDTO rcmTaskMapItemListDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user)throws Exception
    {
        return rcmTaskMapItemDetailDAO.findDetail(rcmTaskMapItemListDTO, rcmTaskMapCommonDTO,user);
    }
    
	public int updateDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO) throws Exception
    {        
        return rcmTaskMapItemDetailDAO.updateDetail(rcmTaskMapItemDetailDTO, rcmTaskMapCommonDTO);
    }
	public int insertDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO) throws Exception
    {        
        return rcmTaskMapItemDetailDAO.insertDetail( rcmTaskMapItemDetailDTO, rcmTaskMapCommonDTO);
    }

	@Override
	public String taskNo(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) throws Exception {
		return rcmTaskMapItemDetailDAO.taskNo(rcmTaskMapCommonDTO);
	}
}
