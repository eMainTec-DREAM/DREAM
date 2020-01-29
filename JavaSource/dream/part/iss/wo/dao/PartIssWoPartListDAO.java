package dream.part.iss.wo.dao;

import java.util.List;

import common.bean.User;
import dream.part.iss.wo.dto.PartIssWoPartListDTO;

/**
 * 자재출고WO - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface PartIssWoPartListDAO
{
    public List findWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;
    
    public int deleteWoPart(String woPartId, User user) throws Exception;

    public String findWoPartTotalCount(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;

    public int linkWoPartDetail(String woPartId, PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;
    
    public int addWoPartDetail(String wkOrId, PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;

}