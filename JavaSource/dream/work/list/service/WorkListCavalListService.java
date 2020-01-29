package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WorkListCavalListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업상세  - 검교정 - 측정값  목록
 * @author  kim21017
 * @version $Id: WorkListCavalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListCavalListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkListCavalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListCavalListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: WorkListCavalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteCavalList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListCavalListDTO workListCavalListDTO, User loginUser) throws Exception;

}
