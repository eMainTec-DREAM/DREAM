package dream.req.qna.service.spring;

import dream.req.qna.dao.MaQnaAnsDetailDAO;
import dream.req.qna.dto.MaQnaAnsDetailDTO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.service.MaQnaAnsDetailService;

/**
 * 답변 - 수신자
 * @author kim2107
 * @version $Id: MaQnaAnsDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maQnaAnsDetailServiceTarget"
 * @spring.txbn id="maQnaAnsDetailService"
 * @spring.property name="maQnaAnsDetailDAO" ref="maQnaAnsDetailDAO"
 */
public class MaQnaAnsDetailServiceImpl implements MaQnaAnsDetailService
{
    private MaQnaAnsDetailDAO maQnaAnsDetailDAO = null;
    
    public MaQnaAnsDetailDAO getMaQnaAnsDetailDAO() {
		return maQnaAnsDetailDAO;
	}

	public void setMaQnaAnsDetailDAO(MaQnaAnsDetailDAO maQnaAnsDetailDAO) {
		this.maQnaAnsDetailDAO = maQnaAnsDetailDAO;
	}

	public MaQnaAnsDetailDTO findDetail(MaQnaAnsListDTO maQnaAnsListDTO, MaQnaCommonDTO maQnaCommonDTO)throws Exception
    {
        return maQnaAnsDetailDAO.findDetail(maQnaAnsListDTO, maQnaCommonDTO);
    }
    
	public int updateDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO) throws Exception
    {        
        return maQnaAnsDetailDAO.updateDetail(maQnaAnsDetailDTO, maQnaCommonDTO);
    }
	public int insertDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO) throws Exception
    {        
        return maQnaAnsDetailDAO.insertDetail( maQnaAnsDetailDTO, maQnaCommonDTO);
    }
}
