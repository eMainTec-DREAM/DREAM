package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * ���� ���汳ü ��� DAO
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkListDAO
{   
	/**
	 * ���� ���汳ü ��� �˻�
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmWorkList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user) throws Exception;
    /**
     * ���� ���汳ü ����
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deletePmPart(String pmId, User user) throws Exception;
    public int deletePmEquip(String pmId, String pmEquipId, User user) throws Exception;
    public int deletePmList(String id, User user) throws Exception;
    
    public int checkOtherPmEquip(String pmId, User user) throws Exception;
	/**
	 * ���� ���汳ü ��� ���� COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user) throws Exception;

}