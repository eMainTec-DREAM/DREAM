package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * ���� �������� ��� DAO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsListDAO
{   
	/**
	 * ���� �������� ��� �˻�
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user) throws Exception;
    /**
     * ���� �������� ����
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deletePmPoint(String pmId, User user) throws Exception;
    public int deletePmEquip(String pmId, String pmEquipId, User user) throws Exception;
    public int deletePmList(String id, User user) throws Exception;
    
    public int checkOtherPmEquip(String pmId, User user) throws Exception;
	/**
	 * ���� �������� ��� ���� COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user) throws Exception;

}