package dream.work.alarm.service;

import java.util.List;

import common.bean.User;
import dream.work.alarm.dto.WorkAlarmDTO;

/**
 * Alarm List - 목록 service 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public interface WorkAlarmService
{     
    /**
     *  grid find
     * @author  nhkim8548
     * @version $Id:$
     * @param workAlarmDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List<WorkAlarmDTO> findList(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public int[] deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(WorkAlarmDTO workAlarmDTO, User user);
    
    public WorkAlarmDTO findDetail(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public int updateDetail(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public WorkAlarmDTO insertWoReqWithAlarm(WorkAlarmDTO workAlarmDTO, User user);
    
    public int insertDetail(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public int[] insertDetail(List<WorkAlarmDTO> list, User user) throws Exception;
    
    public String insertWoReq(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public int insertAlarmReq(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public String findEquipId(WorkAlarmDTO workAlarmDTO, User user) throws Exception;
    
    public WorkAlarmDTO chkValidDTO(WorkAlarmDTO workAlarmDTO);
}
