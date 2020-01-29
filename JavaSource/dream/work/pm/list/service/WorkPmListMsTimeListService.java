package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;

/**
 * 작업시간 목록
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmListMsTimeListService
{     
    /**
     *  grid find
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User loginUser);
    
    /**
     *  delete
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteMsTimeList(String[] deleteRows, User loginUser) throws Exception;

    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmListMsTimeListDTO workPmListMsTimeListDTO, User user) throws Exception;
    
    /**
     *  input 
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailDTO
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int inputMsTimeList(WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
}
