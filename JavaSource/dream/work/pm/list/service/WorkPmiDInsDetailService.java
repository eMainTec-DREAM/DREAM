package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;

/**
 * WorkPmiDIns Page - Detail Service
 * @author youngjoo38
 * @version $Id: WorkPmiDInsDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiDInsDetailService
{
    /**
     * FIND DETAIL
     * @param workPmiDInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiDInsDetailDTO findDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;
    /**
     * INSERT 
     * @param workPmiDInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception;
    /** 
     * UPDATE 
     * @param workPmiDInsDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception;
    
    /**
     * 점검항목 검사
     * @param workPmiDInsDetailDTO
     * @param user
     * @return
     */
    public String checkPoint(WorkPmiDInsDetailDTO workPmiDInsDetailDTO,User user) throws Exception;

    public int completeDetail(WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception;
    public int[] updateList(List list, User user) throws Exception;
}
