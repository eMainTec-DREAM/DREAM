package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;

/**
 * 작업시간 상세
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmListMsTimeDetailService
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
    public WorkPmListMsTimeDetailDTO findDetail(WorkPmListMsTimeListDTO workPmListMsTimeListDTO,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    /**
     * check time
     * @author kim21017
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailDTO
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     */
    public String validTime(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user);

}
