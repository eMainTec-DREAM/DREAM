package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * ���������� �����׸� �� list service
 * @author  euna0207
 * @version $Id: MaEqCatalogPointListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since   1.0
 */
public interface MaEqCatalogPointListService
{   
	/**
	 * ���� �����׸� ��� �˻�
	 * @param maEqCatalogCommonDTO
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user) throws Exception;
    /**
     * ���� �����׸� ����
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * ���� �����׸� ��� ���� COUNT
	 * @param maEqCatalogCommonDTO
	 * @param maEqCatalogPointListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO, User user) throws Exception;

}
