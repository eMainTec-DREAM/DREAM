package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthGridColDTO;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthGridColDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception;
    
    public String findTotalCount(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception;
    
    public String getColums(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);
    
    public String getTables(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);
    
    public String getOrderBy(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);
    
    public String getWhere(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);

	public int updateDetail(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);

	public int insertAuStatus(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);

	public int updateAuStatus(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user);

}