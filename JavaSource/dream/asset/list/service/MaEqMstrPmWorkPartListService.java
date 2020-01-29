package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * ���� �����۾�-��ǰ ���
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkPartListService
{   
	/**
	 * �����۾� ��ǰ �˻�
	 * @param maEqMstrPmWorkDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmWorkPartList(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception;
    /**	
     * ���� �����۾� ��ǰ ����
     * @param deletePmIdRows
     * @param deletePmPartIdRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqPmWorkPartList(String[] deletePmIdRows, String[] deletePmPartIdRows, User user) throws Exception;
	/**
	 * ���� �����۾� ��ǰ ��� ���� COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception;

}
