package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;

/**
 * 작업상세  - 검교정 - 표준기 상세
 * @author  kim210117
 * @version $Id: WorkListGnlCaEqDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface WorkListGnlCaEqDetailService
{    
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
	/**
	 * Find Detail
	 * @param maWoResultMstrCommonDTO
	 * @param compNo
	 * @return
	 */
	public WorkListGnlCaEqDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
	
    public String checkDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, User user)throws Exception;
}
