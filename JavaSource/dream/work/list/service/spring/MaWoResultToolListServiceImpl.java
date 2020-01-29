package dream.work.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.MaWoResultToolListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.dto.MaWoResultToolListDTO;
import dream.work.list.service.MaWoResultToolDetailService;
import dream.work.list.service.MaWoResultToolListService;

/**
 * 작업결과 투입공기구 목록
 * @author kim21017
 * @version $Id: MaWoResultToolListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultToolListServiceTarget"
 * @spring.txbn id="maWoResultToolListService"
 * @spring.property name="maWoResultToolListDAO" ref="maWoResultToolListDAO"
 * @spring.property name="maWoResultToolDetailService" ref="maWoResultToolDetailService"
 */
public class MaWoResultToolListServiceImpl implements MaWoResultToolListService
{
    private MaWoResultToolListDAO maWoResultToolListDAO = null;
    private MaWoResultToolDetailService maWoResultToolDetailService = null;


	public MaWoResultToolDetailService getMaWoResultToolDetailService()
    {
        return maWoResultToolDetailService;
    }

    public void setMaWoResultToolDetailService(
            MaWoResultToolDetailService maWoResultToolDetailService)
    {
        this.maWoResultToolDetailService = maWoResultToolDetailService;
    }

    public List findToolList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser)
    {      
        return maWoResultToolListDAO.findToolList(maWoResultMstrCommonDTO, maWoResultToolListDTO, loginUser);
    }

	public MaWoResultToolListDAO getMaWoResultToolListDAO() {
		return maWoResultToolListDAO;
	}

	public void setMaWoResultToolListDAO(MaWoResultToolListDAO maWoResultToolListDAO) {
		this.maWoResultToolListDAO = maWoResultToolListDAO;
	}
	
	public int deleteToolList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String woToolId : deleteRows)
            {
                result = result + maWoResultToolListDAO.deleteToolList(woToolId, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			MaWoResultToolListDTO maWoResultToolListDTO, User user) throws Exception {
		return maWoResultToolListDAO.findTotalCount(maWoResultMstrCommonDTO, maWoResultToolListDTO, user);
	}
	
	@Override
    public int inputToolList(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maWoResultToolDetailDTO.getMultiPartKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maWoResultToolDetailDTO.setWoToolId(maWoResultToolListDAO.getNextSequence("SQAWOPART_ID"));
            maWoResultToolDetailDTO.setPartId(multiKey[i]);
            result = result + maWoResultToolDetailService.insertDetail(maWoResultToolDetailDTO, maWoResultMstrCommonDTO);
        }
        
        return result;
    }

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception
	{
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = null;
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = null;
		
		for(Map map : gridList)
        {
			maWoResultMstrCommonDTO = (MaWoResultMstrCommonDTO)CommonUtil.makeDTO(map, MaWoResultMstrCommonDTO.class);
			maWoResultToolDetailDTO = (MaWoResultToolDetailDTO)CommonUtil.makeDTO(map, MaWoResultToolDetailDTO.class);
			
        	switch(maWoResultToolDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : maWoResultToolDetailService.updateDetail(maWoResultToolDetailDTO,maWoResultMstrCommonDTO, user);
        			break;
        		case "deleted": 
        			break;
        	}
        	
        }
	}
}

