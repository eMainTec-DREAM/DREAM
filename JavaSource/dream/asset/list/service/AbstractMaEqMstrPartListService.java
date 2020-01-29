package dream.asset.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public abstract class AbstractMaEqMstrPartListService implements MaEqMstrPartListService
{     
	protected MaEqMstrPartListService maEqMstrPartListService;
	
	public AbstractMaEqMstrPartListService(MaEqMstrPartListService maEqMstrPartListService)
	{
		this.maEqMstrPartListService = maEqMstrPartListService;
	}
	
	public abstract List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User loginUser);
    public abstract int deletePartList(String[] deleteRows, String compNo) throws Exception;
    public abstract String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User user) throws Exception;
    public abstract int inputPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartDetailDTO maEqMtrPartDetailDTO, User user) throws Exception;
    public abstract void saveList(List<Map> gridList, User user) throws Exception;
}
