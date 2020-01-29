package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;

/**
 * 작업시간 목록
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public interface WorkPmMsTimeUInsListService
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
    public List findMsTimeList(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User loginUser);
    
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

    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO, User user) throws Exception;
    
    /**
     *  input 
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailDTO
     * @param maPmMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int inputMsTimeList(WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
}
