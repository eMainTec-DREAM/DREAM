package dream.work.pmi.list.service;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;

/**
 * 점검작업 - 상세 service
 * 
 * @author kim21017
 * @version $Id: WorkPmiDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface WorkPmiDetailService 
{    
    public WorkPmiDetailDTO findDetail(WorkPmiCommonDTO workPmiCommonDTO, User user)throws Exception;
    
    public int[] updateList(List<WorkPmiDetailDTO> list, User user) throws Exception;
    
    public int updateDetail(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception;
    
    public int completeDetail(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception;
    
    public int completeCancelDetail(WorkPmiDetailDTO workPmiDetailDTO, User user) throws Exception;
    
    public String checkPoint(WorkPmiDetailDTO workPmiDetailDTO,User user) throws Exception;
    
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
}
