package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListSclCavalListDTO;

/**
 * 작업상세  - 검교정(저울) - 측정값  목록
 * @author  kim21017
 * @version $Id: WorkListSclCavalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListSclCavalListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkListSclCavalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param workListSclCavalListDTO
     * @param loginUser
     * @throws Exception
     */
    public WorkListSclCavalListDTO findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    /**
     *  merge
     * @author  kim21017
     * @version $Id: WorkListSclCavalListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int mergeCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, WorkListSclCavalListDTO workListSclCavalListDTO, User loginUser) throws Exception;

}
