package dream.part.iss.wo.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.wo.dto.PartIssWoItemDetailDTO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;


/**
 * 자재출고확정 - 상세 dao
 * 
 * @author ssong
 * @version $Id: PartIssWoItemDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 */
public interface PartIssWoItemDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     *  
     * @param partIssWoItemDetailDTO
     * @return
     */
    public PartIssWoItemDetailDTO findDetail(PartIssWoItemListDTO partIssWoItemListDTO,User user);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @return
     */
    public int insertDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser);
    public int insertRemark(PartIssWoItemDetailDTO partIssWoItemDetailDTO, User loginUser);
   
    
    
    /**
     * detail update
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @return
     */
    public int updateDetail(PartIssWoItemDetailDTO partIssWoItemDetailDTO);

   
    /**
     * serial Check
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param partIssWoItemDetailDTO
     * @return
     */
    public PartIssWoItemDetailDTO serialCheck(PartIssWoItemDetailDTO partIssWoItemDetailDTO);
    
    
}