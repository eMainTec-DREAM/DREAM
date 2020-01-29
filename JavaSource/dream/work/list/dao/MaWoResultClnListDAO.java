package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 설비 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultClnListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultClnListDTO
     * @param loginUser
     * @return List
     */
    public List findClnList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultClnListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteClnList(String id, String compNo);
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultClnListDTO maWoResultClnListDTO, User loginUser) throws Exception;
}