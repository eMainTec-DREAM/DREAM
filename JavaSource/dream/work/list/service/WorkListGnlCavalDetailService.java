package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세
 * @author  kim210117
 * @version $Id: WorkListGnlCavalDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface WorkListGnlCavalDetailService
{    
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListGnlCavalDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
	
    /**
     * Find List
     * @param maWoResultMstrCommonDTO
     * @param user
     * @return
     */
    public WorkListGnlCavalDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
}
