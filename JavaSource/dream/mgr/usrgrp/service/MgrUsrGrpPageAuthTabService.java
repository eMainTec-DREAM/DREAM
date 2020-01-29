/**
 * 
 */
package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthTabDTO;
/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthTabService {
	public List findList(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
	
	public MgrUsrGrpPageAuthTabDTO findDetail(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
    
    public int updateDetail(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
	
	public int inputAuth(String[] pgpageIds, String[] usrgrpIds, User user) throws Exception;
	
	public int deleteAuth(String[] pgpageIds, String[] usrgrpIds, User user) throws Exception;
	
    public String findTotalCount(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception;
}
