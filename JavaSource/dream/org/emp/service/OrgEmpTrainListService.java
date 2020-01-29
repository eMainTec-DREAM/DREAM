package dream.org.emp.service;

import java.util.List;

import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;

/**
 * 구매신청item 목록
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface OrgEmpTrainListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @param orgEmpTrainListDTO
     * @throws Exception
     */
    public List findItemList(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user);   

}
