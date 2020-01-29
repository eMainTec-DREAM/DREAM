package dream.consult.comp.eqmgr.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.eqmgr.dto.MaEqMngCommonDTO;

/**
 * 설비담당자변경 - 목록 dao
 * @author  jung7126
 * @version $Id: MaEqMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMngCommonDTO
     * @return List
     */
    public List findEqMngList(MaEqMngCommonDTO maEqMngCommonDTO,User user);
    
    public String findTotalCount(MaEqMngCommonDTO maEqMngCommonDTO,User user);


}