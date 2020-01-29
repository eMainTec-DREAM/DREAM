package dream.invt.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.invt.list.dto.InvtWoRsltDTO;

/**
 * @author  ghlee
 * @version $Id$
 * @since   1.0
 */
public interface InvtWoRsltDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(InvtWoRsltDTO invtWoRsltDTO, User user);
    
    public int deleteList(String deleteRow, User user);
    
    public String findTotalCount(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception;

    public int[] insertList(List<InvtWoRsltDTO> list, User user) throws Exception;
}