package dream.consult.comp.list.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;

/**
 * 회사설정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaCompMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaCompMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaCompMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngCommonDTO
     * @return List
     */
    public List findCompList(MaCompMngCommonDTO maCompMngCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCompMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteComp(String id);
    
    public String findTotalCount(MaCompMngCommonDTO maCompMngCommonDTO, User user);
}