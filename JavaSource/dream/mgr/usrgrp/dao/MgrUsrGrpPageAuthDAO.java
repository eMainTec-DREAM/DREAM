package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;

/**
 * 화면권한설정
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MgrUsrGrpPageAuthDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception;
    
    public int[] insertDetail(final List<MgrUsrGrpPageAuthDTO> list, User user) throws Exception;
    
    public int deleteList(String pageId, String usrgrpId, User user) throws Exception;
    
    public String findTotalCount(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception;
    
    public String getColums(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user);
    
    public String getTables(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user);
    
    public String getOrderBy(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user);
    
    public String getWhere(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user);
}