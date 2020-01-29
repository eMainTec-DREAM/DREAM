package dream.req.qna.service.spring;

import dream.req.qna.dao.MaQnaDetailDAO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.dto.MaQnaDetailDTO;
import dream.req.qna.service.MaQnaDetailService;

/**
 * 질의 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: MaQnaDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maQnaDetailServiceTarget"
 * @spring.txbn id="maQnaDetailService"
 * @spring.property name="maQnaDetailDAO" ref="maQnaDetailDAO"
 */
public class MaQnaDetailServiceImpl implements MaQnaDetailService
{
    private MaQnaDetailDAO maQnaDetailDAO = null;
    
    public MaQnaDetailDAO getMaQnaDetailDAO() {
		return maQnaDetailDAO;
	}

	public void setMaQnaDetailDAO(MaQnaDetailDAO maQnaDetailDAO) {
		this.maQnaDetailDAO = maQnaDetailDAO;
	}

	public MaQnaDetailDTO findDetail(MaQnaCommonDTO maQnaCommonDTO)throws Exception
    {
        return maQnaDetailDAO.findDetail(maQnaCommonDTO);
    }
	
	public int updateDetail(MaQnaDetailDTO maQnaDetailDTO) throws Exception
    {        
        return maQnaDetailDAO.updateDetail(maQnaDetailDTO);
    }
	public int confirmDetail(MaQnaDetailDTO maQnaDetailDTO) throws Exception
    {        
        return maQnaDetailDAO.confirmDetail(maQnaDetailDTO);
    }
	public int insertDetail(MaQnaDetailDTO maQnaDetailDTO) throws Exception
    {        
        return maQnaDetailDAO.insertDetail(maQnaDetailDTO);
    }
}
