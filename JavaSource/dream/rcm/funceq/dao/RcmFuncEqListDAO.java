package dream.rcm.funceq.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * ���� dao
 * @author  kim21017
 * @version $Id: RcmFuncEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFuncEqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFuncEqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @return List
     */
    public List findQnaList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFuncEqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user);

	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, User user);
}