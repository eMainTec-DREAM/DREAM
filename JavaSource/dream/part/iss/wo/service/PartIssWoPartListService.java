package dream.part.iss.wo.service;

import java.util.List;

import common.bean.User;
import dream.part.iss.wo.dto.PartIssWoPartListDTO;

/**
 * 자재출고WO - 목록 service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface PartIssWoPartListService
{     
    public List findWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;

    public int deleteWoPartList(String[] deleteRows, User user) throws Exception;

    public String findWoPartTotalCount(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;

    public int linkWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;
    
    public int addWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception;

}
