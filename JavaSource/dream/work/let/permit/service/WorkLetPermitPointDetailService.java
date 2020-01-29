package dream.work.let.permit.service;

import common.bean.User;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * �����۾��㰡�� �۾����� - �����׸� �� Service
 * @author syyang
 * @version $Id: WorkLetPermitPointDetailService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 */
public interface WorkLetPermitPointDetailService
{    
	/**
	 * FIND DETAIL
	 * @param workLetPermitListDTO
	 * @param WorkLetPermitPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkLetPermitPointDetailDTO findDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO WorkLetPermitPointListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
	 * @param workLetPermitListDTO
	 * @param WorkLetPermitPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
	 * @param workLetPermitListDTO
	 * @param WorkLetPermitPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception;
}
