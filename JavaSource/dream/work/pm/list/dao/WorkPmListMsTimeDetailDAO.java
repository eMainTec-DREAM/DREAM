package dream.work.pm.list.dao;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;

/**
 * 작업시간 상세 dao
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmListMsTimeDetailDAO
{
	/**
     * detail find
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
	 * @param workPmListMsTimeListDTO
	 * @param user
	 * @return
	 */
    public WorkPmListMsTimeDetailDTO findDetail(WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User user);
    
    /**
     * detail update
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, User user);
    
    /**
     * detail insert
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailDTO
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    
    public String validTime(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
}