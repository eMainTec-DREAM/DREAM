package dream.work.rpt.mabdpoint.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;

/**
 * �̻�������ġ - �۾���ȹ ��� service
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointWoPlanListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id:$
     * @param maBdPointCommonDTO
     * @param maBdPointWoPlanListDTO
     * @param user
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user);
    
    /**
     * ����
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     */
    public int deleteList(String[] deleteRows, String compNo);

    public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;
	public void linkWoPlan(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;
	
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;

}
