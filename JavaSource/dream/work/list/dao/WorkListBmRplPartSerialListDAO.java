package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;

/**
 * 작업결과 부품Serial 목록 dao
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListBmRplPartSerialListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkListBmRplPartSerialListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListBmRplPartSerialListDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO,MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkListBmRplPartSerialListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception;
}