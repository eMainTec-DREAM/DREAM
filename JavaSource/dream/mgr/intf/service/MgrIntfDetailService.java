package dream.mgr.intf.service;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfDetailDTO;
/**
 * Interface Page - Detail Service
 * @author ghlee
 * @version $Id$
 * @since 1.0
 */
public interface MgrIntfDetailService
{    
	/**
	 * FIND DETAIL
	 * @param mgrIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrIntfDetailDTO findDetail(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;
	/**
	 * INSERT
	 * @param mgrIntfDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user) throws Exception;
    /**
     * UPDATE
     * @param mgrIntfDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user) throws Exception;
}
