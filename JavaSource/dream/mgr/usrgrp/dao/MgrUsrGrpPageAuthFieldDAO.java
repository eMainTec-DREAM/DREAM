package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthFieldDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception;
    
    public String findTotalCount(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user) throws Exception;
    
    public String getColums(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);
    
    public String getTables(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);
    
    public String getOrderBy(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);
    
    public String getWhere(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);

	public int updateDetail(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);

	public int insertAuStatus(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);

	public int updateAuStatus(MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO, User user);
	
}