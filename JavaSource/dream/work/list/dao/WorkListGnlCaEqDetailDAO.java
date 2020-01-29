package dream.work.list.dao;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;

/**
 * 작업상세  - 검교정 - 표준기 상세 dao
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface WorkListGnlCaEqDetailDAO
{

    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkListGnlCaEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCaEqDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);

	/**
	 * Find Detail
	 * @param maWoResultMstrCommonDTO
	 * @param user
	 * @return
	 */
	public WorkListGnlCaEqDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);

    public String checkDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO,User user);
}