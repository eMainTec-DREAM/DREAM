package dream.invt.list.service;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtWoRsltDTO;

/**
 * @author  ghlee
 * @version $Id$
 * @since   1.0
 */
public interface InvtWoRsltService
{     
    public List findList(InvtWoRsltDTO invtWoRsltDTO, User user);
    
    public int delete(String[] m_id, User user) throws Exception;

    public String findTotalCount(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception;
    
    public int linkList(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception;

    public int insert(InvtWoRsltDTO invtWoRsltDTO, User user) throws Exception;

    public int insert(List<InvtWoRsltDTO> list, User user) throws Exception;
}
