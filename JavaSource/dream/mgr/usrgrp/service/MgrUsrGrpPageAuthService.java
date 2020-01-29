/**
 * 
 */
package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
/**
 * 화면권한설정
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthService {
	public List findList(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception;
	
	public MgrUsrGrpPageAuthDTO findDetail(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception;
    
    public int updateDetail(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception;
	
	public int[] inputAuth(String[] pageIds, String[] usrgrpIds, User user) throws Exception;
	
	public int deleteAuth(String[] pageIds, String[] usrgrpIds, User user) throws Exception;
	
    public String findTotalCount(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception;
}
