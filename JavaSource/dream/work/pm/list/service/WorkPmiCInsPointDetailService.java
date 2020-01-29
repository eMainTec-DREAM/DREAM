package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;

/**
 * WorkPmiCInsPoint Page - Detail Service
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiCInsPointDetailService
{
    /**
     * FIND DETAIL
     * @param workPmiCInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiCInsPointDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
    /**
     * INSERT
     * @param workPmiCInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception;
    /**
     * UPDATE 
     * @param workPmiCInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception;
    
    
    // eqAsmbId 가져오기
    /**
     * GETID 
     * @param workPmiCInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String getId(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user) throws Exception;
    
}
