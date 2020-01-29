package dream.req.qna.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.qna.dao.MaQnaListDAO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.service.MaQnaListService;

/**
 * 질의 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaQnaListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maQnaListServiceTarget"
 * @spring.txbn id="maQnaListService"
 * @spring.property name="maQnaListDAO" ref="maQnaListDAO"
 */
public class MaQnaListServiceImpl implements MaQnaListService
{
    private MaQnaListDAO maQnaListDAO = null;

    public MaQnaListDAO getMaQnaListDAO() {
		return maQnaListDAO;
	}

	public void setMaQnaListDAO(MaQnaListDAO maQnaListDAO) {
		this.maQnaListDAO = maQnaListDAO;
	}

	public List findQnaList(MaQnaCommonDTO maQnaCommonDTO)
    {      
        return maQnaListDAO.findQnaList(maQnaCommonDTO);
    }
	
	public int deleteQna(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maQnaListDAO.deleteQna(id,user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaQnaCommonDTO maQnaCommonDTO, User user)
	{
		return maQnaListDAO.findTotalCount(maQnaCommonDTO, user);
	}
}

