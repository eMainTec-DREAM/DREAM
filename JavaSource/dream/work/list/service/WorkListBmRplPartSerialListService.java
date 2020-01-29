package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;

/**
 * 작업결과 부품Serial 목록
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListBmRplPartSerialListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkListBmRplPartSerialListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListBmRplPartSerialListDTO
     * @param maWoResultPartDetailDTO 
     * @param loginUser
     * @throws Exception
     */
    public List findList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: WorkListBmRplPartSerialListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO, MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user) throws Exception;
}
