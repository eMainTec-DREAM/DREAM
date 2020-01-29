package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * 작업시간 상세 dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmMsTimeUInsDetailDAO
{
	/**
     * detail find
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
	 * @param workPmMsTimeUInsListDTO
	 * @param user
	 * @return
	 */
    public WorkPmMsTimeUInsDetailDTO findDetail(WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User user);
    
    /**
     * detail update
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, User user);
    
    /**
     * detail insert
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    public String validTime(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
}