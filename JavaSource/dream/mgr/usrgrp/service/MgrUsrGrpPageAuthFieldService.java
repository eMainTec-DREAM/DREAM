/**
 * 
 */
package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthFieldService {
	public List findList(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception;
	
	public MgrUsrGrpPageAuthFieldDTO findDetail(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception;
    
    public int updateDetail(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception;
	
    public String findTotalCount(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception;

	public int updAuthStatus(String[] pgfieldId, String[] ugpgfieldauId, String[] usrgrpId, String changeStatus, User user) throws Exception;
}
