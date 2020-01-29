package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.PartListImgLinkUrlDTO;

/**
 * ºÎÇ°Image Link DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PartListImgLinkUrlDAO extends BaseJdbcDaoSupportIntf
{
    public List find(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public int delete(String id, User user) throws Exception;
    
    public String findTotalCount(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public int insert(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
    
    public int update(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception;
}