package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;

/**
 * 작업결과 작업필수검사항목 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultStPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultStPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultStPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultStPointListDTO
     * @param loginUser
     * @return List
     */
    public List findStPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultStPointListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteStPointList(String id, String compNo);
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultStPointListDTO maWoResultStPointListDTO, User loginUser) throws Exception;
}