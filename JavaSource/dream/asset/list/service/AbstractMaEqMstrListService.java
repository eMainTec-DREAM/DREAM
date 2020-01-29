package dream.asset.list.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비마스터 - 목록 service
 * @author  youngjoo38  
 * @version $Id$
 * @since   1.0
 */
public abstract class AbstractMaEqMstrListService implements MaEqMstrListService 
{     
	protected MaEqMstrListService maEqMstrListService;
	
	public AbstractMaEqMstrListService(MaEqMstrListService maEqMstrListService)
	{
		this.maEqMstrListService = maEqMstrListService;
	}
	
    /**
     *  grid find
     * @author  hyosung
     * @version $Id: MaEqMstrListService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
     * @param maEqMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws IOException 
     * @throws Exception
     */
    public abstract List findEqMachMstrList(MaEqMstrCommonDTO maEqMstrCommonDTO,User user) throws IOException;
    
    public abstract String findMcTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	
}
