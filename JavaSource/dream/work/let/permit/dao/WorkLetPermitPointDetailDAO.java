package dream.work.let.permit.dao;

import common.bean.User;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * 안전작업허가서 작업유형 - 점검항목 상세 DAO
 * @author syyang
 * @version $Id: WorkLetPermitPointDetailDAO.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 *
 */
public interface WorkLetPermitPointDetailDAO
{
    /**
     * FIND DETAIL
     * @param workLetPermitPointListDTO
     * @param workLetPermitPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkLetPermitPointDetailDTO findDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workLetPermitPointListDTO
     * @param workLetPermitPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param workLetPermitPointListDTO
     * @param workLetPermitPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param workLetPermitPointListDTO
     * @param workLetPermitPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertStdPointDetail(String woLetListId, String woLetCtgType, User user) throws Exception;
    
}