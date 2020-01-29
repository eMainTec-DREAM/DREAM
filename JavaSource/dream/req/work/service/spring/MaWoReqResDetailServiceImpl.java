package dream.req.work.service.spring;

import common.bean.User;
import dream.req.work.dao.MaWoReqResDetailDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.service.MaWoReqResDetailService;

/**
 * 작업요청서 - 처리사항 상세 
 * @author kim2107
 * @version $Id: MaWoReqResDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoReqResDetailServiceTarget"
 * @spring.txbn id="maWoReqResDetailService"
 * @spring.property name="maWoReqResDetailDAO" ref="maWoReqResDetailDAO"
 */
public class MaWoReqResDetailServiceImpl implements MaWoReqResDetailService
{
    private MaWoReqResDetailDAO maWoReqResDetailDAO = null;
    
    public MaWoReqResDetailDAO getMaWoReqResDetailDAO() {
		return maWoReqResDetailDAO;
	}

	public void setMaWoReqResDetailDAO(MaWoReqResDetailDAO maWoReqResDetailDAO) {
		this.maWoReqResDetailDAO = maWoReqResDetailDAO;
	}

	public MaWoReqResDetailDTO findDetail(String woDayListId, String woDayActId, User user)throws Exception
    {
        return maWoReqResDetailDAO.findDetail(woDayListId, woDayActId, user);
    }
    
	public int updateDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO) throws Exception
    {        
	    return maWoReqResDetailDAO.updateDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
    }
	public int insertDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO) throws Exception
    {     
	    return maWoReqResDetailDAO.insertDetail( maWoReqResDetailDTO, maWoReqCommonDTO);
    }
}
