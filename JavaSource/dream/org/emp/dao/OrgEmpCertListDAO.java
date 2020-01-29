package dream.org.emp.dao;

import java.util.List;

import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;

/**
 * 구매신청 item 목록 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface OrgEmpCertListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @param orgEmpCertListDTO
     * @return List
     */
    public List findItemList(MaEmpCommonDTO maEmpCommonDTO, OrgEmpCertListDTO orgEmpCertListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow, String deleteRowsExt, User user);

    public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, OrgEmpCertListDTO orgEmpCertListDTO, User user);
}