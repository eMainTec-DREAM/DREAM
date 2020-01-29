package dream.req.qna.service.spring;

import java.util.List;

import dream.req.qna.dao.MaQnaAnsListDAO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.service.MaQnaAnsListService;

/**
 * 답변 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaQnaAnsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maQnaAnsListServiceTarget"
 * @spring.txbn id="maQnaAnsListService"
 * @spring.property name="maQnaAnsListDAO" ref="maQnaAnsListDAO"
 */
public class MaQnaAnsListServiceImpl implements MaQnaAnsListService
{
    private MaQnaAnsListDAO maQnaAnsListDAO = null;

    public MaQnaAnsListDAO getMaQnaAnsListDAO() {
		return maQnaAnsListDAO;
	}
	public void setMaQnaAnsListDAO(MaQnaAnsListDAO maQnaAnsListDAO) {
		this.maQnaAnsListDAO = maQnaAnsListDAO;
	}
	
	public List findAnsList(MaQnaCommonDTO maQnaCommonDTO, MaQnaAnsListDTO maQnaAnsListDTO)
    {      
        return maQnaAnsListDAO.findAnsList(maQnaCommonDTO, maQnaAnsListDTO);
    }
	
	public int deleteAnsList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maQnaAnsListDAO.deleteAnsList(deleteRows[i], deleteRowsExt[i] );
        }
        
        return result;
    }
}

