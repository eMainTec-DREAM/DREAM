/**
 * 
 */
package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthGridColDTO;
/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthGridColService {
	public List findList(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception;
	
	public MgrUsrGrpPageAuthGridColDTO findDetail(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception;
    
    public int updateDetail(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception;

    public String findTotalCount(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception;

	public int updAuthStatus(String[] pggridcolId, String[] ugpgridcolauId, String[] usrgrpId, String changeStatus, User user) throws Exception;
}
