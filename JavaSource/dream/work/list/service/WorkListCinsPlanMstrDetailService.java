package dream.work.list.service;

import common.bean.User;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrDetailDTO;

/**
 * WorkListCinsPlanMstr Page - Detail Service
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkListCinsPlanMstrDetailService
{
    /**
     * FIND DETAIL
     * @param workListCinsPlanMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkListCinsPlanMstrDetailDTO findDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception;
    /**
     * INSERT 
     * @param workListCinsPlanMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception;
    /** 
     * UPDATE 
     * @param workListCinsPlanMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception;
    
    /**
     * 점검항목 검사
     * @param workListCinsPlanMstrDetailDTO
     * @param user
     * @return
     */
    public String checkPoint(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO,User user) throws Exception;

    public int completeDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception;
}
