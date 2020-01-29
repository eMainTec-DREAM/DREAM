package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * 작업시간 상세
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmMsTimeUInsDetailService
{    
    /**
     * detail find
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkPmMsTimeUInsDetailDTO findDetail(WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    /**
     * check time
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     */
    public String validTime(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);

}
