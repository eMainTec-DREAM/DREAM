package dream.work.let.permit.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * 안전작업허가서 작업유형 - 점검항목 목록 dao
 * @author syyang
 * @version $Id: WorkLetPermitPointListDAO.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 *
 */
public interface WorkLetPermitPointListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param workLetPermitPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param workLetPermitPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception;
    
    /**
     * FIND WoLetCategPointDetail
     * @param woLetCtgPointId
     * @param user
     * @return
     * @throws Exception
     */
    public WorkLetPermitPointDetailDTO getWoLetCategPointDetail(String woLetCtgPointId, User user) throws Exception;
    
}