package dream.mgr.intf.service;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfLogDetailDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;
/**
 * Interface Log Page - Detail Service
 * @author ghlee
 * @version $Id$
 * @since 1.0
 */
public interface MgrIntfLogDetailService
{    
	/**
	 * FIND DETAIL
	 * @param mgrIntfLogListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrIntfLogDetailDTO findDetail(MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception;
}
