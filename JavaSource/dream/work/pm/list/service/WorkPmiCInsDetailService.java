package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsDetailDTO;

/**
 * WorkPmiCIns Page - Detail Service
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiCInsDetailService
{
    /**
     * FIND DETAIL
     * @param workPmiCInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiCInsDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
    /**
     * INSERT 
     * @param workPmiCInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception;
    /** 
     * UPDATE 
     * @param workPmiCInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception;
    
    /**
     * 점검항목 검사
     * @param workPmiCInsDetailDTO
     * @param user
     * @return
     */
    public String checkPoint(WorkPmiCInsDetailDTO workPmiCInsDetailDTO,User user) throws Exception;

    public int completeDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception;
}
