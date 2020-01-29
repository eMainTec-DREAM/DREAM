/**
 * 
 */
package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthBtnDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthBtnService {
	public List findList(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
	
	public MgrUsrGrpPageAuthBtnDTO findDetail(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
    
    public int updateDetail(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
	
	public int inputAuth(String[] pgbtnIds, String[] usrgrpIds, User user) throws Exception;
	
	public int deleteAuth(String[] pgbtnIds, String[] usrgrpIds, User user) throws Exception;
	
    public String findTotalCount(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception;
}
